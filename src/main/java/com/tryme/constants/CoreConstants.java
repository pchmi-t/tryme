package com.tryme.constants;

/**
 * The core constants for common purpose.
 * 
 */
public class CoreConstants {
	
	/** The default DB host. */
	public static final String DB_HOST = "127.0.0.1";

	/** The default db port. */
	public static final int DB_PORT = 27017;

	/** The default database name. */
	public static final String TRYME_DB_NAME = "tryme";

	/** The general error message that will be shown in case of <b>500 ISE</b>. */
	public static final String GENERAL_ERROR_MSG = "An expected error occured! "
			+ "Please check the server configuration. If the problem continue contact the "
			+ "'TryMe' support center.";

	//TODO Research for this
	public static final String AVATAR_DIR_PREFIX = "\\avatars\\";

	/**
	 * All special characters.
	 */
	public static char[] SPECIAL_CHARACTERS = 
		{'@', '!', '`', '"', '^', ';', ':', 
				'-', '+', '(', ')', '#', '$', '*', '{', '}'};

}
