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
package org.revager.gui.workers;

import static org.revager.app.model.Data.translate;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.revager.app.Application;
import org.revager.app.model.appdata.AppAspect;
import org.revager.app.model.schema.Aspect;
import org.revager.app.model.schema.Aspects;
import org.revager.gui.UI;
import org.revager.tools.GUITools;

/**
 * Worker for exporting aspects to a XML file.
 */
public class ExportAspectsWorker extends SwingWorker<Void, Void> {

	/**
	 * The file path.
	 */
	private String filePath = null;

	/**
	 * The aspects.
	 */
	private List<AppAspect> aspects = null;

	/**
	 * Instantiates a new export aspects worker.
	 * 
	 * @param filePath
	 *            the file path
	 * @param aspects
	 *            the aspects
	 */
	public ExportAspectsWorker(String filePath, List<AppAspect> aspects) {
		super();

		this.filePath = filePath;
		this.aspects = aspects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() throws Exception {
		UI.getInstance().getAspectsManagerFrame().notifySwitchToProgressMode();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				UI.getInstance().getAspectsManagerFrame().switchToProgressMode(translate("Exporting aspects ..."));
			}
		});

		try {
			Aspects asps = new Aspects();

			for (AppAspect a : aspects) {
				Aspect asp = new Aspect();
				asp.setCategory(a.getCategory());
				asp.setDescription(a.getDescription());
				asp.setDirective(a.getDirective());
				asp.setId(Integer.toString(a.getId()));

				asps.getAspects().add(asp);
			}

			Application.getInstance().getImportExportCtl().exportAspectsXML(this.filePath, asps);

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					UI.getInstance().getAspectsManagerFrame().setStatusMessage(translate("Aspects exported successfully."),
							false);
				}
			});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, GUITools.getMessagePane(e.getMessage()), translate("Error"),
					JOptionPane.ERROR_MESSAGE);

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					UI.getInstance().getAspectsManagerFrame().setStatusMessage(translate("Cannot export the selected aspects!"),
							false);
				}
			});
		}

		UI.getInstance().getAspectsManagerFrame().notifySwitchToEditMode();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				UI.getInstance().getAspectsManagerFrame().switchToEditMode();
			}
		});

		return null;
	}

}
