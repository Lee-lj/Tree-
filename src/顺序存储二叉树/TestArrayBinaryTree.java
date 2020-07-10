package 顺序存储二叉树;

public class TestArrayBinaryTree {

	public static void main(String[] args) {
		int[] data=new int[]{2,3,4,6,5,1,9};
	    ArrayBinaryTree tree=new ArrayBinaryTree(data);//树类中构造器确定了要传入的值  data数组
	    //前序遍历
	    tree.frontshow();
	    
	    
	}

}
