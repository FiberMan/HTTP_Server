import java.util.Random;

public class MockEmployeesGenerator {
    static String[] firstNames = {"Yuriy", "Anatoliy", "Vitaliy", "Mykola", "Yeva", "Svitlana", "Mariya", "Tatiana"};
    static String[] lastNames = {"Petrenko", "Symonenko", "Ivanenko", "Bondarenko", "Nimchenko", "Radchenko", "Tymoshenko", "Zubrenko"};
    static double baseSalary = 10000;

    static Employee[] generate(int size) {
        Employee[] randomEmployees = new Employee[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            Employee employee = new Employee();
            employee.id = i + 1;

            employee.firstName = firstNames[random.nextInt(firstNames.length)];
            employee.lastName = lastNames[random.nextInt(lastNames.length)];
            employee.salary = baseSalary * (1.5 - random.nextDouble());

            randomEmployees[i] = employee;
        }
        return randomEmployees;
    }

}