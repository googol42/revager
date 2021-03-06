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

import javax.swing.table.AbstractTableModel;

import org.revager.app.Application;
import org.revager.app.FindingManagement;
import org.revager.app.model.schema.Protocol;

/**
 * The Class FindingsTableModel.
 */
@SuppressWarnings("serial")
public class FindingsTableModel extends AbstractTableModel {

	private FindingManagement findMgmt = Application.getInstance().getFindingMgmt();
	private Protocol prot;

	/**
	 * Instantiates a new findings table model.
	 * 
	 * @param currentProt
	 *            the current prot
	 */
	public FindingsTableModel(Protocol currentProt) {
		prot = currentProt;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return findMgmt.getNumberOfFindings(prot);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int row, int column) {
		return prot.getFindings().get(row).getId();
	}
}
