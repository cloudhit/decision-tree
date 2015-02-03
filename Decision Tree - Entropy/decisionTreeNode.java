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
import java.util.Map;
import java.io.*;

public class decisionTreeNode{
	public String attributeName = null;
	public int get_index = -1;
    public int branch_num = 0;
    //public double train_entropy = 0;
    //public int train_num = 0;
    public  List<decisionTreeNode> children = null;
    public  List<String> values = null;
	public decisionTreeNode(){};
	public decisionTreeNode(String name, ArrayList<String> attr_value, int index){
		this.attributeName = name;
        this.children = new ArrayList<decisionTreeNode>();
        this.values = attr_value;
        this.branch_num = attr_value.size();
        this.get_index = index;
	  }
    public String DFS(String test){
            if(this.attributeName == "+" || this.attributeName == "-")
                return this.attributeName;
            String[] tmp = test.split(" ");
            char attribute = tmp[this.get_index].charAt(0);
            int cur = -1;
            for(String i : this.values){
                //System.out.print(i + " ");
                cur ++;
                if(i.indexOf(attribute) != -1){
                    decisionTreeNode child = this.children.get(cur);
                    return child.DFS(test);
                }
            }
            return "false";
    }
	public String[] classify(String[] table, String set){
	     String[] ans = new String[this.branch_num];
         for(int i = 0; i < this.branch_num; i++)
            ans[i] = "";
         String[] tmp = set.split("&");
         for(int i = 0; i < tmp.length; i ++){
                String[] arr = table[Integer.parseInt(tmp[i]) - 1].split(" ");
                char attribute = arr[get_index].charAt(0);
                int num = 0;
                for(String value : values){
                    if(value.indexOf(attribute) != -1){
                        ans[num] += tmp[i] + "&";
                        break;
                    }
                    num ++;
                }
           }
        for(String tmp2 : ans)
            if(tmp2.length() > 0)
                tmp2 = tmp2.substring(0, tmp2.length() - 1);
        return ans;
	}
    public double[] calEntropy(String[] table, String set){
       
        String[] subsets = classify(table, set);        
        double[] ans = new double[subsets.length + 1];
        int set_total = set.split("&").length;
        for(int i = 0; i < subsets.length; i ++){
            String tmp = subsets[i];
            if(tmp.length() == 0 || tmp == null){
                ans[i] = 0;
                continue;
            }
            String[] example = tmp.split("&");
            int total = example.length, cnt = 0;
            for(String exam : example)
             if(table[Integer.parseInt(exam) - 1].charAt(0) == '+')
                cnt++;
            double p_t = 1.0 * cnt/total, p_f = 1.0 * (total - cnt)/total;      
            ans[i] = (p_t == 0 || p_f == 0) ? 0: -1.0 * p_t * (Math.log(p_t)/Math.log(2)) - 1.0 * p_f * (Math.log(p_f)/Math.log(2));
            ans[subsets.length] += 1.0 * total/set_total * ans[i];
        }
        return ans;
    }
    public String count(String[] table, String set){
        String[] tmp = set.split("&");
        int cnt = 0, total = tmp.length;
        for(String i:tmp)
            if (table[Integer.parseInt(i) - 1].charAt(0) == '+')
                cnt ++;
        return (cnt > total /2)? "+":"-";
    }

}