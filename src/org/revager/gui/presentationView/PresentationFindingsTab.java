package org.revager.gui.presentationView;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.revager.app.model.schema.Finding;

public class PresentationFindingsTab extends JPanel {

	private static final long serialVersionUID = -7499170906423144396L;

	public static final Font STANDARD_STATUS_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 35);
	public static final Font STANDARD_STATUS_FONT_BOLD = new Font(Font.SANS_SERIF, Font.BOLD, 35);

	private PreviousFindingPanel previousFindingPanel = new PreviousFindingPanel();
	private CurrentFindingPanel currentFindingPanel = new CurrentFindingPanel();
	private StatusPanel statusPanel;
	private transient Finding lastFinding;

	public PresentationFindingsTab() {
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(previousFindingPanel);
		this.add(currentFindingPanel);
		statusPanel = new StatusPanel();
		this.add(statusPanel);
	}

	public void updateFinding(Finding finding) {
		if (finding == null) {
			return;
		}
		currentFindingPanel.setFinding(finding);
		if (lastFinding != finding) {
			if (lastFinding != null) {
				previousFindingPanel.setFiding(lastFinding);
			}
			lastFinding = finding;
		}
		statusPanel.setFinding(finding);
	}
}
