package team113.communication;

public interface MessagePayload {	
	public enum PayloadType {
		MAP_LOCATION, PATH;
	}
	
	int HEADER_SIZE = 1;
	
	// Decode the message that was encoded with encode().
	void decode(int[] encodedMessage);
	
	// Encode the message. Note that the highest order bit of all the integers must remain unused.
	// In addition, the highest order HEADER_SIZE bits must also remain unused.
	// So the highest order (1 + HEADER_SIZE) bits must remain unused in the first int. 
	int[] encode();
	
	PayloadType getPayloadType();
}
