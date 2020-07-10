package 哈希表;

public class StudentInf {

	private int age;
	private int number;
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public StudentInf(int age, int number) {
		super();
		this.age = age;
		this.number = number;
	}
	
	/**
	 * 直接定址法，根据age将数据存入
	 */
	public int hashCode() {
		return age;
		//如果用取余数法，直接 return age%10就得到了余数3
	}
	@Override
	public String toString() {
		return "StudentInf [age=" + age + ", number=" + number + "]";
	}
	
	public StudentInf(int age) {
		super();
		this.age = age;
	}
	
	
	
}
