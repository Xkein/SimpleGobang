package wuziqi;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Chess {
	static final int radius = 10;
	Point location;
	Player owner;
	int index;

	public Chess(Point loc, Player player) {
		location = loc;
		owner = player;
	}

	public void Put() {
		GoBangManager.chesses[location.x][location.y] = this;
		GoBangManager.putStack.push(this);
		index = GoBangManager.putStack.size();
		GoBangManager.pnlBoard.repaint();
	}

	public void Remove() {
		GoBangManager.chesses[location.x][location.y] = null;
		GoBangManager.putStack.remove(this);
		GoBangManager.pnlBoard.repaint();
	}

	public void Draw(Graphics g) {
		var loc = GoBangManager.pnlBoard.GetCellLocation(location.x, location.y);

		var oldColor = g.getColor();
		g.setColor(owner == Player.P1 ? Color.BLACK : Color.WHITE);
		g.fillOval(loc.x - radius, loc.y - radius, radius * 2, radius * 2);

		if (GoBangManager.showChessIndex) {
			g.setColor(Color.RED);
			g.drawString(String.valueOf(index), loc.x - 4, loc.y + 4);
		}

		if (this == GoBangManager.putStack.lastElement()) {
			g.setColor(Color.RED);
			g.fillRect(loc.x - radius / 4, loc.y - radius / 4, radius / 2, radius / 2);
		}

		g.setColor(oldColor);
	}
}
