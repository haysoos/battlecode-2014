package team113.communication;

import java.util.ArrayList;
import java.util.List;

import team113.communication.MessagePayload.PayloadType;
import team113.rc.RC;
import battlecode.common.GameActionException;

public enum MessageTransmitter {
	
	INSTANCE;
	
	private static final int SHIFT_COUNT = 32 - MessagePayload.HEADER_SIZE - 1;
	private static final int MASK = ((1 << MessagePayload.HEADER_SIZE) - 1) << (SHIFT_COUNT - 1);

	public final class RecieveMessageResult {
		private final PayloadType type;
		private final int[] encodedMessage;
		
		public RecieveMessageResult(PayloadType type, int[] message) {
			this.type = type;
			this.encodedMessage = message;
		}
		
		public PayloadType getPayloadType() {
			return type;
		}
		
		public int[] getEncodedMessage() {
			return encodedMessage;
		}
	}
	
	public RecieveMessageResult recieveBroadcast(RC rc) throws GameActionException {
	    int message = rc.readBroadcast(selectChannel(0));
				
		MetaDataPayload metadataPayload = new MetaDataPayload();		
		metadataPayload.decode(new int[] { (message & MASK) >> SHIFT_COUNT });
	    PayloadType type = metadataPayload.getTypeData();	    
	    
	    if (message < 0) {
	    	return new RecieveMessageResult(type, new int[] { message & Integer.MAX_VALUE });
	    }
	    List<Integer> messageParts = new ArrayList<Integer>();
	    for (int i = 1; message > 0; i++) {
	    	message = rc.readBroadcast(selectChannel(i));	
	    	messageParts.add(message & Integer.MAX_VALUE);
	    }
	    int[] encodedMessage = new int[messageParts.size()];
	    for (int i = 0; i < encodedMessage.length; i++) {
	    	encodedMessage[i] = messageParts.get(i);
	    }
		return new RecieveMessageResult(type, encodedMessage);
	}

	public void broadcast(RC rc, MessagePayload message) {
		int[] messageParts = message.encode();
		
		MetaDataPayload mdPl = new MetaDataPayload();	
		mdPl.setTypeData(message.getPayloadType());
		int[] metadataArr = mdPl.encode();
		int metadata = metadataArr[0] << SHIFT_COUNT;
		
		messageParts[0] = messageParts[0] | metadata;
		
		for (int i = 0; i < messageParts.length; i++) {
			if (i == messageParts.length - 1) {
				rc.broadcast(selectChannel(i), messageParts[i] | Integer.MIN_VALUE);	
			} else {
				rc.broadcast(selectChannel(i), messageParts[i]);
			}
		}
	}

	// TODO come up with different schemes to utilize the channels better.
	private int selectChannel(int count) {
		return count;
	}
}
