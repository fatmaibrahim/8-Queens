package queens;

public class Node {

	private int q[][];
	
	private int h;
	 
	private Node parent;

	
	public int[][] getQ() {
		return q;
	}

	public void setQ(int[][] q) {
		this.q = q;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	
}
