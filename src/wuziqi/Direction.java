package wuziqi;

import java.awt.Point;

public enum Direction {
	North(new Point(0, -1)), // South(new Point(0, 1)),
	West(new Point(-1, 0)), // East(new Point(1, 0)),
	NorthWest(new Point(-1, -1)), // NorthEast(new Point(1, -1)),
	SouthWest(new Point(-1, 1)), // SouthEast(new Point(1, 1))
	;

	public Point offset;

	Direction(Point offset) {
		this.offset = offset;
	}

}
