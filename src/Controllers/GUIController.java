/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUI.*;
import Models.*;
import javax.swing.*;

/**
 *
 * @author marcw
 */
public class GUIController {

	public final GUI window;
	public BulkRefactor batchRename;

	public GUIController(GUI window) {
		this.window = window;
		this.ActionChanged(null);
		this.Update();
	}

	public final void Update() {
		batchRename = new BulkRefactor(
				CAction.List[window.jComboBox_Action.getSelectedIndex()].Action,
				window.jTextField_Target.getText(),
				window.jTextField_Replace.getText(),
				window.jTextField_FilePath.getText(),
				window.jCheckBox_isFiles.isSelected(),
				window.jCheckBox_isFolder.isSelected(),
				window.jCheckBox_SubFolders.isSelected(),
				window.jCheckBox_IgnoreExtension.isSelected());
	}

	public final void ActionChanged(java.awt.event.ActionEvent evt) {
		CAction tempAct = CAction.List[window.jComboBox_Action.getSelectedIndex()];

		boolean par1 = tempAct.Param1Name != null,
				par2 = tempAct.Param2Name != null;

		if (par1) {
			window.jLabel_Target.setText(tempAct.Param1Name);
		}
		if (par2) {
			window.jLabel_Replacement.setText(tempAct.Param2Name);
		}

		window.jLabel_Target.setVisible(par1);
		window.jTextField_Target.setVisible(par1);
		window.jLabel_Replacement.setVisible(par2);
		window.jTextField_Replace.setVisible(par2);
	}

	public final void rename(java.awt.event.ActionEvent evt) {
		this.Update();
		if (this.batchRename.Prepair()) {
			this.batchRename.rename();
			new GUItabelList(this.batchRename.getCFiles(), new CFile(), "Renamed").setVisible(true);
			window.jButton_Undo.setEnabled(true);
		} else {
			JOptionPane.showMessageDialog(new JPanel(), "Could not rename file/folder", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public final void demo(java.awt.event.ActionEvent evt) {
		this.Update();
		this.batchRename.Prepair();
		new GUItabelList(this.batchRename.getCFiles(), new CFile(), "test run").setVisible(true);
	}

	public final void undo(java.awt.event.ActionEvent evt) {
		this.batchRename.undoRename();
		new GUItabelList(this.batchRename.getCFiles(), new CFile(), "undo rename").setVisible(true);
		window.jButton_Undo.setEnabled(false);
	}

}
