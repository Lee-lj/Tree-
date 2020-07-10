package 赫夫曼树的实现;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestHuffManTree {

	public static void main(String[] args) {
		int[] arr=new int[]{29,7,11,23,5,3,8,14};
		//Sort(arr);
		//System.out.println(Arrays.toString(arr));
		
		Node creatHuffMan = creatHuffMan(arr);
		System.out.println(creatHuffMan);
		
		
		
	}
	
	//先进行冒泡排序
	public static void Sort(int[] arr) {
			for(int i=0;i<arr.length-1;i++) {//每次都是开始位置的两个数一比，比较多少轮，
				for(int j=0;j<arr.length-1-i;j++) {//冒泡排序每一轮将一位最大的数挪至最右端，所以每过去一轮，就少和最后的几个已经确定的最大数比较
				  if(arr[j]>arr[j+1]) {
					  int min=arr[j+1];
					  arr[j+1]=arr[j];
					  arr[j]=min;
				}
			}
		}
	}
	
	
	//创建赫夫曼数
	public static Node creatHuffMan(int[] arr) {
		//1.先使用数组中的元素创建若干个二叉树(只有一个节点)
		List<Node> nodes=new ArrayList<>();//用集合来存放创建的二叉树
		for(int value:arr) {
			nodes.add(new Node(value));//将arr中的值遍历，存放在value中存放在nodes集合中
		}
		//结束的条件是集合中只剩下了一颗树
		while(nodes.size()>1) {
			//2.排序
			Collections.sort(nodes);//使实体类implements Comparable<Node>,使这个类可以比较，然后调用java里的Collections.sort方法就可以实现排序
		    //3.取出权值最小的两个二叉树
			Node left=nodes.get(nodes.size()-1);//因为是降序排列，所以-1取最小的节点
			Node right=nodes.get(nodes.size()-2);//取第二小的节点
		    //4。创建一颗新的二叉树
			Node parent=new Node(left.value+right.value);//新创建的二叉树根节点权值为两个小的二叉树根节点之和
		    //5.将取出来的两个二叉树移除(从集合中删除已经组成了二叉树的两个节点)
			nodes.remove(left);
			nodes.remove(right);
		    //6.放入原来的二叉树集合
			nodes.add(parent);
		}
		return nodes.get(0);
	}
}
