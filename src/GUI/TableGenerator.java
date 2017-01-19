package GUI;

import Interfaces.ITableRow;
import java.awt.Color;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Marc Wafik
 */
public class TableGenerator {

	public static JScrollPane ToTable(ArrayList<ITableRow> myData, ITableRow type) {

		JScrollPane jScrollPane = new javax.swing.JScrollPane();
		JTable jTable = new javax.swing.JTable();

		jTable.setModel(new javax.swing.table.DefaultTableModel(
				TableGenerator.ToCells(myData),
				type.getColumNames()
		));
		jTable.setAutoCreateRowSorter(true);
		jScrollPane.setViewportView(jTable);
		jScrollPane.getViewport().setBackground(Color.white);
		return jScrollPane;

	}

	public static Object[][] ToCells(ArrayList<ITableRow> myData) {
		if (myData == null) {
			return new Object[0][0];
		}
		Object[][] Cells = new Object[myData.size()][];
		int i = 0;
		for (ITableRow temprow : myData) {
			Object[] temp = temprow.getCells();
			Cells[i++] = temp;
		}
		return Cells;
	}

	public static JFrame ToFrame(ArrayList<ITableRow> myData, ITableRow colName, String Title) {
		JFrame frame = new JFrame();
		frame.setTitle(Title);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.add(TableGenerator.ToTable(myData, colName));
		frame.pack(); // set the size of the frame
		return frame;
	}
}
