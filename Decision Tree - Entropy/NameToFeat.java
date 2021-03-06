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

public class NameToFeat{
	public FileInputStream file = null;
    public InputStreamReader isr = null;
    public BufferedReader br = null;
    public FileOutputStream fos = null;
    public String str = "";
    public NameToFeat(){};
    public boolean covToFeat(String readFile, String writeFile){
     try{
     	  file = new FileInputStream(readFile);
		    isr = new InputStreamReader(file);
        br = new BufferedReader(isr);
        fos = new FileOutputStream(writeFile);
        int cnt = 0;
    	while((str = br.readLine()) != null){
    	   String[] tmp = str.split(" ");
           char sign = str.charAt(0);
    	     char last_first = tmp[tmp.length - 1].charAt(0);
           char last = str.charAt(str.length() - 1);
           char flag1, flag2;
           if("acdeghi".indexOf(last) != - 1)
             flag1 = 'Y';
           else if("nstyz".indexOf(last) != -1)
             flag1 = 'N';
           else 
             flag1 = 'O';
           if("abcdfj".indexOf(last_first) != -1)
             flag2 = 'Y';
           else if("rsw".indexOf(last_first)!= -1)
             flag2 = 'N';
           else 
             flag2 = 'O';
           char flag3 = (str.indexOf('.') != -1)? 'Y':'N';
           char flag4 = (str.indexOf('-') != -1)?'Y':'N';
           fos.write((sign + " " + flag1 + " " + flag2 + " " + flag3 + " " + flag4 + "\n").getBytes());
           cnt ++;
    	}  System.out.println("number :" + cnt);
        }catch (FileNotFoundException e) {
         System.out.println("can't find file");
         return false;
        }catch (IOException e) {
        System.out.println("read/write fails");
        return false;
        } finally {
          try {
           br.close();
           isr.close();
           file.close();
           fos.close();
           return true;
       } catch (IOException e) {
         e.printStackTrace();
         return false;
       }
     }
    }
    public String[] sendToMemory(String FileName, int length){
      String[] table = new String[length];
      try{
        file = new FileInputStream(FileName);
        isr = new InputStreamReader(file);
        br = new BufferedReader(isr);
        int cnt = 0;
      while((table[cnt++] = br.readLine()) != null);
        }catch (FileNotFoundException e) {
         System.out.println("can't find file");
        }catch (IOException e) {
        System.out.println("read/write fails");
        } finally {
          try {
           br.close();
           isr.close();
           file.close();
       } catch (IOException e) {
         e.printStackTrace();
       }
       return table;
     }
    }
}