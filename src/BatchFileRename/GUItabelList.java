package BatchFileRename;

import javax.swing.*;

/**
 *
 * @author Marc Wafik
 */
public class GUItabelList extends JFrame {

	public GUItabelList(String[][] Cells, String[] colName, String Title) {
		this.setTitle(Title);
		JTable table = new JTable(Cells, colName);
		table.setEnabled(false); // set the tabel to non editabel
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(new JScrollPane(table));				// create scroll pane for wrapping the table and add it to the frame
		pack(); // set the size of the frame
		table.setAutoCreateRowSorter(true); // set the sort by colum
		//Font f = new Font("serif", Font.PLAIN, 15);
		//table.setFont(f);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}
}
