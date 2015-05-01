package net.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ScInt implements ScParameter {

	private static int INT_BYTE_LENGTH = 4;

    private int content;

    public ScInt(int content) {

        this.content = content;
    }

    public int getContent() {

        return content;
    }

    public void setContent(int content) {

        this.content = content;
    }

    @Override
    public byte[] getBytes() {

        ByteBuffer buffer = ByteBuffer.allocate(INT_BYTE_LENGTH);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(content);
        return buffer.array();
    }

    @Override
    public int getByteSize() {
        return INT_BYTE_LENGTH;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + content;
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
		ScInt other = (ScInt) obj;
		if (content != other.content)
			return false;
		return true;
	}
}
