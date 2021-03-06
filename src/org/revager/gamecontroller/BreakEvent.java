package org.revager.gamecontroller;

/**
 * React on break key press events.
 */
public class BreakEvent extends DashboardEvent {

	public BreakEvent(Dashboard dashboard) {
		super(dashboard);
	}

	@Override
	public void callback() {
		dashboard.addBreak();
	}

}
