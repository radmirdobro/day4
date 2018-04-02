package ru.innopolis;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

       /*
        ArrayList<Employee> empDb = new ArrayList();
        empDb.add(new Employee("Вася", 42, "Дворник", 10000));
        empDb.add(new Employee("Надежда", 30, "Секретарь", 15000));
        empDb.add(new Employee("Петр", 21, "Грузчик", 20000));
        */

        //метод save

        Employee e1 = new Employee("Тагир", 63, "Бригадир", 32000);

        //e1.save();

        //e1.delete();

        String gname = "Оля";
        System.out.println("***** Поиск сотрудника по имени: " + gname);

        e1.getByName(gname).getEmployee();

        String gjob = "Секретарь";
        System.out.println("");
        System.out.println("***** Список сотрудников по должности: " + gjob);
        ArrayList<Employee> empGBJ = e1.getByJob(gjob);
        for (int a = 0; a < empGBJ.size(); a++) {
            System.out.print(empGBJ.get(a).getJob());
            System.out.print(" | " + empGBJ.get(a).getName());
            System.out.print(" | " + empGBJ.get(a).getAge());
            System.out.print(" | " + empGBJ.get(a).getSalary());
            System.out.println(" | " + empGBJ.get(a));
        }


        System.out.println("");
        System.out.println("***** Сохранить или обновить: ");
        e1.saveOrUpdate();

        String jobNow = "Грузчик";
        String jobNew = "Повар";
        System.out.println("");
        System.out.println("***** Cмена должностей: " + jobNow + " на " + jobNew);
        e1.changeAllWork(jobNow,jobNew);

        System.out.println("");
        System.out.println("***** Список всех сотрудников");
        e1.allEmployees();
    }
}
