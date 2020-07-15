package wuziqi;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import java.awt.Rectangle;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class GoBangBoardPanel extends JPanel {
	Point curCellAimed = new Point(-1, -1);

	/**
	 * Create the panel.
	 */
	public GoBangBoardPanel() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				curCellAimed = GetPositionCell(e.getPoint());
				if (CellValid(curCellAimed) && GoBangManager.HasChess(curCellAimed) == false) {
					var chess = new Chess(curCellAimed, GoBangManager.putStack.size() % 2 == 0 ? Player.P1 : Player.P2);
					chess.Put();
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				curCellAimed = GetPositionCell(e.getPoint());
				if (CellValid(curCellAimed)) {
					repaint();
				}
			}
		});

		GoBangManager.pnlBoard = this;
		setBackground(Color.ORANGE);
		setPreferredSize(new Dimension(600, 600));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// draw board
		DrawBoard(g);
		
		// draw aiming box
		if (CellValid(curCellAimed)) {
			DrawAimingBox(g, curCellAimed.x, curCellAimed.y);
		}
		
		// draw chess
		for(var chess : GoBangManager.putStack) {
			chess.Draw(g);
		}
	}

	public Rectangle GetBoardRect() {
		final int lineCount = GoBangManager.LineCount;
		var size = getSize();
		return new Rectangle(size.width / (lineCount - 1), size.height / (lineCount - 1),
				size.width * (lineCount - 1) / (lineCount + 1), size.height * (lineCount - 1) / (lineCount + 1));
	}

	public Dimension GetCellSize() {
		final int lineCount = GoBangManager.LineCount;
		var size = GetBoardRect().getSize();
		return new Dimension(size.width / (lineCount - 1), size.height / (lineCount - 1));
	}

	public Point GetCellLocation(int x, int y) {
		var rect = GetBoardRect();
		var gap = GetCellSize();
		var startPoint = rect.getLocation();

		return new Point(startPoint.x + gap.width * x, startPoint.y + gap.height * y);
	}
	
	public Point GetPositionCell(Point pos)
	{
		var rect = GetBoardRect();
		if (rect.contains(pos)) {
			var gap = GetCellSize();
			var relativeLoc = new Point(pos.x - rect.x + gap.width / 2,
					pos.y - rect.y + gap.height / 2);
			return new Point(relativeLoc.x / gap.width, relativeLoc.y / gap.height);
		} else {
			return new Point(-1, -1);
		}
	}
	
	public boolean CellValid(Point cell)
	{
		return cell.x >= 0 && cell.y >= 0;
	}

	void DrawBoard(Graphics g) {
		final int lineCount = GoBangManager.LineCount;

		for (int i = 0; i < lineCount; i++) {
			var p1 = GetCellLocation(0, i);
			var p2 = GetCellLocation(lineCount - 1, i);
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
			g.drawString(Integer.toString(1 + i), p1.x - 14, p1.y + 4);
		}

		for (int i = 0; i < lineCount; i++) {
			var p1 = GetCellLocation(i, 0);
			var p2 = GetCellLocation(i, (lineCount - 1));
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
			g.drawString(Character.toString('A' + i), p1.x - 2, p1.y - 14);
		}

		int radius = 3;

		var point = GetCellLocation(lineCount / 2, lineCount / 2);
		g.fillOval(point.x - radius, point.y - radius, radius * 2, radius * 2);

		point = GetCellLocation(lineCount * 3 / 4, lineCount / 4);
		g.fillOval(point.x - radius, point.y - radius, radius * 2, radius * 2);

		point = GetCellLocation(lineCount / 4, lineCount * 3 / 4);
		g.fillOval(point.x - radius, point.y - radius, radius * 2, radius * 2);

		point = GetCellLocation(lineCount * 3 / 4, lineCount * 3 / 4);
		g.fillOval(point.x - radius, point.y - radius, radius * 2, radius * 2);

		point = GetCellLocation(lineCount / 4, lineCount / 4);
		g.fillOval(point.x - radius, point.y - radius, radius * 2, radius * 2);
	}

	void DrawAimingBox(Graphics g, int x, int y) {
		var point = GetCellLocation(x, y);
		var gap = GetCellSize();

		var oldColor = g.getColor();
		g.setColor(Color.RED);
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				var base = new Point(point.x + gap.width * i / 2, point.y + gap.height * j / 2);

				int[] xPoints = new int[] { base.x, base.x, base.x - gap.width * i / 4 };
				int[] yPoints = new int[] { base.y - gap.height * j / 4, base.y, base.y };
				g.drawPolyline(xPoints, yPoints, 3);
			}
		}
		g.setColor(oldColor);
	}
}
