package ljs.code.mybatis.mapper.java;

import ljs.code.mybatis.BasePojoCodeGenerate;
import ljs.code.mybatis.BaseTag;

import java.io.File;

/**
 * mybatis java 代码生成器
 */
public class MapperJavaCodeGenerate extends BasePojoCodeGenerate
{
    /**
     * 占位符名称
     */
    public class JavaTag extends BaseTag
    {
        //mapper包名
        public String mapperPackageName = "\\*\\{mapperPackageName\\}";
        //mapper类名
        public String mapperClassName = "\\*\\{mapperClassName\\}";
        //简单mapper类名
        public String simpleMapperClassName = "\\*\\{simpleMapperClassName\\}";
        //pojo类名
        public String pojoClassName = "\\*\\{pojoClassName\\}";
        //简单pojo类名
        public String simplePojoClassName = "\\*\\{simplePojoClassName\\}";
        //主键表字段名称
        public String primaryKeyName = "\\*\\{primaryKeyName\\}";
        //主键字段名称
        public String primaryFieldName = "\\*\\{primaryFieldName\\}";
    }

    /**
     * 生成配置
     */
    public static class Config
    {
        //生成mapper包名
        public static String mapperPackageName = "mapper";
        //生成mapper项目相对路径
        public static String mapperPackageLocation = "src/mapper/";
    }

    public MapperJavaCodeGenerate(Class targetClass) throws Exception
    {
        this(targetClass, "mapper", new File("src", "mapper"));
    }

    public MapperJavaCodeGenerate(Class targetClass, String packageName, File packageDir) throws Exception
    {
        super(targetClass, packageName, packageDir);
        init();
    }


    public void init() throws Exception
    {
        this.val = new JavaTag();
        super.init();
        JavaTag val = (JavaTag) this.val;
        val.mapperPackageName = Config.mapperPackageName;
        val.mapperClassName = "mapper." + pojoType.getSimpleName() + "Mapper";
        val.simpleMapperClassName = pojoType.getSimpleName() + "Mapper";
        val.pojoClassName = pojoType.getName();
        val.simplePojoClassName = pojoType.getSimpleName();
        val.primaryKeyName = primaryKeyField.tableFieldName;
        val.primaryFieldName = primaryKeyField.field.getName();

        putTagAndVal(new JavaTag(), val);
    }

    @Override
    public void generate() throws Exception
    {
        File outFile = new File(Config.mapperPackageLocation + pojoType.getSimpleName() + "Mapper.java");
        generate(replaces, getClass().getResourceAsStream("/ljs/code/mybatis/mapper/example/Java.txt"), outFile);
    }
}
