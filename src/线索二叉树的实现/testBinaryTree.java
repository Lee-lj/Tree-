package 线索二叉树的实现;

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
        TreeNode fiveNode = new TreeNode(5);
        left.SetRightNode(fiveNode);
        
        //为右儿子节点添加两个儿子节点
        right.SetLeftNode(new TreeNode(6));
        right.SetRightNode(new TreeNode(7));
        
        
        //中序遍历树
        bintree.middleshow();
        System.out.println("========================");
       
        //中序线索化二叉树
        bintree.binaryTrees();
        /*//线索化后获取5的后继节点
        TreeNode afterFive = fiveNode.rightNode;
        System.out.println(afterFive.value);
        System.out.println("++++++++++++++++++++++++");*/
        
        //遍历中序线索化的二叉树
        bintree.showTree();
        
        //删除节点
        /*int i=6;
        boolean flag=bintree.delete(i);
        if(flag=true) {
        	System.out.println("成功删除"+i+"所在数据");
        }else {
        	System.out.println("删除失败，请检查您要删除的数据");
        }
        bintree.frontshow();*/
	}

}
