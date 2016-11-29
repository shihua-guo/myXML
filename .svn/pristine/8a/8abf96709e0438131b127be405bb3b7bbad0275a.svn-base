package exercise;

public class User {
	private String name;
	private float fee;
	public User(){}
	public User(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", fee=" + fee + "]";
	}
	@Override
	public int hashCode() {
		int result = 17;
		result = 37*result+this.name.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		return this.name==((User)obj).name;
	}
	
	
}
