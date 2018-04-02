package ru.innopolis;

/**
 * Created by Fakhriev Radmir
 */

import java.io.*;
import java.util.ArrayList;


public class Employee implements Serializable {

    private String name;
    private String job;
    private int age;
    private double salary;

    private static final long serialVersionUID = 1L;
    private static String filename = "employee.txt";

    public Employee(String name, int age, String job, double salary) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.salary = salary;
    }

    /**
     * геттеры
     */

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }

    public double getSalary() {
        return salary;
    }


    public Employee() {
    }

    public boolean getEmployee() {
        System.out.print("Имя: " + this.name);
        System.out.print("| Возраст: " + this.age);
        System.out.print("| Должность: " + this.job);
        System.out.println("| Зарплата: " + this.salary);
        return true;
    }


    /**
     * проверка файла на пустоту
     */
    private static boolean testFile() {

        try {
            File file = new File(Employee.filename);
            if (file.length() == 0) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("Файл Пустой!");
            System.exit(0);
        }
        return true;
    }

    /**
     * считывание файла в коллекцию
     */
    private static ArrayList<Employee> readFile() {
        ArrayList<Employee> newEmpDb = null;

        if (Employee.testFile() == true) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Employee.filename))) {
                newEmpDb = (ArrayList<Employee>) ois.readObject();

            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        return newEmpDb;
    }


    /**
     * запись коллекции в файл
     */

    private void writeFile(ArrayList<Employee> EmpDb) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.filename))) {
            oos.writeObject(EmpDb);
            System.out.println("Запись произведена");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    /**
     * вывод всех данных из файла
     */
    public void allEmployees() {
        if (this.testFile() == true) {
            ArrayList<Employee> newEmpDb = this.readFile();
            for (int i = 0; i < newEmpDb.size(); i++) {
                System.out.print(i + ") " + newEmpDb.get(i).name);
                System.out.print(" | " + newEmpDb.get(i).age);
                System.out.print(" | " + newEmpDb.get(i).job);
                System.out.print(" | " + newEmpDb.get(i).salary);
                System.out.println(" | " + newEmpDb.get(i));
            }
        }
    }


    /**
     * метод save
     */

    public boolean save() {
        //читаем файл в коллекцию
        ArrayList<Employee> newEmpDb = this.readFile();
        int dublCounter = 0;
        Employee emp = new Employee(this.name, this.age, this.job, this.salary);
        //добавляем в конец коллекции нового сотрудника
        for (int i = 0; i < newEmpDb.size(); i++) {
            try {
                if (newEmpDb.get(i).hashCode() == emp.hashCode()) {
                    dublCounter++;
                    throw new DublException();

                }
            } catch (DublException e) {
                System.out.println("Такой работник уже записан!");
                break;
            }
        }
        if (dublCounter == 0) {
            newEmpDb.add(emp);
        }

        //записываем коллекцию обратно в файл
        this.writeFile(newEmpDb);
        return true;
    }


    /**
     * метод delete
     */

    public boolean delete() {
        //читаем файл в коллекцию
        ArrayList<Employee> newEmpDb = this.readFile();
        Employee emp = new Employee(this.name, this.age, this.job, this.salary);
        for (int i = 0; i < newEmpDb.size(); i++) {
            if (newEmpDb.get(i).hashCode() == emp.hashCode()) {
                newEmpDb.remove(i);
            }
        }
        //записываем коллекцию обратно в файл
        this.writeFile(newEmpDb);
        return true;
    }


    /**
     * метод getByName
     */
    public static Employee getByName(String gname) {
        //читаем файл в коллекцию
        ArrayList<Employee> newEmpDb = Employee.readFile();
        Employee tempEmp = new Employee();
        for (int i = 0; i < newEmpDb.size(); i++) {

            if (gname.equals(newEmpDb.get(i).name)) {
                tempEmp = newEmpDb.get(i);
                break;
            }
        }
        return tempEmp;
    }


    /**
     * метод getByJob
     */
    public static ArrayList<Employee> getByJob(String gjob) {
        //читаем файл в коллекцию
        ArrayList<Employee> newEmpDb = Employee.readFile();
        ArrayList<Employee> tempEmpDb = new ArrayList();
        for (int i = 0; i < newEmpDb.size(); i++) {
            if (newEmpDb.get(i).job.equals(gjob)) {
                tempEmpDb.add(newEmpDb.get(i));
            }
        }
        return tempEmpDb;
    }


    /**
     * метод saveOrUpdate
     */
    public boolean saveOrUpdate() {
        //читаем файл в коллекцию
        ArrayList<Employee> newEmpDb = this.readFile();
        int dublCounter = 0;
        Employee emp = new Employee(this.name, this.age, this.job, this.salary);
        //добавляем в конец коллекции нового сотрудника
        for (int i = 0; i < newEmpDb.size(); i++) {
            if (newEmpDb.get(i).hashCode() == emp.hashCode()) {
                dublCounter++;
                newEmpDb.set(i, emp);
                System.out.println("Данные по сотруднику "+ this.name +" обновились!");
                break;
            }
        }

        if (dublCounter == 0) {
            newEmpDb.add(emp);
            System.out.println("Сотрудник "+ this.name + "  добавлен!");
        }

        //записываем коллекцию обратно в файл
        this.writeFile(newEmpDb);
        return true;
    }

    public  boolean changeAllWork(String jobNow, String jobNew) {
        //читаем файл в коллекцию
        ArrayList<Employee> newEmpDb = Employee.readFile();

        for (int i = 0; i < newEmpDb.size(); i++) {
            if (newEmpDb.get(i).job.equals(jobNow)) {
                Employee tempEmp = new Employee(newEmpDb.get(i).name, newEmpDb.get(i).age, jobNew, newEmpDb.get(i).salary);
                newEmpDb.set(i, tempEmp);
            }
        }
        this.writeFile(newEmpDb);
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.age;
        result = (int) (prime * result + this.salary);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (name.equals(other.name))
            return true;
        if (job.equals(other.job))
            return true;
        return true;
    }

}