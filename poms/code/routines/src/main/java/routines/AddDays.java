// template routine Java
package routines;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class AddDays {

	/**
	 * AddDays: retourne une date + un nb de jours
	 * 
	 * 
	 * {talendTypes} String
	 * 
	 * {Category} User Defined
	 * 
	 * {param} string("2009-01-01") input: The string need to be printed.
	 * 
	 * {example} addDays("2009-01-01",3) # 2009-01-04
	 */

	public static String addDays(String date, Integer days){
		String result = "";
		Date df = TalendDate.parseDate("yyyy-MM-dd",date);
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.setTime(df);
		calendar.add (Calendar.DAY_OF_MONTH, days);
		result = TalendDate.formatDate("yyyy-MM-dd",calendar.getTime());
		return result;
	}

	/**
	 * moispre: retourne le mois précédant le mois courant
	 * 
	 * 
	 * {talendTypes} String
	 * 
	 * {Category} User Defined
	 * 
	 * {param} string() input: Currently Date
	 * 
	 * {example} moispre(Octobre 2009) # 2009-09
	 */

	public static String moispre(){
		String Mois="";
		Date dt = TalendDate.getCurrentDate();
		GregorianCalendar calendar = new java.util.GregorianCalendar();
		calendar.setTime(dt);
		calendar.add (Calendar.MONTH,-1);
		Mois = TalendDate.formatDate("yyyy-MM",calendar.getTime());
		return Mois;
	}	 
}