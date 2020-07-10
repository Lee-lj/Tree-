package 哈希表;

public class TesrHashTable {

	public static void main(String[] args) {
		StudentInf s1=new StudentInf(16, 3);
		StudentInf s2=new StudentInf(17, 11);
		StudentInf s3=new StudentInf(18, 23);
		StudentInf s4=new StudentInf(19, 24);
		StudentInf s5=new StudentInf(20, 9);

		HashTable ht=new HashTable();
		ht.put(s1);
		ht.put(s2);
		ht.put(s3);
		
		//打印ht中的数据
		System.out.println(ht);

		StudentInf target=new StudentInf(18);
		StudentInf info=ht.getInf(target);
		System.out.println(info);
	}

}
