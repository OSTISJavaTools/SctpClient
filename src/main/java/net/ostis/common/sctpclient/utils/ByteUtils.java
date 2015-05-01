package net.ostis.common.sctpclient.utils;

import java.io.IOException;
import java.io.InputStream;

public class ByteUtils {
	
	
	public static int unsignedShortToInt(byte[] shortBytes){
		if(shortBytes.length != 2){
			throw new IllegalArgumentException("Byte representation of Short should have 2 bytes");
		}
		
		return ((shortBytes[0] & 0xFF )  | (shortBytes[1] & 0xFF) << 8);
		
	}
	
    public static byte[] getBytesFromSource(InputStream source, int count) throws IOException {

        byte[] result = new byte[count];
        source.read(result);
        return result;
    }

}
