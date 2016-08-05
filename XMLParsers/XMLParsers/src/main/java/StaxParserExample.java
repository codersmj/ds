package main.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StaxParserExample {
	public static void main(String[] args) throws FileNotFoundException,
			XMLStreamException {
		
		if (args.length != 1)
			throw new RuntimeException("The name of the XML file is required!");

		List<Employee> employees = null;
		Employee empl = null;
		String text = null;

		XMLInputFactory factory = XMLInputFactory.newInstance();
		XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(new File(args[0])));

		while (reader.hasNext()) {
			int Event = reader.next();

			switch (Event) {
				case XMLStreamConstants.START_ELEMENT: {
					if ("Employee".equals(reader.getLocalName())) {
						empl = new Employee();
						empl.setID(reader.getAttributeValue(0));
					}
					
					if ("Employees".equals(reader.getLocalName()))
						employees = new ArrayList<>();

					break;
				}
				case XMLStreamConstants.CHARACTERS: {
					text = reader.getText().trim();
					break;
				}
				case XMLStreamConstants.END_ELEMENT: {
					switch (reader.getLocalName()) {
						case "Employee":
							employees.add(empl);
							break;
						case "Firstname":
							empl.setFirstname(text);
							break;
						case "Lastname":
							empl.setLastname(text);
							break;
						case "Age":
							empl.setAge(Integer.parseInt(text));
							break;
						case "Salary": {
							empl.setSalary(Double.parseDouble(text));
							break;
						}
					}
					break;
				}
			}
		}

		// Print all employees.
		for (Employee employee : employees)
			System.out.println(employee.toString());
	}
}
