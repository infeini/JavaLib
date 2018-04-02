package auto;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import ljs.io.IOUtil;
import ljs.io.file.FileUtils;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;

public class Auto {
    @Test
    public void test() throws IOException {
        File dir = new File("/home/ljs/IdeaProjects/PLM/src/main/java/zlx/pojo");
        for (File file : FileUtils.list(dir)) {
            System.out.println("<value>zlx.pojo." + file.getName() + "</value>");
        }
    }

    @Test
    public void autoCodePLM() throws Exception {
        run("plm");
    }

    @Test
    public void autoCodeBaoCheHui() throws Exception {
        run("baochehui");
    }

    @Test
    public void autoCodeTraining() throws Exception {
        run("training");
    }

    @Test
    public void autoSpringSecurity() throws Exception {
        run("spring_security");
    }

    public void run(String dbName) throws Exception {
        DataBase dataBase = new DataBase(dbName, "localhost", 3306, "root", "123456");
        File outDir = new File("./test").getCanonicalFile();
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

    public void write(Object model, File view, File out) throws Exception {
        if (out.exists())
            throw new RuntimeException(out.getCanonicalPath() + "已存在,为保护你的文件,中断操作");
        if (!out.getParentFile().exists())
            out.getParentFile().mkdirs();
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
        cfg.setDirectoryForTemplateLoading(view.getParentFile());
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        Template temp = cfg.getTemplate(view.getName());
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
