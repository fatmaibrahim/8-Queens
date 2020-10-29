package queens;

import java.io.FileNotFoundException;
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		QueensBoard board = new QueensBoard(new char[8][8], new int[8]);
		board.hillCliming();
		
		
	}

}
