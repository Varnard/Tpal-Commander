package customize;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

public class CustomTableExample {

	@SuppressWarnings("serial")
	static class EvenOddLabelCellRenderer extends DefaultTableCellRenderer {
	}

    @SuppressWarnings("serial")
	static class MyTableModel extends AbstractTableModel {
		@Override
		public int getColumnCount() {
			return 0;
		}

		@Override
		public int getRowCount() {
			return 0;
		}

		@Override
		public Object getValueAt(int row, int column) {
			return null;
		}
    }

    private CustomTableExample() {
    }
    
    private JComponent createMainPanel() {
    	
        String[] columnNames = {"First Name", "Last Name",
            "Sport", "# of Years", "Vegetarian"};
        Object[][] data = {
        	{ "Kathy", "Smith", "Snowboarding", new Integer(5), new Boolean(false) },
        	{ "John", "Doe", "Rowing", new Integer(3), new Boolean(true) },
        	{ "Sue", "Black", "Knitting", new Integer(2), new Boolean(false) },
        	{ "Jane", "White", "Speed reading", new Integer(20), new Boolean(true) },
        	{ "Joe", "Brown", "Pool", new Integer(10), new Boolean(false) }};

		JTable table = new JTable(data, columnNames);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        final JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JScrollPane(table), BorderLayout.CENTER);

		return panel;
	}

    public static void main(String [] args) {
        final CustomTableExample instance = new CustomTableExample();

        final JFrame frame = new JFrame(CustomTableExample.class.getName());
        frame.getContentPane().add(instance.createMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
        frame.setVisible(true);
    }
}
