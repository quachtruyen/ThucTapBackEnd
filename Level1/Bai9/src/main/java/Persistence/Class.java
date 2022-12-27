package Persistence;

public class Class {
    private int id;
    private String name;
    private int code;

    public Class(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public Class() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
