package test_for_every_dont_know;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
/**.
* javadoc
*/
public class Test {
/**.
* javadoc
* @param strlst strlst
* @return a Linkst
*/
public static linkList creatlnklst(final String[] strlst) {
    linkList lnklst = new linkList();
    for (int i = 0; i < strlst.length - 1; i++) {
     lnklst.insertnode(strlst[i], strlst[i + 1]);
    }
    return lnklst;
}
/**.
* javadoc
* @param args args
*/
public static void main(final String[] args) {
		    String regex = "[^\\p{Alpha}]+";
		 String localAddress = "test2.txt";
		 //String wordstmp;
		 String[] strArrays = null;
		try {
		    FileInputStream fis = new FileInputStream(localAddress);
		    InputStreamReader dis = new InputStreamReader(fis);
		    BufferedReader reader = new BufferedReader(dis);
		    String strSentence = "";    
		    String strtemp = reader.readLine();
		    while (strtemp != null) {
		         strSentence = strSentence + strtemp;
		         strtemp = reader.readLine();
		     }
		     dis.close();
		
		     strArrays = strSentence.split(regex);
		
		 } catch (IOException e) {
		     System.out.println(e);
		 }
		String str1 = new String("To @ explore strange new worlds,\r\n"
		 + "To seek out new life and new civilizations?");
		String strinput = "Seek to explore new and exciting synergies";
		//输入字符串//Seek to explore strange new life and exciting synergies.
		@SuppressWarnings("unused")
		String[] x = str1.split(regex); //分割后字符串
		if (strArrays != null) {
		linkList lnklst = creatlnklst(strArrays); //功能要求1：创建链表
		
		lnklst.displaytree(); //展示链表
		System.out.println("================Task 3================");
		lnklst.searchBridgeWords("explore", "worlds"); //功能要求3：查询桥接词
		lnklst.searchBridgeWords("explore", "strange");
		lnklst.searchBridgeWords("explore", "happy");
		lnklst.searchBridgeWords("happy", "worlds");
		lnklst.searchBridgeWords("the", "assuming");
		lnklst.searchBridgeWords("package", "built");
		System.out.println("================Task 4================");
		lnklst.createNewTxtBasisOfBridgeWords(strinput); //功能要求4：对输入字符串添加桥接词
		
		lnklst.createNewTxtBasisOfBridgeWords("The java API is  of the  the package, "
		        + "depending  who built the  you are using.");
		lnklst.createNewTxtBasisOfBridgeWords("you  a spell over my  and"
		        + " every time I'm  your hand");
		//   The java API is part of the  the package
		//      depending on who built the package you are using.
		System.out.println("================Task 5================");
		lnklst.shortestroad("to", "new"); //功能要求5：查询最短路径
		lnklst.shortestroad("to", "and");
		lnklst.shortestroad("to", "seek");
		lnklst.shortestroad("seek", "to");
		lnklst.shortestroad("seek", "civilizations");
		System.out.println("================Task 6================");
		final int num = 10;
		for (int i = 0; i < num; i++) { //功能要求6：随机遍历
		    System.out.print("第" + (int) (i + 1) + "次遍历  ");
		    lnklst.randomvisit();
		}
		//Proba p = new Proba();
		//p.start();
		//p.start2();
		}
	}
}
