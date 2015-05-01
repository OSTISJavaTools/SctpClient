package net.ostis.common.sctpclient.integration;

import static net.ostis.common.sctpclient.constants.ScElementType.SC_TYPE_ARC_POS;
import static net.ostis.common.sctpclient.constants.ScElementType.SC_TYPE_NODE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.List;

import net.ostis.common.sctpclient.constants.ScEventType;
import net.ostis.common.sctpclient.exception.SctpClientException;
import net.ostis.common.sctpclient.model.ScAddress;
import net.ostis.common.sctpclient.model.ScEvent;
import net.ostis.common.sctpclient.model.response.SctpResponse;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Contains tests for methods dealing with event subscriptions.
 * @author Tsimur_Abayeu.
 * Apr 26, 2015
 */
public class EventTest extends AbstractIntegrationTest {

    private ScAddress el1;

    private ScAddress el3;

    private ScAddress arc2;

	@Override
	@BeforeTest
	public void setupClient() {
		super.setupClient();
	}

    @BeforeTest(dependsOnMethods = "setupClient")
    public void setupElements() throws SctpClientException {
        el1 = client.createElement(SC_TYPE_NODE).getAnswer();
        el3 = client.createElement(SC_TYPE_NODE).getAnswer();
    }

    @AfterTest
    public void cleanElements() throws SctpClientException {
        client.deleteElement(el1);
        client.deleteElement(el3);
        client.deleteElement(arc2);
    }

    @Test
    public void checkEmitionOfEvents() throws SctpClientException {
    	SctpResponse<List<ScEvent>> eventsResponse = client.getListOfEventsHappened();
    	assertFalse(eventsResponse.isEmpty());
    	assertTrue(eventsResponse.getAnswer().isEmpty());
    	client.createEventSubscription(ScEventType.SC_EVENT_CREATION_OF_INPUT_ARC, el1);
    	arc2 = client.createScArc(SC_TYPE_ARC_POS,el3, el1).getAnswer();
    	eventsResponse = client.getListOfEventsHappened();
    	assertFalse(eventsResponse.isEmpty());
    	assertEquals(eventsResponse.getAnswer().size(), 1);
    	client.deleteElement(arc2);
    }

    @Test
	public void checkCreationOfInputArc() throws SctpClientException {
    	SctpResponse<Integer> eventResponse =
    			client.createEventSubscription(ScEventType.SC_EVENT_CREATION_OF_INPUT_ARC, el1);
    	assertFalse(eventResponse.isEmpty());
    	arc2 = client.createScArc(SC_TYPE_ARC_POS,el3, el1).getAnswer();
    	SctpResponse<List<ScEvent>> eventsResponse =
    			client.getListOfEventsHappenedBySubscriptionId(eventResponse.getAnswer());
    	List<ScEvent> events = eventsResponse.getAnswer();
    	assertEquals(events.size(), 1);
    	assertEquals(events.get(0).getArgument(), arc2);
    	client.deleteElement(arc2);
    }

    @Test
	public void checkCreationOfOutputArc() throws SctpClientException {
    	SctpResponse<Integer> eventResponse =
    			client.createEventSubscription(ScEventType.SC_EVENT_CREATION_OF_OUTPUT_ARC, el1);
    	assertFalse(eventResponse.isEmpty());
    	arc2 = client.createScArc(SC_TYPE_ARC_POS,el1, el3).getAnswer();
    	SctpResponse<List<ScEvent>> eventsResponse =
    			client.getListOfEventsHappenedBySubscriptionId(eventResponse.getAnswer());
    	List<ScEvent> events = eventsResponse.getAnswer();
    	assertEquals(events.size(), 1);
    	assertEquals(events.get(0).getArgument(), arc2);
    	client.deleteElement(arc2);
    }

    @Test
	public void checkRemovalOfOutputArc() throws SctpClientException {
    	arc2 = client.createScArc(SC_TYPE_ARC_POS,el1, el3).getAnswer();
    	SctpResponse<Integer> eventResponse =
    			client.createEventSubscription(ScEventType.SC_EVENT_REMOVAL_OF_OUTPUT_ARC, el1);
    	assertFalse(eventResponse.isEmpty());
    	client.deleteElement(arc2);
    	SctpResponse<List<ScEvent>> eventsResponse =
    			client.getListOfEventsHappenedBySubscriptionId(eventResponse.getAnswer());
    	List<ScEvent> events = eventsResponse.getAnswer();
    	assertEquals(events.size(), 1);
    	assertEquals(events.get(0).getArgument(), arc2);
    }

    @Test
	public void checkRemovalOfInputArc() throws SctpClientException {
    	arc2 = client.createScArc(SC_TYPE_ARC_POS,el3, el1).getAnswer();
    	SctpResponse<Integer> eventResponse =
    			client.createEventSubscription(ScEventType.SC_EVENT_REMOVAL_OF_OUTPUT_ARC, el1);
    	assertFalse(eventResponse.isEmpty());
    	client.deleteElement(arc2);
    	SctpResponse<List<ScEvent>> eventsResponse =
    			client.getListOfEventsHappenedBySubscriptionId(eventResponse.getAnswer());
    	List<ScEvent> events = eventsResponse.getAnswer();
    	assertEquals(events.size(), 1);
    	assertEquals(events.get(0).getArgument(), arc2);
    }

    @Test
	public void checkRemovalOfElement() throws SctpClientException {
    	SctpResponse<Integer> eventResponse =
    			client.createEventSubscription(ScEventType.SC_EVENT_REMOVAL_OF_ELEMENT, el1);
    	assertFalse(eventResponse.isEmpty());
    	client.deleteElement(el1);
    	SctpResponse<List<ScEvent>> eventsResponse =
    			client.getListOfEventsHappenedBySubscriptionId(eventResponse.getAnswer());
    	List<ScEvent> events = eventsResponse.getAnswer();
    	assertEquals(events.size(), 1);
    	assertNull(events.get(0).getArgument());
    }
}
