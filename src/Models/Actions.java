/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.*;
import java.util.*;

/**
 *
 * @author marcw
 */
public class Actions {

	private Actions() {
	}

	public static HashMap<String, Actions.Action> List = new HashMap<>();

	private static void addAction(String Name, String Description, BulkRefactor.IRename Action) {
		Actions.List.put(Name, new Action(Name, Description, Action));
	}

	public static class Action {

		public String Name;
		public String Description;
		public BulkRefactor.IRename Action;

		public Action(String Name, String Description, BulkRefactor.IRename Action) {
			this.Name = Name;
			this.Description = Description;
			this.Action = Action;
		}

	}

	static {
		/**
		 * ******************************************************************************* *
		 * * * * * * * * * * * * * * add any custom actions here * * * * * * * * * * * * * *
		 */
		addAction("Replace", "replace all ocucence of that string",
				(BulkRefactor.IRename) (final String target, final String replacement, final File oldFile) -> {
					return oldFile.getName().replace(target, replacement);
				});

		addAction("Regex", "replace all ocucence of that string using regex",
				(BulkRefactor.IRename) (final String target, final String replacement, final File oldFile) -> {
					return oldFile.getName().replaceAll(target, replacement);
				});

		/**
		 * ******************************************************************************* *
		 * * * * * * * * * * * * * * * End of Custom Actions * * * * * * * * * * * * * * * *
		 */
	}

}
