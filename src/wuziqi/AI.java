package wuziqi;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AI {
	public enum Algorithm {
		EstimateFunction, EstimateFunction_Tree
	}

	Algorithm algorithm;
	Player player;

	int treeDepth;
	int treeNodeCount;

	Chess chessBeansForTree;

	private static int[][] position;

	AI() {
		final var lineCount = GoBangManager.LineCount;
		position = new int[lineCount][lineCount];

		for (int i = 0; i < lineCount; i++) {
			for (int j = i; j < lineCount - i; j++) {
				position[i][j] = i;
				position[j][i] = i;
				position[lineCount - i - 1][j] = i;
				position[j][lineCount - i - 1] = i;
			}
		}
	}

	void PutChess() {
		if (GoBangManager.gameOver) {
			return;
		}

		Chess chess = null;

		switch (algorithm) {
		case EstimateFunction:
			chess = getSortList(player, GoBangManager.chesses).get(0);
			break;
		case EstimateFunction_Tree:
			// chess = GetScoreByTree(0, player, GoBangManager.chesses);
			GetScoreByTree(0, player, GoBangManager.chesses, Integer.MIN_VALUE, Integer.MAX_VALUE);
			chess = chessBeansForTree;
			// chess.owner = player;
			break;
		}

		chess.Put();
	}

	private List<Chess> getSortList(Player player, Chess[][] chesses) {
		List<Chess> list = new ArrayList<Chess>();

		final var lineCount = GoBangManager.LineCount;
		for (int i = 0; i < lineCount; i++) {
			for (int j = 0; j < lineCount; j++) {
				var chess = chesses[i][j];
				if (chess == null) {
					chess = new Chess(new Point(i, j), player);
					chess.offense = GetScore(chess.location, player, chesses);
					chess.defense = GetScore(chess.location, player.GetEnemy(), chesses);

					list.add(chess);
				}
			}
		}

		list.sort((Chess left, Chess right) -> -left.compareTo(right));

		return list;
	}

	private int GetScore(Point loc, Player player, Chess[][] chesses) {
		var list = new ArrayList<ModelType>();

		for (var dir : Direction.values()) {
			list.add(GetModel(loc, dir, player, chesses));
		}

		return GetMaxModelScore(list) + position[loc.x][loc.y];
	}

	private ModelType GetModel(Point loc, Direction direction, Player player, Chess[][] chesses) {
		String leftString = "";
		String rightString = "";

		String str;

		var offset = direction.offset;
		leftString = getStringSeq(loc, offset.x, offset.y, player, chesses);
		leftString = new StringBuilder(leftString).reverse().toString();
		rightString = getStringSeq(loc, -offset.x, -offset.y, player, chesses);

		str = leftString + player.GetValue() + rightString;
		// chesses[x2][y2].getBuffer().append("(" + (x2 + 1) + "," + (y2 - 1) + ")" +
		// direction + "\t" + str + "\t");

		// ��ȡ���͵ĵ����ַ���
		String rstr = new StringBuilder(str).reverse().toString();
		// ����str��rstrȥLevel���������ƥ��
		for (var type : ModelType.values()) {
			Pattern pat = Pattern.compile(type.regex[player.GetValue() - 1]);
			Matcher mat = pat.matcher(str); // ��
			// �����true��ƥ��ɹ�
			boolean r1 = mat.find();
			mat = pat.matcher(rstr); // ��
			// �����true��ƥ��ɹ�
			boolean r2 = mat.find();
			if (r1 || r2) {
				// chesses[x2][y2].getBuffer().append(level.name + "\n");
				// if (direction == Direction.NA) {
				// chesses[x2][y2].getBuffer().append("\n");
				// }
				return type;
			}
		}
		return ModelType.NULL;
	}

	private String getStringSeq(Point location, int x, int y, Player player, Chess[][] chesses) {
		String sum = "";

		Point loc = new Point(location);
		for (int k = 0; k < 5; k++) {
			loc.x += x;
			loc.y += y;
			if (GoBangManager.CellValid(loc)) {
				var chess = chesses[loc.x][loc.y];
				var value = chess != null ? chess.owner.GetValue() : Player.None.GetValue();
				sum += value;

			}
		}
		return sum;
	}

	private int GetMaxModelScore(List<ModelType> models) {
		int[] modelCount = new int[ModelType.values().length];
		for (int i = 0; i < ModelType.values().length; i++) {
			modelCount[i] = 0;
		}

		for (var model : models) {
			modelCount[model.index]++;
		}

		int score = 0;
		if (modelCount[ModelType.GO_4.index] >= 2
				|| modelCount[ModelType.GO_4.index] >= 1 && modelCount[ModelType.ALIVE_3.index] >= 1)// ˫��4����4����
			score = 10000;
		else if (modelCount[ModelType.ALIVE_3.index] >= 2)// ˫��3
			score = 5000;
		else if (modelCount[ModelType.SLEEP_3.index] >= 1 && modelCount[ModelType.ALIVE_3.index] >= 1)// ��3��3
			score = 1000;
		else if (modelCount[ModelType.ALIVE_2.index] >= 2)// ˫��2
			score = 100;
		else if (modelCount[ModelType.SLEEP_2.index] >= 1 && modelCount[ModelType.ALIVE_2.index] >= 1)// ��2��2
			score = 10;

		for (var model : models) {
			score = Math.max(score, model.score);
		}

		return score;
	}

	/*
	 * protected Chess GetScoreByTree(int depth, Player player, Chess[][] chesses) {
	 * Chess[][] tmp = new Chess[chesses.length][]; for (int i = 0; i < tmp.length;
	 * i++) { tmp[i] = chesses[i].clone();
	 * 
	 * } List<Chess> orderList = getSortList(player, tmp); if (depth == treeDepth) {
	 * return orderList.get(0); // �ﵽ����ָ����ȣ����������ص�ǰ�����С���ȡ���Ĺ�ֵ��ߵĵ㡣 } for (int i = 0;
	 * i < orderList.size(); i++) { var chess = orderList.get(i); if (chess.GetSum()
	 * > ModelType.ALIVE_4.score) { return chess; } else { //
	 * ���������ģ�����塣�����������������Ͻ������� tmp[chess.location.x][chess.location.y] = chess;
	 * return GetScoreByTree(depth + 1, player.GetEnemy(), tmp); } } return null; }
	 */
	
	protected int GetScoreByTree(int depth, Player player, Chess[][] chesses, int alpha, int beta) {
		Chess[][] tmp = new Chess[chesses.length][];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = chesses[i].clone();

		}
		List<Chess> orderList = getSortList(player, tmp);
		if (depth == treeDepth) {
			return orderList.get(0).GetSum(); // �ﵽ����ָ����ȣ����������ص�ǰ�����С���ȡ���Ĺ�ֵ��ߵĵ㡣
		}
		// ������ǰ���������п����λ�ã�����getSortList��
		for (int i = 0; i < treeNodeCount; i++) {
			Chess chess = orderList.get(0);
			int score;
			if (chess.GetSum() > ModelType.ALIVE_4.score) {
				// �ҵ�Ŀ��
				score = chess.GetSum();
			} else {
				// ���������ģ�����塣�����������������Ͻ�������
				tmp[chess.location.x][chess.location.y] = chess;
				score = GetScoreByTree(depth + 1, player.GetEnemy(), tmp, alpha, beta);
			}
			if (depth % 2 == 0) {
				// �Լ��������ֵ
				if (score > alpha) {
					alpha = score;
					if (depth == 0) {
						// ���
						chessBeansForTree = chess;
						// System.out.println(chessBeansForTree);
					}
				}
				if (alpha >= beta) {
					// ��֦
					score = alpha;
					return score;
				}
			} else {
				if (score < beta) {
					beta = score;
				}
				if (alpha >= beta) {
					// ��֦
					score = beta;
					return score;
				}
			}
		}
		return depth % 2 == 0 ? alpha : beta;
	}

}
