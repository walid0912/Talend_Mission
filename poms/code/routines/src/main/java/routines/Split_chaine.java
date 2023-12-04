package routines;

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
public class Split_chaine {

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
    
    public static String Split_String(String chaine) {
     String[] chaine1 = chaine.split("%");
       return chaine1[0]; //$NON-NLS-1$ //$NON-NLS-2$
    }
    
    public static String ReplacePtVirg(String chaine) {
        String text = Relational.ISNULL(chaine)|| "".equals(chaine.trim())?null:chaine.replace(";", "");
          return text; 
       }
    
    public static String ReplaceNullVide(String chaine) {
        String text = "null".equals(chaine)? "":chaine;   
          return text; 
       }
    
    
    public static String ReplaceChaineByAnotherChaine(String chaine,String C1,String C2) {
    	String text = Relational.ISNULL(chaine)|| "".equals(chaine.trim())?"":chaine.replaceAll(C1, C2);
          return text; 
       }
    
    public static int CompteurChar(String str, char ch) 
	{
  	int compteur = 0;                  
  	for (int i = 0; i < str.length(); i++) 
    if (str.charAt(i) == ch)             
       compteur++;                         
  	return compteur;   
	}
    
    public static String Split_String(String chaine,String c,int Position) {
    	String Mot =  chaine;
    	if(!(Relational.ISNULL(chaine)||"".equals(chaine.trim()))){
              try {
                 String[] chaine1 = chaine.split(c);
                 Mot = chaine1[Position] ;
              }
              catch (IndexOutOfBoundsException e) {
      			System.err.println("IndexOutOfBoundsException with Position parameter \""+Position+"\" : " + e.getMessage());
      			System.err.println("Return default date: "+"");
      		}
        
    	}
    	
          return Mot; 
          
          
       }
    
    public static String Split_String(String chaine,String c,int Position,String defaut) {
    	String Mot =  defaut;
    	if(!(Relational.ISNULL(chaine)||"".equals(chaine.trim()))){
              try {
                 String[] chaine1 = chaine.split(c);
                 Mot = chaine1[Position] ;
              }
              catch (IndexOutOfBoundsException e) {
      			System.err.println("IndexOutOfBoundsException with Position parameter \""+Position+"\" : " + e.getMessage());
      			System.err.println("Return default string: "+defaut);
      		}
        
    	}
    	
          return Mot; 
          
          
       }
    
}
