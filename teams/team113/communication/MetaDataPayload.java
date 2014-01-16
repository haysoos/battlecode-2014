package team113.communication;


public final class MetaDataPayload implements MessagePayload {
	
	private static final int TYPE_BITS = MessagePayload.HEADER_SIZE;
	private static final int MAX_ORDINAL = (1 << TYPE_BITS) - 1;
		
	private PayloadType type;	
	
	private void checkBounds(int parameter, int lowerBound, int upperBound) {
		if (parameter < lowerBound || parameter > upperBound) {
			throw new RuntimeException(parameter + " " + lowerBound + " " + upperBound);
		}
	}
	
	@Override
	public void decode(int[] encodedMessage) {
		type = PayloadType.values()[encodedMessage[0] & MAX_ORDINAL];
	}

	@Override
	public int[] encode() {	
		return new int[] { type.ordinal() };
	}

	@Override
	public PayloadType getPayloadType() {
		return null;
	}


	public PayloadType getTypeData() {
		return type;
	}

	public void setTypeData(PayloadType type) {
		checkBounds(type.ordinal(), 0, MAX_ORDINAL);
		this.type = type;
	}

}
