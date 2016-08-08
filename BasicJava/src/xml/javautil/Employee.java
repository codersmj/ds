package xml.javautil;

public class Employee {
	private String ID;
    private String Firstname;
    private String Lastname;
    private Integer age;
    private Double salary;
    
    public Employee() {}

    public Employee(String ID, String Firstname, String Lastname, Integer age, Double salary) {
    	this.ID = ID;
    	this.Firstname = Firstname;
    	this.Lastname = Lastname;
    	this.age = age;
    	this.salary = salary;
    }

    public void setID(String ID) {
		this.ID = ID;
	}

	public void setFirstname(String firstname) {
		this.Firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.Lastname = lastname;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	@Override
    public String toString() {
            return "<" + ID + ", " + Firstname + ", " + Lastname + ", " + age + ", " + salary + ">";
    }
}
