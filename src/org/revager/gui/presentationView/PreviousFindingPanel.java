package org.revager.gui.presentationView;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.NONE;
import static org.revager.app.model.Data.translate;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.revager.app.model.schema.Finding;
import org.revager.gui.UI;
import org.revager.tools.GUITools;

/**
 * GUI element which displays the previous {@link Finding}.
 * 
 * @see PresentationFindingsTab
 */
public class PreviousFindingPanel extends JPanel {

	private static final long serialVersionUID = 110372565207228520L;

	private transient Finding finding = new Finding();
	private GridBagLayout layout = new GridBagLayout();
	private JLabel titleLable = new JLabel();
	private JLabel severityLable = new JLabel();
	private JTextArea descriptionTextArea = new JTextArea();
	private JScrollPane descriptionTextField;

	public PreviousFindingPanel() {
		super();
		setLayout(layout);
		setBackground(UI.COMPACT_VIEW_BG);
		setBorder(UI.STANDARD_BORDER);

		severityLable.setFont(UI.HUGE_FONT_BOLD);
		severityLable.setForeground(Color.DARK_GRAY);

		titleLable.setText(translate("Previous Finding:"));
		titleLable.setFont(UI.HUGE_FONT_BOLD);

		descriptionTextField = GUITools.setIntoScrllPn(descriptionTextArea);
		GUITools.scrollToTop(descriptionTextField);

		descriptionTextArea.setEditable(false);
		descriptionTextArea.setFont(UI.HUGE_FONT);

		GUITools.addComponent(this, layout, titleLable, 0, 0, 1, 1, 0.0, 0.0, 10, 10, 0, 10, BOTH, EAST);
		GUITools.addComponent(this, layout, severityLable, 1, 0, 1, 1, 0.0, 0.0, 10, 10, 0, 10, NONE, EAST);
		GUITools.addComponent(this, layout, descriptionTextField, 0, 1, 2, 1, 1.0, 1.0, 10, 10, 10, 10, BOTH, EAST);
	}

	public void setFiding(Finding finding) {
		this.finding = finding;
		updateDisplay();
	}

	private void updateDisplay() {
		titleLable.setText(translate("Previous Finding:") + " " + translate("Finding") + " " + finding.getId());
		severityLable.setText(translate(finding.getSeverity()));
		descriptionTextArea.setText(finding.getDescription());
	}

}
