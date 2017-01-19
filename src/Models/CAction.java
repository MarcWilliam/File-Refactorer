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

	public static interface IRename {

		public void rename(final CFile file, String... params);
	}

	public IRename Action;
	public String name,
			description,
			params[];

	private CAction(IRename Action, String Name, String Description, String... params) {
		this.Action = Action;
		this.name = Name;
		this.description = Description;
		this.params = params;
	}

	public void rename(final CFile file, String... params) {
		this.Action.rename(file, params);
	}

	@Override
	public String toString() {
		return name + "  :  " + description;
	}

	/**
	 * ******************************************************************************* *
	 * * * * * * * * * * * * * * add any custom actions here * * * * * * * * * * * * * *
	 */
	public final static CAction[] List = {
		new CAction((IRename) Auto.Movies, "Auto (Movies)", ""),
		//////////////////////////////////////////////////////////////////////////////////

		new CAction((IRename) Auto.Series, "Auto (Series)", ""),
		//////////////////////////////////////////////////////////////////////////////////

		new CAction((IRename) (final CFile file, final String... params) -> {
			file.destination.name = file.source.name.replace(params[0], "");
		}, "Remove all", "remove all ocucence of that string", "Target"),
		//////////////////////////////////////////////////////////////////////////////////

		new CAction((IRename) (final CFile file, final String... params) -> {
			file.destination.name = file.source.name.replace(params[0], params[1]);
		}, "Replace all", "replace all ocucence of that string", "Target", "Replacement"),
		//////////////////////////////////////////////////////////////////////////////////

		new CAction((IRename) (final CFile file, final String... params) -> {
			file.destination.name = file.source.name.replaceAll(params[0], params[1]);
		}, "Regex", "replace all ocucence of that string using regex", "Target", "Replacement"),
		//////////////////////////////////////////////////////////////////////////////////

		new CAction((IRename) (final CFile file, final String... params) -> {
			file.destination.name = file.source.name + params[0];
		}, "Append", "Append a sting to the file", "Append"),
		//////////////////////////////////////////////////////////////////////////////////

		new CAction((IRename) (final CFile file, final String... params) -> {
			file.destination.name = params[0] + file.source.name;
		}, "Prepend", "Append a sting to the file", "Prepend")

	};
	/**
	 * ******************************************************************************* *
	 * * * * * * * * * * * * * * * End of Custom Actions * * * * * * * * * * * * * * * *
	 */
}
