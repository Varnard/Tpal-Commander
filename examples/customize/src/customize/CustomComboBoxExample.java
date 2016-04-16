package customize;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.plaf.basic.*;

public class CustomComboBoxExample {
	
	@SuppressWarnings("serial")
	static class ColorCellRenderer extends DefaultListCellRenderer {
	}

	static class ColorComboBoxEditor extends BasicComboBoxEditor {
	}

    private CustomComboBoxExample() {
    }

    private JComponent createMainPanel() {
    	
		String colors[] = { "black", "gray", "white", "red", "green", "blue" };
		//Color colors[] = { Color.black, Color.gray, Color.white,
		//	Color.red,  Color.green, Color.blue };

		final JComboBox comboBox = new JComboBox(colors);
		comboBox.setMaximumRowCount(4);
		comboBox.setEditable(false);
		//comboBox.setRenderer(new ColorCellRenderer());
		//comboBox.setEditor(new ColorComboBoxEditor());
		
		final JLabel label = new JLabel();
		label.setOpaque(true);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5));
		label.setText((String) comboBox.getSelectedItem());
		
		final JPanel panel = new JPanel(new BorderLayout());
		panel.add(comboBox, BorderLayout.NORTH);
		panel.add(label, BorderLayout.CENTER);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				label.setText((String) comboBox.getSelectedItem());
			}
		});

		return panel;
	}

    public static void main(String [] args) {
        final CustomComboBoxExample instance = new CustomComboBoxExample();

        final JFrame frame = new JFrame(CustomComboBoxExample.class.getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 250);
        frame.setVisible(true);
    }
}
