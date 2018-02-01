package ljs.reflect;

public enum EnumType {
    A(EnumType.class), B(Integer.class), C(Long.class);

    public Object value;

    EnumType(Object value) {
        this.value = value;
    }
}
