# 小马



// 在线面试平台。将链接分享给你的朋友以加入相同的房间。
// Author: tdzl2003<dengyun@meideng.net>

Description
输入n, 表示节点数, 以及若干条边(a, b), 表示a是b的父节点. 判断这些节点是否构成一棵二叉树. 如输入 表示有3个节点, 其中节点0是节点1和节点2的父节点, 3个节点构成了一二叉树.

要求实现函数：

bool IsBinaryTree(int n, const std::vector<std::pair<int, int>>& edges) (C++) 
def is_binary_tree(n, edges) (Python)
Sample Input:
3, (0, 1), (0, 2)
Sample Output:
true


public boolean IsBinaryTree(int n,List<Pair<Integer,Integer>> edges){
  //二叉树，用边表示，每个节点最多一个父节点，两个子节点，因此最多作为左值出现两次，右值出现一次
  //连通规则，边的个数 = n-1
  //节点编号的个数 = 节点的个数  
  //节点编号不一定有规则，
  //考虑环的情况
  //  (0,1),(1,2),(2,0)
  //  (0,1),(1,0)
  //  5 (3,4),(4,3) , (0, 1), (0, 2)
  // 有一个节点，没有父节点
  //连通问题
  //并查集

  if(n -1 != edges.size()){
  	return false;
  }

  Map<Integer,Integer> leftCount=new HashMap<>();
  Map<Integer,Integer> rightCount=new HashMap<>();
  for(Pair<Integer,Integer> edge:edges ){
    int parent = edge.getLeft();
    int child = edge.getRight();
    if(parent == child){
     	return false; 
    }
    
    if(leftCount.containsKey( parent)){
      int parentCount=leftCount.get(parent);
      if(parentCount>2){
       	return false; 
      }
      leftCount.put(parent,parentCount+1);
    }else{
     	leftCount.put(parent, 1); 
    }
    
    if(rightCount.containsKey( child)){
    		int childCount=rightCount.get(child);
      if(child >1){
        return false;
      }else{
       	rightCount.put(child,1); 
      }
    }

  }
  return true;
}

class TreeNode{

  int val;
  TreeNode left;
  TreeNode right;
  public TreeNode(){
    
  }
  public TreeNode(int val){ this.val=val;}


}





--------------

  


Description
给一个长度为2<=n<=10^5的deque，每次操作取出队首两个元素，将较大的放到队尾，较小的放到队首，求第1<=m<=10^18次操作时取出的元素。队列元素可能会重复
求第m次取出的元素，1<=m<=10^18

3,5,2,4,1
3,2,4,1,5
2,4,1,5,3
2,1,5,3,4
1,5,3,4,2	//开始循环
1,3,4,2,5
1,4,2,5,3
1,2,5,3,4
1,5,3,4,2	//循环重复点
1,3,4,2,5
1,3,2,5,3

public List<Integer> getList(Deque<Integer> deque){
  //如果出现多个最小元素，则第一个最小元素会锁定队首，其余的会进入循环
  //Deque<Integer> deque=new LinkedList<>();
	//取出两个队首元素

  //10^18次运算，则对于10^5长度的队列，一定能将最小元素排到对头
  //进入循环点后，除去最小元素，剩余元素均为原始队列中挖去最小元素
  //最小元素位于队首时进入循环，最小元素到队首需要【最小元素下标】次运算。
  //循环步长为元素个数-1


  //判断长度，
  //最小元素不一定能到队首进入循环

 List<Integer> res=new ArrayList<>();
    
  for(int i=0 ; i<Math.min( deque.size(), m) -1 ; i++){
    int first=deque.pop();
    int second=deque.pop();
    if(first >second){
      deque.offer(first);
      deque.push(second);

}else{
  deque.offer(second);
  deque.push(first);
}

  }
  int first=deque.pop();
  int second=deque.pop();

  if(m<n){
    res.add(first);
    res.add(second);

return res;  

  }

  if(first >second){
      deque.offer(first);
      deque.push(second);
  }else{
      deque.offer(second);
      deque.push(first);
  }
  //进入循环
  //循环的尾数
  int  tailRound = m % (n-1);


  int target = deque.pop();
  res.add(target);
  for(int i = tailRound-1 ; i>=0;i++){
  	int tail=deque.pop();
    target=tail;
    deque.offer(tail);
  }

  res.add(target);
  return res;

}



















