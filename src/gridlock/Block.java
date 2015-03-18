package gridlock;

import exception.NotAppliableException;

public class Block {

	private Square head;
	private Square tail;
	private boolean horizontal;
	private int id;
	private int size;

	public Block(int id, Square head, Square tail, boolean horizontal, int size) {
		super();
		this.head = head;
		this.tail = tail;
		this.horizontal = horizontal;
		this.size = size;
	}
	
	public Block moveBlock(int moves) {
		if(horizontal) {
			Square newHead = new Square(head.getI(), head.getJ() + moves);
			Square newTail = new Square(tail.getI(), tail.getJ() + moves);
			Block newBlock = new Block(getId(), newHead, newTail, true, getSize());
			return newBlock;
		} else {
			Square newHead = new Square(head.getI() + moves, head.getJ());
			Square newTail = new Square(tail.getI() + moves, tail.getJ());
			Block newBlock = new Block(getId(), newHead, newTail, false, getSize());
			return newBlock;
		}
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
		try {
			return (Block) this.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
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

}
