package queens;

import java.awt.List;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class QueensBoard {

	public static int TOTAL_QUEENS = 8;
	public char[][] board;
	private int[] queenPositions;
	private int cost=0;
	private int expanded=0;
		

	public QueensBoard(char[][] board, int[] positions) {

		this.board = board;
		this.queenPositions = positions;

		// TODO Auto-generated constructor stub
	}

	public char[][] readBoard() throws FileNotFoundException{
		char[][]state=new char[8][8];
		Scanner sc = new Scanner(new BufferedReader(new FileReader("board.txt")));
	      while(sc.hasNextLine()) {
	         for (int i=0; i<state.length; i++) {
	            String line = sc.nextLine();
	            int k=0;
	            for (int j = 0; j < line.length(); j++) {
	            	if(line.charAt(j)==' '){
	            		j++;
	            	}
	               state[i][k] = line.charAt(j);
	               k++;
	            }
	         }
	      }
	      return state;
	}
	public int getH(char[][]state){
		int h=0;
		// check in rows 
		for (int i = 0; i < state.length; i++) {
			ArrayList<Boolean> pairs = new ArrayList<Boolean>();
			for (int j = 0; j < state[i].length; j++) {

				if (state[i][j] == 'Q') {
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
				if (state[row+j][0+j] == 'Q') {
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
				if (state[0+j][col+j] == 'Q') {
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
				if (state[0+j][i-j] == 'Q') {
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
				if (state[row1+j][col1-j] == 'Q') {
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

				if (board[j][i] == 'Q') {
					pairs.add(true);
				}

			}
			if (pairs.size() != 0){
				for (int j = 1; j <pairs.size() ; j++) {
					h += pairs.size()-j;
				}
			}
				
		}
		System.out.println("h = "+h);
		
		return h;
		
	}
	// only one queen per col
	public void queenPerCol(char [][] state){
		int row[]=new int[8];
		Random random = new Random();
		int queens[]= new int[8] ;
		int flag=0;
		for (int i = 0; i < state.length; i++) {
			int count =0;
			for (int j = 0; j < state[i].length; j++) {
				if(state[j][i]=='Q'){
					if(flag==1){
						state[j][i]='#';
					}
					count++;
					flag=1;
				}
			}
			flag=0;
			queens[i]=count ;
		}
		for (int i = 0; i < queens.length; i++) {
			if(queens[i]>1){
				for (int j = 0; j < queens.length; j++) {
					if(queens[j]==0){
						state[random.nextInt(8)][j]='Q';
						queens[j]++;
						break;
					}
				}
				queens[i]--;
				i--;
			}
		}
		for(int i=0;i<8;i++){
			for(int j=0;j<8;j++){
				if(board[j][i]=='Q'){
					queenPositions[i]=j;
				}
			}
		}
	}
	
	public void moveQueen(int row, int col) {

		// original queen will become a 2 and act as a marker
		board[queenPositions[col]][col] = '*';

		board[row][col] = 'Q';

	}

	
	// random restart
	private int[] generateQueens() {

		ArrayList<Integer> randomPos = new ArrayList<Integer>();

		Random r = new Random();
		for (int i = 0; i < TOTAL_QUEENS; i++) {
			randomPos.add(r.nextInt(8));
		}

		int[] randomPositions = new int[TOTAL_QUEENS];

		for (int i = 0; i < randomPos.size(); i++) {
			randomPositions[i] = randomPos.get(i);
		}

		return randomPositions;
	}
	public void resetQueen(int row, int col) {

		if (board[row][col] == 'Q')
			board[row][col] = '#';
	}


	public void placeQueens() {

		queenPositions = generateQueens();

		for (int i = 0; i < board.length; i++) {
			board[queenPositions[i]][i] = 'Q';
		}

	}
	public void resetBoard(int col) {

		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == '*')
				board[i][col] = 'Q';
		}
	}
	public void placeBestQueen(int col, int queenPos) {

		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == 'Q')
				board[i][col] = '*';

		}
		board[queenPos][col] = 'Q';
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == '*')
				board[i][col] = '#';

		}
	}
	public void printBoard() {

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	
	public void hillCliming() throws IOException{
		board= readBoard();
		long start = System.currentTimeMillis();
		boolean climb = true;
		int climbCount = 0;
		readBoard();
		System.out.println("Givin Board");
		printBoard();
		
		queenPerCol(board);
		// 5 restarts
		while (climb) {
			//QueensBoard board = new QueensBoard(new char[TOTAL_QUEENS][TOTAL_QUEENS], new int[8]);
			if(climbCount>1){
				System.out.println("Stucked in local max random restart will begin");
				placeQueens();
			}
			// randomly place queens
			//placeQueens();
			System.out.println("Trial #: " + (climbCount+1));
			System.out.println("Original board:");
			printBoard();
			System.out.println("# pairs of queens attacking each other: "+ getH(board) + "\n");
			// score to be compared against
			int localMin = getH(board);
			boolean best = false;
			// array to store best queen positions by row (array index is column)
			int[] bestQueenPositions = new int[8];
			// iterate through each column 
			for (int j = 0; j < board.length; j++) {
				System.out.println("Iterating through COLUMN " + j + ":");
				best = false;
				//  iterate through each row
				for (int i = 0; i < board.length; i++) {
					cost++;
					// skip score calculated by original board
					if (i != queenPositions[j]) {
						
						// move queen 
						moveQueen(i, j);
						printBoard();
						System.out.println();
						// calculate score, if best seen then store queen position
						if (getH(board) < localMin) {
							best = true;
							localMin = getH(board);
							bestQueenPositions[j] = i;
						}
						// reset to original queen position
						resetQueen(i, j);

					}
				}
				
				// change 2 back to 1
				resetBoard(j);
				if (best) {
					// if a best score was found, place queen in this position
					placeBestQueen(j, bestQueenPositions[j]);
					System.out.println("Best board found this iteration: ");
					printBoard();
					System.out.println("# pairs of queens attacking each other: "+ getH(board) + "\n");
					expanded++;
				} else {
					System.out.println("No better board found.");
					printBoard();
					System.out.println("# pairs of queens attacking each other: "+ getH(board) + "\n");
					expanded++;
				}
			}

			// if score = 0, hill climbing has solved problem
			if (getH(board) == 0)
				climb = false;

			climbCount++;

			// only 5 restarts
			if (climbCount == 1000) {
				climb = false;
			}
			System.out.println("Done in " + (climbCount-1) + " restarts.");
		}
		long end = System.currentTimeMillis();
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.println("Execution time is " + formatter.format((end - start) / 1000d) + " seconds");
		System.out.println("Cost = "+cost);
		System.out.println("Number of expanded Nodes = "+ expanded);
		write(board) ;

	}
	private void write(char state[][]) throws IOException{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < state.length; i++)//for each row
		{
		   for(int j = 0; j < state.length; j++)//for each column
		   {
		      builder.append(state[i][j]+"");//append to the output string
		      if(j < state.length - 1)//if this is not the last row element
		         builder.append(" ");//then add comma (if you don't like commas you can use spaces)
		   }
		   builder.append("\n");//append new line at the end of the row
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
		writer.write(builder.toString());//save the string representation of the board
		writer.close();
	}

}
