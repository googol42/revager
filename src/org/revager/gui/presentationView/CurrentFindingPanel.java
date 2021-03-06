package org.revager.gui.presentationView;

import static java.awt.GridBagConstraints.BOTH;
import static java.awt.GridBagConstraints.EAST;
import static java.awt.GridBagConstraints.NONE;
import static java.awt.GridBagConstraints.NORTHWEST;
import static java.awt.GridBagConstraints.REMAINDER;
import static java.awt.GridBagConstraints.WEST;
import static org.revager.app.model.Data.translate;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import org.revager.app.model.schema.Finding;
import org.revager.gui.UI;
import org.revager.gui.helpers.DefaultTableHeaderCellRenderer;
import org.revager.gui.models.FindAspTableModel;
import org.revager.gui.models.FindExtRefTableModel;
import org.revager.gui.models.FindRefTableModel;
import org.revager.tools.GUITools;

/**
 * GUI element which displays the current {@link Finding}.
 * 
 * @see PresentationFindingsTab
 */
public class CurrentFindingPanel extends JPanel {

	private static final long serialVersionUID = 1898465156775918217L;
	private static final int ROW_HEIGHT = 32;

	private transient Finding finding = new Finding();
	private GridBagLayout layout = new GridBagLayout();
	private JLabel labelFindingTitle = new JLabel();
	private JLabel labelFindingSeverity = new JLabel();
	private JTextArea textDescription = new JTextArea();
	private FindRefTableModel modelReferences;
	private FindExtRefTableModel modelExtReferences;
	private FindAspTableModel modelAspects;
	private JTable tableReferences;
	private JTable tableExtReferences;
	private JTable tableAspects;
	private JScrollPane scrollDescription;
	private JScrollPane scrollReferences;
	private JScrollPane scrollExtReferences;
	private JScrollPane scrollAspects;

	public CurrentFindingPanel() {
		super();
		setLayout(layout);
		setPreferredSize(new Dimension(100, 280));
		setBorder(UI.STANDARD_BORDER);
		setBackground(UI.EDIT_VIEW_BG);

		modelReferences = new FindRefTableModel(finding);
		modelExtReferences = new FindExtRefTableModel(finding);
		modelAspects = new FindAspTableModel(finding);

		tableAspects = setupTable(modelAspects);
		tableReferences = setupTable(modelReferences);
		tableExtReferences = setupTable(modelExtReferences);

		scrollAspects = new JScrollPane(tableAspects);
		scrollReferences = new JScrollPane(tableReferences);
		scrollExtReferences = new JScrollPane(tableExtReferences);

		scrollAspects.getViewport().setBackground(Color.WHITE);
		scrollReferences.getViewport().setBackground(Color.WHITE);
		scrollExtReferences.getViewport().setBackground(Color.WHITE);

		labelFindingSeverity.setFont(UI.VERY_HUGE_HUGE_FONT_BOLD);
		labelFindingSeverity.setForeground(Color.DARK_GRAY);

		labelFindingTitle.setFont(UI.VERY_HUGE_HUGE_FONT_BOLD);

		scrollDescription = GUITools.setIntoScrllPn(textDescription);
		GUITools.scrollToTop(scrollDescription);

		textDescription.setEditable(false);
		textDescription.setFont(UI.VERY_HUGE_HUGE_FONT);

		GUITools.addComponent(this, layout, labelFindingTitle, 0, 0, 1, 1, 0.7, 0.0, 10, 10, 0, 10, NONE, WEST);
		GUITools.addComponent(this, layout, labelFindingSeverity, 1, 0, 1, 1, 0.3, 0.0, 10, 10, 0, 10, NONE, EAST);
		GUITools.addComponent(this, layout, scrollDescription, 0, 1, 1, REMAINDER, 1.0, 1.0, 10, 10, 10, 10, BOTH,
				NORTHWEST);
		GUITools.addComponent(this, layout, scrollReferences, 1, 1, 1, 1, 0.5, 0.4, 10, 10, 0, 10, BOTH, NORTHWEST);
		GUITools.addComponent(this, layout, scrollExtReferences, 1, 2, 1, 1, 0.5, 0.3, 10, 10, 10, 10, BOTH, NORTHWEST);
		GUITools.addComponent(this, layout, scrollAspects, 1, 3, 1, 1, 0.5, 0.4, 10, 10, 10, 10, BOTH, NORTHWEST);
	}

	public void setFinding(Finding finding) {
		this.finding = finding;
		updateDisplay();
	}

	public Finding getFinding() {
		return finding;
	}

	private JTable setupTable(TableModel tableModel) {
		JTable table = GUITools.newStandardTable(tableModel, true);
		table.getColumnModel().getColumn(0).setHeaderRenderer(new FindingPanelHeadRenderer());
		table.getColumnModel().getColumn(0).setCellRenderer(new FindingPanelCellRenderer());
		table.setRowHeight(ROW_HEIGHT);
		return table;
	}

	private void updateDisplay() {
		Dimension size = new Dimension(500, (int) labelFindingTitle.getPreferredSize().getHeight());
		labelFindingSeverity.setHorizontalAlignment(JLabel.RIGHT);
		labelFindingSeverity.setMaximumSize(size);
		labelFindingSeverity.setMinimumSize(size);
		labelFindingSeverity.setPreferredSize(size);
		labelFindingTitle.setText(translate("Current Finding:") + " " + translate("Finding") + " " + finding.getId());
		labelFindingSeverity.setText(translate(finding.getSeverity()));
		textDescription.setText(finding.getDescription());
		modelReferences.setFinding(finding);
		modelReferences.fireTableDataChanged();
		modelExtReferences.setFinding(finding);
		modelExtReferences.fireTableDataChanged();
		modelAspects.setFinding(finding);
		modelAspects.fireTableDataChanged();
	}

	private class FindingPanelCellRenderer extends DefaultTableCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public Font getFont() {
			return UI.VERY_LARGE_FONT;
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setToolTipText(GUITools.getTextAsHtml("<font size=\"5\">" + (String) value + "</font>"));
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		}
	};

	private class FindingPanelHeadRenderer extends DefaultTableHeaderCellRenderer {
		private static final long serialVersionUID = 1L;

		@Override
		public Font getFont() {
			return UI.VERY_LARGE_FONT;
		}
	};

}
