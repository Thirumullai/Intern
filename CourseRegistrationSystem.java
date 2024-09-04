import java.util.ArrayList;
import java.util.Scanner;

// Course class
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private ArrayList<Student> registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }

    public String getSchedule() {
        return schedule;
    }

    public void registerStudent(Student student) {
        if (getAvailableSlots() > 0) {
            registeredStudents.add(student);
            student.addCourse(this);
            System.out.println(student.getName() + " has been registered for " + title + ".");
        } else {
            System.out.println("Sorry, the course " + title + " is full.");
        }
    }

    public void removeStudent(Student student) {
        if (registeredStudents.remove(student)) {
            student.removeCourse(this);
            System.out.println(student.getName() + " has been removed from " + title + ".");
        } else {
            System.out.println(student.getName() + " is not registered in " + title + ".");
        }
    }

    public void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Available Slots: " + getAvailableSlots() + "/" + capacity);
    }
}

// Student class
class Student {
    private String studentID;
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public void addCourse(Course course) {
        registeredCourses.add(course);
    }

    public void removeCourse(Course course) {
        registeredCourses.remove(course);
    }

    public void displayRegisteredCourses() {
        System.out.println(name + "'s Registered Courses:");
        for (Course course : registeredCourses) {
            System.out.println("- " + course.getTitle());
        }
    }
}

// CourseRegistrationSystem class
public class CourseRegistrationSystem {
    private ArrayList<Course> courses;
    private ArrayList<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public Student findStudentByID(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                return student;
            }
        }
        return null;
    }

    public void displayCourseListing() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            course.displayCourseDetails();
            System.out.println("-------------------");
        }
    }

    public void studentRegistration(String studentID, String courseCode) {
        Student student = findStudentByID(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            course.registerStudent(student);
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    public void courseRemoval(String studentID, String courseCode) {
        Student student = findStudentByID(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            course.removeStudent(student);
        } else {
            System.out.println("Invalid student ID or course code.");
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        // Adding some courses
        system.addCourse(new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science.", 3, "Mon & Wed 10:00 - 11:30 AM"));
        system.addCourse(new Course("MATH201", "Calculus I", "Introduction to calculus.", 2, "Tue & Thu 2:00 - 3:30 PM"));
        system.addCourse(new Course("ENG301", "English Literature", "Study of English literature.", 2, "Mon & Wed 1:00 - 2:30 PM"));

        // Adding some students
        system.addStudent(new Student("S001", "Alice"));
        system.addStudent(new Student("S002", "Bob"));

        int option;
        do {
            System.out.println("\n--- Course Registration System ---");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    system.displayCourseListing();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    system.studentRegistration(studentID, courseCode);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.nextLine();
                    system.courseRemoval(studentID, courseCode);
                    break;
                case 4:
                    System.out.print("Enter Student ID: ");
                    studentID = scanner.nextLine();
                    Student student = system.findStudentByID(studentID);
                    if (student != null) {
                        student.displayRegisteredCourses();
                    } else {
                        System.out.println("Invalid student ID.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 5);

        scanner.close();
    }
}
