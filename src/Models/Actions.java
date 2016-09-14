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

	public static ArrayList< Actions.Action> List = new ArrayList<>();

	private static void addAction(String Name, String Description, BulkRefactor.IRename Action) {
		Actions.List.add(new Action(Name, Description, "Target", "Replacement", Action));
	}

	private static void addAction(String Name, String Description, String Param1Name, String Param2Name, BulkRefactor.IRename Action) {
		Actions.List.add(new Action(Name, Description, Param1Name, Param2Name, Action));
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

		@Override
		public String toString() {
			return Name + "  :  " + Description;
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

		addAction("Replace", "replace all ocucence of that string", "Target", "Replacement",
				(BulkRefactor.IRename) (final String param1, final String param2, final File oldFile, final boolean ignoreExtension) -> {
					IgnoreExt tmp = IgnoreExt.Find(oldFile, ignoreExtension);
					return tmp.name.replace(param1, param2) + tmp.extension;
				});

		addAction("Regex", "replace all ocucence of that string using regex", "Target", "Replacement",
				(BulkRefactor.IRename) (final String param1, final String param2, final File oldFile, final boolean ignoreExtension) -> {
					IgnoreExt tmp = IgnoreExt.Find(oldFile, ignoreExtension);
					return tmp.name.replaceAll(param1, param2) + tmp.extension;
				});

		addAction("Append", "Append a sting to the file", "Append", null,
				(BulkRefactor.IRename) (final String param1, final String param2, final File oldFile, final boolean ignoreExtension) -> {
					IgnoreExt tmp = IgnoreExt.Find(oldFile, ignoreExtension);
					return tmp.name + param2 + tmp.extension;
				});

		/**
		 * ******************************************************************************* *
		 * * * * * * * * * * * * * * * End of Custom Actions * * * * * * * * * * * * * * * *
		 */
	}

}
