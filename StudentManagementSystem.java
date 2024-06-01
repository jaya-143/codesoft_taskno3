package codeSoft_numbergame;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 1.Create a Student class to represent individual students. Include attributes such as name, roll number, grade, and any other relevant details.
class Student {
    private String name;
    private String rollNumber;
    private String grade;

    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }
    
    public String getName() {
        return name;
    }
    public String getRollNumber() {
    	return rollNumber;
    }
    
    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return "Student Name:" + name + ", Roll Number:" + rollNumber + ", Grade: " + grade;
    }
}

// 2.Implement a StudentManagementSystem class to manage the collection of students. Include methods to add a student, remove a student, search for a student, and display all students.
public class StudentManagementSystem {
	private List<Student> students;
    private Scanner scanner;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
// 5.Allow users to interact with the Student Management System by providing options such as adding a new student, editing an existing student's information, searching for a student, displaying all students, and exiting the application.
    public void add_Student() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();
        
// 6.Implement input validation to ensure that required fields are not left empty and that the student
        if (validateInput(name, rollNumber, grade)) {
            students.add(new Student(name, rollNumber, grade));
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Error: Name, roll number, and grade cannot be empty.");
        }
    }

    public void remove_Student() {
        System.out.print("Enter roll number of student to remove: ");
        String remove_RollNumber = scanner.nextLine();
        //students.removeIf(student -> student.getRollNumber().equals(removeRollNumber));
        boolean removed = false;
        for (Student student : students) {
            if (student.getRollNumber().equals(remove_RollNumber)) {
                students.remove(student);
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("Student removed successfully.");
        }
        else {
            System.out.println("Student not found.");
        }
    }
    
    boolean found;
    public void search_Student() {
        System.out.print("Enter roll number of student to search: ");
        String searchRollNumber = scanner.nextLine();
        for (Student student : students) {
            if (student.getRollNumber().equals(searchRollNumber)) {
                System.out.println(student);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found.");
        }
    }

    public void display_All_Students() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

// 4.Implement methods to read and write student data to a storage medium, such as a file or a database.
    public void save_Students_To_File(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student student : students) {
            	writer.println(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
            }
            System.out.println("Students saved to file.");
        } catch (IOException e) {
        	System.out.println("Error saving students to file: " + e.getMessage());
        }
    }

    public void loadStudentsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], parts[1], parts[2]));
                }
            }
            System.out.println("Students loaded from file.");
        } catch (IOException e) {
        	System.out.println("Error loading students from file: " + e.getMessage());
        }
    }
    
    private boolean validateInput(String name, String rollNumber, String grade) {
        return !name.isEmpty() && !rollNumber.isEmpty() && !grade.isEmpty();
    }

 // 3.Design the user interface for the Student Management System. This can be a console-based interface
    public void start() {
        loadStudentsFromFile("students.txt");

        while (true) {
            System.out.println("\nSTUDENT MANAGEMENT SYSTEM");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Save Students to File");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    add_Student();
                    break;
                case 2:
                    remove_Student();
                    break;
                case 3:
                    search_Student();
                    break;
                case 4:
                    display_All_Students();
                    break;
                case 5:
                    save_Students_To_File("students.txt");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        system.start();
    }
}
