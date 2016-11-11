/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUI.*;
import Models.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author marcw
 */
public class GUIController {

	public final GUI window;
	public BulkRefactor batchRename;
	public final ArrayList<BulkRefactor> previousOps;

	public GUIController(GUI window) {
		this.window = window;
		this.previousOps = new ArrayList<>();
		this.sync(null);
	}

	public final void sync(java.awt.event.ActionEvent evt) {
		this.syncData(evt);
		this.syncGUI(evt);
	}

	public final void syncData(java.awt.event.ActionEvent evt) {
		String params[] = new String[this.window.jTextField_params.length];

		for (int i = 0; i < this.window.jTextField_params.length; i++) {
			params[i] = this.window.jTextField_params[i].getText();
		}

		this.batchRename = new BulkRefactor(
				CAction.List[this.window.jComboBox_Action.getSelectedIndex()].Action,
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
		if (this.batchRename.Prepair()) {
			this.batchRename.rename();
			new GUItabelList(this.batchRename.getCFiles(), new CFile(), "Renamed").setVisible(true);
			this.previousOps.add(this.batchRename);
		} else {
			JOptionPane.showMessageDialog(new JPanel(), "Could not rename file/folder", "Error", JOptionPane.ERROR_MESSAGE);
		}
		this.syncGUI(null);
	}

	public final void demo(java.awt.event.ActionEvent evt) {
		this.syncData(null);
		this.batchRename.Prepair();
		new GUItabelList(this.batchRename.getCFiles(), new CFile(), "test run").setVisible(true);
		this.syncGUI(null);
	}

	public final void undo(java.awt.event.ActionEvent evt) {
		this.syncData(null);
		int lastIndex = previousOps.size() - 1;
		this.previousOps.remove(lastIndex).undoRename();
		this.syncGUI(null);
	}

}
