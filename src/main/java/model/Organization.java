package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "organization")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization {
    private String name;
    private String address;

    @XmlElementWrapper(name = "departments")
    @XmlElement(name = "department")
    List<Department> departments;
}
