package 顺序存储二叉树;

public class ArrayBinaryTree {

	int[] data;//创建一个数组，存放节点数据，认为这个数组就是完全二叉树

	public ArrayBinaryTree(int[] data) {
		this.data=data;
	}

	public void frontshow() {
		frontshow(0);//如果直接调用下面的方法，需要输入一个start位置，这个方法直接将下面的方法从0开始，保证能够从头开始遍历
	}
	
	//前序遍历的方法，跟链式存储的差不多，但是顺序存储不用再定义节点类，直接使用了数组来存放
	public void frontshow(int start) {//定义start，开始遍历的位置
		if(data==null||data.length==0) {//数组为空或长度为0就直接返回
			return;
		}
		//先遍历当前节点的内容
		System.out.println(data[start]);//打印根节点的内容
		
		//打印左儿子节点，下标就为2*start+1
		if(start*2+1<data.length) {
			frontshow(start*2+1);//如果有左儿子节点，就递归打印
		}
		
		//打印右儿子节点，下标就为2*start+2
		if(start*2+2<data.length) {
			frontshow(start*2+2);//如果有左儿子节点，就递归打印
		}
		
	}


	
}
