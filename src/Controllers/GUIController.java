/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.*;
import Models.BulkRefactor.IRename;

/**
 *
 * @author marcw
 */
public class GUIController {

	public static BulkRefactor batchRename = new BulkRefactor();

	public static void Update(IRename Action, String target, String replacement, String originPath, boolean forFile, boolean forDir, boolean forSubDir) {
		batchRename.setAll(Action, target, replacement, originPath, forFile, forDir, forSubDir);
	}

}
