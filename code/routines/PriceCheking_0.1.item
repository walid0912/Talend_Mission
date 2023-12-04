package routines;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

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
public class PriceCheking {

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
    
    public static boolean isNumeric(String inputData) {
    	inputData = inputData.replaceAll(",","").replaceAll(".","").replaceAll("-","").replaceAll("\\+", "") ;
    	  NumberFormat formatter = NumberFormat.getInstance();
    	  ParsePosition pos = new ParsePosition(0);
    	  formatter.parse(inputData, pos);
    	  return inputData.length() == pos.getIndex();
    	}
    
    
    public static float arrondirFloat(float d,int nb_after_virgul){
    	
    	// format d arrondi à 2 chiffres arrondirFloat


    	DecimalFormat f = new DecimalFormat();
    	f.setMaximumFractionDigits(nb_after_virgul);
    	 
    	// execution du format
    	String dFormat= f.format(d);
    	 
    	//Pour avoir un double il faut remplacer les , par des .
    	//
    	String temp = dFormat.replace(",",".");
    	 
    	float dArrondi = Float.parseFloat(temp);
	

    	return dArrondi;
    } 
    	
    
    public static float GetPercentageAdd(int valeur, float total) {

    	
       float a = total+ (total*valeur/100) ;
       System.out.println(valeur+"% de "+total+" = "+a );
      
       return a;
        		
    }
    
    public static float GetPercentageS(int valeur, float total) {
    	    	
    	    	float a = total- (total*valeur/100) ;
    	        System.out.println("-"+valeur+"% de "+total+" = "+a );
    	      
    	       return a;
    	        		
    	    }
    
    public static boolean PriceCheking(String In_Price,float N_Price, int percent) {
    	  	                
    	String Clean_In_Price =  StringHandling.EREPLACE((Relational.ISNULL(In_Price)|| "".equals(In_Price.trim()))?"0":
             StringHandling.EREPLACE(In_Price,"-","").trim(),",",".");	
    	 
    	 System.out.println("Client Price : " + Clean_In_Price );
    	 
    	 	 
     if (arrondirFloat(Float.parseFloat(Clean_In_Price),2) >= GetPercentageS (percent,N_Price ) && arrondirFloat(Float.parseFloat(Clean_In_Price),2) <= GetPercentageAdd (percent,N_Price ) ) {
    	 System.out.println("Price accepted, price send : " + arrondirFloat(Float.parseFloat(Clean_In_Price),2) + " price calculated : "+ N_Price+ " , the tolerance percent is +/-"+percent+"%");
    	 
    	 return true;	 
         }
         else 
         System.out.println("Incorrect price, price send : " + arrondirFloat(Float.parseFloat(Clean_In_Price),2) + " price calculated : "+ N_Price+ " , the tolerance percent is +/-"+percent+"%");
    	 return false;
    	 
       }
    
    
}
