package com.java.ac.sc.entities;

public class Cert {
    private Integer id;

    private String name;
    
    public Cert() {
		super();
	}

	public Cert(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	@Override
	public String toString() {
		return "Cert [id=" + id + ", name=" + name + "]";
	}
    
}