package com.mtl.pojo;

public class Area {
    private int id;
    private String name;
    private int pid;

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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Area() {
    }

    public Area(int id, String name, int pid) {
        this.id = id;
        this.name = name;
        this.pid = pid;
    }
}
