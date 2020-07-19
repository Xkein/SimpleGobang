package wuziqi;

import java.awt.Point;
import java.util.Stack;

import javax.swing.JOptionPane;

public class GoBangManager {
	static final int LineCount = 15;
	static GoBangBoardPanel pnlBoard;
	static GoBangMenuPanel pnlMenu;
	static Chess[][] chesses = new Chess[LineCount][LineCount];
	static Stack<Chess> putStack = new Stack<Chess>();

	static boolean showChessIndex = false;
	static boolean gameOver = false;
	static Mode mode;
	static AI ai = new AI();
	static boolean p1First;

	static boolean HasChess(Point point) {
		return CellValid(point) && chesses[point.x][point.y] != null;
	}

	static Player GetWinner() {
		Chess lastChess = putStack.lastElement();
		var loc = lastChess.location;
		var owner = lastChess.owner;

		for (Direction dir : Direction.values()) {
			Point offset = dir.offset;
			int count = 1;
			for (int i = 1; i < 5; i++) {
				var nextLoc = new Point(loc.x + offset.x * i, loc.y + offset.y * i);
				if (HasChess(nextLoc) && chesses[nextLoc.x][nextLoc.y].owner == owner) {
					count++;
				} else
					break;
			}
			for (int i = 1; i < 5; i++) {
				var nextLoc = new Point(loc.x + offset.x * -i, loc.y + offset.y * -i);
				if (HasChess(nextLoc) && chesses[nextLoc.x][nextLoc.y].owner == owner) {
					count++;
				} else
					break;
			}

			if (count >= 5) {
				return owner;
			}

		}

		return Player.None;
	}

	static void Undo() {
		if (mode == Mode.PVE) {
			if (putStack.size() > 1) {
				putStack.lastElement().Remove();
				putStack.lastElement().Remove();
			}
		} else if (putStack.size() > 0) {
			putStack.lastElement().Remove();
		}
		gameOver = false;
	}

	static void StartNewGame(Mode mode, AI.Algorithm algorithm, boolean p1First, int treeDepth, int treeNodeCount) {
		chesses = new Chess[LineCount][LineCount];
		putStack.clear();
		gameOver = false;
		GoBangManager.mode = mode;
		GoBangManager.p1First = p1First;
		ai.algorithm = algorithm;
		ai.treeDepth = treeDepth;
		ai.treeNodeCount = treeNodeCount;
		ai.player = p1First ? Player.P2 : Player.P1;

		if (p1First == false && mode == Mode.PVE) {
			ai.PutChess();
		}

		pnlBoard.repaint();
	}

	static boolean CellValid(Point cell) {
		return cell.x >= 0 && cell.y >= 0 && cell.x < LineCount && cell.y < LineCount;
	}

	static void PutChess(Point cell) {
		var chess = new Chess(cell, putStack.size() % 2 == 0 ? Player.P1 : Player.P2);
		chess.Put();

		CheckWinner();

		if (gameOver == false && Mode.PVE == mode) {
			ai.PutChess();
			CheckWinner();
		}
	}

	static void CheckWinner() {
		var winner = GetWinner();

		if (winner != Player.None) {
			gameOver = true;
			JOptionPane.showMessageDialog(pnlBoard, "ЪЄеп: " + winner.name);
		}
	}

}
