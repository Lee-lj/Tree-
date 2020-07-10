package 链式存储二叉树;

public class  testBinaryTree{
//测试链式二叉树
	public static void main(String[] args) {
		BinaryTree bintree=new BinaryTree();//创建一棵树
		
		TreeNode root=new TreeNode(1);//创建根节点
		
        bintree.SetRootNode(root);//将根节点赋值给树
        
        //创建两个儿子节点
        TreeNode left=new TreeNode(2);
        TreeNode right=new TreeNode(3);
        
        //将两个儿子节点加入到根节点下
        root.SetLeftNode(left);
        root.SetRightNode(right);//这样一个树就创建好了
        
        //为左儿子节点添加两个儿子节点
        left.SetLeftNode(new TreeNode(4));
        left.SetRightNode(new TreeNode(5));
        
        //为右儿子节点添加两个儿子节点
        right.SetLeftNode(new TreeNode(6));
        right.SetRightNode(new TreeNode(7));
        
        //前序遍历树
        bintree.frontshow();
        System.out.println("========================");
        //中序遍历树
        bintree.middleshow();
        System.out.println("========================");
        //后序遍历
        bintree.aftershow();
        System.out.println("========================");
        
        //前序查找,查找一个节点数据为5的节点，如果有就返回一个TreeNode类型的节点，没有就返回Null
        TreeNode result=bintree.frontSearch(7);
        System.out.println(result);
        System.out.println("========================");
        
        //中序查找
        TreeNode result1=bintree.middleSearch(3);
        System.out.println(result1);
        System.out.println("========================");
        
        //后序查找
        TreeNode result2=bintree.afterSearch(10);
        System.out.println(result2);
        System.out.println("========================");
        
        //删除节点
        int i=6;
        boolean flag=bintree.delete(i);
        if(flag=true) {
        	System.out.println("成功删除"+i+"所在数据");
        }else {
        	System.out.println("删除失败，请检查您要删除的数据");
        }
        bintree.frontshow();
	}

}
