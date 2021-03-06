package gridlock;

import exception.InvalidBlockException;

public class Block {

	private Square head;
	private Square tail;
	private boolean horizontal;
	private int id;
	private int size;

	public Block(int id, Square head, Square tail, boolean horizontal, int size) {
		super();
		this.id = id;
		this.head = head;
		this.tail = tail;
		this.horizontal = horizontal;
		this.size = size;

		if (horizontal) {
			if (head.getJ() <= tail.getJ() || head.getI() != tail.getI())
				throw new InvalidBlockException();
		} else {
			if (head.getI() <= tail.getI() || head.getJ() != tail.getJ())
				throw new InvalidBlockException();
		}
	}

	// public Block moveBlock(int moves) {
	// if (horizontal) {
	// Square newHead = new Square(head.getI(), head.getJ() + moves);
	// Square newTail = new Square(tail.getI(), tail.getJ() + moves);
	// Block newBlock = new Block(getId(), newHead, newTail, true,
	// getSize());
	// return newBlock;
	// } else {
	// Square newHead = new Square(head.getI() + moves, head.getJ());
	// Square newTail = new Square(tail.getI() + moves, tail.getJ());
	// Block newBlock = new Block(getId(), newHead, newTail, false,
	// getSize());
	// return newBlock;
	// }
	// }

	public Block moveBlock(int moves) {
		Square newHead = getNewSquare(head, moves);
		Square newTail = getNewSquare(tail, moves);
		return new Block(getId(), newHead, newTail, horizontal, getSize());
	}

	private Square getNewSquare(Square aux, int moves) {
		return (horizontal == true) ? new Square(aux.getI(), aux.getJ() + moves)
				: new Square(aux.getI() + moves, aux.getJ());
	}

	public Square getHead() {
		return head;
	}

	public Square getTail() {
		return tail;
	}

	public boolean isSamePosition(Block other) {
		if (head.getI() != other.getHead().getI()
				|| head.getJ() != other.getHead().getJ()
				|| tail.getI() != other.getTail().getI()
				|| tail.getJ() != other.getTail().getJ())
			return false;
		return true;
	}

	public Block copyBlock() {
		return cloneBlock();
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public int getId() {
		return id;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "Block [id=" + id + ", head=" + head + ", tail=" + tail
				+ ", horizontal=" + horizontal + ", id=" + ", size=" + size
				+ "]";
	}

	public Block cloneBlock() {
		return new Block(id, new Square(head.getI(), head.getJ()), new Square(
				tail.getI(), tail.getJ()), horizontal, size);
	}

}
