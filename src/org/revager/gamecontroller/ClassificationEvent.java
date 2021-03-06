package org.revager.gamecontroller;

/**
 * React on classification key press events.
 */
public class ClassificationEvent extends DashboardEvent {

	private final Classification classification;
	private final int owner;

	public ClassificationEvent(Dashboard dashboard, int owner, Classification classification) {
		super(dashboard);
		this.classification = classification;
		this.owner = owner;
	}

	@Override
	public boolean delayCallback() {
		return false;
	}

	@Override
	public void callback() {
		dashboard.addClassification(eventFinding, owner, classification);
	}

}
