package auto

import java.lang.Exception
import java.sql.Connection
import java.sql.Statement

object LoadTool
{
    //加载数据库结构
    fun loadDataBaseInfo(dbName: String, jdbcConnect: Connection): DBInfo
    {
        var dbInfo: DBInfo = DBInfo(dbName)
        var statement = jdbcConnect.createStatement()
        try
        {
            var sql: String = "show tables"
            if (statement.execute(sql))
            {
                var resultSet = statement.resultSet
                while (resultSet.next())
                {
                    var tableName = resultSet.getString(1)
                    if (InfoTool.checkFormat(tableName))
                        dbInfo.tableInfos.add(loadTableInfo(tableName, jdbcConnect))
                    else
                        throw Exception("数据表:${tableName},命名格式不规范")
                }
            }
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
        finally
        {
            statement.close()
        }
        return dbInfo
    }

    //加载数据库表结构
    fun loadTableInfo(tableName: String, jdbcConnect: Connection): TableInfo
    {
        var tableInfo: TableInfo = TableInfo(tableName)
        var statement: Statement = jdbcConnect.createStatement()
        try
        {
            val sql = "SHOW FULL COLUMNS FROM $tableName"
            if (statement.execute(sql))
            {
                val resultSet = statement.resultSet
                while (resultSet.next())
                {
                    var field = resultSet.getString("Field")
                    var type = JdbcJavaTypeMapper.mapperJavaType(resultSet.getString("Type"))
                    var key = resultSet.getString("Key") == "PRI"
                    var comment = resultSet.getString("Comment")
                    var fieldInfo = FieldInfo(field, type, key, comment)

                    if (InfoTool.checkFormat(field))
                        if (key)
                        {
                            if (tableInfo.key != null)
                                throw Exception("数据表:${tableName},存在多个主键")
                            tableInfo.key = fieldInfo
                        }
                        else
                            tableInfo.fieldInfos.add(fieldInfo)
                    else
                        throw Exception("数据表:${tableName}中的$field,命名不规范")
                }
            }
        }
        catch (e: Exception)
        {
            e.printStackTrace()
        }
        finally
        {
            statement.close()
        }
        return tableInfo
    }
}
