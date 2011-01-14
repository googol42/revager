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
package org.revager.gui.actions.meeting;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.AbstractAction;

import org.revager.app.Application;
import org.revager.app.model.schema.Meeting;
import org.revager.gui.UI;
import org.revager.gui.dialogs.MeetingDialog;
import org.revager.tools.GUITools;

/**
 * The Class ConfirmMeetingAction.
 */
@SuppressWarnings("serial")
public class ConfirmMeetingAction extends AbstractAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		MeetingDialog meetDialog = UI.getInstance().getMeetingDialog();

		Meeting currentMeeting = meetDialog.getCurrentMeeting();

		DateFormat dateF = SimpleDateFormat.getDateInstance(DateFormat.LONG);

		Meeting newMeeting = new Meeting();

		try {
			newMeeting.setPlannedDate(GUITools.dateString2Calendar(
					UI.getInstance().getMeetingDialog().getDateTxtFld()
							.getText(), dateF));
		} catch (ParseException exc) {
			newMeeting.setPlannedDate(new GregorianCalendar());
		}

		newMeeting.setPlannedStart(meetDialog.getBegin());
		newMeeting.setPlannedEnd(meetDialog.getEnd());
		newMeeting.setPlannedLocation(meetDialog.getLocationTxt());
		newMeeting.setCanceled(meetDialog.getCanceled());
		Application.getInstance().getReviewMgmt().setRecommendation("");

		if (meetDialog.isNewMeeting()) {
			Application.getInstance().getMeetingMgmt().addMeeting(newMeeting);
			newMeeting.setComments("");
		} else {
			newMeeting.setComments(currentMeeting.getComments());
			newMeeting.setProtocol(currentMeeting.getProtocol());
			Application.getInstance().getMeetingMgmt()
					.editMeeting(currentMeeting, newMeeting);

		}

		UI.getInstance().getMainFrame().updateMeetingsTree();

		meetDialog.setVisible(false);

		UI.getInstance().getMainFrame().updateButtons();
	}

}
