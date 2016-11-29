package dom4j;

import java.util.Properties;

public class Sta implements User{
	private long id;
	private String name;
	@Override
	public void init(Properties pro) {
		this.id = Integer.parseInt(pro.getProperty("id"));
		this.name = pro.getProperty("name");
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
