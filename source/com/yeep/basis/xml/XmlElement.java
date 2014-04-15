package com.yeep.basis.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yeep.basis.util.Utility;

/**
 * The XmlElement is a generic containment class for elements within an XML
 */
public class XmlElement
{
	private String name;
	private String data;
	private Map<String, String> attributes;
	private List<XmlElement> subElements;
	private XmlElement parent;

	// Constructors
	public XmlElement()
	{
		this(null, null, null);
	}

	public XmlElement(String name)
	{
		this(name, null, null);
	}

	public XmlElement(String name, Map<String, String> attributes)
	{
		this(name, null, attributes);
	}

	public XmlElement(String name, String data)
	{
		this(name, data, null);
	}

	public XmlElement(String name, String data, Map<String, String> attributes)
	{
		this.name = name;
		this.data = data;
		this.attributes = (attributes == null) ? new HashMap<String, String>() : attributes;
		this.subElements = new ArrayList<XmlElement>();
	}

	// Methods
	/**
	 * Add attribute to this xml element.
	 * 
	 * @param name name of key
	 * @param value new attribute value
	 * @return old attribute value
	 *  
	 */
	public Object addAttribute(String name, String value)
	{
		if (name == null || value == null)
			throw new IllegalArgumentException("The name and value of the attribute should not be null!");
		return attributes.put(name, value);
	}

	/**
	 * Get the value of the specific attribute name
	 * @param name
	 * @return
	 */
	public String getAttribute(String name)
	{
		if (name == null)
			throw new IllegalArgumentException("The name of the attribute should not be null!");
		return attributes.get(name);
	}

	/**
	 * Get the value of the specific attribute name,
	 * if the attribute is not found, return the default value
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public String getAttribute(String name, String defaultValue)
	{
		String ret = getAttribute(name);
		return ret == null ? defaultValue : ret;
	}

	/**
	 * Remove the specific element
	 * @param element
	 */
	public void removeElement(XmlElement element)
	{
		this.subElements.remove(element);
	}

	/**
	 * Remove the specific element by the given index
	 * @param element
	 */
	public void removeElement(int index)
	{
		this.subElements.remove(index);
	}

	/**
	 * Remove all sub-elements
	 */
	public void removeSubElements()
	{
		this.subElements.clear();
	}

	/**
	 * Add a sub element to the xml element
	 * @param e
	 * @return
	 */
	public boolean addElement(XmlElement subElement)
	{
		if (subElement == null)
			throw new IllegalArgumentException("SubElement you set should not be null!");

		subElement.removeFromParent();
		subElement.setParent(this);
		return this.subElements.add(subElement);
	}

	/**
	 * Insert a subelement to the specific position
	 * @param subElement
	 * @param pos
	 */
	public void insertElement(XmlElement subElement, int pos)
	{
		if (subElement == null)
			throw new IllegalArgumentException("SubElement you set should not be null!");
		subElement.removeFromParent();
		subElement.setParent(this);
		this.subElements.add(pos, subElement);
	}

	/**
	 * Get the firtst subElement for the given element name and the specific attribute
	 * @param elementName
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	public XmlElement getElement(String elementName, String attributeName, String attributeValue)
	{
		if (elementName == null || attributeName == null || attributeValue == null)
			throw new IllegalArgumentException("SubElement you set should not be null!");

		XmlElement ret = null;
		for (XmlElement xmlElement : this.subElements)
		{
			if (name.equalsIgnoreCase(xmlElement.getName()) && attributeValue.equals(getAttribute(attributeName)))
			{
				ret = xmlElement;
			}
		}

		return ret;
	}

	/**
	 * Remove from it's parent element
	 */
	private void removeFromParent()
	{
		if (parent == null)
		{
			return;
		}
		parent.removeElement(this);
		parent = null;
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean equal = false;
		if ((obj != null) && (obj instanceof XmlElement))
		{
			XmlElement other = (XmlElement) obj;
			if (Utility.equals(attributes, other.attributes) && Utility.equals(data, other.data)
					&& Utility.equals(name, other.name) && Utility.equals(subElements, other.subElements))
			{
				equal = true;
			}
		}
		return equal;
	}

	@Override
	public int hashCode()
	{
		int hashCode = 23;
		if (attributes != null)
		{
			hashCode += (attributes.hashCode() * 13);
		}
		if (data != null)
		{
			hashCode += (data.hashCode() * 17);
		}
		if (name != null)
		{
			hashCode += (name.hashCode() * 29);
		}
		if (subElements != null)
		{
			hashCode += (subElements.hashCode() * 57);
		}
		return hashCode;
	}

	// Getters and Setters
	public void setParent(XmlElement parent)
	{
		this.parent = parent;
	}

	public XmlElement getParent()
	{
		return this.parent;
	}

	public void setData(String data)
	{
		this.data = data;
	}

	public String getData()
	{
		return this.data;
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Map<String, String> getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes)
	{
		this.attributes = attributes;
	}

	public List<XmlElement> getSubElements()
	{
		return subElements;
	}

	public void setSubElements(List<XmlElement> subElements)
	{
		this.subElements = subElements;
	}
}
