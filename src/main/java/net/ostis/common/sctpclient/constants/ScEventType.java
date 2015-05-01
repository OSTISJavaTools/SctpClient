package net.ostis.common.sctpclient.constants;

import net.ostis.common.sctpclient.model.ScParameter;

/**
 * Contains event types that can be triggered
 * for any sc-element in sc-memory.
 * @author Tsimur_Abayeu
 * Apr 24, 2015
 */
public enum ScEventType implements ScParameter {

	SC_EVENT_CREATION_OF_OUTPUT_ARC(0),
	SC_EVENT_CREATION_OF_INPUT_ARC(1),
	SC_EVENT_REMOVAL_OF_OUTPUT_ARC(2),
	SC_EVENT_REMOVAL_OF_INPUT_ARC(3),
	SC_EVENT_REMOVAL_OF_ELEMENT(4),
	;

	private int value;

	private ScEventType(final int value) {
	    this.value = value;
	}

	public long getValue() {
		return value;
	}

	@Override
	public byte[] getBytes() {
		return new byte[] { (byte) value };
	}

	@Override
	public int getByteSize() {
	    return 1;
	}
}
