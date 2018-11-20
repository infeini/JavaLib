package ljs.autocode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class Table {
    public StringWrap name;
    public Field keyField;
    public ArrayList<Field> fields;
    public String info;

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public StringWrap getName() {
        return name;
    }

    public void setName(StringWrap name) {
        this.name = name;
    }

    public Field getKeyField() {
        return keyField;
    }

    public void setKeyField(Field keyField) {
        this.keyField = keyField;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    private Table(StringWrap name, String info, Field keyField, ArrayList<Field> fields) {
        this.name = name;
        this.info = info;
        this.keyField = keyField;
        this.fields = fields;
    }

    public static Table load(Connection jdbc, String tableName, String tableInfo) throws Exception {
        Statement statement;
        statement = jdbc.createStatement();
        String sql = "SHOW FULL COLUMNS FROM `" + tableName + "`";
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();

        StringWrap tName = new StringWrap(tableName);
        Field tKeyField = null;
        ArrayList<Field> tFields = new ArrayList<>();

        while (resultSet.next()) {
            boolean isKey = resultSet.getString("Key").equals("PRI");
            boolean isForeign = resultSet.getString("Key").equals("MUL");
            StringWrap name = new StringWrap(resultSet.getString("Field"));
            Field field = new Field(
                    name,
                    new MyType(resultSet.getString("Type"), isKey || isForeign, name),
                    isKey,
                    resultSet.getString("Comment"),
                    resultSet.getString("Null").equals("YES")
            );
            if (field.key) {
                if (tKeyField != null)
                    throw new Exception("表:" + tableName + "没有主键!");
                else
                    tKeyField = field;
            } else
                tFields.add(field);
        }
        return new Table(tName, tableInfo, tKeyField, tFields);
    }

    public boolean haveDate() {
        boolean result = false;
        if (keyField.type.javaType() == Date.class)
            result = true;
        else
            for (Field field : fields) {
                if (field.type.javaType() == Date.class) {
                    result = true;
                    break;
                }
            }
        return result;
    }
}
