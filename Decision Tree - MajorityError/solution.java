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

public class solution{

	public static void main(String[] args) {
		decisionTree tree = new decisionTree();
		tree.BuildTree();
		tree.levelOrder();
		System.out.print(tree.makeJudgement());
	}
}