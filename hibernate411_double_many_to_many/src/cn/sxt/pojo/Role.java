package cn.sxt.pojo;

import java.util.HashSet;
import java.util.Set;

public class Role {
	private int id;
	private String name;
	private Set<Func> funcs = new HashSet<Func>();
	public Role() {
	}
	public Role(String name) {
		super();
		this.name = name;
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
	public Set<Func> getFuncs() {
		return funcs;
	}
	public void setFuncs(Set<Func> funcs) {
		this.funcs = funcs;
	}
	
}
