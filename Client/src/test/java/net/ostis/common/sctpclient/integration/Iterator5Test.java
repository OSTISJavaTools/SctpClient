package net.ostis.common.sctpclient.integration;

import static net.ostis.common.sctpclient.constants.ScElementType.SC_TYPE_ARC_POS;
import static net.ostis.common.sctpclient.constants.ScElementType.SC_TYPE_NODE;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import net.ostis.common.sctpclient.constants.ScIteratorType;
import net.ostis.common.sctpclient.exception.SctpClientException;
import net.ostis.common.sctpclient.model.ScAddress;
import net.ostis.common.sctpclient.model.ScIterator;
import net.ostis.common.sctpclient.model.ScParameter;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Contains tests for methods dealing with iterators
 * for constructions consisting of 5 elements.
 * @author Tsimur_Abayeu, Andrew Nepogoda.
 * Apr 22, 2015
 */
public class Iterator5Test extends AbstractIntegrationTest {

	private ScAddress el1;

    private ScAddress el3;

    private ScAddress el5;

    private ScAddress el7;

    private ScAddress el9;

    private ScAddress arc2;

    private ScAddress arc4;

    private ScAddress arc6;

    private ScAddress arc8;

	@Override
	@BeforeTest
	public void setupClient() {
		super.setupClient();
	}

    @BeforeTest(dependsOnMethods = "setupClient")
    public void setupElements() throws SctpClientException {
        el1 = client.createElement(SC_TYPE_NODE).getAnswer();
        el3 = client.createElement(SC_TYPE_NODE).getAnswer();
        el5 = client.createElement(SC_TYPE_NODE).getAnswer();
        el7 = client.createElement(SC_TYPE_NODE).getAnswer();
        el9 = client.createElement(SC_TYPE_NODE).getAnswer();
        arc2 = client.createScArc(SC_TYPE_ARC_POS,el1, el3).getAnswer();
        arc4 = client.createScArc(SC_TYPE_ARC_POS,el5, arc2).getAnswer();
        arc6 = client.createScArc(SC_TYPE_ARC_POS,el1, el7).getAnswer();
        arc8 = client.createScArc(SC_TYPE_ARC_POS,el9, arc6).getAnswer();
    }

    @AfterTest
    public void cleanElements() throws SctpClientException {
        client.deleteElement(el1);
        client.deleteElement(el3);
        client.deleteElement(el5);
        client.deleteElement(el7);
        client.deleteElement(el9);
        client.deleteElement(arc2);
        client.deleteElement(arc4);
        client.deleteElement(arc6);
        client.deleteElement(arc8);
    }

    private List<ScIterator> createIterator(
    		final ScParameter firstParameter,
    		final ScParameter secondParameter,
    		final ScParameter thirdParameter,
    		final ScParameter fourthParameter,
    		final ScParameter fifthParameter,
    		final ScIteratorType iteratorType) throws SctpClientException {
    	List<ScParameter> params = new ArrayList<>();
    	params.add(firstParameter);
    	params.add(secondParameter);
    	params.add(thirdParameter);
    	params.add(fourthParameter);
    	params.add(fifthParameter);
    	return client.searchByIterator(iteratorType, params).getAnswer();
    }

    @Test
	public void checkScIterator5_A_A_F_A_A() throws SctpClientException {
    	List<ScIterator> result = createIterator(
    			SC_TYPE_NODE,
    			SC_TYPE_ARC_POS,
    			el3,
    			SC_TYPE_ARC_POS,
    			SC_TYPE_NODE,
    			ScIteratorType.SCTP_ITERATOR_5_A_A_F_A_A);
    	assertEquals(result.size(), 1);
    	assertEquals(el1, result.get(0).getElement(0));
    	assertEquals(arc2, result.get(0).getElement(1));
    	assertEquals(el3, result.get(0).getElement(2));
    	assertEquals(arc4, result.get(0).getElement(3));
    	assertEquals(el5, result.get(0).getElement(4));
    }

    @Test
	public void checkScIterator5_A_A_F_A_F() throws SctpClientException {
    	List<ScIterator> result = createIterator(
    			SC_TYPE_NODE,
    			SC_TYPE_ARC_POS,
    			el7,
    			SC_TYPE_ARC_POS,
    			el9,
    			ScIteratorType.SCTP_ITERATOR_5_A_A_F_A_F);
    	assertEquals(result.size(), 1);
    	assertEquals(el1, result.get(0).getElement(0));
    	assertEquals(arc6, result.get(0).getElement(1));
    	assertEquals(el7, result.get(0).getElement(2));
    	assertEquals(arc8, result.get(0).getElement(3));
    	assertEquals(el9, result.get(0).getElement(4));
    }

    @Test
	public void checkScIterator5_F_A_A_A_A() throws SctpClientException {
    	List<ScIterator> result = createIterator(
    			el1,
    			SC_TYPE_ARC_POS,
    			SC_TYPE_NODE,
    			SC_TYPE_ARC_POS,
    			SC_TYPE_NODE,
    			ScIteratorType.SCTP_ITERATOR_5_F_A_A_A_A);
    	assertEquals(result.size(), 2);
    	assertEquals(el1, result.get(0).getElement(0));
    	assertEquals(el1, result.get(1).getElement(0));
    }

    @Test
	public void checkScIterator5_F_A_F_A_A() throws SctpClientException {
    	List<ScIterator> result = createIterator(
    			el1,
    			SC_TYPE_ARC_POS,
    			el7,
    			SC_TYPE_ARC_POS,
    			SC_TYPE_NODE,
    			ScIteratorType.SCTP_ITERATOR_5_F_A_F_A_A);
    	assertEquals(result.size(), 1);
    	assertEquals(el1, result.get(0).getElement(0));
    	assertEquals(arc6, result.get(0).getElement(1));
    	assertEquals(el7, result.get(0).getElement(2));
    	assertEquals(arc8, result.get(0).getElement(3));
    	assertEquals(el9, result.get(0).getElement(4));
    }

    @Test
	public void checkScIterator5_F_A_F_A_F() throws SctpClientException {
    	List<ScIterator> result = createIterator(
    			el1,
    			SC_TYPE_ARC_POS,
    			el3,
    			SC_TYPE_ARC_POS,
    			el5,
    			ScIteratorType.SCTP_ITERATOR_5_F_A_F_A_F);
    	assertEquals(result.size(), 1);
    	assertEquals(el1, result.get(0).getElement(0));
    	assertEquals(arc2, result.get(0).getElement(1));
    	assertEquals(el3, result.get(0).getElement(2));
    	assertEquals(arc4, result.get(0).getElement(3));
    	assertEquals(el5, result.get(0).getElement(4));
    }

    @Test
	public void checkScIterator5_F_A_A_A_F() throws SctpClientException {
    	List<ScIterator> result = createIterator(
    			el1,
    			SC_TYPE_ARC_POS,
    			SC_TYPE_NODE,
    			SC_TYPE_ARC_POS,
    			el5,
    			ScIteratorType.SCTP_ITERATOR_5_F_A_F_A_F);
    	assertEquals(result.size(), 1);
    	assertEquals(el1, result.get(0).getElement(0));
    	assertEquals(arc2, result.get(0).getElement(1));
    	assertEquals(el3, result.get(0).getElement(2));
    	assertEquals(arc4, result.get(0).getElement(3));
    	assertEquals(el5, result.get(0).getElement(4));
    }
}
