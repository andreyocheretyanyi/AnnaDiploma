package ua.com.anna.borodina.annadiploma.model.dao;



public class Block {
    private int id;
    private String name;

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

    public Block(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
