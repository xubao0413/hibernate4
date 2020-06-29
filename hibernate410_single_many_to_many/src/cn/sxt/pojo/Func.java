package cn.sxt.pojo;

public class Func {
	private int id;
	private String name;
	private String url;
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
}
