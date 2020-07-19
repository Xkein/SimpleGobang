package wuziqi;

public enum Player {
	P1("ºÚ·½"), P2("°×·½"), None("");

	String name;

	Player(String name) {
		this.name = name;
	}

	public int GetValue() {
		switch (this) {
		case P1:
			return 1;
		case P2:
			return 2;
		default:
			return 0;
		}
	}
	
	public Player GetEnemy()
	{
		switch (this) {
		case P1:
			return P2;
		case P2:
			return P1;
		default:
			return None;
		}
	}

}
