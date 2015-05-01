package net.ostis.common.sctpclient.transport;

import java.io.InputStream;

import net.ostis.common.sctpclient.exception.SctpClientException;
import net.ostis.common.sctpclient.model.request.SctpRequest;
import net.ostis.common.sctpclient.model.response.SctpResponse;

interface SctpResponseBuilder<T> {

    SctpResponse<T> build(InputStream source, SctpRequest sctpRequest) throws SctpClientException;

}
