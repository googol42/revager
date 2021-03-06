package org.revager.gui.presentationView;

import static org.revager.app.model.Data.translate;
import static org.revager.app.model.appdata.AppSettingKey.APP_PROTOCOL_WARNING_TIME;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import org.revager.app.model.ApplicationData;
import org.revager.app.model.Data;
import org.revager.app.model.DataException;
import org.revager.app.model.appdata.AppSettingKey;
import org.revager.app.model.schema.Finding;
import org.revager.gamecontroller.Dashboard;
import org.revager.gui.UI;

/**
 * GUI element displayed in the presentation view.
 */
public class StatusPanel extends JPanel {

	private static final long serialVersionUID = 4044468994799470896L;
	private static final Font STANDARD_STATUS_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 35);

	private JProgressBar totalDurationProgress;
	private JLabel findingTimeField;
	private HurryUpImage hurryUpImage;
	private HighlightedLable breakField;
	private HighlightedLable continueDiscussionField;
	private JLabel votingsField;
	private int totalProtocolSeconds;
	private transient Dashboard dashboard;

	public StatusPanel() {
		dashboard = Dashboard.getInstance();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0 };
		setLayout(gridBagLayout);
		setBackground(UI.BLUE_BACKGROUND_COLOR);

		addTotalTime();
		addFindingTime();
		addBreak();
		addHurryUp();
		addContinueDiscussion();
		addVotings();

		UI.getInstance().getProtocolClockWorker().addPropertyChangeListener(evt -> {
			Object seconds = evt.getNewValue();
			if (seconds instanceof Integer) {
				this.totalProtocolSeconds = (int) seconds;
				updateDisplay();
			}
		});
	}

	public void setFinding(Finding finding) {
		dashboard.setFinding(finding);
		updateDisplay();
	}

	private void updateDisplay() {
		totalDurationProgress.setMaximum(getMaxProtocolSeconds());
		totalDurationProgress.setValue(totalProtocolSeconds);
		totalDurationProgress.setString(intTimeToString(totalProtocolSeconds));
		int findingTime = dashboard.getFindingTime();
		findingTimeField.setText(intTimeToString(findingTime));
		hurryUpImage.setImageOpacity((float) findingTime / getMaxFindingSeconds());

		if (dashboard.controllersConnected()) {
			breakField.setText(dashboard.getBreakText());
			continueDiscussionField.setText(Integer.toString(dashboard.getFocusNumber()));
			votingsField.setText(dashboard.getClassificationDetails());
		} else {
			String htmlStart = "<html><span style=\"font-size:0.8em;\">";
			String htmlEnd = "</span></html>";
			continueDiscussionField.setText(htmlStart + translate("Gamecontroller not connected...") + htmlEnd);
			votingsField.setText(htmlStart + translate("Please connect them and restart application.") + htmlEnd);
			breakField.setText("-");
		}
	}

	private int getMaxFindingSeconds() {
		ApplicationData appData = Data.getInstance().getAppData();
		try {
			return Integer.parseInt(appData.getSetting(AppSettingKey.APP_FINDING_WARNING_TIME)) * 60;
		} catch (DataException | NumberFormatException e) {
			return 3 * 60;
		}
	}

	private int getMaxProtocolSeconds() {
		ApplicationData appData = Data.getInstance().getAppData();
		try {
			return Integer.parseInt(appData.getSetting(APP_PROTOCOL_WARNING_TIME)) * 60;
		} catch (DataException | NumberFormatException e) {
			return 120 * 60;
		}
	}

	private String intTimeToString(int seconds) {
		return seconds / 60 + translate("min") + " " + seconds % 60 + translate("sec");
	}

	private void addTotalTime() {
		JLabel totalDurationLabel = new JLabel(translate("Total Duration:"));
		totalDurationLabel.setFont(STANDARD_STATUS_FONT);
		GridBagConstraints totalDurationGridConstraints = new GridBagConstraints();
		totalDurationGridConstraints.insets = new Insets(15, 0, 0, 15);
		totalDurationGridConstraints.gridx = 0;
		totalDurationGridConstraints.gridy = 1;
		totalDurationGridConstraints.anchor = GridBagConstraints.EAST;
		add(totalDurationLabel, totalDurationGridConstraints);

		totalDurationProgress = new JProgressBar();
		totalDurationProgress.setFont(STANDARD_STATUS_FONT);
		totalDurationProgress.setStringPainted(true);
		GridBagConstraints totalDurationProgressGridConstraints = new GridBagConstraints();
		totalDurationProgressGridConstraints.insets = new Insets(15, 0, 0, 15);
		totalDurationProgressGridConstraints.fill = GridBagConstraints.BOTH;
		totalDurationProgressGridConstraints.gridwidth = 2;
		totalDurationProgressGridConstraints.gridx = 1;
		totalDurationProgressGridConstraints.gridy = 1;
		totalDurationProgressGridConstraints.anchor = GridBagConstraints.EAST;
		add(totalDurationProgress, totalDurationProgressGridConstraints);
	}

	private void addFindingTime() {
		JLabel findingTimeLabel = new JLabel(translate("Finding Duration:"));
		findingTimeLabel.setFont(STANDARD_STATUS_FONT);
		GridBagConstraints findingTimeLabelGridConstraints = new GridBagConstraints();
		findingTimeLabelGridConstraints.insets = new Insets(0, 0, 0, 15);
		findingTimeLabelGridConstraints.gridx = 0;
		findingTimeLabelGridConstraints.gridy = 2;
		findingTimeLabelGridConstraints.anchor = GridBagConstraints.EAST;
		add(findingTimeLabel, findingTimeLabelGridConstraints);

		findingTimeField = new JLabel();
		findingTimeField.setFont(STANDARD_STATUS_FONT);
		GridBagConstraints findingTimeFieldGridConstraints = new GridBagConstraints();
		findingTimeFieldGridConstraints.gridx = 1;
		findingTimeFieldGridConstraints.gridy = 2;
		findingTimeFieldGridConstraints.anchor = GridBagConstraints.WEST;
		add(findingTimeField, findingTimeFieldGridConstraints);
	}

	private void addHurryUp() {
		hurryUpImage = new HurryUpImage();
		GridBagConstraints hurryUpPanelGridConstraints = new GridBagConstraints();
		hurryUpPanelGridConstraints.fill = GridBagConstraints.BOTH;
		hurryUpPanelGridConstraints.gridheight = GridBagConstraints.REMAINDER;
		hurryUpPanelGridConstraints.gridwidth = GridBagConstraints.REMAINDER;
		hurryUpPanelGridConstraints.gridx = 2;
		hurryUpPanelGridConstraints.gridy = 2;
		hurryUpPanelGridConstraints.insets = new Insets(15, 15, 15, 15);
		hurryUpPanelGridConstraints.anchor = GridBagConstraints.EAST;
		add(hurryUpImage, hurryUpPanelGridConstraints);
	}

	private void addBreak() {
		JLabel breakLabel = new JLabel(translate("Break:"));
		breakLabel.setFont(STANDARD_STATUS_FONT);
		GridBagConstraints continueDiscussionLabelGridConstraints = new GridBagConstraints();
		continueDiscussionLabelGridConstraints.insets = new Insets(0, 0, 0, 15);
		continueDiscussionLabelGridConstraints.gridx = 0;
		continueDiscussionLabelGridConstraints.gridy = 3;
		continueDiscussionLabelGridConstraints.anchor = GridBagConstraints.EAST;
		add(breakLabel, continueDiscussionLabelGridConstraints);

		breakField = new HighlightedLable(STANDARD_STATUS_FONT);
		GridBagConstraints breakFieldGridConstraints = new GridBagConstraints();
		breakFieldGridConstraints.gridx = 1;
		breakFieldGridConstraints.gridy = 3;
		breakFieldGridConstraints.anchor = GridBagConstraints.WEST;
		add(breakField, breakFieldGridConstraints);
	}

	private void addContinueDiscussion() {
		JLabel continueDiscussionLabel = new JLabel(translate("Focus on Discussion:"));
		continueDiscussionLabel.setFont(STANDARD_STATUS_FONT);
		GridBagConstraints continueDiscussionLabelGridConstraints = new GridBagConstraints();
		continueDiscussionLabelGridConstraints.insets = new Insets(0, 0, 0, 15);
		continueDiscussionLabelGridConstraints.gridx = 0;
		continueDiscussionLabelGridConstraints.gridy = 4;
		continueDiscussionLabelGridConstraints.anchor = GridBagConstraints.EAST;
		add(continueDiscussionLabel, continueDiscussionLabelGridConstraints);

		continueDiscussionField = new HighlightedLable(STANDARD_STATUS_FONT);
		GridBagConstraints continueDiscussionFieldGridConstraints = new GridBagConstraints();
		continueDiscussionFieldGridConstraints.gridx = 1;
		continueDiscussionFieldGridConstraints.gridy = 4;
		continueDiscussionFieldGridConstraints.anchor = GridBagConstraints.WEST;
		add(continueDiscussionField, continueDiscussionFieldGridConstraints);
	}

	private void addVotings() {
		JLabel votingsLabel = new JLabel(translate("Votes:"));
		votingsLabel.setFont(STANDARD_STATUS_FONT);
		GridBagConstraints votingLabelGridConstraints = new GridBagConstraints();
		votingLabelGridConstraints.insets = new Insets(0, 0, 0, 15);
		votingLabelGridConstraints.gridx = 0;
		votingLabelGridConstraints.gridy = 5;
		votingLabelGridConstraints.anchor = GridBagConstraints.EAST;
		add(votingsLabel, votingLabelGridConstraints);

		votingsField = new JLabel();
		votingsField.setFont(STANDARD_STATUS_FONT);
		GridBagConstraints votingsFieldGridConstraints = new GridBagConstraints();
		votingsFieldGridConstraints.gridx = 1;
		votingsFieldGridConstraints.gridy = 5;
		votingsFieldGridConstraints.anchor = GridBagConstraints.WEST;
		add(votingsField, votingsFieldGridConstraints);
	}

}
