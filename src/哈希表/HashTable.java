package 哈希表;

import java.util.Arrays;

public class HashTable {

	private StudentInf[] data=new StudentInf[100];//不知道要存储的数据的数量，所以尽量大
	
	/**
	 * 向哈希表的数组中添加数据(向散列表中添加元素)
	 * @param stuInf
	 */
	public void put(StudentInf stuInf) {
		//stuInf.hashCode()，取得要存的数据的年龄，将年龄为下标直接定址存数据
		data[stuInf.hashCode()]=stuInf;
	}
	
	public StudentInf getInf(StudentInf stuInf) {//输入一个学生信息，取得输入的信息的年龄，按年龄为下标取出哈希表数据
		return data[stuInf.hashCode()];
	}

	@Override
	public String toString() {
		return "HashTable [data=" + Arrays.toString(data) + "]";
	}
	
}
