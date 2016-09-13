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

	public static javax.swing.JButton jButton_Demo;
	public static javax.swing.JButton jButton_FilePath;
	public static javax.swing.JButton jButton_Run;
	public static javax.swing.JButton jButton_Undo;

	public static javax.swing.JCheckBox jCheckBox_SubFolders;
	public static javax.swing.JCheckBox jCheckBox_isFiles;
	public static javax.swing.JCheckBox jCheckBox_isFolder;

	public static javax.swing.JComboBox<String> jComboBox_Action;

	public static javax.swing.JTextField jTextField_FilePath;
	public static javax.swing.JTextField jTextField_Replace;
	public static javax.swing.JTextField jTextField_Target;

	public static BulkRefactor batchRename = new BulkRefactor();

	public static void Update() {
		batchRename.setAll(
				Actions.List.get((String) jComboBox_Action.getSelectedItem()).Action,
				jTextField_Target.getText(),
				jTextField_Replace.getText(),
				jTextField_FilePath.getText(),
				jCheckBox_isFiles.isSelected(),
				jCheckBox_isFolder.isSelected(),
				jCheckBox_SubFolders.isSelected());
	}

	public static void rename(java.awt.event.ActionEvent evt) {
		GUIController.Update();
		if (GUIController.batchRename.Prepair()) {
			GUIController.batchRename.rename();
			new GUItabelList(GUIController.batchRename.getCFiles(), new CFile(), "Renamed").setVisible(true);
			jButton_Undo.setEnabled(true);
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
		jButton_Undo.setEnabled(false);
	}

}
