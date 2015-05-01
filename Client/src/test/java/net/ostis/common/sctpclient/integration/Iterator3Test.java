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
 * for constructions consisting of 3 elements.
 * @author Tsimur_Abayeu, Andrew Nepogoda.
 * Apr 22, 2015
 */
public class Iterator3Test extends AbstractIntegrationTest {

    private ScAddress el1;

    private ScAddress el3;

    private ScAddress el5;

    private ScAddress arc2;

    private ScAddress arc4;

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
        arc2 = client.createScArc(SC_TYPE_ARC_POS,el1, el3).getAnswer();
        arc4 = client.createScArc(SC_TYPE_ARC_POS,el1, el5).getAnswer();
    }

    @AfterTest
    public void cleanElements() throws SctpClientException {
        client.deleteElement(el1);
        client.deleteElement(el3);
        client.deleteElement(el5);
        client.deleteElement(arc2);
        client.deleteElement(arc4);
    }

    private List<ScIterator> createIterator(
    		final ScParameter firstParameter,
    		final ScParameter secondParameter,
    		final ScParameter thirdParameter,
    		final ScIteratorType iteratorType) throws SctpClientException {
    	List<ScParameter> params = new ArrayList<>();
    	params.add(firstParameter);
    	params.add(secondParameter);
    	params.add(thirdParameter);
    	return client.searchByIterator(iteratorType, params).getAnswer();
    }

    @Test
	public void checkScIterator3_F_A_A() throws SctpClientException {
    	List<ScIterator> result = createIterator(
    			el1,
    			SC_TYPE_ARC_POS,
    			SC_TYPE_NODE,
    			ScIteratorType.SCTP_ITERATOR_3_F_A_A);
    	assertEquals(result.size(), 2);
    	assertEquals(client.checkElementExistence(el1).getAnswer(), Boolean.TRUE);
    	assertEquals(client.checkElementExistence((ScAddress) result.get(0).getElement(0)).getAnswer(), Boolean.TRUE);
    	assertEquals(el1, result.get(0).getElement(0));
    	assertEquals(el1, result.get(1).getElement(0));
    }

    @Test
	public void checkScIterator3_A_A_F() throws SctpClientException {
    	List<ScIterator> result = createIterator(
    			SC_TYPE_NODE,
    			SC_TYPE_ARC_POS,
    			el5,
    			ScIteratorType.SCTP_ITERATOR_3_A_A_F);
    	assertEquals(result.size(), 1);
    	assertEquals(el1, result.get(0).getElement(0));
    	assertEquals(arc4, result.get(0).getElement(1));
    	assertEquals(el5, result.get(0).getElement(2));
    }

    @Test
   	public void checkScIterator3_F_A_F() throws SctpClientException {
       	List<ScIterator> result = createIterator(
       			el1,
       			SC_TYPE_ARC_POS,
       			el5,
       			ScIteratorType.SCTP_ITERATOR_3_F_A_F);
       	assertEquals(result.size(), 1);
       	assertEquals(el1, result.get(0).getElement(0));
       	assertEquals(arc4, result.get(0).getElement(1));
       	assertEquals(el5, result.get(0).getElement(2));
    }
}
