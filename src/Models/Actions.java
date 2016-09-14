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
		Actions.List.put(Name, new Action(Name, Description, "Target", "Replacement", Action));
	}

	private static void addAction(String Name, String Description, String Param1Name, String Param2Name, BulkRefactor.IRename Action) {
		Actions.List.put(Name, new Action(Name, Description, Param1Name,
				Param2Name, Action));
	}

	public static class Action {

		public String Name,
				Description,
				Param1Name,
				Param2Name;
		public BulkRefactor.IRename Action;

		public Action(String Name, String Description, String Param1Name, String Param2Name, BulkRefactor.IRename Action) {
			this.Name = Name;
			this.Description = Description;
			this.Param1Name = Param1Name;
			this.Param2Name = Param2Name;
			this.Action = Action;
		}

	}

	private static class IgnoreExt {

		public String name,
				extension;

		private IgnoreExt(String name, String extension) {
			this.name = name;
			this.extension = extension;
		}

		public static IgnoreExt Find(final File theFile, final boolean IgnoreExtension) {
			IgnoreExt ret = new IgnoreExt(theFile.getName(), "");
			if (IgnoreExtension && theFile.isFile()) {
				int i = theFile.getName().lastIndexOf('.');
				if (i > 0) {
					ret.extension = ret.name.substring(i);
					ret.name = ret.name.substring(0, i);
				}
			}
			return ret;
		}
	}

	static {
		/**
		 * ******************************************************************************* *
		 * * * * * * * * * * * * * * add any custom actions here * * * * * * * * * * * * * *
		 */

		addAction("Replace", "replace all ocucence of that string",
				(BulkRefactor.IRename) (final String target, final String replacement, final File oldFile, final boolean ignoreExtension) -> {
					IgnoreExt tmp = IgnoreExt.Find(oldFile, ignoreExtension);
					return tmp.name.replace(target, replacement) + tmp.extension;
				});

		addAction("Regex", "replace all ocucence of that string using regex",
				(BulkRefactor.IRename) (final String target, final String replacement, final File oldFile, final boolean ignoreExtension) -> {
					IgnoreExt tmp = IgnoreExt.Find(oldFile, ignoreExtension);
					return tmp.name.replaceAll(target, replacement) + tmp.extension;
				});

		addAction("Append", "Append a sting to the file", "Append", null,
				(BulkRefactor.IRename) (final String target, final String replacement, final File oldFile, final boolean ignoreExtension) -> {
					IgnoreExt tmp = IgnoreExt.Find(oldFile, ignoreExtension);
					return tmp.name + replacement + tmp.extension;
				});

		/**
		 * ******************************************************************************* *
		 * * * * * * * * * * * * * * * End of Custom Actions * * * * * * * * * * * * * * * *
		 */
	}

}
