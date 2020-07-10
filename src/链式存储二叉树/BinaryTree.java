package 链式存储二叉树;

//此类定义链式存储二叉树的树体的类
public class BinaryTree {
	
	private TreeNode root;//根节点

	public void SetRootNode(TreeNode root) {
		this.root=root;
	}//设置根节点的方法
	
	public TreeNode GetRootNode(TreeNode root) {
		return root;
	}//获取根节点的方法

	//前序遍历的方法
	public void frontshow() {
		root.frontshow();
		
	}

	public void middleshow() {
		root.middleshow();
	}

	public void aftershow() {
		root.aftershow();
	}

	public TreeNode frontSearch(int i) {
		return root.frontSearch(i);
	}

	public TreeNode middleSearch(int i) {
		return root.middleSearch(i);
	}

	public TreeNode afterSearch(int i) {
		return root.afterSearch(i);
	}

	public boolean delete(int i) {
		boolean c;
		if(root.value==i) {
			root=null;
			c= true;//如果根节点的数据刚好是要删除的数据，那么将其置为null，即可将整个树删除
		}else{
			c=root.delete(i);//如果要删除的不是根节点，就在节点中遍历查找，然后删除
		}
		return c;
	}

}

