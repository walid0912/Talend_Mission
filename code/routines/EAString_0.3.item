package routines;

import java.util.Date;

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
public class EAString {

    public static String setUnknownWhenNull(String text) {
        text = (Relational.ISNULL(text)||"".equals(text.trim()) ? "": text);
    	return text;
    }
    public static String setUnknownWhenNull(String text, String default_value) {
        text = (Relational.ISNULL(text)||"".equals(text.trim()) ? default_value : text);
    	return text;
    }
    
    /*Remplacer apostrophe dans les champs text */
    public static String setSpaceWhenApost(String text) {
        text = (Relational.ISNULL(text)||"".equals(text.trim()) ? text : text.replace("'", ""));
    	return text;
    }
    
    public static Integer setDateKey(String dateTime) {
    	int date_key = 19000101;
    	String error_date_msg = "DateTime parameter \""+dateTime+"\" is not valid";
    	if(!(Relational.ISNULL(dateTime)||"".equals(dateTime.trim()))){
    		try{
    			dateTime = dateTime.replaceAll("\\D", "").substring(0,8);
    			if(TalendDate.isDate(dateTime, "yyyyMMdd") &&  Integer.parseInt(dateTime.substring(0,4)) >=1900  ){
        			date_key = Integer.parseInt(dateTime);
        		}else{
    				System.err.println(error_date_msg+"\nReturn default date_key: -1");
    			}
    		} catch (IndexOutOfBoundsException e) {
    			System.err.println("IndexOutOfBoundsException with String parameter \""+dateTime+"\" : " + e.getMessage());
    			System.err.println("Return default integer: 19000101");
    		}
    	}
    	return date_key;
    }

    public static String setTime(String dateTime) {
    	String time = "00:00:00";
    	String error_date_msg = "DateTime parameter \""+dateTime+"\" is not valid";
    	if(!(Relational.ISNULL(dateTime)||"".equals(dateTime.trim()))){
    		String hour = "00";
    		String minute = "00";
    		String second = "00";
    		try {
    			hour = dateTime.replaceAll("\\D", "").substring(8,10);
    			try {
    				minute = dateTime.replaceAll("\\D", "").substring(10,12);
        			try {
        				second = dateTime.replaceAll("\\D", "").substring(12,14);
            		} catch (IndexOutOfBoundsException e) {
            			System.err.println("IndexOutOfBoundsException with String parameter \""+dateTime+"\" : " + e.getMessage()+"\nReturn default second: 00");
            		}
        		} catch (IndexOutOfBoundsException e) {
        			System.err.println("IndexOutOfBoundsException with String parameter \""+dateTime+"\" : " + e.getMessage()+"\nReturn default minute: 00");
        		}
    		} catch (IndexOutOfBoundsException e) {
    			System.err.println("IndexOutOfBoundsException with String parameter \""+dateTime+"\" : " + e.getMessage()+"\nReturn default hour: 00");
    		}
    		if(TalendDate.isDate(hour+minute+second, "HHmmss")){
    			time = hour+":"+minute+":"+second;
			}else{
				System.err.println(error_date_msg+"\nReturn default time string: 00:00:00");
			}
    	}
    	return time;
    }
    
    public static Date setFullDate(String dateTime) {
    	Date fullDate = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "1900-01-01 00:00:00");
    	String error_date_msg = "DateTime parameter \""+dateTime+"\" is not valid";
       	if(!(Relational.ISNULL(dateTime)||"".equals(dateTime.trim()))){
    		try {
    			dateTime = dateTime.replaceAll("\\D", "").substring(0,14);
    			if(TalendDate.isDate(dateTime, "yyyyMMddHHmmss")){
    				fullDate = TalendDate.parseDate("yyyyMMddHHmmss", dateTime);
    			}else{
    				System.err.println(error_date_msg+"\nReturn default date: "+fullDate);
    			}    			
    		} catch (IndexOutOfBoundsException e) {
    			System.err.println("IndexOutOfBoundsException with String parameter \""+dateTime+"\" : " + e.getMessage());
    			System.err.println("Return default date: "+fullDate);
    		}
    	}   
    	return fullDate;
    }
/*
    public static String setDefaultInteger(String text) {
        text = (Relational.ISNULL(text)||"".equals(text.trim()) ? "UNKNOWN" : text);
    	return text;
    }
*/
    
    public static Boolean isNumeric(String str) {
    	if(str.equals("")){
    		return false;
    	}
    	for(int i=str.length();--i>=0;){ 
    		int chr=str.charAt(i); 
    		if((chr<48&&chr!=45)|| chr>57) 
    		return false;
    		} 
    		return true;
    }
 /* Type de produitJET2 */   
    public static String getTypeProduct(String str){
     String type ;
     if (  Relational.ISNULL(str) || "".equals(str.trim()) ) { 
    	 type = "" ;
       }
     else
    	  if (str.contains("XSW"))  {
    		  type = "XSW";
    	  }
    	  else
    		  type = "" ;
    return type ;
       
    }
    
    public static String trimCover( String str){
     if (  "_".equals(StringHandling.RIGHT(str.trim(), 1) ) ) {
    	 
    	 return StringHandling.LEFT(str.trim(),(StringHandling.LEN(str.trim())-1)) ;
     }
     else 
    	return str.trim() ;
    }
    
  /* produits Jet2 */  
    public static String getProduct(String Cov, String str, String cd){
    	//String  sCov = Cov.replaceAll(" ", "_");
    	String  sCov = StringHandling.TRIM(Cov).replaceAll(" ", "_");
    	String MajStr = StringHandling.UPCASE(str) ;
    	String product ;
			   if ("Jet2 Holidays".equalsIgnoreCase(cd) ) {
				   product = sCov ;
			       }
			   else if ("Jet2.com".equalsIgnoreCase(cd) ) {
				       product = trimCover(sCov+"_"+getTypeProduct(MajStr));
			            
			          }
			   else 
			    if (  Relational.ISNULL(MajStr) || "".equals(MajStr.trim()) ) { 
			         	product = sCov+"_A";
			           }
			    	 else
			        	    if (MajStr.contains("HA4") ) {
			        	    	 product = sCov+"_D_"+getTypeProduct(MajStr);
			        	      }
			                else 
			    	               if (MajStr.contains("HA3") ) {
			    	            	   product = sCov+"_C_"+getTypeProduct(MajStr);  
			    	               }
			    	               else 
			    	            	    if (MajStr.contains("HA2") ) {
			    	            	          product = sCov+"_B_"+getTypeProduct(MajStr); 
			    	                        }
			    	            	    else   	    
				    	            	    		 product = sCov+"_A_"+getTypeProduct(MajStr);
				    	            	    	 
				    	            	    	 
           
      return trimCover(product) ;
      
     }
    	  
  public static String setCoverType(String str) {
	  if(str==null || str.equalsIgnoreCase("")) return "";
	  return str.contains("WSC")?"DYNAMIC":"" ;
    }
  
  //EA GERMANY
  public static String replaceComa(String str) {
	  if(str==null || str.equalsIgnoreCase("")) return "";
	  if(str.indexOf(",")>0){
		  str = str.replaceAll(",", " ");
	  }
	  return str;
    }
  
	/**
	 * parse la date java.sql.SQLException: Only dates between January 1, 1753 and December 31, 9999 are accepted.
	 * 
	 * @param BENEFICIARYBIRTHDATE
	 * @return
	 */
	public static Date parseDate(String BENEFICIARYBIRTHDATE) {
		//System.out.println(BENEFICIARYBIRTHDATE);
		if (BENEFICIARYBIRTHDATE == null || BENEFICIARYBIRTHDATE.equalsIgnoreCase("")) {
			return null;
		}
		Date dd = TalendDate.parseDate("dd/MM/yyyy", "01/01/1800");
		try {
			dd = TalendDate.parseDate("dd/MM/yyyy", BENEFICIARYBIRTHDATE);
		} catch (Exception e) {
			dd = TalendDate.parseDate("dd/MM/yyyy", "01/01/1800");
		}
		return dd;
	}
  
  /**
   * Returns the number of appearances that a string have on another string.
   * 
   * @param source    a string to use as source of the match
   * @param sentence  a string that is a substring of source
   * @return the number of occurrences of sentence on source 
   */
  public static int numberOfOccurrences(String source, String sentence) {
      int occurrences = 0;

      if (source.contains(sentence)) {
          int withSentenceLength    = source.length();
          int withoutSentenceLength = source.replace(sentence, "").length();
          occurrences = (withSentenceLength - withoutSentenceLength) / sentence.length();
      }

      return occurrences;
  }
  
  
  public static void main(String[] args) {
	String gg = "azeazeze;azeaze;azeazezae;azezaeaz;eazeaze;";
	  System.out.println(numberOfOccurrences(gg,";"));
	  
	  
	  String File_Name = "TRT_EAGermany_GCC_Sales_20170921.csv";
	  
	  String ff = StringHandling.LEFT(File_Name,25);
	  System.out.println(ff);
  }
  
  }
    
  
