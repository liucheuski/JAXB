import model.Department;
import model.Employee;
import model.Organization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class XmlGo {

    private static final String XML = "src/main/resources/test.xml";

    public static void main(String[] args) {
        Employee employee1 = new Employee("1", "Tom", null);
        Employee employee2 = new Employee("2", "Jack", "1");
        Employee employee3 = new Employee("3", "Roberto", null);
        List<Employee> employees = List.of(employee1, employee2, employee3);
        Department department = new Department("1", "Accounting", "Grodno", employees);
        List<Department> departments = List.of(department);
        Organization organization = new Organization("Firma", "Minsk", departments);

        try {
            JAXBContext context = JAXBContext.newInstance(Organization.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(organization, System.out);
            File file = new File(XML);
            marshaller.marshal(organization, file);
            System.err.println("Write to file: " + file.getAbsolutePath());

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Organization organizationFromFile = (Organization) unmarshaller.unmarshal(new FileReader(XML));
            List<Department> departmentsFromFile = organizationFromFile.getDepartments();
            for (Department dept : departmentsFromFile) {
                System.out.println("Employee: " + dept.getDeptName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
