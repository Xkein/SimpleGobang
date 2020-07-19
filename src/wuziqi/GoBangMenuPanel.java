package wuziqi;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import java.awt.Component;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GoBangMenuPanel extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	JRadioButton rdbtnPvp;
	JRadioButton rdbtnWeFirst;
	JRadioButton rdbtnEF;
	JComboBox comboBoxDepth;
	JComboBox comboBoxNodeCount;

	/**
	 * Create the panel.
	 */
	public GoBangMenuPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null,
				"\u5728\u952E\u76D8\u4E0A\u5355\u51FB\u9F20\u6807\u53F3\u952E\uFF0C\u67E5\u770B\u5404\u4E2A\u4F30\u503C",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		panel_4.add(textArea);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u6A21\u5F0F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_1_1);

		rdbtnPvp = new JRadioButton("\u4EBA\u4EBA\u5BF9\u6218");
		buttonGroup_2.add(rdbtnPvp);
		rdbtnPvp.setSelected(true);
		panel_1_1.add(rdbtnPvp);

		JRadioButton rdbtnPve = new JRadioButton("\u4EBA\u673A\u5BF9\u6218");
		buttonGroup_2.add(rdbtnPve);
		panel_1_1.add(rdbtnPve);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u8D77\u624B", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_1);

		rdbtnWeFirst = new JRadioButton("\u6211\u65B9\u5148\u624B");
		rdbtnWeFirst.setSelected(true);
		buttonGroup.add(rdbtnWeFirst);
		panel_1.add(rdbtnWeFirst);

		JRadioButton rdbtnEnemyFirst = new JRadioButton("\u654C\u65B9\u5148\u624B");
		buttonGroup.add(rdbtnEnemyFirst);
		panel_1.add(rdbtnEnemyFirst);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u667A\u80FD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_2);

		rdbtnEF = new JRadioButton("\u4F30\u503C\u51FD\u6570");
		rdbtnEF.setSelected(true);
		buttonGroup_1.add(rdbtnEF);
		panel_2.add(rdbtnEF);

		JRadioButton rdbtnEFT = new JRadioButton("\u4F30\u503C\u51FD\u6570+\u641C\u7D22\u6811");
		buttonGroup_1.add(rdbtnEFT);
		panel_2.add(rdbtnEFT);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(
				new TitledBorder(null, "\u641C\u7D22\u6811", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_3);

		JLabel lblNewLabel = new JLabel("\u641C\u7D22\u6DF1\u5EA6\uFF1A");
		panel_3.add(lblNewLabel);

		comboBoxDepth = new JComboBox();
		comboBoxDepth.setModel(new DefaultComboBoxModel(new Integer[] { 1, 2, 3 }));
		comboBoxDepth.setSelectedIndex(0);
		panel_3.add(comboBoxDepth);

		JLabel lblNewLabel_1 = new JLabel("\u6BCF\u5C42\u8282\u70B9");
		panel_3.add(lblNewLabel_1);

		comboBoxNodeCount = new JComboBox();
		comboBoxNodeCount.setModel(new DefaultComboBoxModel(new Integer[] { 3, 5, 10 }));
		comboBoxNodeCount.setSelectedIndex(0);
		panel_3.add(comboBoxNodeCount);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5176\u4ED6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JCheckBox chckbxNewCheckBox = new JCheckBox("\u663E\u793A\u987A\u5E8F");
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				GoBangManager.showChessIndex = chckbxNewCheckBox.isSelected();
				GoBangManager.pnlBoard.repaint();
			}
		});
		panel.add(chckbxNewCheckBox);

		JButton btnNewButton = new JButton("\u6094\u68CB");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					GoBangManager.Undo();
				}
			}
		});
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\u65B0\u6E38\u620F");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				GoBangManager.StartNewGame(rdbtnPvp.isSelected() ? Mode.PVP : Mode.PVE,
						rdbtnEF.isSelected() ? AI.Algorithm.EstimateFunction : AI.Algorithm.EstimateFunction_Tree,
						rdbtnWeFirst.isSelected(), (int) comboBoxDepth.getSelectedItem(),
						(int) comboBoxNodeCount.getSelectedItem());
			}
		});
		panel.add(btnNewButton_1);

	}

}
