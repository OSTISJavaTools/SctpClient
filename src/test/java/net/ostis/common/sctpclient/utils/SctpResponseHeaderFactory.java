package net.ostis.common.sctpclient.utils;

import net.ostis.common.sctpclient.constants.SctpCommandType;
import net.ostis.common.sctpclient.model.response.SctpResponseHeader;
import net.ostis.common.sctpclient.model.response.SctpResultType;

public class SctpResponseHeaderFactory {

    public static SctpResponseHeader createNewResponse(SctpCommandType commandType, int commandId,
	    SctpResultType resultType, int argumentSize) {
	SctpResponseHeader newSctpResponseHeader = new SctpResponseHeader();
	newSctpResponseHeader.setCommandType(commandType);
	newSctpResponseHeader.setCommandId(commandId);
	newSctpResponseHeader.setResultType(resultType);
	newSctpResponseHeader.setArgumentSize(argumentSize);
	return newSctpResponseHeader;
    }

}
