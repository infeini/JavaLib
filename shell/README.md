#### &nbsp;&nbsp;shell是一个用java实现的本地命令执行工具库,能在一个进程里连续执行命令,而不用重复创建进程。
#### &nbsp;&nbsp;同时支持安卓端命令执行
<br></br>
### 一、获取shell实例
```java
Shell shell = Shell.newShell();
```
若出现乱码可以指定编码
```java
Shell shell = Shell.newShell("UTF-8");
```
安卓端获取一个拥有root权限的shell
```java
Shell shell = Shell.newAndroid(true);
```
<br></br>
二、执行命令
```java
Shell shell = Shell.newShell();
shell.execute(new Command("ls /") {
            @Override
            public void out(String line) {
                System.out.println(line);
            }
            @Override
            public void error(String errorLine) {
                System.err.println(errorLine);
            }
        });
```