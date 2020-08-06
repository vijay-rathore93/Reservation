package com.bookingservice.utility;

import java.util.Random;

//@Service
//@RequiredArgsConstructor
public class AlphaNumeric {

	int val = 16;

	

	public String getBookingIdValue() {

		String value = "";

		Random rand = new Random();

		for (int i = 0; i < val; i++) {
			int x = Math.abs(rand.nextInt() % 62);

			if (x >= 0 && x <= 9) {
				value = value + Character.toString((char) (x + 48));
			} else if (x >= 10 && x <= 35) {
				value = value + Character.toString((char) (x + 55));
			} else if (x >= 36 && x <= 61) {
				value = value + Character.toString((char) (x + 61));
			}

		}

		return value;

	}

}
