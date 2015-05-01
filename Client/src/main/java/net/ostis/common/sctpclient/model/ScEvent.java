package net.ostis.common.sctpclient.model;

/**
 * Wrapper class for information about happened sc-event.
 * @author Tsimur_Abayeu
 * Apr 24, 2015
 */
public class ScEvent {

	private int subscriptionId;

	private ScAddress element;

	private ScAddress argument;

	public ScEvent() {
		super();
	}

	public ScEvent(final int subscriptionId,final ScAddress element, final ScAddress argument) {
		super();
		this.subscriptionId = subscriptionId;
		this.element = element;
		this.argument = argument;
	}

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public ScAddress getElement() {
		return element;
	}

	public void setElement(ScAddress element) {
		this.element = element;
	}

	public ScAddress getArgument() {
		return argument;
	}

	public void setArgument(ScAddress argument) {
		this.argument = argument;
	}
}
