package creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class Employees implements Cloneable {

    private List<String> employeesList;

    public Employees() {
        this.employeesList = new ArrayList<>();
    }

    public Employees(List<String> list) {
        this.employeesList = list;
    }

    public void loadEmployees() {
        employeesList.add("Oleh");
        employeesList.add("Bohdan");
        employeesList.add("Olena");
        employeesList.add("Maksym");
        employeesList.add("Taras");
    }

    public List<String> getEmployeeList() {
        return employeesList;
    }

    @Override
    public Object clone() {
        List<String> temp = new ArrayList<>();
        for (String employee : this.getEmployeeList()) {
            temp.add(employee);
        }
        return new Employees(temp);
    }
}
