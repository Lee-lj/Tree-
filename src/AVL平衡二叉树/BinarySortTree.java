package AVL平衡二叉树;

public class BinarySortTree {

	Node root;//创建根节点
	
	/**
	 * 向二叉排序树中添加节点
	 * @param node
	 */
	public void add(Node node) {
		//如果是空树，添加的节点就是根节点
		if(root==null) {
			root=node;
		}else {//不是空树就调节点的添加方法
			root.add(node);
		}
	}

	/**
	 * 中序遍历二叉排序树,结果从小到大
	 */
	public void middleshow() {
		if(root!=null) {
			root.middleshow(root);
		}
		
	}
	
	/**
	 * 根据数据查找节点
	 * @param value
	 * @return
	 */
	public Node search(int value) {
		if(root==null) {
			return null;
		}else {
			return root.search(value);
		}
	}
	
	/**
	 * 删除一个叶子节点
	 * 删除含有一个子节点的节点
	 * 删除有两个子节点的节点
	 * @param calue
	 */
	public void delete(int value) {
		if(root==null) {
			return;
		}else {
			//找到要删除的节点
			Node target=search(value);
			if(target==null) {//如果要删除的节点找不到
				return;
			}
			//找到要删除节点的父节点
			Node parent = searchParent(value);
			
			//如果要删除的节点为叶子节点
			if(target.leftNode==null&&target.rightNode==null) {
			    //如果要删除的是左儿子节点
				if(parent.leftNode.value==value) {
					parent.leftNode=null;
				}else{//如果要删除的是右儿子节点
					parent.rightNode=null;
				}
			}else if(target.leftNode!=null&&target.rightNode!=null){//删除的节点有两个子节点的情况
				//删除右子树中最小的叶子节点，取该节点的值  
				int min=deleteMin(target.rightNode);
				//将取到的最小值替换掉要删除的节点的值
				target.value=min;
			}else {//删除的节点有一个左子节点或右子节点
				///删除的节点存在左子节点
				if(target.leftNode!=null) {
					//如果要删除的是左儿子节点
					if(parent.leftNode.value==value) {
						parent.leftNode=target.leftNode;
					}else{//如果要删除的是右儿子节点
						parent.rightNode=target.leftNode;
					}
				}else {//不然就是/删除的节点含有右子节点
					if(parent.leftNode.value==value) {
						parent.leftNode=target.rightNode;
					}else{//如果要删除的是右儿子节点
						parent.rightNode=target.rightNode;
					}
				}
			}
			
		}
	}
	
	/**
	 * 删除一棵树中权最小的一颗节点
	 * @param node
	 * @return
	 */
	private int deleteMin(Node node) {
        Node target=node;//将传入的node放入新的target变量
        //循环向左找，找到最小的值
        while(target.leftNode!=null) {
        	target=target.leftNode;
        }
        //如果这个节点有子节点，(有右子节点)，删除这个最小节点
        delete(target.value);
        return target.value;
	}

	/**
	 * 此方法用来查找要删除节点的父节点
	 * @param value
	 * @return
	 */
	public Node searchParent(int value) {
		if(root==null) {//如果根节点为空
			return null;
		}else {
			return root.searchParent(value);
		}
	}
}
