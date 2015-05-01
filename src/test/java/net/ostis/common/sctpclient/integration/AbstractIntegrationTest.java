package net.ostis.common.sctpclient.integration;

import net.ostis.common.sctpclient.client.SctpClient;
import net.ostis.common.sctpclient.client.SctpClientImpl;

/**
 * Base class for every integration test class.
 * @author Tsimur_Abayeu
 * Apr 22, 2015
 */
public abstract class AbstractIntegrationTest {

	private static final String SERVER_URL = "localhost";

	private static final int SERVER_PORT = 55770;

	protected SctpClient client;

    public void setupClient() {
    	client = new SctpClientImpl();
    	client.init(SERVER_URL, SERVER_PORT);
    }
}
