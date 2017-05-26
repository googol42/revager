package org.revager.gui.presentationView;

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
import static org.revager.app.model.Data.translate;
import org.revager.app.model.DataException;
import org.revager.app.model.appdata.AppSettingKey;
import org.revager.app.model.schema.Finding;
import org.revager.gamecontroller.Dashboard;
import org.revager.gui.UI;

public class StatusPanel extends JPanel {

	public static final Font STANDARD_STATUS_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 35);
	public static final Font STANDARD_STATUS_FONT_BOLD = new Font(Font.SANS_SERIF, Font.BOLD, 35);

	private static final long serialVersionUID = 4044468994799470896L;

	private transient Finding finding;
	private JProgressBar totalDurationProgress;
	private JLabel findingTimeField;
	private HurryUpImage hurryUpImage;
	private JLabel breakField;
	private JLabel continueDiscussionField;
	private JLabel votingsField;
	private int totalProtocolSeconds;
	private Dashboard dashboard;

	public StatusPanel() {
		dashboard = new Dashboard();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] { 0.03, 0.17, 0.8 };
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
		this.finding = finding;
		dashboard.setFinding(finding);
		updateDisplay();
	}

	private void updateDisplay() {
		ApplicationData appData = Data.getInstance().getAppData();
		int maxProtocolSeconds;
		try {
			maxProtocolSeconds = Integer.parseInt(appData.getSetting(APP_PROTOCOL_WARNING_TIME)) * 60;
		} catch (DataException | NumberFormatException e) {
			maxProtocolSeconds = 120 * 60;
		}
		int maxFindingSeconds;
		try {
			maxFindingSeconds = Integer.parseInt(appData.getSetting(AppSettingKey.APP_FINDING_WARNING_TIME)) * 60;
		} catch (DataException | NumberFormatException e) {
			maxFindingSeconds = 5 * 60;
		}

		totalDurationProgress.setMaximum(maxProtocolSeconds);
		totalDurationProgress.setValue(totalProtocolSeconds);
		totalDurationProgress
				.setString(totalProtocolSeconds / 60 + translate("min") + " " + totalProtocolSeconds % 60 + "sec");
		findingTimeField.setText(dashboard.getFindingTimeText());
		breakField.setText(dashboard.getBreakText());
		// TODO: do not use totalProtocolSeconds
		hurryUpImage.setImageOpacity((float) totalProtocolSeconds / maxFindingSeconds);
		continueDiscussionField.setText("" + dashboard.getContinue());
		votingsField.setText("" + dashboard.getVotings());
	}

	private void addTotalTime() {
		JLabel totalDurationLabel = new JLabel("Gesamtdauer:");
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
		JLabel findingTimeLabel = new JLabel("Befunddauer:");
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
		hurryUpPanelGridConstraints.anchor = GridBagConstraints.SOUTHEAST;
		add(hurryUpImage, hurryUpPanelGridConstraints);
	}

	private void addBreak() {
		JLabel breakLabel = new JLabel("Pause:");
		breakLabel.setFont(STANDARD_STATUS_FONT);
		GridBagConstraints continueDiscussionLabelGridConstraints = new GridBagConstraints();
		continueDiscussionLabelGridConstraints.insets = new Insets(0, 0, 0, 15);
		continueDiscussionLabelGridConstraints.gridx = 0;
		continueDiscussionLabelGridConstraints.gridy = 3;
		continueDiscussionLabelGridConstraints.anchor = GridBagConstraints.EAST;
		add(breakLabel, continueDiscussionLabelGridConstraints);

		breakField = new JLabel();
		breakField.setFont(STANDARD_STATUS_FONT);
		GridBagConstraints breakFieldGridConstraints = new GridBagConstraints();
		breakFieldGridConstraints.gridx = 1;
		breakFieldGridConstraints.gridy = 3;
		breakFieldGridConstraints.anchor = GridBagConstraints.WEST;
		add(breakField, breakFieldGridConstraints);
	}

	private void addContinueDiscussion() {
		JLabel continueDiscussionLabel = new JLabel("Diskussionsfokus:");
		continueDiscussionLabel.setFont(STANDARD_STATUS_FONT);
		GridBagConstraints continueDiscussionLabelGridConstraints = new GridBagConstraints();
		continueDiscussionLabelGridConstraints.insets = new Insets(0, 0, 0, 15);
		continueDiscussionLabelGridConstraints.gridx = 0;
		continueDiscussionLabelGridConstraints.gridy = 4;
		continueDiscussionLabelGridConstraints.anchor = GridBagConstraints.EAST;
		add(continueDiscussionLabel, continueDiscussionLabelGridConstraints);

		continueDiscussionField = new JLabel();
		continueDiscussionField.setFont(STANDARD_STATUS_FONT);
		GridBagConstraints continueDiscussionFieldGridConstraints = new GridBagConstraints();
		continueDiscussionFieldGridConstraints.gridx = 1;
		continueDiscussionFieldGridConstraints.gridy = 4;
		continueDiscussionFieldGridConstraints.anchor = GridBagConstraints.WEST;
		add(continueDiscussionField, continueDiscussionFieldGridConstraints);
	}

	private void addVotings() {
		JLabel votingsLabel = new JLabel("Abgestimmt:");
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
