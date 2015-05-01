package net.ostis.common.sctpclient.exception;


@SuppressWarnings("serial")
public class TransportException extends SctpClientException {

    public TransportException(String message) {

        super(message);
    }

    /**
     * @param e
     */
    public TransportException(Exception e) {

        super(e);
    }

}
