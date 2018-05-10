package ljs.ddos;

public enum DDosType {
    TCP(0), UDP(1), HTTP(2);
    public int value;

    DDosType(int value) {
        this.value = value;
    }
}