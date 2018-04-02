package ljs.os;

public enum OsType {
    UNKNOW(0, "Unknow"),
    LINUX(1, "Linux"),
    WINDOWS(2, "Windows"),
    MAC(3, "Mac");
    public int value;
    public String name;

    OsType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
