package boards;

import gridlock.Block;
import gridlock.Square;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Board Format
 * 
 * 
 * square_i square_j
 * head_i head_j tail_i tail_j isHorizontal id size
 */

public class BoardParser {

	public static Board parseFile(String path) {
		File file = new File(path);
		Square finalSquare = null;
		int[][] board = new int[6][6];
		List<Block> blocks = new ArrayList<Block>();
		// Board board = new Board(matriz, list, finalSquare)
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			// Read final square
			if ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				int i = Integer.parseInt(split[0]);
				int j = Integer.parseInt(split[1]);
				finalSquare = new Square(i, j);
			}
			// Read Blocks
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				Square head = new Square(Integer.parseInt(split[0]),
						Integer.parseInt(split[1]));
				Square tail = new Square(Integer.parseInt(split[2]),
						Integer.parseInt(split[3]));
				boolean horizontal = Integer.parseInt(split[4]) == 1 ? true
						: false;
				int id = Integer.parseInt(split[5]);
				int size = Integer.parseInt(split[6]);
				blocks.add(new Block(id, head, tail, horizontal, size));
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: Failed to load board. File not found.");
		} catch (IOException e) {
			System.out.println("ERROR: Failed to load board. File has errors.");
		}

		// Complete Matrix
		for (Block b : blocks) {
			if (b.isHorizontal()) {
				for (int j = b.getTail().getJ(); j <= b.getHead().getJ(); j++) {
					board[b.getTail().getI()][j] = b.getId();
				}
			} else {
				for (int i = b.getTail().getI(); i <= b.getHead().getI(); i++) {
					board[i][b.getTail().getJ()] = b.getId();
				}
			}
		}

		return new Board(board, blocks, finalSquare);
	}

}
