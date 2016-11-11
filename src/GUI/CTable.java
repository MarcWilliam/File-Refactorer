package GUI;

import Models.CFile;
import java.util.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Marc Wafik
 */
public class CTable {

	public static interface ITable {

		String[] getCells();

		String[] getColumNames();

	}

	public static JScrollPane getTable(ArrayList<CFile> myData, ITable type) {

		JScrollPane jScrollPane = new javax.swing.JScrollPane();
		JTable jTable = new javax.swing.JTable();

		jTable.setModel(new javax.swing.table.DefaultTableModel(
				CTable.getCells(myData),
				type.getColumNames()
		));
		jTable.setAutoCreateRowSorter(true);
		jScrollPane.setViewportView(jTable);

		return jScrollPane;

	}

	public static String[][] getCells(ArrayList<CFile> myData) {
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
}
