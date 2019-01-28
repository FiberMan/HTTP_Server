class Employee {
    int id;
    String firstName;
    String lastName;
    double salary;

    public Employee() {
    }

    Employee(int idNew, String firstNameNew, String lastNameNew, double salaryNew) {
        id = idNew;
        firstName = firstNameNew;
        lastName = lastNameNew;
        salary = salaryNew;
    }

    public String toString() {
        return "Name: " + firstName + " " + lastName + ", Salary: " + salary;
    }
}