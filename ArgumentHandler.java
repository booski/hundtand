import java.util.HashMap;
import java.util.List;

/**
	
	<p>A class for simple handling of config data from file of command-line arguments.</p>
	
	<p>Data is passed to this class as a collection of Strings together with a regex with which to split each string into a leading identifier and a trailing value. These identifiers are then mapped to their values, where the values are available in all eligible formats of <code>boolean</code>, <code>int</code>, <code>double</code> and String.</p>
	
	<p>For example, if the user were to supply the string "value=5" and the delimiter "=" to be parsed, "value" would be mapped to the <code>int</code> value 5, the <code>double</code> value 5.0 and the String value "5".<br/>
	All value parsing is handled by <code>Integer.parseInt()</code> and respective methods for the other data types.</p>
	
	<p>Optionally, the user can specify an 'ignore pattern': a regex defining entries that are to be ignored by the parser - usually useful for comments in configuration files. This regex will be applied to each whole argument String individually, so there is no way of defining multiline or block comments.<br/>
	The ignore pattern can also be useful in ensuring that blank lines are never parsed.</p>
	
*/
public class ArgumentHandler {
	
	private HashMap<String, Integer> intMap;
	private HashMap<String, Boolean> boolMap;
	private HashMap<String, Double> doubleMap;
	private HashMap<String, String> stringMap;
	private String ignorePattern = null;
	
	/**
		The default constructor. Will not have a default ignore pattern and thus parse all strings supplied to it.
	*/
	
	public ArgumentHandler() {
		
		intMap = new HashMap<>();
		boolMap = new HashMap<>();
		doubleMap = new HashMap<>();
		stringMap = new HashMap<>();
	
	}
	
	/**
		Creates an ArgumentHandler instance populated with the supplied data.
		
		@param args The array of Strings that contain the key-value pairs to be parsed.
		@param delimiter The regular expression used to split each String into key and value.
		@param ignorePattern The regular expression used to identify Strings to be ignored by the parser.
	*/
	
	public ArgumentHandler(String[] args, String delimiter, String ignorePattern) {
		
		this();
		this.ignorePattern = ignorePattern;
		this.parse(args, delimiter);
		
	}
	
	/**
		Creates an ArgumentHandler instance populated with the supplied data.
		
		@param args The List of Strings that contain the key-value pairs to be parsed.
		@param delimiter The regular expression used to split each String into key and value.
		@param ignorePattern The regular expression used to identify Strings to be ignored by the parser.
	*/
	
	public ArgumentHandler(List<String> args, String delimiter, String ignorePattern) {
		
		this();
		this.ignorePattern = ignorePattern;
		this.parse(args, delimiter);
		
	}
	
	/**
		Sets the ignore pattern to the supplied regex. Setting this to <code>null</code> will cause all Strings to be parsed.
		
		@param regex The regular expression defining Strings to be ignored by the parser.
	*/
	
	public void setIgnorePattern(String regex) {
		
		ignorePattern = regex;
		
	}
	
	/**
		Populates this ArgumentHandler instance with the supplied data.
		
		@param args The array of Strings that contains the key-value pairs to be parsed.
		@param delimiter The regular expression used to split each String into key and value.
	*/
	
	public void parse(String[] args, String delimiter) {
		
		for(String arg : args) {
			
			parse(arg, delimiter);
			
		}
	}
	
	/**
		Populates this ArgumentHandler instance with the supplied data.
		
		@param args The List of Strings that contains the key-value pairs to be parsed.
		@param delimiter The regular expression used to split each String into key and value.
	*/
	
	public void parse(List<String> args, String delimiter) {
		
		for(String arg : args) {
			
			parse(arg, delimiter);
			
		}
	}
	
	/**
		Adds a single key-value pair to this ArgumentHandler.
		
		@param s The String to be parsed.
		@param delimiter The regular expression used to split the String into key and value.
	*/
	
	public void parse(String s, String delimiter) {
		
		if(ignorePattern != null && s.matches(ignorePattern)) {
			
			return;
			
		}
		
		String[] parts = s.split(delimiter);
		
		if(parts[1].equalsIgnoreCase("true") || parts[1].equalsIgnoreCase("false")) {
			
			boolMap.put(parts[0], Boolean.parseBoolean(parts[1]));
			
		}
		
		try {
			
			intMap.put(parts[0], Integer.parseInt(parts[1]));
			
		} catch(Exception e) {}
		
		try {
			
			doubleMap.put(parts[0], Double.parseDouble(parts[1]));
			
		} catch(Exception e) {}
		
		stringMap.put(parts[0], parts[1]);
		
	}
	
	/**
		Returns the <code>int</code> mapping associated with the supplied key.
		
		@param arg The key for which a value is to be retrieved.
		
		@return The <code>int</code> associated with the supplied key.
		
		@throws NullPointerException If no mapping exists.
	*/
	
	public int getInt(String arg) throws NullPointerException {
		
		return intMap.get(arg);
		
	}
	
	/**
		Returns the <code>double</code> mapping associated with the supplied key.
		
		@param arg The key for which a value is to be retrieved.
		
		@return The <code>double</code> associated with the supplied key.
		
		@throws NullPointerException If no mapping exists.
	*/
	
	public double getDouble(String arg) throws NullPointerException {
		
		return doubleMap.get(arg);
		
	}
	
	/**
		Returns the <code>boolean</code> mapping associated with the supplied key.
		
		@param arg The key for which a value is to be retrieved.
		
		@return The <code>boolean</code> associated with the supplied key.
		
		@throws NullPointerException If no mapping exists.
	*/
	
	public boolean getBool(String arg) throws NullPointerException {
		
		return boolMap.get(arg);
		
	}
	
	/**
		Returns the String mapping associated with the supplied key.
		
		@param arg The key for which a value is to be retrieved.
		
		@return The String associated with the supplied key.
	*/
	
	public String getString(String arg) {
		
		return stringMap.get(arg);
		
	}
}
