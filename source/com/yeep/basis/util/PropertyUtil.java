package com.yeep.basis.util;

import java.util.Properties;

public class PropertyUtil
{
	/**
	 * Get value of the specific key from the properties
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getProperty(Properties properties, String key)
	{
		if (properties == null)
			throw new IllegalArgumentException("Properties should not be null!");
		return properties.getProperty(key);
	}

	/**
	 * Get String value of the specific key from the properties
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getString(Properties properties, String key)
	{
		return getProperty(properties, key);
	}

	/**
	 * Get String value of the specific key from the properties.
	 * <p>
	 * If the value is null, then return the default value
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getString(Properties properties, String key, String defaultValue)
	{
		String ret = getString(properties, key);
		return ret != null ? ret : defaultValue;
	}

	/**
	 * Get Integer value of the specific key from the properties
	 * @param properties
	 * @param key
	 * @return
	 */
	public static Integer getInteger(Properties properties, String key)
	{
		return Integer.valueOf(getString(properties, key));
	}

	/**
	 * Get Integer value of the specific key from the properties
	 * <p>
	 * If the value is null, then return the default value
	 * @param properties
	 * @param key
	 * @return
	 */
	public static Integer getInteger(Properties properties, String key, Integer defaultValue)
	{
		String ret = getString(properties, key);
		return ret != null ? Integer.valueOf(ret) : defaultValue;
	}

	/**
	 * Get Double value of the specific key from the properties
	 * @param properties
	 * @param key
	 * @return
	 */
	public static Double getDouble(Properties properties, String key)
	{
		return Double.valueOf(getString(properties, key));
	}

	/**
	 * Get Double value of the specific key from the properties
	 * <p>
	 * If the value is null, then return the default value
	 * @param properties
	 * @param key
	 * @return
	 */
	public static Double getDouble(Properties properties, String key, Double defaultValue)
	{
		String ret = getString(properties, key);
		return ret != null ? Double.valueOf(ret) : defaultValue;
	}

	/**
	 * Get Boolean value of the specific key from the properties
	 * @param properties
	 * @param key
	 * @return
	 */
	public static Boolean getBoolean(Properties properties, String key)
	{
		return Boolean.valueOf(getString(properties, key));
	}

	/**
	 * Get Boolean value of the specific key from the properties
	 * <p>
	 * If the value is null, then return the default value
	 * @param properties
	 * @param key
	 * @return
	 */
	public static Boolean getBoolean(Properties properties, String key, Boolean defaultValue)
	{
		String ret = getString(properties, key);
		return ret != null ? Boolean.valueOf(ret) : defaultValue;
	}
}