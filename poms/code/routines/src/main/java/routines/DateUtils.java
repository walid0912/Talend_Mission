package routines;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * isValid: Checks if the field is a valid date.  The pattern is used with
     * 			<code>java.text.SimpleDateFormat</code>.  If strict is true, then the
     * 			length will be checked so '2/12/1999' will not pass validation with
     * 			the format 'MM/dd/yyyy' because the month isn't two digits.
     * 			The setLenient method is set to <code>false</code> for all.</p>
     * 			Return true if the date is valid.
     * 
     * {talendTypes} String, String, boolean
     * 
     * {Category} DateUtils
     *
     * {param} value The value validation is being performed on.
     * {param} datePattern The pattern passed to <code>SimpleDateFormat</code>.
     * {param} strict Whether or not to have an exact match of the datePattern.
     */
    public static boolean isValid(String value, String datePattern, boolean strict) {

        if (value == null
                || datePattern == null
                || datePattern.length() <= 0) {

            return false;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(datePattern);
        formatter.setLenient(false);

        try {
            formatter.parse(value);
        } catch(ParseException e) {
            return false;
        }

        if (strict && (datePattern.length() != value.length())) {
            return false;
        }

        return true;
    }
    
    public static Date getDateUS(String dt1) {
    	if(dt1==null){
    		return TalendDate.parseDate("MMddyyyy", "01011990");
    	}
    	Date date1 = TalendDate.parseDate("MMddyyyy", dt1);
    	//System.out.println(dt1 +"======>" + date1);
    	return date1;
    }
    
    public static int compareDt(String dt1,String dt2) {
    	Date date1 = TalendDate.parseDate("yyyyMMdd", dt1);
    	Date date2 = TalendDate.parseDate("yyyyMMdd", dt2);
    	return date1.compareTo(date2);
    }
    
    public static int compareDtEur(String dt1,String dt2) {
    	Date date1 = TalendDate.parseDate("ddMMyyyy", dt1);
    	Date date2 = TalendDate.parseDate("ddMMyyyy", dt2);
    	return date1.compareTo(date2);
    }
    
    public static int getDuration(String dep,String end) {
    	Date dateDep = TalendDate.parseDate("MMddyyyy", dep);
    	Date dateEnd = TalendDate.parseDate("MMddyyyy", end);
    	long diffDays = (dateEnd.getTime() - dateDep.getTime() ) / (24 * 60 * 60 * 1000)+1;
    	int daysdiff = (int) diffDays;
    	return daysdiff;
    }
    
}
