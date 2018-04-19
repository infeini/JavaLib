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
        run(dbName, "localhost");
    }

    public void run(String dbName, String host) throws Exception {
        DataBase dataBase = new DataBase(dbName, host, 3306, "root", "123456");
        File outDir = new File("./code").getCanonicalFile();
        for (Table table : dataBase.tables) {
            Config config = new Config();
            config.init(outDir, table, "zlx.base.BasePojo", "zlx.pojo", "zlx.mapper", "zlx.service", "zlx.controller");

            HashMap<Object, Object> model = new HashMap<>();
            model.put("config", config);
            model.put("table", table);

            model.put("keyField", table.getKeyField());
            model.put("fields", table.getFields());
            model.put("tableName", table.getName().table());
            model.put("tableInfo", table.getInfo());
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
