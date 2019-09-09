package com.java.ac.sc.entities;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    private Integer id;

    private Integer pid;

    private String name;

    private String icon;

    private String url;
    
    private Boolean open;
    
    private List<Permission> children = new ArrayList<>();
    
    private Boolean checked;

    public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean check) {
		this.checked = check;
	}

	public Permission() {
		super();
	}

	public Permission(Integer id, Integer pid, String name, String icon, String url, Boolean open,
			List<Permission> children) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.icon = icon;
		this.url = url;
		this.open = open;
		this.children = children;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}
    
}