package test;

import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import com.yeep.basis.xml.XmlElement;
import com.yeep.basis.xml.XmlHandler;

public class TestXmlElement
{
	public static void main(String[] args)
	{
		InputStream in = TestXmlElement.class.getResourceAsStream("TestXmlElement.xml");
		XmlHandler handler = new XmlHandler();

		try
		{
			XmlElement root = handler.load(in);
			printElement("", root);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static void printElement(String prefix, XmlElement element)
	{
		if (element == null)
			return;

		prefix += "#";
		System.out.println(prefix + "Element.name = [" + element.getName() + "], value = [" + element.getData() + "]");

		Map<String, String> attrs = element.getAttributes();
		Set<String> keySet = attrs.keySet();
		if (keySet != null && !keySet.isEmpty())
		{
			for (String name : keySet)
			{
				System.out.println(prefix + "Attribute : name = [" + name + "], value = [" + attrs.get(name) + "]");
			}
		}

		if (element.getSubElements() != null && !element.getSubElements().isEmpty())
		{
			for (XmlElement subElement : element.getSubElements())
			{
				printElement(prefix, subElement);
			}
		}
	}
}
