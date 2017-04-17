package gameLogic;

import gameGUI.GameBoard;
import gameGUI.GameWindow;
import gameGUI.OnUserAction;

public class Manager {

	OnUserAction listener = new OnUserAction() {
		
		@Override
		public void onButtonClicked(int x, int y) {
			logic.play(x, y);
		}
	};
	
	GameWindow window = new GameWindow(GameBoard.getBoard_height(), GameBoard.getBoard_width());
	GameBoard board = new GameBoard();
	Logic logic = new Logic(board);
	
	public void runGame(){
		board.runBoard(board);
	}
	
	
}
