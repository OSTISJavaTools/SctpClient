package net.ostis.common.sctpclient.client;

import net.ostis.common.sctpclient.model.ScParameter;
import net.ostis.common.sctpclient.model.request.RequestHeaderType;
import net.ostis.common.sctpclient.model.request.SctpRequest;
import net.ostis.common.sctpclient.model.request.SctpRequestBody;
import net.ostis.common.sctpclient.model.request.SctpRequestHeader;

public class DefaultRequestBuilder implements RequestBuilder {

    @Override
    public SctpRequest buildRequest(RequestHeaderType headerType, ScParameter... parameters) {

        SctpRequest newRequest = new SctpRequest();
        SctpRequestBody requestBody = new SctpRequestBody();
        requestBody.addParameters(parameters);
        SctpRequestHeader requestHeader = headerType.getRequestHeader();
        requestHeader.setArgumentSize(requestBody.getByteLenght());
        newRequest.setHeader(requestHeader);
        newRequest.setBody(requestBody);

        return newRequest;
    }

}
