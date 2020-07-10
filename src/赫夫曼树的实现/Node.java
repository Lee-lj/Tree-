package 赫夫曼树的实现;

public class Node implements Comparable<Node>{
//implements Comparable<Node>,使这个类可以比较，然后调用java里的Collections.sort方法就可以实现排序
	int value;//节点的权值
	
	Node leftNode;//左儿子节点
	Node rightNode;//右儿子节点
	
	public Node(int value) {//构造器，传入值
		this.value=value;
	}

	@Override
	public int compareTo(Node o) {
		return -(this.value-o.value);//加上-号让他降序排序
	}
}
