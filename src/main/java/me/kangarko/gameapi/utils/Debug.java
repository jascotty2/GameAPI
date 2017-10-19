package me.kangarko.gameapi.utils;

/**
 * Utility class
 */
public class Debug {

	/**
	 * Traces method calls from the invocation place.
	 *
	 * @param message the title of the message for clarity
	 */
	public static final void trace(String message) {
		System.out.println("------------------------------------------------------------------------------------------------------------");
		System.out.println(message);
		System.out.println("");

		for (final StackTraceElement e : new Throwable().getStackTrace())
			if (e.toString().contains("me."))
				System.out.println("    at " + e.toString());

		System.out.println("------------------------------------------------------------------------------------------------------------");
	}
}
