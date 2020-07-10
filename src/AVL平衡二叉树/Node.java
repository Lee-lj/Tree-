package AVL平衡二叉树;

public class Node {

	int value;//节点存储的数据
	Node leftNode;
	Node rightNode;
	
	public Node(int value) {
		this.value = value;
	}
	
	/**
	 * 返回当前节点的高度(根节点的高度为1)
	 * @return
	 */
	public int height() {
		//比较左右子树的高度，然后将较大的高度加上根节点的高度1，返回,如果左右子树为空，就返回0
		return Math.max(leftNode==null?0:leftNode.height(), rightNode==null?0:rightNode.height())+1;
	}
	
	//获取左子树高度
	public int leftHeight() {
		if(leftNode==null) {
			return 0;
		}
		return leftNode.height();
	}

	//获取左子树高度
		public int rightHeight() {
			if(rightNode==null) {
				return 0;
			}
			return rightNode.height();
		}
	/**
	 * 向子树中添加节点
	 * @param node
	 */
	public void add(Node node) {
		if(node==null) {
			return;
		}
		//判断传入的节点比根节点大还是小
		if(node.value<this.value) {//添加的节点比根节点小
			if(this.leftNode==null) {
				this.leftNode=node;//如果左儿子节点不存在，传入的节点就是左儿子节点
			}else {
				this.leftNode.add(node);//左儿子节点存在，就递归向下添加node节点
			}
		}else {//添加的节点比根节点大
			if(this.rightNode==null) {//如果右儿子节点不存在，传入的节点就是右儿子节点
				this.rightNode=node;
			}else {
				this.rightNode.add(node);//右儿子节点存在，就递归向下添加node节点
			}
		}
	//查询此平衡二叉树是否平衡,通过左右子树的高度差进行判断，绝对值不大于1的话就是平衡二叉树
		//左子树高度减右子树高度大于等于2，右旋转
		if(this.leftHeight()-this.rightHeight()>=2) {
			if(this.leftNode!=null&&this.leftNode.leftHeight()<this.leftNode.rightHeight()) {
				//如果根节点的左儿子节点的左子树高度小于右子树高度，先进行左儿子节点的左旋转,然后根节点进行右旋转
				this.leftNode.leftRotate();
				rightRotate();
			}else {
				//如果根节点的左儿子节点的左子树高度大于右子树高度，直接进行右旋转就完成了，因为此时的结构就是左儿子的左子树大于右子树，只用单旋转就ok
				rightRotate();
			}
			
		}
		//左子树高度减右子树高度小于等于-2，左旋转
		if(this.leftHeight()-this.rightHeight()<=-2) {
			if(this.rightNode!=null&&this.rightNode.leftHeight()>this.rightNode.rightHeight()) {
				//如果根节点的右儿子节点的左子树高度大于右子树高度，先进行右儿子节点的右旋转,然后根节点进行左旋转
				this.rightNode.rightRotate();
				leftRotate();
			}else {
				//如果根节点的右儿子节点的左子树高度小于右子树高度，直接进行左旋转就完成了，因为此时的结构就是右儿子的左子树小于右子树，只用单旋转就ok
			    leftRotate();
			}
		}
			
		
		
	}

	/**                  此时的树：   8            需要将其旋转为        6
	 *                        6     9          5     8
	 * 将二叉树进行右旋转                       5     7          4       7   9
	 *                 4
	 */                
	private void rightRotate() {
		//1.创建一个新节点，值就是当前节点的值(8),为了以8为父节点建立子树
		Node newRightNode=new Node(value);
		//2.把新节点的右子树设置为当前节点的右子树(8的右儿子为9)
		newRightNode.rightNode=this.rightNode;
		//3.把新节点的左子树设置为当前节点(8)的左子树(6)的右子树(7)
		newRightNode.leftNode=this.leftNode.rightNode;
		//4.把当前节点的值替换为左儿子节点的值(根节点从8变为6)
		this.value=this.leftNode.value;
		//5.把当前节点的左儿子节点设置为原来的左孙子节点(将根节点6的左儿子6删除，新的左儿子为5)
		this.leftNode=this.leftNode.leftNode;
		//6.将当前节点的右儿子设置为第一步建立的子树
		this.rightNode=newRightNode;
	}

	//将二叉树进行左旋转，方法跟右旋转方法相同
	private void leftRotate() {
		Node newLeftNode=new Node(value);
		newLeftNode.leftNode=this.leftNode;
		newLeftNode.rightNode=this.rightNode.leftNode;
		this.value=this.rightNode.value;
		this.leftNode=newLeftNode;
		this.rightNode=this.rightNode.rightNode;
	}
	
	
	//中序遍历，结果由小到大
	public void middleshow(Node node) {
		if(node==null) {
			return;
		}
	    middleshow(node.leftNode);
		System.out.println(node.value);
		middleshow(node.rightNode);
	}

	/**
	 * 查找节点
	 * @param value
	 */
	public Node search(int value) {
        if(this.value==value) {
        	return this;
        }else if(this.value>value) {
        	if(this.leftNode==null) {
        		return null;
        	}
        	return this.leftNode.search(value);
        }else {
        	if(this.rightNode==null) {
        		return null;
        	}
        	return this.rightNode.search(value);
        }	
    }

	@Override
	public String toString() {
		return "Node [value=" + value + ", leftNode=" + leftNode + ", rightNode=" + rightNode + "]";
	}

	/**
	 * 查找要删除节点的父节点
	 * @param value2
	 */
	public Node searchParent(int value) {
		if((this.leftNode!=null&&this.leftNode.value==value)||(this.rightNode!=null&&this.rightNode.value==value)) {
			return this;//如果当前节点的左儿子或者右儿子节点不为空，并且存储的值刚好等于要查的value，返回当前的节点
		}else {
			if(this.value<value&&this.rightNode!=null) {//当前的值小于传入的值,从右儿子开始查找
				return this.rightNode.searchParent(value);
			}else if(this.value>value&&this.leftNode!=null){//当前的值大于传入的值,从右儿子开始查找
				return this.leftNode.searchParent(value);
			}
			return null;//value都查找不出时返回null
		}
	}   


	
}
