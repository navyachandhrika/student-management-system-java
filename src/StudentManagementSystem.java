import java.util.*;
import java.io.*;
public class StudentManagementSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc=new Scanner(System.in);
    public static void main(String args[])
    {
        loadFromFile();
        while(true)
        {
            System.out.println("Student Management System -----");
            System.out.println("1. Add student");
            System.out.println("2. view students");
            System.out.println("3. update students");
            System.out.println("4. delete students");
            System.out.println("5. search student");
            System.out.println("6. exit");
            System.out.println("Enter your choice");
            int choice=sc.nextInt();


            switch(choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                {
                    searchStudent();
                    break;
                }
                case 6: {
                    saveToFile();
                    System.out.println("Thank you");
                    System.exit(0);
                    break;
                }
                default:
                    System.out.println("Invalid choice. Try again");

            }
        }
    }
    static void addStudent()
    {
        System.out.println("Enter student ID: ");
        int id=sc.nextInt();
        sc.nextLine();

        System.out.println("Enter student Name: ");
        String name=sc.nextLine();

        System.out.println("Enter student age: ");
        int age=sc.nextInt();
        sc.nextLine();

        System.out.println("Enter student course: ");
        String course=sc.nextLine();

        Student s =new Student(id, name, age, course);
        students.add(s);
        System.out.println("Student added successfully");
    }
    static void viewStudents()
    {
        if(students.isEmpty())
        {
            System.out.println("No students available");
            return;
        }
        System.out.println("ID\tName\tAge\tCourse");
        System.out.println(".....................");
        for(Student s:students)
        {
            s.display();
        }
    }

    static void updateStudent() {
        System.out.println("Enter student id to update");
        int id = sc.nextInt();
        sc.nextLine();
        boolean found = false;
        for (Student s : students) {
            if (s.id == id) {
                found=true;
                System.out.println("\nCurrent details: ");
                viewStudents();
                System.out.println("\nWhat do you want to update?");
                System.out.println("1. Name");
                System.out.println("2.Age");
                System.out.println("3.Course");
                System.out.println("4.Update all");
                System.out.println("Enter your choice: ");
                int option = sc.nextInt();
                sc.nextLine();
                switch (option) {
                    case 1: {
                        System.out.println("Enter new name: ");
                        s.name = sc.nextLine();
                        break;
                    }
                    case 2: {
                        System.out.println("Enter new age: ");
                        s.age = sc.nextInt();
                        break;
                    }
                    case 3: {
                        System.out.println("Enter new course: ");
                        s.course = sc.nextLine();
                        break;
                    }
                    case 4: {
                        System.out.println("Enter new name: ");
                        s.name = sc.nextLine();
                        System.out.println("Enter new age: ");
                        s.age = sc.nextInt();
                        System.out.println("Enter new course: ");
                        s.course = sc.nextLine();
                        break;
                    }
                    default:
                        System.out.println("Invalid choice");
                        return;
                }
            }
        }
        if(!found) {
            System.out.println("Student not found");
        }
    }

      static void deleteStudent(){
        System.out.println("Enter student id to delete");
        int id=sc.nextInt();
        for(Student s:students)
        {
            if(s.id==id)
            {
                students.remove(s);
                System.out.println("Student deleted successfully");
                return;
            }
        }
        System.out.println("Student not found");
    }
    static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt"))) {
            for (Student s : students) {
                writer.write(s.id + "," + s.name + "," + s.age + "," + s.course);
                writer.newLine();
            }
            System.out.println("Data saved to file at: "+new File("Students.txt").getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("students.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String course = parts[3];
                students.add(new Student(id, name, age, course));
            }
            System.out.println("Data loaded from file.");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found, starting fresh.");
        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    static void searchStudent()
    {
        System.out.println("\nSearch by: ");
        System.out.println("1. By ID");
        System.out.println("2. By Name");
        System.out.println("Enter your choice");
        int choice=sc.nextInt();
        sc.nextLine();
        boolean found=false;
        switch(choice)
        {
            case 1:
            {
                System.out.println("enter ID to search: ");
                int id=sc.nextInt();
                sc.nextLine();
                for(Student s:students) {
                    if (s.id == id) {
                        System.out.println("Student found: ");
                        System.out.println("ID\tName\tAge\tCourse");
                        System.out.println(".....................");
                        s.display();
                        found = true;
                        break;

                    }

                }
            }
            case 2:
            {
                System.out.println("Enter name to search: ");
                String name=sc.nextLine();
                System.out.println("ID\tName\tAge\tCourse");
                System.out.println(".....................");
                for(Student s:students)
                {
                    if(s.name.equalsIgnoreCase(name)) {
                        s.display();
                        found=true;
                    }
                }
            }
            default:
                System.out.println("Invalid choice!");
        }
        if(!found)
        {
            System.out.println("No student found");
        }
    }
}
