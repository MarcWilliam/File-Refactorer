/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.*;
import Models.BatchRename.IRename;

/**
 *
 * @author marcw
 */
public class GUIController {

	public static BatchRename batchRename = new BatchRename();

	public static void Demo(IRename Action, String target, String replacement, String originPath, boolean forFile, boolean forDir, boolean forSubDir) {
		batchRename.setAll(Action, target, replacement, originPath, true, true, true);
	}

}
