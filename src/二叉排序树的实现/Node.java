package 二叉排序树的实现;

public class Node {

	int value;//节点存储的数据
	Node leftNode;
	Node rightNode;
	
	public Node(int value) {
		this.value = value;
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
