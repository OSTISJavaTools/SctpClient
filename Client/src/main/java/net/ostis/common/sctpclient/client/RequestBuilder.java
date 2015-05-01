package net.ostis.common.sctpclient.client;

import net.ostis.common.sctpclient.model.ScParameter;
import net.ostis.common.sctpclient.model.request.RequestHeaderType;
import net.ostis.common.sctpclient.model.request.SctpRequest;

public interface RequestBuilder {

    public SctpRequest buildRequest(RequestHeaderType requestHeaderType, ScParameter... parameters);

}
