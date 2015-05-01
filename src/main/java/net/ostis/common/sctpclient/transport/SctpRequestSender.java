package net.ostis.common.sctpclient.transport;

import net.ostis.common.sctpclient.exception.InitializationException;
import net.ostis.common.sctpclient.exception.SctpClientException;
import net.ostis.common.sctpclient.exception.ShutdownException;
import net.ostis.common.sctpclient.model.request.SctpRequest;
import net.ostis.common.sctpclient.model.response.SctpResponse;

public interface SctpRequestSender {

    void init(String host, int port) throws InitializationException;

    void shutdown() throws ShutdownException;

    <Type> SctpResponse<Type> sendRequest(SctpRequest request) throws SctpClientException;
}
