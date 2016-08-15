package xml.sax;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class TestSaxParser 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		//Locate the file
		File xmlFile = new File("/Users/shilpajain/git/ds/BasicJava/src/xml/sax/sample.xml");
		
		//Create the parser instance
		UsersXmlParser parser = new UsersXmlParser();
		
		//Parse the file
		ArrayList<User> users = parser.parseXml(new FileInputStream(xmlFile));
		
		//Verify the result
		System.out.println(users);
	}
}
