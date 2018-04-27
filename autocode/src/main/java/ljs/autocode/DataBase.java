package ljs.autocode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase {
    public String name;
    public String ip;
    public int port;
    public ArrayList<Table> tables;

    private Connection getConnect(String userName, String password) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + name + "?serverTimezone=GMT%2B8&useSSL=false", userName, password);
    }

    public DataBase(String name, String ip, int port, String userName, String password) throws Exception {
        this.name = name;
        this.ip = ip;
        this.port = port;
        Connection jdbc = getConnect(userName, password);
        this.tables = new ArrayList<>();
        Statement statement = jdbc.createStatement();
        String sql = "show table status";
        if (statement.execute(sql)) {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String tableName = resultSet.getString(resultSet.findColumn("Name"));
                String tableInfo = resultSet.getString(resultSet.findColumn("Comment"));
                if (MyString.checkFormat(tableName))
                    tables.add(Table.load(jdbc, tableName, tableInfo));
                else
                    throw new Exception("数据表:" + tableName + ",命名格式不规范");
            }
        }
    }
}
