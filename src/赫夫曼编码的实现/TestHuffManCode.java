package 赫夫曼编码的实现;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class TestHuffManCode {

	public static void main(String[] args) {

		String msg="can you can a can as a can canner can a can.";
		byte[] bytes = msg.getBytes();//所有数据都可以转换为一个byte数组，对byte数组进行编码
		byte[] h=huffmanZip(bytes);//进行赫夫曼编码
		System.out.println(bytes.length);//压缩之前的长度为44
		System.out.println(h.length);//压缩之后的长度为16
		
		byte[] newBytes=decode(huffmanTreeCodes,h);//使用huffcode这张表进行解码,解码之后就为99, 97, 110, 32, 121, 111,这样一个数组
		System.out.println(new String(newBytes));//将newBytes作为一个字符串进行输出，得到结果can you can a can as a can canner can a can.
        
		/*压缩文件
		String src="1.jpg";//源文件为jpg图片
		String dst="2.zip";//压缩为2的压缩文件
		try {
			zipFolder(src,dst);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		//解压文件
		String src="2.zip";
		String dst="3.jpg";
		try {
			readZip(src, dst);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 此方法对源文件src进行压缩，然后存放到指定的dst位置
	 * @param src
	 * @param dst
	 * @throws IOException 
	 */
	public static void zipFolder(String src,String dst) throws IOException {
		//创建一个输入流指向源文件
		InputStream is=new FileInputStream(src);
		//将文件存放到与输入流指向的文件等长的byte数组中，然后进行排序，is的available是这个输入流的长度，
		byte[] b=new byte[is.available()];
		//读取文件内容,数组b就是文件中的内容
		is.read(b);
		is.close();
		//使用赫夫曼编码进行文件的编码,得到folderZip数组
		byte[] folderZip = huffmanZip(b);
		//输出流，不仅要输出编码后的folderZip数组，还要输出赫夫曼编码表
		OutputStream out=new FileOutputStream(dst);
		ObjectOutputStream outs=new ObjectOutputStream(out);
		//将压缩后的byte数组写入文件
		outs.writeObject(folderZip);
		//将赫夫曼编码表写入文件
		outs.writeObject(huffmanTreeCodes);
		//关闭输出流
		outs.close();
		out.close();
		
	}
	
	/**
	 * 此方法用于将压缩文件src解压成dst文件
	 * @param src
	 * @param dst
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void readZip(String src,String dst) throws Exception{
		//创建一个输入流,读取源文件
		InputStream is=new FileInputStream(src);
        ObjectInputStream ois=new ObjectInputStream(is);//将读取到的ZIP直接存入对象流读取
		//读取byte数组
		byte[] read = (byte[])ois.readObject();
		//读取戈夫曼编码表
		Map<Byte, String> code=(Map<Byte, String>) ois.readObject();
		ois.close();
		is.close();
		//使用写好的decode方法解码,得到新的byte数组
		byte[] bytes = decode(code,read);
		//创建一个输出流
		OutputStream out=new FileOutputStream(dst);
		//写出数据
		out.write(bytes);
		out.close();
	}

	
	/**
	 * 进行赫夫曼编码压缩编码的方式	
	 * @param bytes
	 * @return
	 */
	private static byte[] huffmanZip(byte[] bytes) {
        //先统计每一个字符出现的次数，并放入一个集合中
		List<Node> nodes=getNodes(bytes);
		//创建赫夫曼树
		Node tree=createHuffmanTree(nodes);
		System.out.println(tree);
		//创建赫夫曼编码表
		Map<Byte, String> huffcode=getCodes(tree);
		//编码
		byte[] b=zip(bytes,huffmanTreeCodes);
		return b;
	}

	/**
	 * 进行赫夫曼编码
	 * @param bytes
	 * @param huffmanTreeCodes2
	 * @return
	 */
	private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanTreeCodes) {
        StringBuilder sb=new StringBuilder();
        for(byte b:bytes) {//遍历byte数组，将所有的byte二进制字符串数组连接在一起,得到二进制字符串 11101001011010011011011001010111010010011011101001001110011100110111010010111010000110101110001011101001001101110100110111
        	sb.append(huffmanTreeCodes.get(b));
        }
        //定义长度,八个字符一组
        int length;
        if(sb.length()%8==0) {
        	length=sb.length()/8;
        }else {
        	length=sb.length()/8+1;//如果除不尽，就多建立一个来存储剩余的二进制码
        }
        System.out.println(sb.toString());
        //用于存储压缩后的二进制 字符串
        byte[] by=new byte[length];
        //用于记录新的byte八位字符的位置(八位二进制数保存在一个byte数组中，index记录是第几个数组)
        int index=0;
        for(int i=0;i<sb.length();i+=8) {//每次向后跳八位，将这一组字符通过substring截取出来
        	String strByte;
        	if(i+8>sb.length()) {
        		strByte=sb.substring(i);//如果i+8超出了二进制数组的长度，就只截取它前面的一部分
        	}else {
        	    strByte=sb.substring(i,i+8);
        	}
        	byte byt=(byte)Integer.parseInt(strByte,2);//将strbyte转为二进制
            by[index]=byt;
            index++;
        }
		return by;
	}

	/**
	 * 根据赫夫曼树递归获取赫夫曼编码表
	 * @param tree
	 * @return
	 */
	//用于临时存储到达叶节点的路径
	static StringBuilder sb=new StringBuilder();
    //用于存储赫夫曼编码
	static Map<Byte, String> huffmanTreeCodes =new HashMap<>();
	//根据赫夫曼树获取赫夫曼编码
	private static Map<Byte, String> getCodes(Node tree) {
        if(tree==null) {
        	return null;
        }
        getCodes(tree.leftNode,"0",sb);//定义每个节点的左儿子路径为0
        getCodes(tree.rightNode,"1",sb);//定义每个节点的左儿子路径为1
		return huffmanTreeCodes;
	}

	//找到每个节点的路径表示
	private static void getCodes(Node node, String code, StringBuilder sb) {
        StringBuilder sb2=new StringBuilder(sb);
        sb2.append(code);
        if(node.data==null) {//data是存储的字符，如果data为空表示它是一个构建出来的父节点，递归找寻它的叶节点
        	getCodes(node.leftNode,"0",sb2);
            getCodes(node.rightNode,"1",sb2);
        }else {//不为空表示为一个叶子节点，将它的路径存入Map
        	huffmanTreeCodes.put(node.data, sb2.toString());
        }
        
         
		
	}

	/**
	 * 此方法用于创建赫夫曼树
	 * @param nodes
	 * @return
	 */
	private static Node createHuffmanTree(List<Node> nodes) {
        while(nodes.size()>1) {
        	//排序
        	Collections.sort(nodes);
        	//取出两个权值最低的二叉子树
        	Node left=nodes.get(nodes.size()-1);
        	Node right=nodes.get(nodes.size()-2);
        	//创建一个新的二叉树
        	Node parent=new Node(null,left.value+right.value);//新创建的赫夫曼树都没右要存储字符，所以为null
        	//将前面取出的两个小的带权节点作为新二叉树的子节点
        	parent.leftNode=left;
        	parent.rightNode=right;
        	//将已经取出的字符删除
        	nodes.remove(left);
        	nodes.remove(right);
        	//将新创建的二叉树放入集合
        	nodes.add(parent);
        }
		return nodes.get(0);//因为最后只剩下了一个建立好的赫夫曼树，所以返回0
	}

	/**
	 * 把byte数组转换为Node集合
	 * @param bytes
	 * @return
	 */
	private static List<Node> getNodes(byte[] bytes) {
		List<Node> nodes=new ArrayList<>();
		//存储每一个byte出现的次数
		Map<Byte,Integer> counts=new HashMap<>();
		//统计每一个byte出现的次数
		for(byte b:bytes) {
			Integer count=counts.get(b);
			if(count==null) {//如果第一次取到一个字符，这个字符的次数为null，那么让他的出现次数从1开始
				counts.put(b, 1);
			}else {//如果不是第一次取到这个字符，就让这个字符出现的次数加一
				counts.put(b, count+1);
			}
		}//此时就统计出了每个字符出现的次数{97=1, 115=1, 117=3, 104=1, 105=3, 106=1, 122=1, 108=1, 110=1}前面为ASC码
		//接下来将每一个Map键值对转换为Node对象
		for(Map.Entry<Byte, Integer> entry:counts.entrySet()) {//遍历Map键值对,Map.Entry<Byte, Integer> entry:counts.entrySet()是Map集合遍历的一种方式
			nodes.add(new Node(entry.getKey(),entry.getValue()));
		}
		return nodes;
	}

	//使用huffmanTreeCodes编码表进行解码
		private static byte[] decode(Map<Byte, String> huffmanTreeCodes, byte[] bytes) {
	        StringBuilder sb=new StringBuilder();
			//1.将一个连续的byte数组转换8位一个的多个新数组
			for(int i=0;i<bytes.length;i++) {
				byte b=bytes[i];
				boolean flag=(i==bytes.length-1);//判断此时的i是否是bytes数组的最后一段数组，如果是，flag为false，就不用补齐这八位，直接返回，不是的话为true，
				sb.append(byteToBitStr(!flag,b));//调用byteToBitStr方法将byte转换为8位长度的字符串，然后将所有的8位连接在一起
				                                 //flag取反是因为如果是最后一位，这里为true，转为false，方法才能得到false
				
			}
			//2.将赫夫曼数的键值进行调换
			Map<String, Byte> map=new HashMap<>();
			for(Map.Entry<Byte, String> entry:huffmanTreeCodes.entrySet()) {
				map.put(entry.getValue(), entry.getKey());
			}
			//3.将字符串按照指定的赫夫曼编码解码
			
			//创建一个集合，用于存储byte
			List<Byte> list=new ArrayList<>();
			
			//处理字符串，将一整个的二进制字符串切割比对Map取到ASC码代表的数字
			for(int i=0;i<sb.length();) {//这里为了切割字符串为单个的字符，不能直接i++
				int count=1;
				boolean flag=true;
				Byte b=null;
				while(flag) {
					String key=sb.substring(i,i+count);
					b = map.get(key);//通过字符串截取到的字符作为key在Map中取到键值
					if(b==null) {
						count++;//如果当前截取的字符串在Map中取不到键值，将count加一位，实现i加一位，多取一个二进制码，然后再进行比对取值
					}else {
						flag=false;//如果当前的二进制码在Map中取到了对应的值，将flag置为false，跳出循环
					}
				}
				list.add(b);//将取到的字符的键值加入到集合中
				i+=count;
			}
			//将集合转变为数组
			byte[] b=new byte[list.size()];
			for(int i=0;i<b.length;i++) {
				b[i]=list.get(i);
			}
		    return b;
		}

		/**
		 * 设置byte数组转为固定长度(8位)的字符串
		 * @param b
		 * @return
		 */
	    private static String byteToBitStr(boolean flag,byte b) {//如果最后一位是正数，此方法会将这个正数补全为8位，负数不会补全，通过flag决定是否补全
	    	int temp=b;
	    	if(flag) {
	    	    temp|=256;//用256(100 000 000)来或这个数就可以实现字符的固定长度
	    	}
	    	String str=Integer.toBinaryString(temp);
	    	if(flag) {
	    		return str.substring(str.length()-8);//这样就将byte转换为8位长度的字符串
	    	}else {
	    		return str;
	    	}
	    }
}
