[![Build Status](https://travis-ci.org/LiuJiangshan/LjsJavaLib.svg?branch=master)](https://travis-ci.org/LiuJiangshan/LjsJavaLib) [![GitHub release](https://img.shields.io/github/release/LiuJiangshan/LjsJavaLib.svg)](https://github.com/LiuJiangshan/LjsJavaLib/releases) [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
### 工具库介绍:
| 模块     | 介绍                                                         |
| -------- | ------------------------------------------------------------ |
| autocode | 代码生成器,通过freemarker模版引擎,使用数据库表及字段信息通过模版生成相应代码,内置模版包括了:实体、spring的service、controller、mybatis的mapper.java和mapper.xml,可自定义模版,可直接生成javascript、typescript等文本代码. |
| base     | 包含一些基础java工具类                                       |
| ddos     | 通过socket模拟http请求,测试服务器性能(待完善)                |
| gson     | Gson的一些序列化配置工具类                                   |
| poi      | 通过org.apache.poi将excel转换为json或者定义好的实体对象      |
| shell    | java终端命令行执行器(支持android)                            |
| spring   | 基于spring的一些工具类                                       |
| tree     | 可展开树状结构数据                                           |
| weixin   | 微信支付api签名工具类                                        |
| zip      | 压缩包解压工具类                                             |
### 通过maven引用
```
<dependency>
    <groupId>com.github.liujiangshan.LjsJavaLib</groupId>
    <artifactId>base</artifactId>
    <version>version</version>
</dependency>
<repositories>
    <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
    </repository>
</repositories>
```
### 通过gradle引用
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
```
implementation 'com.github.liujiangshan.LjsJavaLib:base:version'
```