package 赫夫曼编码的实现;

public class Node implements Comparable<Node>{

	Byte data;//要传输的字符
	int value;//当前节点的数据(当前节点的权值)
	Node leftNode;
	Node rightNode;
	
	public Node(Byte data,int value) {
		this.data=data;
		this.value=value;
	}

	@Override
	public int compareTo(Node o) {
		return o.value-this.value;
	}

	@Override
	public String toString() {
		return "Node [data=" + data + ", value=" + value + "]";
	}

	
	
}
