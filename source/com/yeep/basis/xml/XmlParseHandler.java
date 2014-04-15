package com.yeep.basis.xml;

import java.io.CharArrayWriter;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParseHandler extends DefaultHandler
{
	// Root element
	private XmlElement rootElement = null;
	private XmlElement currentElement = null;

	private CharArrayWriter contents = new CharArrayWriter();

	// Constructor
	public XmlParseHandler()
	{
	}

	/**
	 * Returns the root for the XmlElement hiearchy.
	 * <p>
	 * Methods that want to retrieve elements from this root should use
	 * the {@link XmlElement#getElement(String)} in order to get the wanted
	 * element.
	 * @return a XmlElement if it has been loaded or initialized with it; null otherwise.
	 */
	protected XmlElement getRoot()
	{
		return this.rootElement;
	}

	// Implement the content hander methods that
	// will delegate SAX events to the tag tracker network.
	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes attrs) throws SAXException
	{
		XmlElement element = new XmlElement(localName);
		if (!hasRoot())
		{
			this.rootElement = element;
			this.currentElement = element;
		}
		else
		{
			this.currentElement.addElement(element);
			this.currentElement = element;
		}

		if (attrs != null)
		{
			for (int i = 0; i < attrs.getLength(); i++)
			{
				element.addAttribute(attrs.getLocalName(i), attrs.getValue(i));
			}
		}
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException
	{
		if (this.contents != null && this.contents.toString() != null && this.currentElement != null)
		{
			String data = contents.toString().trim();
			this.currentElement.setData(data);
			this.currentElement = this.currentElement.getParent();
		}
	}

	public void characters(char ch[], int start, int length)
	{
		contents.reset();
		contents.write(ch, start, length);
	}

	/**
	 * Returns true if there is no root, otherwise return false
	 * @return
	 */
	private boolean hasRoot()
	{
		return this.rootElement != null;
	}
}
