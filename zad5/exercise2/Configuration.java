package exercise2;

import java.util.*;

public class Configuration {
	public int interval;

	public int duration;

	public int departure;

	public void load(Properties props) throws ConfigurationException {
		
		interval = this.valueOfProperty(props, "interval");
		duration = this.valueOfProperty(props, "duration", interval);
		departure = this.valueOfProperty(props, "departure", interval);
	}
	
	/**
	 * Extracted two methods from long code
	 */
	private int valueOfProperty(Properties property, String name) throws ConfigurationException {
		String valueString = property.getProperty(name);
		if (valueString == null) {
			throw new ConfigurationException(name + " offset");
		}
		int value = Integer.parseInt(valueString);
		if (value <= 0) {
			throw new ConfigurationException(name + " > 0");
		}
		return value;
	}
	
	private int valueOfProperty(Properties property, String name, int interval) throws ConfigurationException {
		int value = this.valueOfProperty(property, name);
		if ((value % interval) != 0) {
			throw new ConfigurationException(name + " % interval");
		}
		return value;
	}
}
