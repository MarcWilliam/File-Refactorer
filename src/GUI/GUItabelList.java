package GUI;

import Models.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Marc Wafik
 */
public class GUItabelList extends JFrame {

	public static interface ITable {

		String[] getCells();

		String[] getColumNames();
	}

	/**
	 *
	 * @param myData an array list with the data
	 * @param colName a new object of that class
	 * @param Title the title of the table
	 */
	public GUItabelList(ArrayList<CFile> myData, ITable colName, String Title) {
		this.setTitle(Title);
		JTable table = new JTable(this.getCells(myData), this.getColumNames(colName));
		table.setEnabled(false); // set the tabel to non editabel
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(new JScrollPane(table));				// create scroll pane for wrapping the table and add it to the frame
		pack(); // set the size of the frame
		table.setAutoCreateRowSorter(true); // set the sort by colum
		//Font f = new Font("serif", Font.PLAIN, 15);
		//table.setFont(f);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	}

	private String[][] getCells(ArrayList<CFile> myData) {
		if (myData == null) {
			return new String[0][0];
		}
		String[][] Cells = new String[myData.size()][];
		int i = 0;
		for (ITable temprow : myData) {
			Cells[i++] = temprow.getCells();
		}
		return Cells;
	}

	private String[] getColumNames(ITable myData) {
		return myData.getColumNames();
	}
}
