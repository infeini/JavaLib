package auto.freemarker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DataBase
{
    public String name;
    public String ip;
    public int port;
    public ArrayList<Table> tables;

    private Connection getConnect(String userName, String password) throws Exception
    {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + name + "", userName, password);
    }

    public DataBase(String name, String ip, int port, String userName, String password) throws Exception
    {
        this.name = name;
        this.ip = ip;
        this.port = port;
        Connection jdbc = getConnect(userName, password);
        this.tables = new ArrayList<>();
        Statement statement = jdbc.createStatement();
        String sql = "show tables";
        if (statement.execute(sql))
        {
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next())
            {
                String tableName = resultSet.getString(1);
                if (MyString.checkFormat(tableName))
                    tables.add(Table.load(jdbc, tableName));
                else
                    throw new Exception("数据表:" + tableName + ",命名格式不规范");
            }
        }
    }
}
