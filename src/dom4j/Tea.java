package dom4j;

import java.util.Properties;

public class Tea implements User{
	private String port;
	private int age;
	private String gender;
	@Override
	public void init(Properties pro) {
		this.port = pro.getProperty("port");
		this.age  = Integer.parseInt(pro.getProperty("age"));
		this.gender = pro.getProperty("gender");
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
