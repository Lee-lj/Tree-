package 线索二叉树的实现;

//此类定义链式存储二叉树的树体的类
public class BinaryTree {
	
	private TreeNode root;//根节点
	
	//用于临时存储前驱节点
	TreeNode front=null;

	public void SetRootNode(TreeNode root) {
		this.root=root;
	}//设置根节点的方法
	
	public TreeNode GetRootNode(TreeNode root) {
		return root;
	}//获取根节点的方法
	
	//遍历线索二叉树
	public void showTree() {
		//用于临时存储当前遍历的节点,然后将节点挨个存储到node
		TreeNode node=root;
		while(node!=null) {
			//循环找到开始的节点
			while(node.leftType==0) {
				node=node.leftNode;
			}
			//打印当前节点的值
			System.out.println(node.value);
			//如果当前节点的右指针指向的是后继节点，后继节点可能还有后继节点
			while(node.rightType==1) {
				node=node.rightNode;
				System.out.println(node.value);
			}
			//替换当前节点为下一个节点
			node=node.rightNode;
		}
	}
	
	public void binaryTrees() { 
		binaryTrees(root);//直接调用下面的方法，并且传入初始值root
	}
	
	//中序线索化二叉树的方法
	public void binaryTrees(TreeNode treeNode) {
		//当前节点如果是null，就直接返回
		if(treeNode==null) {
			return;
		}
		//因为是中序化，所以先递归处理左子树，直到左子树为空
		binaryTrees(treeNode.leftNode);
		  //处理前驱节点
		  if(treeNode.leftNode==null) {
			  //让当前节点的左指针指向前驱节点
			  treeNode.leftNode=front;
			  //改变当前节点左指针的类型，也就是将标签置为1，意思是指向的是前驱节点
			  treeNode.leftType=1;
		  }
		  //处理前驱节点的右指针，如果前驱节点的右指针为null(没有右儿子子树)，就让前驱节点的右指针指向当前节点    如果定位到了起始的叶子节点，如果不判断他的儿子节点为空，就会报错，因为他没有儿子节点
		  if(front!=null&&front.rightNode==null) {
			  //让前驱节点的右指针指向当前节点
			  front.rightNode=treeNode;
			  //改变前驱节点右指针的类型，也就是将标签置为1，意思是指向的是后一个节点
			  front.rightType=1;
		  }	
		  //每处理一个节点，当前节点是下一个节点的前驱节点
		  front=treeNode;
		//处理自己
		
		//处理右子树，直到右+子树为空
		binaryTrees(treeNode.rightNode);
	}

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

