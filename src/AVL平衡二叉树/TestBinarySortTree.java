package AVL平衡二叉树;

public class TestBinarySortTree {

	public static void main(String[] args) {
		//int[] arr=new int[] {8,9,6,5,7,4};//进行右旋转的验证数组
		//int[] arr=new int[] {2,1,4,3,5,6};//进行左旋转的验证数组
		//int[] arr=new int[] {8,5,9,4,6,7};//进行双螺旋(先左旋转，然后右旋转，左儿子的右子树大于左子树高度)验证数组，
		int[] arr=new int[] {7,5,10,9,8,11};//进行双螺旋(先右旋转，然后左旋转，右儿子的右子树小于左子树高度)验证数组，
		BinarySortTree tree=new BinarySortTree();
		//循环添加
		for(int i:arr) {
			tree.add(new Node(i));
		}
		//查看树的高度 
		System.out.println(tree.root.height());
		//查看根节点的值，左子树，右子树高度
		System.out.println(tree.root.value);
		System.out.println(tree.root.leftHeight());
		System.out.println(tree.root.rightHeight());
		System.out.println("==============");
		//遍历树
		tree.middleshow();
	}

}
