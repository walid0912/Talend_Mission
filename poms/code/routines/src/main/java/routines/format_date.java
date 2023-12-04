package routines;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/*
 * user specification: the function's comment should contain keys as follows: 1. write about the function's comment.but
 * it must be before the "{talendTypes}" key.
 * 
 * 2. {talendTypes} 's value must be talend Type, it is required . its value should be one of: String, char | Character,
 * long | Long, int | Integer, boolean | Boolean, byte | Byte, Date, double | Double, float | Float, Object, short |
 * Short
 * 
 * 3. {Category} define a category for the Function. it is required. its value is user-defined .
 * 
 * 4. {param} 's format is: {param} <type>[(<default value or closed list values>)] <name>[ : <comment>]
 * 
 * <type> 's value should be one of: string, int, list, double, object, boolean, long, char, date. <name>'s value is the
 * Function's parameter name. the {param} is optional. so if you the Function without the parameters. the {param} don't
 * added. you can have many parameters for the Function.
 * 
 * 5. {example} gives a example for the Function. it is optional.
 */
public class format_date {

	/**
	 * helloExample: not return value, only print "hello" + message.
	 * 
	 * 
	 * {talendTypes} String
	 * 
	 * {Category} User Defined
	 * 
	 * {param} string("world") input: The string need to be printed.
	 * 
	 * {example} helloExemple("world") # hello world !.
	 */
	public static void helloExample(String message) {
		if (message == null) {
			message = "World"; //$NON-NLS-1$
		}
		System.out.println("Hello " + message + " !"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static Date stringToDate(String d) throws ParseException {
		DateFormat formatter = new SimpleDateFormat(
				"EEE MMM d HH:mm:ss z yyyy", Locale.ENGLISH);
		Date date = (Date) formatter.parse(d);

		java.sql.Date date_sql = new java.sql.Date(date.getTime());
		return date_sql;
	}

}
