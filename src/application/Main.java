package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static java.time.format.DateTimeFormatter.ofPattern;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        //Aprendizado com LocalDate

        LocalDate d03 = LocalDate.parse("2024-06-15");
        System.out.println("Data: " + d03);

        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault());

        LocalDateTime d01 = LocalDateTime.parse("2024-06-15T10:30");
        System.out.println("Data e hora: " + d01.format(f1));

        //Aprendizado com LocalDateTime



        sc.close();
    }
}
