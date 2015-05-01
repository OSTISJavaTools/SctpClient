package net.ostis.common.sctpclient.transport;

import net.ostis.common.sctpclient.client.DefaultRequestBuilder;
import net.ostis.common.sctpclient.client.RequestBuilder;
import net.ostis.common.sctpclient.constants.ScElementType;
import net.ostis.common.sctpclient.constants.ScIteratorType;
import net.ostis.common.sctpclient.model.ScAddress;
import net.ostis.common.sctpclient.model.ScIterator;
import net.ostis.common.sctpclient.model.ScIteratorFactory;
import net.ostis.common.sctpclient.model.request.RequestHeaderType;
import net.ostis.common.sctpclient.model.request.SctpRequest;
import net.ostis.common.sctpclient.transport.SctpRequestBytesBuilder;

import org.junit.Assert;
import org.junit.Test;

public class SctpRequestBuilderTest {

    @Test
    public void checkCommandRequestBytesSuccessTest() {
	ScAddress checkedAddress = new ScAddress(0, 135);

	RequestBuilder requestBuilder = new DefaultRequestBuilder();
	SctpRequest request = requestBuilder.buildRequest(RequestHeaderType.CHECK_ELEMENT, checkedAddress);
	byte[] reqBytes = SctpRequestBytesBuilder.build(request);
	byte[] expectedBytes = new byte[] { 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, -121, 0 };

	Assert.assertArrayEquals(expectedBytes, reqBytes);
    }

    @Test
    public void searchIteratorRequestBytesSuccesTest() {
	ScAddress addressFirst = new ScAddress(0, 135);
	ScElementType elemntTypeSecond = ScElementType.SC_TYPE_ARC_POS;
	ScElementType elementTypeThird = ScElementType.SC_TYPE_NODE;
	ScIterator iterator3FAA = ScIteratorFactory.create3FAA(addressFirst, elemntTypeSecond, elementTypeThird);

	RequestBuilder requestBuilder = new DefaultRequestBuilder();
	SctpRequest checkedRequest = requestBuilder.buildRequest(RequestHeaderType.ITERATOR_SEARCH,
		ScIteratorType.SCTP_ITERATOR_3_F_A_A, iterator3FAA);

	byte[] checkedByteRequest = SctpRequestBytesBuilder.build(checkedRequest);

	byte[] expectedBytes = new byte[] { 12, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, -121, 0, -128, 0, 1, 0 };

	Assert.assertArrayEquals(expectedBytes, checkedByteRequest);
    }

}
