package net.ostis.common.sctpclient.utils.bytewrapper;

import net.ostis.common.sctpclient.model.ScAddress;

public class ScByteBuffer extends ByteBufferWrapper {

    public ByteBufferWrapper putScAddress(ScAddress address) {

        return this;
    }

}
