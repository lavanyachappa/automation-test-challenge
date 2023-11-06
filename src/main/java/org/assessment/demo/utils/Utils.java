package org.assessment.demo.utils;

import java.util.Random;

public class Utils {

	public void pause(int seconds) {
		try {
			System.out.println("NOT RECOMMENDED STILL -> Sleeping for :: " + seconds + " seconds");
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void pauseMilliSeconds(int milliSeconds) {
		try {
			System.out.println("NOT RECOMMENDED STILL -> Sleeping for :: " + milliSeconds + " milliSeconds");
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getRandom(int length) {

		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i++) {
			sb.append(random.nextInt(9));
		}

		return (sb.toString());

	}

}
