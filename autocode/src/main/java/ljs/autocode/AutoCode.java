package ljs.autocode;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import ljs.io.IOUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class AutoCode {
    public void run(String dbName) throws Exception {
        run(dbName, "localhost", "root", "123456", "zlx");
    }

    public void run(String dbName, String host, String user, String passWord, String domain) throws Exception {
        run(dbName, host, user, passWord, domain + ".base.BasePojo", domain + ".pojo", domain + ".mapper", domain + ".service", domain + ".controller");
    }

    public void run(String dbName, String host, String user, String password, String supperPojoClassName, String pojoPackage, String mapperPackage, String servicePackage, String controllerPackage) throws Exception {
        DataBase dataBase = new DataBase(dbName, host, 3306, user, password);
        File outDir = new File("./code").getCanonicalFile();
        for (Table table : dataBase.tables) {
            Config config = new Config();
            config.init(outDir, table, supperPojoClassName, pojoPackage, mapperPackage, servicePackage, controllerPackage);

            HashMap<Object, Object> model = new HashMap<>();
            // 配置信息
            model.put("config", config);
            // 表信息
            model.put("table", table);
            // 表主键信息
            model.put("keyField", table.getKeyField());
            // 表所有字段信息
            model.put("fields", table.getFields());
            // 表注解信息
            model.put("tableInfo", table.getInfo());

            // 快捷字段
            model.put("tableName", config.tableName);//表名称

            //生成pojo
            write(model, config.pojoModel, config.getPojoSaveAs());
            //生成simplePojo
            write(model, config.simplePojoModel, config.getSimplePojoSaveAs());
            //生成pojo介绍
            write(model, config.pojoInfoModel, config.getPojoInfoSaveAs());
            //生成mybatis mapper接口
            write(model, config.mapperJavaModel, config.getMapperJavaSaveAs());
            //生成mybatis xml配置文件
            write(model, config.mapperXmlModel, config.getMapperXmlSaveAs());
            //生成service java文件
            write(model, config.serviceModel, config.getServiceSaveAs());
            //生成controller java文件
            write(model, config.controllerModel, config.getControllerSaveAs());
        }
    }

    public void write(Object model, String view, File out) throws Exception {
        if (out.exists())
            throw new RuntimeException(out.getCanonicalPath() + "已存在,为保护你的文件,中断操作");
        if (!out.getParentFile().exists())
            out.getParentFile().mkdirs();
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setClassForTemplateLoading(getClass(), "/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        Template temp = cfg.getTemplate(view);
        if (!out.getParentFile().exists())
            out.getParentFile().mkdirs();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out, false)));
        try {
            temp.process(model, writer);
        } finally {
            IOUtil.close(writer);
        }
        System.out.println("输出:" + out.getCanonicalPath());
    }
}
