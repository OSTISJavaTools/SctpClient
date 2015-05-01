package net.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.commons.lang3.ArrayUtils;

import net.ostis.common.sctpclient.constants.ScParameterSize;

public class ScAddress implements ScParameter {

    private int segment;

    private int offset;

    public ScAddress(int segment, int offset) {

        this.segment = segment;
        this.offset = offset;
    }
    
    public int getSegment() {
		return segment;
	}

	public void setSegment(int segment) {
		this.segment = segment;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
    public byte[] getBytes() {

        ByteBuffer tempBuffer = ByteBuffer.allocate(ScParameterSize.SC_ADDRESS.getSize());
        tempBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byte[] segmentArray = {(byte)(segment), (byte)(segment >> 8) };
        byte[] offsetArray = {(byte)(offset), (byte)(offset >> 8)};
        return ArrayUtils.addAll(segmentArray, offsetArray);
    }

    @Override
    public int getByteSize() {

        return ScParameterSize.SC_ADDRESS.getSize();
    }

    public String toString() {

        return "ScAddress [segment:" + this.segment + " offset:" + this.offset + "]";
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + offset;
        result = prime * result + segment;
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScAddress other = (ScAddress) obj;
        if (offset != other.offset)
            return false;
        if (segment != other.segment)
            return false;
        return true;
    }
}
