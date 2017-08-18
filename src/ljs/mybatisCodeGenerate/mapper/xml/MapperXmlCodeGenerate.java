package ljs.mybatisCodeGenerate.mapper.xml;

import ljs.mybatisCodeGenerate.BaseCodeGenerate;
import ljs.mybatisCodeGenerate.BaseTag;

import java.io.File;

/**
 * mybatis xml 配置文件生成器
 */
public class MapperXmlCodeGenerate extends BaseCodeGenerate
{
    class XmlTag extends BaseTag
    {
        //运行空间命名
        public String tag_mapperClassName = "\\*\\{mapperClassName\\}";
        //resultMap ID命名
        public String tag_resultMapName = "\\*\\{resultMapName\\}";
        //实体类接口命名
        public String tag_pojoClassName = "\\*\\{pojoClassName\\}";
        //结果集命名
        public String tag_resultList = "\\*\\{resultList\\}";
        //表名
        public String tag_tableName = "\\*\\{tableName\\}";
        //主键表字段名称
        public String primaryColumnName = "\\*\\{primaryColumnName\\}";
        //主键字段名称
        public String tag_primaryFieldName = "\\*\\{primaryFieldName\\}";
        //模板查询语句
        public String tag_queryByExample = "\\*\\{queryByExample\\}";
        //模板查询语句
        public String tag_updateSet = "\\*\\{updateSet\\}";
    }

    public MapperXmlCodeGenerate(Class targetClass) throws Exception
    {
        this(targetClass, "mapper", new File("src", "mapper"));
    }

    public MapperXmlCodeGenerate(Class targetClass, String packageName, File packageDir) throws Exception
    {
        super(targetClass, packageName, packageDir);
        init();
    }

    public String getResultMapCode()
    {
        if (myFields.isEmpty())
            return "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\t\t<result property=\"" + primaryKeyField.field.getName() + "\" column=\"" + primaryKeyField.tableFieldName + "\"/>");
        stringBuffer.append("\n");
        for (MyField myField : myFields)
        {
            stringBuffer.append("\t\t<result property=\"" + myField.field.getName() + "\" column=\"" + myField.tableFieldName + "\"/>");
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public String getQueryByExampleCode()
    {
        if (myFields.isEmpty())
            return "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\t<where>\n");
        stringBuffer.append("\t\t<if test=\"pojo." + primaryKeyField.field.getName() + "!=null\">AND " + primaryKeyField.tableFieldName + "=#\\{pojo." + primaryKeyField.field.getName() + "\\}</if>");
        stringBuffer.append("\n");
        for (MyField myField : myFields)
        {
            Class fieldType = myField.field.getType();
            String fieldName = myField.field.getName();
            if (fieldType == String.class)
                stringBuffer.append("\t\t<if test=\"pojo." + fieldName + "!=null and pojo." + fieldName + "!=''\">AND " + myField.tableFieldName + " LIKE '%\\$\\{pojo." + fieldName + "\\}%'</if>");
            else
                stringBuffer.append("\t\t<if test=\"pojo." + fieldName + "!=null\">AND " + myField.tableFieldName + "=#\\{pojo." + fieldName + "\\}</if>");
            stringBuffer.append("\n");
        }
        stringBuffer.append("\t</where>\n");
        return stringBuffer.toString();
    }

    public String getUpdateSetCode()
    {
        if (myFields.isEmpty())
            return "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\t<set>\n");
        for (MyField myField : myFields)
        {
            String fieldName = myField.field.getName();
            stringBuffer.append("\t\t<if test=\"pojo." + fieldName + "!=null\">" + myField.tableFieldName + "=#\\{pojo." + fieldName + "\\},</if>");
            stringBuffer.append("\n");
        }
        stringBuffer.append("\t</set>\n");
        return stringBuffer.toString();
    }

    public void init() throws Exception
    {
        val = new XmlTag();
        super.init();
        XmlTag val = (XmlTag) this.val;
        val.tag_mapperClassName = "mapper." + pojoType.getSimpleName() + "Mapper";
        val.tag_resultMapName = pojoType.getSimpleName() + "ResultMap";
        val.tag_pojoClassName = "pojo." + pojoType.getSimpleName();
        val.tag_resultList = getResultMapCode();
        val.tag_tableName = tableName;
        val.primaryColumnName = primaryKeyField.tableFieldName;
        val.tag_primaryFieldName = primaryKeyField.field.getName();
        val.tag_queryByExample = getQueryByExampleCode();
        val.tag_updateSet = getUpdateSetCode();

        putTagAndVal(new XmlTag(), val);
    }

    @Override
    public void generate() throws Exception
    {
        File outFile = new File(packageDir, pojoType.getSimpleName() + "Mapper.xml");
        generate(replaces, ClassLoader.getSystemClassLoader().getResourceAsStream("ljs/mybatisCodeGenerate/mapper/example/Xml.txt"), outFile);
    }
}
