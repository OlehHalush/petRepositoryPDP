package creational.prototype;

public class RunTest {
    public static void main(String[] args) {
        Employees emps = new Employees();
        emps.loadEmployees();

        Employees newEmpsList = (Employees) emps.clone();
        Employees newEmpsList2 = (Employees) emps.clone();
        newEmpsList.getEmployeeList().add("Ivan");
        newEmpsList2.getEmployeeList().remove("Olena");

        System.out.println("Original list: " + emps.getEmployeeList());
        System.out.println("New employee list with added 1 employee: " + newEmpsList.getEmployeeList());
        System.out.println("New employee list2 with removed 1 employee: " + newEmpsList2.getEmployeeList());
    }
}
