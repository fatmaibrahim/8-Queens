package queens;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;


public class BeamSearch {
	private int numOfQueen=8;
	private int numMove=8;
	private int maxiteration=1000;

	private LinkedList<int [][]> visitedNode=new LinkedList<int [][]>();
	
	public void RunAlgorithm(int k) {
		int[][] initialState = null;
		try {
			initialState = readBoard();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(k<1) {
			return;
		}
		
		/**Create an empty PriorityQueue**/
		long starttime = System.currentTimeMillis();
		 PriorityQueue<Node> pq = new 
	             PriorityQueue<Node>(new NodeComparator()); 
		 
		 Node start=new Node();
		 start.setQ(initialState);
		 start.setParent(null);
		 start.setH(getH(initialState));
		 /** insert start node in priority queue**/
		 pq.add(start);
		 visitedNode.add(GetPositionOfQueen(initialState));
		 /**Until PriorityQueue is empty **/
		 Node u=null;
		 int NumofExpandedNode=0;
		 boolean complete=false;
		 while(!pq.isEmpty()) {
			  u=pq.poll();
			  printBoard(u.getQ());
			  System.out.println("h = "+u.getH());
			  NumofExpandedNode++;
			  if(NumofExpandedNode==maxiteration) {
				  break;
			  }
			  if(u.getH()==0) {   //is goal
				 complete=true;
				break; 
			 }else {
				 int [][]postionQueen=GetPositionOfQueen(u.getQ());
				 for(int i=0;i<postionQueen.length;i++) {
					for(int j=0;j<numMove;j++) {
						Node child=AddChildNode(postionQueen[i][0],postionQueen[i][1],j,u);
						if(child!=null) {
							if(!visited(child.getQ())) {    // if not visited add to priority queue
								pq.add(child);	
								visitedNode.add(GetPositionOfQueen(child.getQ()));
								if(k<pq.size()) {
									RemoveLastElementINPriorityQueue(pq);
								}
							}
						}
					}
				 }
			 }
			 
		 }
		 
		 long endtime = System.currentTimeMillis();
		 if(complete) {
			 System.out.println("the beam search algorithm reach to the goal !!");
		 }else {
			 System.out.println("the beam search algorithm can't find the goal it reach the max iteration:"+maxiteration);
		 }
		 double t=(endtime - starttime) / 1000;
		 System.out.println("Execution time is " + t+ " seconds");
		 System.out.println("Final Board: ");
		 printBoard(u.getQ());
		 if(complete) {
		 System.out.println("The cost to the final board: "+GetCost(u));
		 }
		 System.out.println("Number of expanded nodes: "+NumofExpandedNode);
		 
		 
		 
	}
	
	
	private int GetCost(Node u) {
		if(u==null) {
			return 0;
		}
		return 1+GetCost(u.getParent());
	}
	public void printBoard(int[][] q) {
			for(int i=0;i<q.length;i++) {
				for(int j=0;j<q.length;j++) {
					if(q[i][j]==1) {
						System.out.print(" Q");
					}else {
						System.out.print(" #");
					}
				}
				System.out.println();
			}
	}
	void RemoveLastElementINPriorityQueue(PriorityQueue<Node> pQueue) {
		Iterator<Node> itr = pQueue.iterator(); 
		Object last = null;
		while (itr.hasNext()) 
            last=itr.next(); 
	
		pQueue.remove(last);
	}
	private boolean visited(int[][] q) {
		int [][] state=GetPositionOfQueen(q);
		for(int i=0;i<visitedNode.size();i++) {
			if(StateEqual(state,visitedNode.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	private boolean StateEqual(int[][] state, int[][] is) {
		for(int i=0;i<state.length;i++) {
			boolean flag=false;
			for(int j=0;j<is.length;j++) {
				if(state[i][0]==is[j][0]&&state[i][1]==is[j][1]) {
					flag=true;
					break;
				}
			}
			if(!flag) {
				return false;
			}
		}
		return true;
	}
	
	Node AddChildNode(int i,int j,int move,Node parent) {
		
		switch(move) {
		case 0:
			if(CheckNewPosition(i+1,j+1,parent.getQ())) {    //move vertically left
				Node child=new Node();
			  	 child.setParent(parent);
			  	int [][] q=CopyMatrix(parent.getQ());
				q[i][j]=0;
			  	q[i+1][j+1]=1;
			  	child.setQ(q);
				 child.setH(getH(q)); 
			  	 return child;
			}
			break;
		case 1:
			if(CheckNewPosition(i-1,j+1,parent.getQ())) {    //move vertically left
				Node child=new Node();
			  	 child.setParent(parent);
			  	int [][] q=CopyMatrix(parent.getQ());
				q[i][j]=0;
			  	q[i-1][j+1]=1;
			  	child.setQ(q);
				 child.setH(getH(q)); 
			  	 return child;
			}
			break;
		case 2:
			if(CheckNewPosition(i,j+1,parent.getQ())) {    //move vertically left
				Node child=new Node();
			  	 child.setParent(parent);
			  	int [][] q=CopyMatrix(parent.getQ());
				q[i][j]=0;
			  	q[i][j+1]=1;
			  	child.setQ(q);
				 child.setH(getH(q)); 
			  	 return child;
			}
			break;
		case 3:
			if(CheckNewPosition(i+1,j-1,parent.getQ())) {    //move vertically left
				Node child=new Node();
			  	 child.setParent(parent);
			  	int [][] q=CopyMatrix(parent.getQ());
				q[i][j]=0;
			  	q[i+1][j-1]=1;
			  	child.setQ(q);
				 child.setH(getH(q)); 
			  	 return child;
			}
			break;
			
		case 4:
			if(CheckNewPosition(i,j-1,parent.getQ())) {    //move  horizontally left
				Node child=new Node();
			  	 child.setParent(parent);
			  	int [][] q=CopyMatrix(parent.getQ());
				q[i][j]=0;
			  	q[i][j-1]=1;
			  	child.setQ(q);
				 child.setH(getH(q)); 
			  	 return child;
			}
			break;
		case 5:
			if(CheckNewPosition(i-1,j-1,parent.getQ())) {    //move vertically left
				 Node child=new Node();
			  	 child.setParent(parent);
			  	int [][] q=CopyMatrix(parent.getQ());
				q[i][j]=0;
				q[i-1][j-1]=1;
			  	child.setQ(q);
				 child.setH(getH(q)); 
			  	 return child;
			}
			break;
			
		case 6:
			
			if(CheckNewPosition(i+1,j,parent.getQ())) {    //move vertically right
				Node child=new Node();
			  	 child.setParent(parent);
			  	int [][] q=CopyMatrix(parent.getQ());
				q[i][j]=0;
			  	q[i+1][j]=1;
			  	child.setQ(q);
				  child.setH(getH(q)); 
			  	 return child;
			}
			break;
		case 7:
			if(CheckNewPosition(i-1,j,parent.getQ())) {    //move vertically left
				Node child=new Node();
			  	 child.setParent(parent);
			  	int [][] q=CopyMatrix(parent.getQ());
				q[i][j]=0;
			  	q[i-1][j]=1;
			  	child.setQ(q);
				 child.setH(getH(q)); 
			  	 return child;
			}
			break;
			
		}
		
	  	return null;
	  	 
	}
	
	private int[][] CopyMatrix(int[][] q) {
		int [][] res=new int[q.length][q.length];
		for(int i=0;i<q.length;i++) {
			for(int j=0;j<q.length;j++) {
				res[i][j]=q[i][j];
			}
		}
		return res;
	}


	boolean CheckNewPosition(int i,int j,int [][]q) {
		if(i<0||i>=numOfQueen||j<0||j>=numOfQueen) { // check board
			return false;
		}
		if(q[i][j]==1) {                                // check no queen there position
			return false;
		}
		return true;
	}
	
	int[][] GetPositionOfQueen(int [][] q){
		int position[][]=new int [q.length][2];
		int index=0;
		for(int i=0;i<q.length;i++) {
			for(int j=0;j<q.length;j++) {
				if(q[i][j]==1) {
					position[index][0]=i;
					position[index][1]=j;
					index++;
				}
			}
		}
		return position;
	}
	/*
	public int getH(int[][]state){
		int h=0;
		// check in rows 
		for (int i = 0; i < state.length; i++) {
			ArrayList<Boolean> pairs = new ArrayList<Boolean>();
			for (int j = 0; j < state[i].length; j++) {

				if (state[i][j] == 1) {
					pairs.add(true);
				}

			}
			if (pairs.size() != 0){
				for (int j = 1; j <pairs.size() ; j++) {
					h += pairs.size()-j;
				}
			}
				
		}
		//check diagonals 
		// part 1 : down left corner to main diagonal
		int row=7;
		for (int i = 0; i < 8; i++) {
			ArrayList<Boolean> pairs = new ArrayList<Boolean>();
			
			for (int j = 0; j <= i; j++) {
				if (state[row+j][0+j] == 1) {
					pairs.add(true);
				}
			}
			row--;
			if (pairs.size() != 0){
				for (int j = 1; j <pairs.size() ; j++) {
					h += pairs.size()-j;
				}
			}
		}
		//upper part of board
		int index=7;
		for (int i = 1; i < 8; i++) {
			ArrayList<Boolean> pairs = new ArrayList<Boolean>();
			int col=1;
			for (int j = 0; j < index; j++) {
				if (state[0+j][col+j] == 1) {
					pairs.add(true);
				}
				
			}
			if (pairs.size() != 0){
				for (int j = 1; j <pairs.size() ; j++) {
					h += pairs.size()-j;
				}
			}
			col++;
			index--;
		}
		//check back diagonals
		for (int i = 0; i < 8; i++) {
			ArrayList<Boolean> pairs = new ArrayList<Boolean>();
			for (int j = 0; j <=i ; j++) {
				if (state[0+j][i-j] == 1) {
					pairs.add(true);
				}
			}
			if (pairs.size() != 0){
				for (int j = 1; j <pairs.size() ; j++) {
					h += pairs.size()-j;
				}
			}
		}
		//  down stream
		int row1 =1;
		int col1=7;
		for (int i = 7; i >0; i--) {
			ArrayList<Boolean> pairs = new ArrayList<Boolean>();
			for (int j = 0; j <i; j++) {
				if (state[row1+j][col1-j] == 1) {
					pairs.add(true);
				}
			}
			if (pairs.size() != 0){
				for (int j = 1; j <pairs.size() ; j++) {
					h += pairs.size()-j;
				}
			}
			row1++;
		}
		
		//check for columns
		for (int i = 0; i < state.length; i++) {
			ArrayList<Boolean> pairs = new ArrayList<Boolean>();
			for (int j = 0; j < state[i].length; j++) {

				if (state[j][i] == 1) {
					pairs.add(true);
				}

			}
			if (pairs.size() != 0){
				for (int j = 1; j <pairs.size() ; j++) {
					h += pairs.size()-j;
				}
			}
				
		}
		
		return h;
		
	}
	*/
	public int getH(int [][]state) {
		int[][]a=GetPositionOfQueen(state);
		int h=0;
		for(int i=0;i<a.length;i++) {
			for(int j=i+1;j<a.length;j++) {
			if(a[i][0]==a[j][0]){
				h++;
			}
			if(a[i][1]==a[j][1]) {
				h++;
			}
			if(abs(a[i][0]-a[j][0])==abs(a[i][1]-a[j][1])) {
				h++;
			}
			}
		}
		return h;
	}
	private int abs(int i) {
		if(i<0)
			return i*-1;
		return i;
	}


	public int[][] readBoard() throws FileNotFoundException{
		int[][]state=new int[numOfQueen][numOfQueen];
		Scanner sc = new Scanner(new BufferedReader(new FileReader("board")));
	      while(sc.hasNextLine()) {
	         for (int i=0; i<state.length; i++) {
	            String line = sc.nextLine();
	            int k=0;
	            for (int j = 0; j < line.length(); j++) {
	            	if(line.charAt(j)==' '){
	            		continue;
	            	}
	            	if(line.charAt(j)=='Q') {
	            		state[i][k] = 1;
		  	               k++;
	            	}else if(line.charAt(j)=='#') {
	            		state[i][k] = 0;
		  	               k++;
	            	}
	            }
	         }
	      }
	      return state;
	}
}
class NodeComparator implements Comparator<Node>{ 
    
    // Overriding compare()method of Comparator  

	@Override
	public int compare(Node o1, Node o2) {
		if (o1.getH() > o2.getH()) 
            return 1; 
        else  if(o1.getH() < o2.getH())
        	return -1;
		
		return 0;
	}
		
}

