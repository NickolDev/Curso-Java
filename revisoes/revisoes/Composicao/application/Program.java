package Composicao.application;

import Composicao.entities.Department;
import Composicao.entities.HourContract;
import Composicao.entities.Worker;
import Composicao.entities.enums.WorkerLevel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Entre o nome do departamento: ");
        String departmentName = sc.nextLine();

        Department dept = new Department(departmentName);

        System.out.println("Entre os dados do trabalhador:");
        System.out.print("Nome: ");
        String workerName = sc.nextLine();
        System.out.print("Nivel: JUNIOR, MID_LEVEL ou SENIOR: ");
        WorkerLevel workerLevel = WorkerLevel.valueOf(sc.nextLine());
        System.out.print("Salario base: ");
        double baseSalary = sc.nextDouble();

        Worker worker = new Worker(workerName, workerLevel, baseSalary, dept);

        System.out.print("Quantos contratos esse trabalhador tem? ");
        int n = sc.nextInt();

        for (int i=1; i<=n; i++) {
            System.out.println("Entre com os dados do contrato #" + i + ":");
            System.out.print("Data (DD/MM/YYYY): ");
            LocalDate contractDate = LocalDate.parse(sc.next(), fmt);
            System.out.print("Valor por hora: ");
            double valuePerHour = sc.nextDouble();
            System.out.print("Duracao (horas): ");
            int hours = sc.nextInt();
            HourContract contract = new HourContract(contractDate, valuePerHour, hours);
            worker.addContract(contract);
        }

        System.out.println();
        System.out.print("Entre o ano e mes para calcular o ganho (MM/YYYY): ");
        String monthAndYear = sc.next();

        int month = Integer.parseInt(monthAndYear.substring(0, 2));
        int year = Integer.parseInt(monthAndYear.substring(3));

        System.out.println("Nome: " + worker.getName());
        System.out.println("Departamento: " + worker.getDepartment().getName());
        System.out.println("Ganho para " + monthAndYear + ": " + String.format("%.2f", worker.income(year, month)));

        sc.close();
    }
}