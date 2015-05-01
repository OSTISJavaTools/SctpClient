package net.ostis.common.sctpclient.transport;

import net.ostis.common.sctpclient.exception.SctpClientException;
import net.ostis.common.sctpclient.model.response.SctpResponseHeader;

interface RespBodyBuilder<T> {

    T getAnswer(byte[] bytes, SctpResponseHeader responseHeader) throws SctpClientException;
}
