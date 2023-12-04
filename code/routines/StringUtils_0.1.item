package routines;

public class StringUtils {

	/**
	 * 
	 * isEmpty: Checks if a String is empty ("") or null. Return true if the
	 * String is empty or null {talendTypes} boolean | Boolean
	 * 
	 * {Category} StringUtils
	 * 
	 * {param} str the String to check, may be null
	 * 
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 
	 * isEmpty: Checks if a String is not empty ("") and not null. Return true
	 * if the String is not empty and not null
	 * 
	 * {talendTypes} boolean | Boolean
	 * 
	 * {Category} StringUtils
	 * 
	 * {param} str the String to check, may be null
	 * 
	 */
	public static boolean isNotEmpty(String str) {
		return !StringUtils.isEmpty(str);
	}

	/**
	 * stringPad: not return value, only print "hello" + message.
	 * 
	 * 
	 * {talendTypes} String, int, String, boolean
	 * 
	 * {Category} SPCoding
	 * 
	 * {param}
	 * 
	 */

	public static String stringPad(String s, int n, String c,
			boolean paddingLeft) {
		StringBuilder str = new StringBuilder(s == null ? c : s);
		int strLength = str.length();
		if (n > 0 && n > strLength) {
			for (int i = 0; i <= n; i++) {
				if (paddingLeft) {
					if (i < n - strLength)
						str.insert(0, c);
				} else {
					if (i > strLength)
						str.append(c);
				}
			}
		} else if (n < strLength) {
			return str.substring(0, n);
		}
		return str.toString();
	}
	
	
	/**
	 * Check if the email is on a valid format
	 * @param email
	 * @return true if the email is valid
	 * @return false if the email is not valid
	 */
    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
 }
	
}
