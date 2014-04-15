package com.yeep.basis.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

/**
 * The <code>Utility</code> is a number of utility operations that can be used anywhere.
 */
public final class Utility
{
	private static final String GETTER_METHOD = "get";
	private static final String SETTER_METHOD = "set";

	public static final String SEPARATOR_SPACE = " ";
	public static final String SEPARATOR_HYPHEN = " - ";

	/**
	 * Get the combined value for the given attributes of the specified object<br>
	 * with the given separator
	 * 
	 * @param object
	 * @param displayNames
	 * @param separator
	 * @return
	 */
	public static String getValue(Object object, String[] attributes, String separator)
	{
		if (object == null)
			return null;

		if (attributes == null)
			return object.toString();

		StringBuffer value = new StringBuffer("");

		try
		{
			for (String displayName : attributes)
			{
				value.append(Utility.getAttribute(object, displayName)).append(separator);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		String valueStr = value.toString();
		int index = valueStr.lastIndexOf(separator);
		return index == -1 ? "" : valueStr.substring(0, index);
	}

	/**
	 * Get the combined value for the given attributes of the specified object
	 * <p>
	 * Use the defautl separator " " - space
	 * 
	 * @param object
	 * @param displayNames
	 * @return
	 */
	public static String getValue(Object object, String[] attributes)
	{
		return getValue(object, attributes, SEPARATOR_SPACE);
	}

	/**
	 * Get the attribute object for specific input object by the given fullname
	 * <p>
	 * <p>
	 * Example input = classA, fullname = classB.c return classA.getClassB().getC();
	 * <p>
	 * 
	 * @param input
	 * @param fullname
	 * @return
	 */
	public final static Object getAttribute(Object input, String fullname)
	{
		Object object = input;
		try
		{
			StringTokenizer st = new StringTokenizer(fullname, ".");
			String name = null;
			while (st.hasMoreTokens())
			{
				name = st.nextToken();
				object = getProperty(object, name);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return object;
	}

	/**
	 * Set the value of the attribute object for specific input object by the given fullname
	 * 
	 * @param input
	 * @param fullname
	 * @param value
	 * @return
	 */
	public final static void setAttribute(Object input, String fullname, Object value)
	{
		Object object = input;
		try
		{
			StringTokenizer st = new StringTokenizer(fullname, ".");
			String name = null;
			while (st.hasMoreTokens())
			{
				name = st.nextToken();
				setProperty(object, name, value);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Get property for specific object by given fieldName
	 * <p>
	 * The 'get' method of the field should be defined.
	 * 
	 * @param bean
	 * @param fieldName
	 * @return
	 */
	public final static Object getProperty(Object bean, String fieldName)
	{
		if (bean == null)
			throw new IllegalArgumentException("Bean can not be null");
		Object obj = null;

		try
		{
			Method method = bean.getClass().getMethod(getBeanMethod(fieldName, GETTER_METHOD), new Class[] {});
			method.setAccessible(true);
			obj = method.invoke(bean, new Object[] {});
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}

		return obj;
	}

	/**
	 * Set property for specific object by given fieldName
	 * <p>
	 * The 'set' method of the field should be defined.
	 * 
	 * @param bean
	 * @param fieldName
	 * @return
	 */
	public final static void setProperty(Object bean, String fieldName, Object value)
	{
		if (bean == null)
			throw new IllegalArgumentException("Bean can not be null");

		try
		{
			Method method = bean.getClass().getMethod(getBeanMethod(fieldName, SETTER_METHOD),
					new Class[] { value.getClass() });
			method.setAccessible(true);
			method.invoke(bean, new Object[] { value });
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Returns true if the specified objects are equal. They are equal if they
	 * are both null OR if the <code>equals()</code> method return true. (
	 * <code>obj1.equals(obj2)</code>).
	 * 
	 * @param obj1
	 *            first object to compare with.
	 * @param obj2
	 *            second object to compare with.
	 * @return true if they represent the same object; false if one of them is
	 *         null or the <code>equals()</code> method returns false.
	 */
	public static boolean equals(Object obj1, Object obj2)
	{
		if (obj1 == obj2)
			return true;

		return (obj1 != null && obj2 != null) ? obj1.equals(obj2) : false;
	}

	/**
	 * get the name of Getter or Setter method of the specified field
	 * 
	 * @param field
	 * @return
	 */
	private final static String getBeanMethod(String field, String type)
	{
		if (field == null || field.trim().length() == 0)
			throw new IllegalArgumentException("The input field could not be null or empty");
		String firstLetter = field.substring(0, 1);
		String restLetter = field.length() > 1 ? field.substring(1) : "";

		return type + firstLetter.toUpperCase() + restLetter;
	}
}
