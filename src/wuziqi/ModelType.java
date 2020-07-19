package wuziqi;

public enum ModelType {
	CON_5("����", 0, new String[] { "11111", "22222" }, 100000),
	ALIVE_4("����", 1, new String[] { "011110", "022220" }, 10000),
	GO_4("����", 2, new String[] { "011112|0101110|0110110", "022221|0202220|0220220" }, 500),
	DEAD_4("����", 3, new String[] { "211112", "122221" }, -5),
	ALIVE_3("����", 4, new String[] { "01110|010110", "02220|020220" }, 200),
	SLEEP_3("����", 5,
			new String[] { "001112|010112|011012|10011|10101|2011102", "002221|020221|022021|20022|20202|1022201" },
			50),
	DEAD_3("����", 6, new String[] { "21112", "12221" }, -5),
	ALIVE_2("���", 7, new String[] { "00110|01010|010010", "00220|02020|020020" }, 5),
	SLEEP_2("�߶�", 8,
			new String[] { "000112|001012|010012|10001|2010102|2011002", "000221|002021|020021|20002|1020201|1022001" },
			3),
	DEAD_2("����", 9, new String[] { "2112", "1221" }, -5), NULL("null", 10, new String[] { "", "" }, 0);

	public String name;
	public int index;
	public String[] regex;// ������ʽ
	public int score;// ��ֵ

	// ���췽��
	private ModelType(String name, int index, String[] regex, int score) {
		this.name = name;
		this.index = index;
		this.regex = regex;
		this.score = score;
	}

	// ���Ƿ���
	@Override
	public String toString() {
		return this.name;
	}
}
