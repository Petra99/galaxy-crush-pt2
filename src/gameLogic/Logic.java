package gameLogic;

import gameGUI.Figure;
import gameGUI.GameBoard;

public class Logic {

	private GameBoard board;

	private int currX;
	private int currY;
	private int score;

	// mouseclicks

	// instead of static

	public Logic(GameBoard board) {

		this.currX = -1;
		this.currY = -1;

		this.board = board;
	}

	public void play(int x, int y) {
		if (this.currX == -1 && this.currY == -1) {
			this.currX = x;
			this.currY = y;
		} else {
			if (isMoveLegal(currX, currY, x, y)) {
				swapElements(currX, currY, x, y);
				modifyBoard(currX, currY, x, y);
			}
			currX = -1;
			currY = -1;
		}

	}

	private void modifyBoard(int currX, int currY, int x, int y) {

		for (int row = 0; row < GameBoard.getBoard_height(); row++) {
			for (int col = 0; col < GameBoard.getBoard_width(); col++) {

				checkIfDownMatch(row, col);
				checkIfUpMatch(row, col);
				checkIfLeftMatch(row, col);
				checkIfRightMatch(row, col);

				if (checkIfDownMatch(row, col) || checkIfUpMatch(row, col) || checkIfLeftMatch(row, col)
						|| checkIfRightMatch(row, col)) {
					board.reorder();
					board.refill();
				} else {
					swapElements(x, y, currX, currY);
				}

			}
		}
	}

	private boolean checkIfDownMatch(int x, int y) {
		int counter = 1;
		while (x <= 5 && board.getPlanetAt(x, y).equals(board.getPlanetAt(x + 1, y))) {
			counter++;
		}
		if (checkCounter(counter)) {
			getScore(counter);
			for (int row = x; row < x + counter - 1; row++) {
				board.setPlanetAt(row, y, null);
			}
			return true;
		} else {
			return false;
		}

	}

	private boolean checkIfUpMatch(int x, int y) {
		int counter = 0;
		while (x > 0 && board.getPlanetAt(x, y).equals(board.getPlanetAt(x - 1, y))) {
			x--;
			counter++;
		}
		if (checkCounter(counter)) {
			getScore(counter);
			for (int row = x - counter + 1; row < x; row++) {
				board.setPlanetAt(row, y, null);
			}
			return true;
		}
		return false;
	}

	private boolean checkIfLeftMatch(int x, int y) {
		int counter = 0;
		while (y > 0 && board.getPlanetAt(x, y).equals(board.getPlanetAt(x, y - 1))) {
			y--;
			counter++;
		}
		if (checkCounter(counter)) {
			getScore(counter);
			for (int col = y - counter + 1; col < y; col++) {
				board.setPlanetAt(x, col, null);
			}
			return true;
		}
		return false;
	}

	private boolean checkIfRightMatch(int x, int y) {
		int counter = 0;
		while (y < 5 && board.getPlanetAt(x, y).equals(board.getPlanetAt(x, y + 1))) {
			y++;
			counter++;
		}
		if (checkCounter(counter)) {
			getScore(counter);
			for (int col = y - counter + 1; col < y; col++) {
				board.setPlanetAt(x, col, null);
			}
			return true;
		}
		return false;
	}

	private boolean checkCounter(int counter) {
		if (counter >= 3) {
			return true;
		} else {
			return false;
		}

	}

	private void swapElements(int currX, int currY, int x, int y) {
		Figure currFigure = board.getPlanetAt(currX, currY);
		Figure nextFigure = board.getPlanetAt(x, y);
		Figure tempFigure = currFigure;
		currFigure = nextFigure;
		nextFigure = tempFigure;
		System.out.println(currX);
		System.out.println(currY);
		System.out.println(x);
		System.out.println(y);

	}

	private static boolean isMoveLegal(int currx, int curry, int x, int y) {
		if (Math.abs(currx - x) == 1) {
			if (curry == y) {
				return true;
			}
		} else if (Math.abs(curry - y) == 1) {
			if (currx == x) {
				return true;
			}
		}
		return false;

	}

	public int getScore(int counter) {

		// GameBoard.getlblScoreHolder().setText("" + counter*10);
		return score;
	}

	// public static void checkStartBoard() {
	// for (int x = 1; x < GameBoard.getBoard_height() - 1; x++) {
	// for (int y = 0; y < GameBoard.getBoard_width(); y++) {
	// if (board[x][y].getIconPath() == (GameBoard.board[x -
	// 1][y].getIconPath())) {
	// ImageIcon icon = (ImageIcon) GameBoard.board[x][y].getIconPath();
	// GameBoard.board[x][y].setIconPath(GameBoard.board[x -
	// 1][y].getIconPath());
	// GameBoard.board[x - 1][y].setIconPath(icon);
	// }
	// }
	// }
	//
	// }

}
