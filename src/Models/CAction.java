/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author marcw
 */
public class CAction {

	public BulkRefactor.IRename Action;
	public String name,
			description,
			params[];

	private CAction(BulkRefactor.IRename Action, String Name, String Description, String... params) {
		this.Action = Action;
		this.name = Name;
		this.description = Description;
		this.params = params;
	}

	@Override
	public String toString() {
		return name + "  :  " + description;
	}

	public final static CAction[] List = {
		/**
		 * ******************************************************************************* *
		 * * * * * * * * * * * * * * add any custom actions here * * * * * * * * * * * * * *
		 */
		new CAction((BulkRefactor.IRename) (final CFile file, final String... params) -> {
			file.newName = file.oldName.replace(params[0], "");
		}, "Remove all", "remove all ocucence of that string", "Target"),
		//////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		new CAction((BulkRefactor.IRename) (final CFile file, final String... params) -> {
			file.newName = file.oldName.replace(params[0], params[1]);
		}, "Replace all", "replace all ocucence of that string", "Target", "Replacement"),
		//////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		new CAction((BulkRefactor.IRename) (final CFile file, final String... params) -> {
			file.newName = file.oldName.replaceAll(params[0], params[1]);
		}, "Regex", "replace all ocucence of that string using regex", "Target", "Replacement"),
		//////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		new CAction((BulkRefactor.IRename) (final CFile file, final String... params) -> {
			file.newName = file.oldName + params[0];
		}, "Append", "Append a sting to the file", "Append"),
		//////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

		new CAction((BulkRefactor.IRename) (final CFile file, final String... params) -> {
			file.newName = params[0] + file.oldName;
		}, "Prepend", "Append a sting to the file", "Prepend")
	/**
	 * ******************************************************************************* *
	 * * * * * * * * * * * * * * * End of Custom Actions * * * * * * * * * * * * * * * *
	 */
	};
}
