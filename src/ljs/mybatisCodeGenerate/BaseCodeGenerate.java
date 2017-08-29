package ljs.mybatisCodeGenerate;

import ljs.lib.StringUtils;
import ljs.io.IOUtil;
import ljs.mybatisCodeGenerate.annotation.FieldInfo;
import ljs.mybatisCodeGenerate.annotation.TableInfo;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * mybatis java 代码生成器
 */
public abstract class BaseCodeGenerate
{
    public BaseTag val;

    public void init() throws Exception
    {
        val.generateDate = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date());
    }

    public class MyField
    {
        public Field field;
        public String tableFieldName;
    }

    public Class pojoType;
    public String tableName;
    public List<MyField> myFields;
    public MyField primaryKeyField;

    //生成mapper包名
    public String packageName;
    //生成mapper项目相对路径
    public File packageDir;

    public BaseCodeGenerate(Class pojoType, String packageName, File packageDir) throws Exception
    {
        this.packageName = packageName;
        this.packageDir = packageDir;

        this.pojoType = pojoType;
        TableInfo tableInfo = (TableInfo) pojoType.getAnnotation(TableInfo.class);
        if (tableInfo == null)
            throw new Exception(pojoType.getName() + "不包含注解@TableInfo,无法获取该实体对应的表信息");
        tableInfo = (TableInfo) pojoType.getAnnotation(TableInfo.class);
        this.tableName = tableInfo.tableName();
        if (StringUtils.isEmpty(tableName))
            throw new Exception("表名不能为空");
        this.myFields = new ArrayList<>();
        for (Field field : pojoType.getDeclaredFields())
        {
            FieldInfo fieldInfo = field.getAnnotation(FieldInfo.class);
            if (fieldInfo == null)
                continue;
            MyField myField = new MyField();
            myField.field = field;
            myField.tableFieldName = fieldInfo.tableFieldName();
            if (fieldInfo.isPrimaryKey())
            {
                if (primaryKeyField != null)
                    throw new Exception("不能有两个主键字段:" + primaryKeyField.field.getName() + "和" + field.getName());
                primaryKeyField = myField;
            } else
                myFields.add(myField);
        }
    }

    public HashMap<String, String> replaces = new HashMap<>();

    public void putTagAndVal(BaseTag tag, BaseTag val) throws Exception
    {
        Class type = tag.getClass();
        for (Field field : type.getFields())
        {
            field.setAccessible(true);
            String fieldName = field.getName();
            String key = field.get(tag).toString();
            String value = field.get(val).toString();
            replaces.put(key, value);
        }
    }

    /**
     * 开始生成代码
     */
    public abstract void generate() throws Exception;

    /**
     * 生成代码
     */
    public void generate(HashMap<String, String> replaces, InputStream exampleIn, File outFile) throws Exception
    {
        StringBuffer codeBuffer = IOUtil.toString(exampleIn, "UTF-8", true);
        for (String tag : replaces.keySet())
        {
            String val = replaces.get(tag);
            if (StringUtils.isEmpty(val))
                continue;
            else
                StringUtils.replaceAll(tag, val, codeBuffer);
        }
        IOUtil.write(codeBuffer, outFile, "UTF-8");
    }
}
