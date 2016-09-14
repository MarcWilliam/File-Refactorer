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

	public static GUI window;

	public static BulkRefactor batchRename;

	public static void Update() {
		batchRename = new BulkRefactor(
				Actions.List.get((String) window.jComboBox_Action.getSelectedItem()).Action,
				window.jTextField_Target.getText(),
				window.jTextField_Replace.getText(),
				window.jTextField_FilePath.getText(),
				window.jCheckBox_isFiles.isSelected(),
				window.jCheckBox_isFolder.isSelected(),
				window.jCheckBox_SubFolders.isSelected(),
				window.jCheckBox_IgnoreExtension.isSelected());
	}

	public static void ActionChanged(java.awt.event.ActionEvent evt) {
		Actions.Action tempAct = Actions.List.get((String) window.jComboBox_Action.getSelectedItem());

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

	public static void rename(java.awt.event.ActionEvent evt) {
		GUIController.Update();
		if (GUIController.batchRename.Prepair()) {
			GUIController.batchRename.rename();
			new GUItabelList(GUIController.batchRename.getCFiles(), new CFile(), "Renamed").setVisible(true);
			window.jButton_Undo.setEnabled(true);
		} else {
			JOptionPane.showMessageDialog(new JPanel(), "Could not rename file/folder", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void demo(java.awt.event.ActionEvent evt) {
		GUIController.Update();
		GUIController.batchRename.Prepair();
		new GUItabelList(GUIController.batchRename.getCFiles(), new CFile(), "test run").setVisible(true);
	}

	public static void undo(java.awt.event.ActionEvent evt) {
		GUIController.batchRename.undoRename();
		new GUItabelList(GUIController.batchRename.getCFiles(), new CFile(), "undo rename").setVisible(true);
		window.jButton_Undo.setEnabled(false);
	}

}
