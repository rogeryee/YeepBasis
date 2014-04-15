package com.yeep.basis.xml;

import java.io.InputStream;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlHandler
{
	// Parse Handler
	private XmlParseHandler parseHandler;
	private XMLReader xmlReader;

	// Constructor
	/**
	 * Default Constructor for XmlHandler<
	 * <p>
	 * Using the XmlParseHandler as the default handler
	 * @throws SAXException 
	 */
	public XmlHandler()
	{
		this.parseHandler = new XmlParseHandler();
	}

	/**
	 * Constructor for the specific handler
	 * 
	 * @param parseHandler
	 * @throws SAXException 
	 */
	public XmlHandler(XmlParseHandler parseHandler)
	{
		this.parseHandler = parseHandler;
	}

	/**
	 * Loads and parse the xml from the InputStream
	 * @param input the input stream to load XML source
	 * @return the Root element of the xml source
	 */
	public XmlElement load(InputStream input) throws Exception
	{
		try
		{
			initialize();
			this.xmlReader.parse(new InputSource(input));
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			input.close();
		}
		return this.parseHandler.getRoot();
	}

	/**
	 * Initialize the xmlhandler
	 * @throws SAXException
	 */
	private void initialize() throws SAXException
	{
		if (xmlReader == null)
		{
			this.xmlReader = XMLReaderFactory.createXMLReader();
			this.xmlReader.setContentHandler(this.parseHandler);

			// Don't Vallidate the xml by DTD
			this.xmlReader.setFeature("http://xml.org/sax/features/validation", false);
		}
	}
}
