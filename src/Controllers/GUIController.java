/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUI.*;
import Interfaces.ITableRow;
import Models.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marcw
 */
public class GUIController {

	public final GUI window;
	public CRoot CurrentRoot;
	public final ArrayList<CRoot> previousOps;

	public GUIController(GUI window) {
		this.window = window;
		this.previousOps = new ArrayList<>();
		this.sync(null);
	}

	public final void sync(java.awt.event.ActionEvent evt) {
		this.syncGUI(evt);
		this.syncData(evt);
	}

	public final void syncData(java.awt.event.ActionEvent evt) {
		String params[] = new String[this.window.jTextField_params.length];

		for (int i = 0; i < this.window.jTextField_params.length; i++) {
			params[i] = this.window.jTextField_params[i].getText();
		}

		this.CurrentRoot = new CRoot(
				this.previousOps.size(),
				CAction.List[this.window.jComboBox_Action.getSelectedIndex()],
				this.window.jTextField_FilePath.getText(),
				this.window.jCheckBox_isFiles.isSelected(),
				this.window.jCheckBox_isFolder.isSelected(),
				this.window.jCheckBox_SubFolders.isSelected(),
				this.window.jCheckBox_IgnoreExtension.isSelected(),
				params
		);
	}

	public final void syncGUI(java.awt.event.ActionEvent evt) {
		this.syncAction(evt);
		// hide or display the undo button
		this.window.jButton_Undo.setEnabled(!this.previousOps.isEmpty());
	}

	public final void syncAction(java.awt.event.ActionEvent evt) {
		CAction selectedAction = CAction.List[this.window.jComboBox_Action.getSelectedIndex()];
		boolean[] pars = new boolean[this.window.jLabel_params.length];

		for (int i = 0; i < this.window.jLabel_params.length; i++) {

			pars[i] = i < selectedAction.params.length && selectedAction.params[i] != null;
			//sync gui elements
			if (pars[i]) {
				this.window.jLabel_params[i].setText(selectedAction.params[i]);
			}
			this.window.jLabel_params[i].setVisible(pars[i]);
			this.window.jTextField_params[i].setVisible(pars[i]);
		}
	}

	public final void rename(java.awt.event.ActionEvent evt) {
		this.syncData(null);
		if (this.CurrentRoot.Prepair()) {
			this.CurrentRoot.rename();
			//new GUItabelList(this.batchRename.getCFiles(), new CFile(), "Renamed").setVisible(true);

			// add row to table
			DefaultTableModel model = (DefaultTableModel) this.window.jTable1.getModel();

			CAction action = CAction.List[this.window.jComboBox_Action.getSelectedIndex()];

			model.addRow(this.CurrentRoot.getCells());

			this.window.jTabbedPane1.add(" " + String.valueOf(this.window.jTabbedPane1.getTabCount() - 2 + 1) + " ",
					TableGenerator.ToTable((ArrayList<ITableRow>) (ArrayList<?>) this.CurrentRoot.getCFiles(), new CFile())
			);

			this.previousOps.add(this.CurrentRoot);
		} else {
			JOptionPane.showMessageDialog(new JPanel(), "Could not rename file/folder", "Error", JOptionPane.ERROR_MESSAGE);
		}
		this.syncGUI(null);
	}

	public final void demo(java.awt.event.ActionEvent evt) {
		this.syncData(null);
		this.CurrentRoot.Prepair();
		TableGenerator.ToFrame((ArrayList<ITableRow>) (ArrayList<?>) this.CurrentRoot.getCFiles(),
				new CFile(),
				"test run").setVisible(true);
		this.syncGUI(null);
	}

	public final void undo(java.awt.event.ActionEvent evt) {
		this.syncData(null);
		int lastIndex = previousOps.size() - 1;
		this.previousOps.remove(lastIndex).undoRename();

		DefaultTableModel model = (DefaultTableModel) this.window.jTable1.getModel();
		model.removeRow(model.getRowCount() - 1);

		this.window.jTabbedPane1.remove(this.window.jTabbedPane1.getTabCount() - 1);

		this.syncGUI(null);
	}

}
