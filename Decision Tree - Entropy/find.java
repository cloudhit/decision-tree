import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.HashMap;
import java.util.Arrays;
import java.lang.Math;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.io.*;

public class find{
    public static void main(String[] args) {
    	FileInputStream file = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        int[] pos = new int[40];
        int[] neg = new int[40];
        try{
        	String str = "";
        	String str1 = "";
        	file = new FileInputStream("badges-train.txt");
        	isr = new InputStreamReader(file);
        	br = new BufferedReader(isr);
          while((str = br.readLine()) != null){
          	str1 += str + "\n";
          	String[] tmp2 = str.split(" ");
            //int delta = (tmp2[tmp2.length - 1].length() - tmp2[1].length() >= 0)?1:0;
            int len = tmp2.length;
            char tmp = tmp2[tmp2.length - 1].charAt(0);
            if(!(tmp <= 'z' && tmp >= 'a'))
            	continue;
          	if(str.charAt(0) == '+'){
          		pos[tmp - 'a']++;
          	}
          	else {
          		neg[tmp - 'a']++;
          	}
            /*
            String[] tmp1 = str.split(" ");
            if(tmp1.length != 4)
            	continue;
            if(str.charAt(0) == '+'){
            	str = tmp1[2];
            	if(str.indexOf('.') != -1)
            		pos[0]++;
            	else
            		pos[1]++;
            		
            }else{
            	str = str.substring(1);
            	if(str.indexOf('.') != -1)
            		neg[0]++;
            	else
            		neg[1]++;
            }*/
          }
        }catch (FileNotFoundException e) {
         System.out.println("找不到指定文件");
        }catch (IOException e) {
        System.out.println("读取文件失败");
        } finally {
          try {
           br.close();
           isr.close();
           file.close();
    // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
       } catch (IOException e) {
         e.printStackTrace();
       }
       for(int i = 0; i <26; i++){
       	System.out.print((char)(i + 97) + ":" + pos[i] + " ");
       }
       System.out.println();
       for(int i = 0; i <26; i++){
       	System.out.print((char)(i + 97) + ":" + neg[i] + " ");
       }

    }
 }
}