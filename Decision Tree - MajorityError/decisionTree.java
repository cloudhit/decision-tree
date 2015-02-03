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

public class decisionTree{
	private decisionTreeNode root = null;
	public String[] table = null;
    public decisionTreeNode buildDecisionTree(String[] table, String set, double MajorError, ArrayList<String> attribute, ArrayList<ArrayList<String>> values, ArrayList<Integer> indexes, String flag){
        if(set.length() == 0 || attribute.size() == 0 || MajorError == 0){
            decisionTreeNode leaf = new decisionTreeNode();
            ArrayList<String> vnull = new ArrayList();
            if(set.length() == 0)
             leaf = new decisionTreeNode(flag, vnull, -1);    
            else if(attribute.size() == 0 || MajorError == 0)
             leaf = new decisionTreeNode(leaf.count(table, set), vnull, -1);
             return leaf;
        }
        //choose the largest information gain
        double[][] MajorErrors = new double[attribute.size()][];
        double max = 0;
        int cur = -1, index_cur = -1 ;
        String attr_cur = "";
        ArrayList<String> value_cur = null;
        decisionTreeNode[] node = new decisionTreeNode[attribute.size()];
        for(int i = 0; i < attribute.size(); i ++){
            node[i] = new decisionTreeNode(attribute.get(i), values.get(i), indexes.get(i));
            //node[i].buildTreeNode(attribute.get(i));
            MajorErrors[i] = node[i].calMajorityError(table, set);
            double information_gain = MajorError - MajorErrors[i][MajorErrors[i].length - 1];
            information_gain = (information_gain >= 0.000000001)? information_gain:0;
            System.out.println(MajorError);
            System.out.println(MajorErrors[i][MajorErrors[i].length - 1] + "\n");
            if(max <= information_gain){
                max = information_gain;
                cur = i;
                attr_cur = attribute.get(i);
                index_cur = indexes.get(i);  
                value_cur = values.get(i);  
            }

        }
        String[] subsets = node[cur].classify(table, set);
        int p = 0;/*
        }*/
        for(int i = 0; i < node[cur].values.size(); i++){
            attribute.remove(cur);
            indexes.remove(cur);
            values.remove(cur);
            decisionTreeNode child = buildDecisionTree(table, subsets[i], MajorErrors[cur][i], attribute, values, indexes, node[cur].count(table, set));
            attribute.add(cur, attr_cur);
            indexes.add(cur, index_cur);
            values.add(cur, value_cur);
            node[cur].children.add(child);
        }
        return node[cur];
    }
    //Build the decision tree
    public void BuildTree(){
    	NameToFeat nt = new NameToFeat();
    	nt.covToFeat("badges-train.txt", "train.txt");
        table = nt.sendToMemory("train.txt", 200);
        double MajorError_s = initialMajorityError(table);
        String set = "";
        for(int i = 1; i <= 199; i ++)
        	set += i + "&";
        set += 200;
        ArrayList<String> attribute = new ArrayList<String>();
        attribute.add("last"); attribute.add("last_first");attribute.add("speciChar1");attribute.add("speciChar2");
        ArrayList<ArrayList<String>> values = new ArrayList();
        ArrayList<String> last_value  = new ArrayList(); last_value.add("acdeghi");last_value.add("nstyz");last_value.add("other");//last_value.add("other");
        ArrayList<String> last_first_value  = new ArrayList(); last_first_value.add("abcdfj");last_first_value.add("rsw");last_first_value.add("other");
        ArrayList<String> speciChar1_value  = new ArrayList(); speciChar1_value.add("Y");speciChar1_value.add("N");
        ArrayList<String> speciChar2_value  = new ArrayList(); speciChar2_value.add("Y");speciChar2_value.add("N");
        values.add(last_value); values.add(last_first_value); values.add(speciChar1_value); values.add(speciChar2_value);
        ArrayList<Integer> indexes = new ArrayList();
        indexes.add(1); indexes.add(2); indexes.add(3); indexes.add(4);
        this.root = buildDecisionTree(table, set, MajorError_s, attribute, values, indexes, "");
    }

    public double initialMajorityError(String[] table){
    	int total = table.length, cnt = 0;
        for(String tmp : table){
           if(tmp.charAt(0) == '+')    
           	cnt ++;
        }
        double t1 = 1.0 * cnt / total, t2 = 1.0 * (total - cnt) / total;
        return 1 - Math.max(t1, t2);
    }
    //predict the test data
    public double makeJudgement(){
        NameToFeat nt = new NameToFeat();
        nt.covToFeat("badges-test.txt", "test.txt");
        table = nt.sendToMemory("test.txt", 94);
        int error = 0;
        for(String i : table)
        {
            error = (this.root.DFS(i).charAt(0) == i.charAt(0))?error: error + 1;
           
        }
        System.out.println(error);
        return (1.0*error)/table.length;
    }
    //traversal the decision tree
    public void levelOrder(){
       Queue<decisionTreeNode> qu = new LinkedList();
       qu.offer(this.root);
       int cur1 = 1, cur2 = 0;
       while(!qu.isEmpty()){
        for(int i = 1; i <= cur1; i ++){
        decisionTreeNode p = qu.poll();

        System.out.print(p.attributeName + ":" + p.get_index + ":" + "    ");
        for(String j : p.values)
            System.out.print(j);
        System.out.println();
             for(int k = 0; k < p.children.size(); k++){
                qu.offer(p.children.get(k));
                cur2++;
             }
                if(i == cur1){
                    cur1 = cur2;
                    cur2 = 0;
                    System.out.println();
                    break;
                }       
            }
        }    
    } 
    public static void main(String[] args) {
    	//BuildTree();
    }
}