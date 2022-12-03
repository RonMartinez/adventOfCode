package year2020.day25;

import java.io.IOException;

public class Day25 {

	private static final long ENCRYPTION_DIVISOR = 20201227L;
	private static final long PUBLIC_SUBJECT_NUMBER = 7L;
	private static final long STARTING_VALUE = 1L;
	
	public static void main(String[] args) throws IOException {
		//sample
//		Long cardPublicKey = 5764801L;
//		Long doorPublicKey = 17807724L;
		
		//real input
		Long cardPublicKey = 19774466L;
		Long doorPublicKey = 7290641L;
		
		Long cardLoopSize = findLoopSize(cardPublicKey);
		System.out.println(cardLoopSize);

		Long doorLoopSize = findLoopSize(doorPublicKey);
		System.out.println(doorLoopSize);

		Long encryptionKey = null;
		if(cardLoopSize < doorLoopSize) {
			encryptionKey = transformSubjectNumber(doorPublicKey, cardLoopSize);	
		} else {
			encryptionKey = transformSubjectNumber(cardPublicKey, doorLoopSize);
		}

		System.out.println(encryptionKey);
	}

	private static Long transformSubjectNumber(Long subjectKey, long loopSize) {
		Long value = 1L;
		for(int i = 0; i < loopSize; i++) {
			value *= subjectKey;
			value = value % ENCRYPTION_DIVISOR;
		}
		return value;
	}

	private static Long findLoopSize(Long publicKey) {
		boolean foundLoopSize = false;
		long loopSize = 0L;
		long value = STARTING_VALUE;
		while( ! foundLoopSize) {
			value *= PUBLIC_SUBJECT_NUMBER;
			value = value % ENCRYPTION_DIVISOR;
			if(publicKey.equals(value)) {
				foundLoopSize = true;
			}
			loopSize++;
		}
		return loopSize;
	}


}
