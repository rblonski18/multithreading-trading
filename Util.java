package blonski_CSCI201L_Assignment2;

import java.time.Duration;
import java.time.Instant;

public class Util {

		public static void printMessage(String message, Instant first) {
			Instant second = Instant.now();
			Duration duration = Duration.between(first, second);
			long mill = duration.toMillis();
			long min = duration.toMinutes();
			min %= 60;
			mill %= 1000;
			long seconds = duration.getSeconds();
			System.out.println("["+min+":"+seconds+":"+mill+"]" + " " + message);
		}

}
