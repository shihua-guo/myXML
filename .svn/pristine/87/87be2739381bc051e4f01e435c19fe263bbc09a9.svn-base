package dom4j;

import java.util.Properties;

public class DoT implements User{
	private int port;
	private String test;
	@Override
	public void init(Properties pro) {
		this.port = Integer.parseInt(pro.getProperty("port"));
		this.test = pro.getProperty("test");
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
}
