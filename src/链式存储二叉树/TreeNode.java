package 链式存储二叉树;

//此类定义树中的节点
public class TreeNode {

	 int value;//树中存储的数据，也叫做节点的权
	
	private TreeNode leftNode;//这个节点的左儿子节点
	
	private TreeNode rightNode;//这个节点的右儿子节点
	
	public TreeNode(int value) {
		this.value=value;
	}
	
	//设置左节点
	public void SetLeftNode(TreeNode leftNode) {
		this.leftNode=leftNode;
	}
	
	
	//设置右节点
	public void SetRightNode(TreeNode rightNode) {
		this.rightNode=rightNode;
	}

	//前序遍历的方法
	public void frontshow() {
        System.out.println(value);//先打印自己的值
        if(leftNode!=null) {
        	leftNode.frontshow();//使用递归的方法打印儿子节点,儿子节点继续使用前序遍历方法，打印自己再打印自己的儿子节点
        }
        if(rightNode!=null) {
        	rightNode.frontshow();//使用递归的方法打印儿子节点
        }
	}
	
	//中序遍历方法
	public void middleshow() {
		if(leftNode!=null) {
			leftNode.middleshow();//打印左节点
		}
		System.out.println(value);//打印根节点
		if(rightNode!=null){
			rightNode.middleshow();//打印右节点
		}
	}
	
	//后序遍历方法
	public void aftershow() {
		if(leftNode!=null) {
			leftNode.middleshow();//打印左节点
		}
		if(rightNode!=null){
			rightNode.middleshow();//打印右节点
		}
		System.out.println(value);//打印根节点
	}

	
	//前序查找方式
	public TreeNode frontSearch(int i) {
		TreeNode target=null;//target主要存放左儿子节点或右儿子节点查找到的节点信息，初始值定为null,如果查不到直接返回就是null
		if(this.value==i) {
			return this;//如果根节点就等于要查找的值，就直接返回
		}else {
			if(leftNode!=null) {
				target=leftNode.frontSearch(i);//如果左儿子节点不为空，继续用递归的方式查找
			}
			if(target!=null) {
				return target;//如果左儿子节点检查到了匹配的数据，就将数据返回，没查找到就继续往下,,不为空就是左儿子检查到了匹配的节点
			}
			if(rightNode!=null) {
				target=rightNode.frontSearch(i);
			}
		}
		return target;
	}
	
	//中序查找方式
	public TreeNode middleSearch(int i) {
		TreeNode target=null;
		if(leftNode!=null) {//判断左儿子是否匹配
			target=leftNode.middleSearch(i);
		}
		if(target!=null) {
			return target;//如果左儿子节点检查到了匹配的数据，就将数据返回，没查找到就继续往下,,不为空就是左儿子检查到了匹配的节点
		}
		if(value==i) {
			return this;
		}
		if(rightNode!=null) {
			target=rightNode.frontSearch(i);
		}
		return target;
	}

	public TreeNode afterSearch(int i) {
		TreeNode target=null;
		if(leftNode!=null) {//判断左儿子是否匹配
			target=leftNode.middleSearch(i);
		}
		if(target!=null) {
			return target;//如果左儿子节点检查到了匹配的数据，就将数据返回，没查找到就继续往下,,不为空就是左儿子检查到了匹配的节点
		}
		if(rightNode!=null) {
			target=rightNode.frontSearch(i);
		}
		if(value==i) {
			return this;
		}
		return target;
	}

	//删除一个结点(节点有子节点的话就相当于删除了一个子树)
	public boolean delete(int i) {
		boolean fine=false;
		TreeNode parent=this;//将当前的根节点保存
		if(parent.leftNode!=null&&parent.leftNode.value==i) {//如果根节点的左儿子是要删除的节点，直接将左儿子置为null就完成了删除
			parent.leftNode=null;
			fine=true;
		}
		if(fine!=false) {//fine不等于false表示删除成功,直接将其返回即可
			return fine;
		}
		if(parent.rightNode!=null&&parent.rightNode.value==i) {//如果根节点的右儿子是要删除的节点，直接将左儿子置为null就完成了删除
			parent.rightNode=null;
			fine=true;
		}
		if(fine!=false) {//fine不等于false表示删除成功,直接将其返回即可
			return fine;
		}
		//如果左右儿子节点都没找到，开始将左儿子节点作为parent，遍历左儿子节点的子节点
        parent=leftNode;
		if(parent!=null) {//用递归遍历并删除左儿子的子节点
			parent.delete(i);
		}
		parent=rightNode;
		if(parent!=null) {//用递归遍历并删除右儿子的子节点
			parent.delete(i);
		}
		return fine;
	}
}
