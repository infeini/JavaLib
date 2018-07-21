package ljs.shell;

public interface ShellListener {

    void onCreated(String msg);

    void onCreateFail(String error);
}
