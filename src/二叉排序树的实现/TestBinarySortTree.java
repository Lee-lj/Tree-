package 二叉排序树的实现;

public class TestBinarySortTree {

	public static void main(String[] args) {
		int[] arr=new int[] {7,3,10,12,5,1,9};
		BinarySortTree tree=new BinarySortTree();
		//循环向树中添加节点  两种for循环的方式
		/*for(int i=0;i<arr.length;i++) {
			tree.add(new Node(arr[i]));
		}*/
		for(int i:arr) {//直接遍历取出arr中的数
			tree.add(new Node(i));
		}

		//中序遍历这个二叉排序树得到的数组刚好原数组排序后的结果
		//tree.middleshow();
		//System.out.println("==================");
		//查找一个存在的节点
		//Node result = tree.search(7);
		//System.out.println(result);
		//System.out.println("==================");
		//查找一个不存在的节点
		//Node worryresult = tree.search(20);
		//System.out.println(worryresult);
		//System.out.println("==================");
		//添加一个节点
		//tree.add(new Node(2));
		//中序遍历
		//tree.middleshow();
		//System.out.println("==================");
		
		//删除一个叶子节点
		//tree.delete(12);
		//tree.middleshow();
		//System.out.println("==================");
		
		//删除只含有一个子节点的节点
		//tree.delete(10);
		//tree.middleshow();
		
		//删除有两个子节点的节点
		tree.delete(3);
		tree.middleshow();
	}

}
