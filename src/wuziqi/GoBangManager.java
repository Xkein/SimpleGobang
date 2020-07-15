package wuziqi;

import java.awt.Point;
import java.util.Stack;

public class GoBangManager {
	static final int LineCount = 15;
	static GoBangBoardPanel pnlBoard;
	static Chess[][] chesses = new Chess[LineCount][LineCount];
	static Stack<Chess> putStack = new Stack<Chess>();
	
	static boolean HasChess(Point point)
	{
		return chesses[point.x][point.y] != null;
	}
	
	
	static Player GetWinner()
	{
		Chess lastChess = putStack.lastElement();
		var loc = lastChess.location;


		
		return Player.None;
	}
	
	static void StartNewGame()
	{
		chesses = new Chess[LineCount][LineCount];
		putStack.clear();
		
		pnlBoard.repaint();
	}
}
