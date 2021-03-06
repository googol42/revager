/* 
 * Copyright 2009 Davide Casciato, Sandra Reich, Johannes Wettinger
 * 
 * This file is part of Resi.
 *
 * Resi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Resi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Resi. If not, see <http://www.gnu.org/licenses/>.
 */
package org.revager.gui.models;

import static org.revager.app.model.Data.translate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import org.revager.app.model.appdata.AppCSVColumnName;

/**
 * The Class CSVColumnsComboBoxModel.
 */
@SuppressWarnings("serial")
public class CSVColumnsComboBoxModel extends AbstractListModel<String> implements ComboBoxModel<String> {

	private Map<String, AppCSVColumnName> columns = new HashMap<>();

	private final String DESCRIPTION = translate("Description");
	private final String REFERENCE = translate("Reference");
	private final String SEVERITY = translate("Severity");
	private final String REPORTER = translate("Bug Reporter");

	private String selection = null;

	/**
	 * Instantiates a new cSV columns combo box model.
	 */
	public CSVColumnsComboBoxModel() {
		super();

		columns.put(DESCRIPTION, AppCSVColumnName.DESCRIPTION);
		columns.put(REFERENCE, AppCSVColumnName.REFERENCE);
		columns.put(SEVERITY, AppCSVColumnName.SEVERITY);
		columns.put(REPORTER, AppCSVColumnName.REPORTER);
	}

	@Override
	public String getSelectedItem() {
		return selection;
	}

	/**
	 * Gets the selected column.
	 * 
	 * @return the selected column
	 */
	public AppCSVColumnName getSelectedColumn() {
		return columns.get(getSelectedItem());
	}

	@Override
	public void setSelectedItem(Object item) {
		selection = item.toString();
	}

	/**
	 * Sets the selected column.
	 * 
	 * @param col
	 *            the col
	 */
	public void setSelectedColumn(AppCSVColumnName col) {
		Iterator<Entry<String, AppCSVColumnName>> iter = columns.entrySet().iterator();

		while (iter.hasNext()) {
			Entry<String, AppCSVColumnName> entry = iter.next();

			if (entry.getValue() == col) {
				setSelectedItem(entry.getKey());
			}
		}
	}

	@Override
	public String getElementAt(int index) {
		switch (index) {
		case 0:
			return DESCRIPTION;
		case 1:
			return REFERENCE;
		case 2:
			return SEVERITY;
		case 3:
			return REPORTER;

		default:
			return "";
		}
	}

	@Override
	public int getSize() {
		return AppCSVColumnName.values().length;
	}

}
