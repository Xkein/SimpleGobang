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

public class GoBangMenuPanel extends JPanel {
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public GoBangMenuPanel() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "\u5728\u952E\u76D8\u4E0A\u5355\u51FB\u9F20\u6807\u53F3\u952E\uFF0C\u67E5\u770B\u5404\u4E2A\u4F30\u503C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		panel_4.add(textArea);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u6A21\u5F0F", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_1_1);
		
		JRadioButton rdbtnPvp = new JRadioButton("\u4EBA\u4EBA\u5BF9\u6218");
		buttonGroup_2.add(rdbtnPvp);
		rdbtnPvp.setSelected(true);
		panel_1_1.add(rdbtnPvp);
		
		JRadioButton rdbtnNewRadioButton_1_1 = new JRadioButton("\u4EBA\u673A\u5BF9\u6218");
		buttonGroup_2.add(rdbtnNewRadioButton_1_1);
		panel_1_1.add(rdbtnNewRadioButton_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "\u8D77\u624B", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		add(panel_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u6211\u65B9\u5148\u624B");
		rdbtnNewRadioButton.setSelected(true);
		buttonGroup.add(rdbtnNewRadioButton);
		panel_1.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\u654C\u65B9\u5148\u624B");
		buttonGroup.add(rdbtnNewRadioButton_1);
		panel_1.add(rdbtnNewRadioButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "\u667A\u80FD", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_2);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("\u4F30\u503C\u51FD\u6570");
		rdbtnNewRadioButton_2.setSelected(true);
		buttonGroup_1.add(rdbtnNewRadioButton_2);
		panel_2.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("\u4F30\u503C\u51FD\u6570+\u641C\u7D22\u6811");
		buttonGroup_1.add(rdbtnNewRadioButton_3);
		panel_2.add(rdbtnNewRadioButton_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u641C\u7D22\u6811", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel_3);
		
		JLabel lblNewLabel = new JLabel("\u641C\u7D22\u6DF1\u5EA6\uFF1A");
		panel_3.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new Integer[] {1, 2, 3}));
		comboBox.setSelectedIndex(0);
		panel_3.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("\u6BCF\u5C42\u8282\u70B9");
		panel_3.add(lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new Integer[] {3, 5, 10}));
		comboBox_1.setSelectedIndex(0);
		panel_3.add(comboBox_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5176\u4ED6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\u663E\u793A\u987A\u5E8F");
		panel.add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("\u6094\u68CB");
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u65B0\u6E38\u620F");
		panel.add(btnNewButton_1);

	}

}
