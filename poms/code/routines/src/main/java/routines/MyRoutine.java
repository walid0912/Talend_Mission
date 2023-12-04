package routines;

import routines.system.RandomUtils;
import org.apache.oro.text.regex.*;

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
public class MyRoutine {

    
    /**
     * {talendTypes} String
     * 
     * {Category} TalendDataGenerator
     * 
     * {example} getFirstName() # Bill.
     */
    public static String getFirstName() {
        String[] list = { "Ace", "Luffy", "Usopp", "Zoro", "Robin", "Sanji", "Brook", "Nami", "Chopper", "Franky", "Jimbei", "Sabo", "Law", "Kid"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$
        Integer random = 0 + ((Long) Math.round(RandomUtils.random() * (list.length - 1 - 0))).intValue();
        return list[random];
    }

    /**
     * {talendTypes} String
     * 
     * {Category} TalendDataGenerator
     * 
     * {example} getLastName() # Johnson.
     */
    public static String getLastName() {
        String[] list = {"Trafalgar", "Portagas D", "Tony", "Vinsmoke", "Roronoa", "Monkey D", "Nico", "Cyborg", "Eustass" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        Integer random = 0 + ((Long) Math.round(RandomUtils.random() * (list.length - 1 - 0))).intValue();
        return list[random];
    }
    
    /* public static boolean isValidEmail(String email) {
    	Perl5Matcher matcher = new Perl5Matcher();
    	return true; } */
    
    public static boolean isValidEmail(String email) {
    	Perl5Matcher matcher = new Perl5Matcher();
    	Perl5Compiler compiler = new Perl5Compiler();
    	Pattern pattern ;
    	try{
    	pattern = compiler.compile("^[\\w_.-]+@[\\w_.-]+\\.[\\w]+$");
    	if(!matcher.matches(email, pattern)) {
    	return false;
    	}
    	} catch(MalformedPatternException e) {
    	throw new RuntimeException(e);
    	}
    	return true;
    	}

}
