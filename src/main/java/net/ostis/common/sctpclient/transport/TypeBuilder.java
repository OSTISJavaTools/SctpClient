package net.ostis.common.sctpclient.transport;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.commons.lang3.ArrayUtils;

import net.ostis.common.sctpclient.constants.ScParameterSize;
import net.ostis.common.sctpclient.model.ScAddress;
import net.ostis.common.sctpclient.utils.ByteUtils;

class TypeBuilder {

	private static final int SEGMENT_SIZE = 2;

	private static final int OFFSET_SIZE = 2;

	public static ScAddress buildScAddress(byte[] bytes) {

		byte[] segmentBytes = ArrayUtils.subarray(bytes, 0, SEGMENT_SIZE);
		byte[] offsetBytes = ArrayUtils.subarray(bytes, SEGMENT_SIZE,
				SEGMENT_SIZE + OFFSET_SIZE);
		int segment = ByteUtils.unsignedShortToInt(segmentBytes);
		int offset = ByteUtils.unsignedShortToInt(offsetBytes);
		return new ScAddress(segment, offset);
	}

	public static ScAddress buildScAddress(InputStream source)
			throws IOException {

		byte[] segmentBytes = ByteUtils
				.getBytesFromSource(source, SEGMENT_SIZE);
		byte[] offsetBytes = ByteUtils.getBytesFromSource(source, OFFSET_SIZE);
		int segment = ByteUtils.unsignedShortToInt(segmentBytes);
		int offset = ByteUtils.unsignedShortToInt(offsetBytes);
		return new ScAddress(segment, offset);
	}

}
