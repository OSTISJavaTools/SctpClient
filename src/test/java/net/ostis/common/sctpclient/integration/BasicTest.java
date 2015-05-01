package net.ostis.common.sctpclient.integration;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import net.ostis.common.sctpclient.constants.ScElementType;
import net.ostis.common.sctpclient.exception.SctpClientException;
import net.ostis.common.sctpclient.model.ScAddress;
import net.ostis.common.sctpclient.model.ScString;
import net.ostis.common.sctpclient.model.response.SctpResponse;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Contains tests for basic methods.
 * 
 * @author Tsimur_Abayeu, Andrew Nepogoda. Apr 22, 2015
 */
public class BasicTest extends AbstractIntegrationTest {

	@Override
	@BeforeTest
	public void setupClient() {
		super.setupClient();
	}

	@Test
	public void checkCreateElementMethod() throws SctpClientException {
		SctpResponse<ScAddress> createResponse = client
				.createElement(ScElementType.SC_TYPE_NODE);
		assertFalse(createResponse.isEmpty());
	}

	@Test
	public void checkDeleteElementMethod() throws SctpClientException {
		SctpResponse<ScAddress> createResponse = client
				.createElement(ScElementType.SC_TYPE_NODE);
		SctpResponse<Boolean> deleteResponse = client
				.deleteElement(createResponse.getAnswer());
		assertTrue(deleteResponse.getAnswer());
	}

	@Test
	public void checkCreateArcMethod() throws SctpClientException {
		SctpResponse<ScAddress> node1Resp = client
				.createElement(ScElementType.SC_TYPE_NODE);
		SctpResponse<ScAddress> node2Resp = client
				.createElement(ScElementType.SC_TYPE_NODE);
		final ScAddress node1 = node1Resp.getAnswer();
		final ScAddress node2 = node2Resp.getAnswer();
		SctpResponse<ScAddress> arcResp = client.createScArc(
				ScElementType.SC_TYPE_ARC_ACCESS, node1, node2);
		assertFalse(arcResp.isEmpty());
	}

	@Test
	public void checkCreateScLinkMethod() throws SctpClientException {
		SctpResponse<ScAddress> createResponse = client.createScLink();
		assertFalse(createResponse.isEmpty());
	}

	@Test(enabled = false)
	public void checkArcBeginAndEndMethod() throws SctpClientException {
		SctpResponse<ScAddress> node1Resp = client
				.createElement(ScElementType.SC_TYPE_NODE);
		SctpResponse<ScAddress> node2Resp = client
				.createElement(ScElementType.SC_TYPE_NODE);
		final ScAddress node1 = node1Resp.getAnswer();
		final ScAddress node2 = node2Resp.getAnswer();
		SctpResponse<ScAddress> arcResp = client.createScArc(
				ScElementType.SC_TYPE_ARC_ACCESS, node1, node2);
		final ScAddress arc = arcResp.getAnswer();
		SctpResponse<List<ScAddress>> begEndResp = client
				.getArcBeginAndEnd(arc);
		assertFalse(begEndResp.isEmpty());
		final ScAddress begElement = begEndResp.getAnswer().get(0);
		final ScAddress endElement = begEndResp.getAnswer().get(1);
		assertEquals(node1, begElement);
		assertEquals(node2, endElement);
	}

	@Test
	public void checkSearchElementBySystemIdentifierMethod()
			throws SctpClientException {
		SctpResponse<ScAddress> response = client.searchElement(new ScString(
				"lang_en"));
		assertFalse(response.isEmpty());
	}

	@Test
	public void checkSearchLinksMethod() throws SctpClientException {
		SctpResponse<List<ScAddress>> response = client
				.searchScLinks(new ScString("Русский язык"));
		assertFalse(response.isEmpty());
	}

	// TODO: should be enabled after error at sc-machine is fixed.
	@Test(enabled = false)
	public void checkSetSystemIdentifierMethod() throws SctpClientException {
		SctpResponse<ScAddress> nodeResp = client
				.createElement(ScElementType.SC_TYPE_NODE);
		final ScAddress newElement = nodeResp.getAnswer();
		final String idtf = "test1_idtf";
		SctpResponse<Boolean> setSysIdtfResp = client.setSystemIdentifier(
				newElement, new ScString(idtf));
		assertTrue(setSysIdtfResp.getAnswer());
		SctpResponse<ScAddress> getElementResp = client
				.searchElement(new ScString(idtf));
		ScAddress reloadElem = getElementResp.getAnswer();
		assertEquals(newElement, reloadElem);
	}

	@Test
	public void checkGetAndSetLinkContentMethods() throws SctpClientException {
		SctpResponse<ScAddress> createResponse = client.createScLink();
		final ScAddress link = createResponse.getAnswer();
		final String linkContent = "test content";
		final ScString content = new ScString(linkContent);
		SctpResponse<Boolean> setContentResponse = client.setScRefContent(link,
				content);
		assertTrue(setContentResponse.getAnswer());
		SctpResponse<String> getContentResponse = client.getScLinkContent(link);
		assertFalse(getContentResponse.isEmpty());
		assertEquals(linkContent, getContentResponse.getAnswer());
	}
}
