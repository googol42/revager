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

import java.io.File;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.revager.app.Application;
import org.revager.app.model.ApplicationData;
import org.revager.app.model.Data;
import org.revager.app.model.appdata.AppCatalog;
import org.revager.app.model.schema.Aspect;
import org.revager.app.model.schema.Catalog;
import org.revager.gui.UI;
import org.revager.tools.FileTools;
import org.revager.tools.GUITools;

/**
 * Worker for loading the default catalogs.
 */
public class LoadDefCatalogsWorker extends SwingWorker<Void, Void> {

	/**
	 * Reference to application data.
	 */
	private ApplicationData appData = Data.getInstance().getAppData();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.SwingWorker#doInBackground()
	 */
	@Override
	protected Void doInBackground() {
		UI.getInstance().getAspectsManagerFrame().notifySwitchToProgressMode();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				UI.getInstance().getAspectsManagerFrame().switchToProgressMode(translate("Importing catalog ..."));
			}
		});

		String fileEnding = Data.getInstance().getResource("fileEndingCatalog");
		String pathCatalogs = Data.getInstance().getResource("path.catalogs")
				+ Data.getInstance().getLocale().getLanguage() + "/";
		String pathWorkDir = Data.getInstance().getAppData().getAppDataPath()
				+ Data.getInstance().getResource("workDirectoryName");

		new File(pathWorkDir).mkdir();
		AppCatalog appCatalog = null;

		for (final String catalogName : Data.getDefaultCatalogs()) {
			try {
				/*
				 * Import catalog from file
				 */
				URL catalog = getClass().getResource(pathCatalogs + catalogName + "." + fileEnding);
				File catalogFile = new File(pathWorkDir + "catalog." + fileEnding);

				UI.getInstance().getAspectsManagerFrame().notifySwitchToProgressMode();

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						UI.getInstance().getAspectsManagerFrame()
								.switchToProgressMode(translate("Importing catalog ...") + " " + catalogName);
					}
				});

				FileTools.copyFile(catalog, catalogFile);

				Catalog resiCatalog = Application.getInstance().getImportExportCtl()
						.importCatalogXML(catalogFile.getAbsolutePath());

				catalogFile.delete();

				/*
				 * Write catalog into the database
				 */
				int suffix = 1;

				String newCatalogName = catalogName;

				while (appData.getCatalog(newCatalogName) != null) {
					newCatalogName = newCatalogName + " " + suffix;
					suffix++;
				}

				appCatalog = appData.newCatalog(newCatalogName);
				appCatalog.setDescription(resiCatalog.getDescription());

				for (Aspect asp : resiCatalog.getAspects().getAspects()) {
					appCatalog.newAspect(asp.getDirective(), asp.getDescription(), asp.getCategory());
				}

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						UI.getInstance().getAspectsManagerFrame().updateTree();

						UI.getInstance().getAspectsManagerFrame().setStatusMessage(translate("Catalog imported successfully."),
								false);
					}
				});
			} catch (Exception e) {
				JOptionPane.showMessageDialog(UI.getInstance().getAspectsManagerFrame(),
						GUITools.getMessagePane(
								translate("Cannot import selected file. The content is not conform to the expected format (Resi XML Schema).")
										+ "\n\n" + e.getMessage()),
						translate("Error"), JOptionPane.ERROR_MESSAGE);

				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						UI.getInstance().getAspectsManagerFrame().setStatusMessage(translate("Cannot import catalog!"), false);
					}
				});
			}
		}

		final AppCatalog catalog = appCatalog;

		UI.getInstance().getAspectsManagerFrame().notifySwitchToEditMode();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				UI.getInstance().getAspectsManagerFrame().updateTree(catalog, null, null);

				UI.getInstance().getAspectsManagerFrame().switchToEditMode();
			}
		});

		return null;
	}

}
