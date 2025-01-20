package Program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import Entities.Employee;

public class Main {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Employee> list = new ArrayList<>();

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		System.out.print("Enter salary: R$ ");
		Double parameter = sc.nextDouble();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
			String line = bufferedReader.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				if (fields.length < 3) {
					System.out.println("Invalid line format: " + line);
				}
				else {
					String name = fields[0];
					String email = fields[1];
					try {
						Double salary = Double.parseDouble(fields[2]);
						list.add(new Employee(name, email, salary));
					}
					catch (NumberFormatException e) {
						System.out.println("Invalid salary value: " + fields[2]);
					}
				}
				line = bufferedReader.readLine();
			}
			List<Employee> newList1 = list.stream().filter(x -> x.getSalary() > parameter).sorted((x1, x2) -> x1.getName().compareTo(x2.getName())).collect(Collectors.toList());
			double sum = list.stream().filter(x -> x.getName().toUpperCase().contains("M")).mapToDouble(Employee::getSalary).sum();
			System.out.println("Email of people whose salary is more than R$ " + parameter + ":");
			for (Employee obj : newList1) {
				System.out.println(obj.getEmail());
			}
			System.out.println(String.format("Sum of salary of people whose name starts with 'M': R$ %.2f", sum));
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();
		
	}
}