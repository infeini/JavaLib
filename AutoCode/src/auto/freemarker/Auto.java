package auto.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import ljs.io.IOUtil;
import ljs.lib.ArrayUtils;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class Auto
{
    @Test
    public void autoCode() throws Exception
    {
        DataBase dataBase = new DataBase("plm", "localhost", 3306, "root", "123456");
        String[] appendAbleTables = {"product", "product_line", "needs"};
        File outDir = new File("./codeOut").getCanonicalFile();
        for (Table table : dataBase.tables)
        {
            Config config = new Config();
            config.init(outDir, table, "base.BasePojo", "pojo", "mapper", "service", "controller");
            if (ArrayUtils.getIndex(appendAbleTables, table.name.table()) != -1)
            {
                config.init(
                        outDir,
                        table,
                        "base.BaseAppendAblePojo",
                        config.getPojoPackage() + ".appendable",
                        config.getMapperPackage() + ".appendable",
                        config.getServicePackage() + ".appendable",
                        config.getControllerPackage() + ".appendable");

            }
            if (table.getName().table().equals("append_data"))
                config.supperPojoClassName = "base.BaseAppendData";

            HashMap<Object, Object> model = new HashMap<>();
            model.put("config", config);
            model.put("table", table);
            //生成pojo
            write(model, config.pojoModel, config.getPojoSaveAs());
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

    public void write(Object model, File view, File out) throws Exception
    {
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
        try
        {
            temp.process(model, writer);
        } finally
        {
            IOUtil.close(writer);
        }
        System.out.println("输出:" + out.getCanonicalPath());
    }
}
