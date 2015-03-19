package gridlock;

public class Square {
	private int i;
	private int j;

	public Square(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	@Override
	public String toString() {
		return "Square [i=" + i + ", j=" + j + "]";
	}

}
