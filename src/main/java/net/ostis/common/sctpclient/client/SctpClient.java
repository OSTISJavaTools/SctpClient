package net.ostis.common.sctpclient.client;

import java.util.List;

import net.ostis.common.sctpclient.constants.ScElementType;
import net.ostis.common.sctpclient.constants.ScEventType;
import net.ostis.common.sctpclient.constants.ScIteratorType;
import net.ostis.common.sctpclient.exception.SctpClientException;
import net.ostis.common.sctpclient.model.ScAddress;
import net.ostis.common.sctpclient.model.ScEvent;
import net.ostis.common.sctpclient.model.ScIterator;
import net.ostis.common.sctpclient.model.ScParameter;
import net.ostis.common.sctpclient.model.ScString;
import net.ostis.common.sctpclient.model.response.SctpResponse;

public interface SctpClient {

    public void init(String host, int port);

    public void shutdown();

    public SctpResponse<String> getScLinkContent(ScAddress address) throws SctpClientException;

    public SctpResponse<Boolean> checkElementExistence(ScAddress address)
            throws SctpClientException;

    public SctpResponse<List<ScIterator>> searchByIterator(ScIteratorType iteratorType,
            List<ScParameter> params) throws SctpClientException;

    public SctpResponse<ScAddress> searchElement(ScString identifier) throws SctpClientException;

    public SctpResponse<Boolean> deleteElement(ScAddress address) throws SctpClientException;

    public SctpResponse<ScAddress> createElement(ScElementType type) throws SctpClientException;

    public SctpResponse<ScAddress> createScLink() throws SctpClientException;

    public SctpResponse<ScAddress> createScArc(ScElementType type, ScAddress begAddress,
            ScAddress endAddress) throws SctpClientException;

    public SctpResponse<List<ScAddress>> getArcBeginAndEnd(ScAddress arcAddress)
            throws SctpClientException;

    public SctpResponse<List<ScAddress>> searchScLinks(ScString content) throws SctpClientException;

    public SctpResponse<Boolean> setScRefContent(ScAddress address, ScString content)
            throws SctpClientException;

    public SctpResponse<Boolean> setSystemIdentifier(ScAddress address, ScString identifier)
            throws SctpClientException;

    /**
     * Creates subscription for event of certain type for given sc-element.
     *
     * @param 	scEventType
     * @param 	address
     * @return 	response containing id of created subscription.
     * 			Can be used later to check if monitored event has happened
     * 			({@link SctpClient#getListOfEventsHappened()}).
     * 			empty response if error occurred while trying to create subscription.
     * @throws SctpClientException
     */
    public SctpResponse<Integer> createEventSubscription(ScEventType scEventType, ScAddress address)
    		throws SctpClientException;

    /**
     * Removes subscription having given subscriptionId.
     *
     * @param 	subscriptionId
     * @return 	response containing true if subscription was removed successfully
     * 			response containing false otherwise.
     * @throws SctpClientException
     */
    public SctpResponse<Boolean> deleteEventSubscription(int subscriptionId)
    		throws SctpClientException;

    /**
     *	Retrieves list of sc-events happened in sc-memory.
     *
     * @return 	{@link List} of {@link ScEvent}. Can be empty if there is no happened events.
     * 			empty response if error occurred.
     * @throws SctpClientException
     */
    public SctpResponse<List<ScEvent>> getListOfEventsHappened()
    		throws SctpClientException;

    /**
     * Retrieves list of sc-events happened in sc-memory by given subscriptionId.
     *
     * @param	subscriptionId
     * @return	{@link List} of {@link ScEvent}. Can be empty if there is no happened events.
     * 			empty response if error occurred.
     * @throws SctpClientException
     */
    public SctpResponse<List<ScEvent>> getListOfEventsHappenedBySubscriptionId(int subscriptionId) throws SctpClientException;
}
