package ljs.poi;

import com.alibaba.fastjson.JSON;
import ljs.exception.KnowException;
import ljs.reflect.FieldUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ExcelUtils {
    /**
     * 校验数据接口
     */
    public interface CheckPojo<T> {
        void check(T pojo, Row row) throws KnowException;
    }

    public static void checkHead(Map<Integer, String> heads, Class type) throws KnowException {
        List<String> fieldNames = FieldUtils.getFieldNames(type);
        for (int i = 0; i < heads.size(); i++) {
            String headName = heads.get(i);

            if (fieldNames.indexOf(headName) == -1)
                throw new KnowException("第" + (i + 1) + "个表头\"" + headName + "\"为未知字段,正确的字段为" + JSON.toJSONString(fieldNames) + "其中一个或多个");
        }
    }

    public static <T> List<T> getData(InputStream in, Class<T> type, CheckPojo<T> check) throws KnowException, IOException {
        Map<Integer, String> heads = new HashMap<>();
        List<T> datas = new ArrayList<>();

        XSSFWorkbook excel = new XSSFWorkbook(in);
        XSSFSheet sheet = excel.getSheetAt(0);
        if (sheet.getLastRowNum() > 10000)
            throw new KnowException("服务器性能有限,数据行数最大不允许超过10000行!");
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                Object cellData = getCellData(cell);
                if (cellData == null)
                    throw new KnowException(getPosition(cell) + "数据为空,无法获取表头信息!");
                String headTitle = cellData.toString();
                //跳过注释
                if (columnIndex == 0 && headTitle.startsWith("#")) {
                    heads.clear();
                    break;
                } else
                    heads.put(columnIndex, headTitle);
            }
            //已获取到表头信息
            if (!heads.isEmpty())
                break;
        }
        if (heads.isEmpty())
            throw new KnowException("获取表头失败");
        checkHead(heads, type);

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            HashMap<String, Object> map = new HashMap<>();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int cellIndex = cell.getColumnIndex();
                map.put(heads.get(cellIndex), getCellData(cell));
            }
            T pojo = JSON.parseObject(JSON.toJSONString(map), type);
            if (check != null)
                check.check(pojo, row);
            datas.add(pojo);
        }
        return datas;
    }

    public static String getPosition(Row row) {
        return "第" + row.getRowNum() + "行:";
    }

    public static String getPosition(Cell cell) {
        return "第" + cell.getRowIndex() + "行,第" + cell.getColumnIndex() + "列:";
    }

    public static Object getCellData(Cell cell) {
        Object data;
        switch (cell.getCellTypeEnum()) {
            case _NONE:
                data = null;
                break;
            case BLANK:
                data = "";
                break;
            case ERROR:
                data = "";
                break;
            case STRING:
                data = cell.getStringCellValue();
                break;
            case BOOLEAN:
                data = cell.getBooleanCellValue();
                break;
            case FORMULA:
                data = cell.getCellFormula();
                break;
            case NUMERIC:
                data = cell.getNumericCellValue();
                break;
            default:
                data = null;
                break;
        }
        return data;
    }
}