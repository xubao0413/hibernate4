package cn.sxt.pojo;

import java.util.HashSet;
import java.util.Set;

public class Func {
	private int id;
	private String name;
	private String url;
	private Set<Role> roles=new HashSet<Role>();
	public Func() {
		// TODO Auto-generated constructor stub
	}
	public Func(String name, String url) {
		super();
		this.name = name;
		this.url = url;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}
