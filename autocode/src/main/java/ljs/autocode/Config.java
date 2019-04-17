package ljs.autocode;

import ljs.io.file.FileUtils;

import java.io.File;

public class Config {
    //输出文件夹
    public File outDir;

    public Table table;
    //实体包名
    public String pojoPackage;
    public String supperPojoClassName;
    //实体名称
    public String pojoName;
    //表名称
    public String tableName;
    //实体存储路径
    public File pojoSaveAs;
    //简单实体存储路径
    public File simplePojoSaveAs;
    private String templatePackage = "template/";
    //简单实体模板文件
    public String simplePojoModel = templatePackage + "simplePojo.java.vm";
    //实体模板文件
    public String pojoModel = templatePackage + "pojo.java.vm";

    public File pojoInfoSaveAs;
    //实体介绍模板文件
    public String pojoInfoModel = templatePackage + "pojoInfo.md.vm";

    //mapper包名
    public String mapperPackage;
    //mapper名称
    public String mapperName;
    //mapper java接口存储路径
    public File mapperJavaSaveAs;
    //mapper 接口模板文件
    public String mapperJavaModel = templatePackage + "mybatis.java.vm";
    //mapper xml存储路径
    public File mapperXmlSaveAs;
    //mapper xml模板文件
    public String mapperXmlModel = templatePackage + "mybatis.xml.vm";

    //service包名
    public String servicePackage;
    //service名称
    public String serviceName;
    //service存储路径
    public File serviceSaveAs;
    //service模板文件
    public String serviceModel = templatePackage + "service.java.vm";

    //controller包名
    public String controllerPackage;
    //controller名称
    public String controllerName;
    //controller存储路径
    public File controllerSaveAs;
    //controller模板文件
    public String controllerModel = templatePackage + "controller.java.vm";

    public void init(File outDir, Table table, String supperPojoClassName, String pojoPackage, String mapperPackage, String servicePackage, String controllerPackage) {
        this.outDir = outDir;
        this.table = table;
        this.supperPojoClassName = supperPojoClassName;
        this.pojoPackage = pojoPackage;
        this.pojoName = table.name.type();
        this.tableName = table.name.table();
        this.mapperPackage = mapperPackage;
        this.mapperName = pojoName + "Mapper";
        this.servicePackage = servicePackage;
        this.serviceName = pojoName + "Service";
        this.controllerPackage = controllerPackage;
        this.controllerName = pojoName + "Controller";
    }

    public File getPojoSaveAs() {
        pojoSaveAs = new File(FileUtils.getFile(outDir, pojoPackage.split("\\." )), pojoName + ".java" );
        return pojoSaveAs;
    }

    public File getSimplePojoSaveAs() {
        if (simplePojoSaveAs == null)
            simplePojoSaveAs = new File(FileUtils.getFile(outDir, "simplePojo" ), pojoName + ".java" );
        return simplePojoSaveAs;
    }

    public File getPojoInfoSaveAs() {
        if (pojoInfoSaveAs == null)
            pojoInfoSaveAs = new File(FileUtils.getFile(outDir, "pojoInfo" ), pojoName + ".md" );
        return pojoInfoSaveAs;
    }

    public File getMapperJavaSaveAs() {
        if (mapperJavaSaveAs == null)
            mapperJavaSaveAs = new File(new File(FileUtils.getFile(outDir, mapperPackage.split("\\." )), "java" ), mapperName + ".java" );
        return mapperJavaSaveAs;
    }

    public File getMapperXmlSaveAs() {

        if (mapperXmlSaveAs == null)
            mapperXmlSaveAs = new File(new File(FileUtils.getFile(outDir, mapperPackage.split("\\." )), "xml" ), mapperName + ".xml" );
        return mapperXmlSaveAs;
    }

    public File getServiceSaveAs() {
        if (serviceSaveAs == null)
            serviceSaveAs = new File(FileUtils.getFile(outDir, servicePackage.split("\\." )), serviceName + ".java" );
        return serviceSaveAs;
    }

    public File getControllerSaveAs() {
        if (controllerSaveAs == null)
            controllerSaveAs = new File(FileUtils.getFile(outDir, controllerPackage.split("\\." )), controllerName + ".java" );
        return controllerSaveAs;
    }

    // getter
    public String getSupperPojoClassName() {
        return supperPojoClassName;
    }

    public Table getTable() {
        return table;
    }

    public String getPojoPackage() {
        return pojoPackage;
    }

    public void setPojoPackage(String pojoPackage) {
        this.pojoPackage = pojoPackage;
    }

    public String getPojoName() {
        return pojoName;
    }

    public void setPojoName(String pojoName) {
        this.pojoName = pojoName;
    }

    public String getMapperPackage() {
        return mapperPackage;
    }

    public void setMapperPackage(String mapperPackage) {
        this.mapperPackage = mapperPackage;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(String servicePackage) {
        this.servicePackage = servicePackage;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getControllerPackage() {
        return controllerPackage;
    }

    public void setControllerPackage(String controllerPackage) {
        this.controllerPackage = controllerPackage;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }
}