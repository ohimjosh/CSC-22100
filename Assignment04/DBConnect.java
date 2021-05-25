package sample;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DBConnect {
    static final String DB_URL = "jdbc:mysql://localhost:3306/studentdatabase";
    static final String USER = "root";
    static final String PASS = "207975137";

    public static void Conn() {
        // Open a connection
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            if (conn != null) {
                System.out.println("Table created");
                String sT = "CREATE TABLE IF NOT EXISTS Students"
                        + "(empID INT UNSIGNED not NULL ,"
                        + "firstName VARCHAR(45),"
                        + "lastName VARCHAR(45),"
                        + "email VARCHAR(255),"
                        + "gender ENUM ('M', 'F', 'U'),"
                        + "PRIMARY KEY(empID))";
                PreparedStatement stmt = conn.prepareStatement(sT);
                stmt.execute(sT);

                String cO = "CREATE TABLE IF NOT EXISTS Courses"
                        + "(courseID VARCHAR(45),"
                        + "courseTitle VARCHAR(200),"
                        + "department VARCHAR(45),"
                        + "PRIMARY KEY(courseID))";
                stmt.execute(cO);

                String cL = "CREATE TABLE IF NOT EXISTS Classes"
                        + "(courseID VARCHAR(45),"
                        + "studentID INT UNSIGNED not NULL,"
                        + "sectionNo INT UNSIGNED not NULL,"
                        + "year INTEGER,"
                        + "semester ENUM ('fall', 'spring'),"
                        + "grade ENUM ('A', 'B', 'C', 'D', 'F', 'W'),"
                        + "PRIMARY KEY(courseID, studentID, sectionNo))";
                stmt.execute(cL);


                new addTables.populateClass();
                new addTables.populateCourse();
                new addTables.populateStudent();
                new addTables.updateClasses();
                addTables.studentAdd();
                new addTables.letterData();

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static class addTables {
        static Map<String, String[]> courses = new HashMap<>();
        static Map<String, String[]> classes = new HashMap<>();

        public static void parseData() throws IOException {
            // Test for Parsing txt
            List<String> content = Files.readAllLines(Paths.get("C:\\Users\\pug\\Downloads\\Spring 2021\\CSC 22100 - P\\data.csv"));
            for (int i = 1; i < content.size(); i++) {
                String[] s = content.get(i).split(",");

                // Courses :: Key , Title/Department :: Values
                String[] course1 = new String[]{s[2], s[6]};
                courses.put(s[0], course1);

                // Classes: courseID :: Key, studentID, sectionNo, semester, year, grade :: Values
                String[] class_array = new String[]{s[1], s[4], s[3]};
                classes.put(s[0], class_array);
            }
            // Classes contains: courseId, sectionNo, semester, year
            String[] tempt = new String[]{};
            for (Map.Entry<String, String[]> entry : classes.entrySet()) {
                //System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));
                tempt = entry.getValue();
                //System.out.println(tempt[0]);

            }
        }

        public static class populateClass {
            String classTable = "insert into classes (courseID, studentID, sectionNo, year, semester, grade) values (?, ?, ?, ?, ?, ?)";
            Connection conn;
            {
                try {
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement ps = conn.prepareStatement(classTable);
                    String[] tempt = new String[]{};
                    int count = 0;
                    for (Map.Entry<String, String[]> entry : classes.entrySet()) {
                        tempt = entry.getValue();
                        ps.setString(1, entry.getKey()); //courseID
                        ps.setString(2, String.valueOf(count)); //studentID
                        count++;
                        ps.setString(3, tempt[0]); //sectionNo
                        ps.setString(4, tempt[2]); //year
                        ps.setString(5, tempt[1]); //semester
                        ps.setString(6, null); //grade
                        ps.addBatch();
                    }
                    ps.executeBatch();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        public static class populateCourse {
            String courseTable = "insert into Courses (courseID, courseTitle, department) values (?, ?, ?)";
            Connection conn;

            {
                try {
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement ps = conn.prepareStatement(courseTable);
                    for (Map.Entry<String, String[]> entry : courses.entrySet()) {
                        String[] tempt = entry.getValue();
                        ps.setString(1, entry.getKey()); //courseID
                        ps.setString(2, tempt[0]); //courseTitle
                        ps.setString(3, tempt[1]); //department
                        ps.addBatch();
                    }
                    ps.executeBatch();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }

        public static class populateStudent {
            String popStud = "REPLACE INTO Students" + "(empID, firstName, lastName, email, gender) VALUES (?, ?, ?, ?, ?)";
            Connection conn;

            {
                try {
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement ps = conn.prepareStatement(popStud);
                    Integer[] empID = new Integer[]{63772505, 43775455, 23772445, 28776423};
                    String[] firstName = new String[]{"Muler", "Jules", "Ash", "Missy"};
                    String[] lastName = new String[]{"Mayer", "Notch", "Canner", "May"};
                    String[] email = new String[]{"MulerMayer@gmail.com", "JulesNotch@gmail.com", "AshCanner@gmail.com", "MissyMay@gmail.com"};
                    String[] gender = new String[]{"M", "F", "U", "F"};
                    for (int i = 0; i < firstName.length; i++) {
                        ps.setInt(1, empID[i]); //empID
                        ps.setString(2, firstName[i]); //firstName
                        ps.setString(3, lastName[i]); //lastName
                        ps.setString(4, email[i]); //email
                        ps.setString(5, gender[i]); //gender
                        ps.addBatch();
                    }
                    ps.executeBatch();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        public static class updateStudent {
            PreparedStatement ps = null;
            Connection conn;

            {
                try {
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    String upStud = "UPDATE Students SET email= 'MaxRuby@gmail.com' WHERE emplID= 28776423";
                    ps = conn.prepareStatement(upStud);
                    ps.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public static class updateCourse {
            PreparedStatement ps = null;
            Connection conn;

            {
                try {
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    String upCour = "UPDATE Courses SET courseTitle= 'Data Structures' WHERE courseID= 32130";
                    ps = conn.prepareStatement(upCour);
                    ps.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public static class updateClasses {
            PreparedStatement ps = null;
            Connection conn;

            {
                try {
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    String upclass = "UPDATE Classes SET grade='B' WHERE studentID= 87654321";
                    ps = conn.prepareStatement(upclass);
                    ps.execute();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void studentAdd() {
            Scanner myStu = new Scanner(System.in);
            System.out.print("Enter total students: ");
            int studentTotal = Integer.parseInt(myStu.nextLine());
            Connection conn;

            for (int i = 0; i < studentTotal; i++) {
                System.out.print("Enter courseID: ");
                String courseID = myStu.nextLine();
                System.out.print("Enter studentID: ");
                int studentID = Integer.parseInt(myStu.nextLine());
                System.out.print("Enter sectionNo: ");
                int sectionNo = Integer.parseInt(myStu.nextLine());
                System.out.print("Enter year: ");
                int year = Integer.parseInt(myStu.nextLine());
                System.out.print("Enter semester: ");
                String semester = myStu.nextLine();
                System.out.print("Enter grade: ");
                String grade = myStu.nextLine();


                try {
                    conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement ps = conn.prepareStatement("INSERT Classes (courseID, studentID, sectionNo, year, semester, grade)"
                            + "VALUES ('" + courseID + "','" + studentID + "','" + sectionNo + "','" + year + "','" + semester + "','" + grade + "')");
                    System.out.println("Data INSERTED to Classes table");
                    ps.executeUpdate();


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }


        public static class letterData {
            HashMap<Character, Float> g = new HashMap<Character, Float>();
            HashMap<Character, Float> probability_map = new HashMap<Character, Float>();
            float numStudent = 0;
            int allLetters = 0;

            {
                try {
                    String totalGrades = "";
                    Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                    PreparedStatement ps = conn.prepareStatement("SELECT * FROM CLASSES WHERE (courseID = '22100') AND (semester = 'spring')");
                    ResultSet result = ps.executeQuery();

                    while (result.next()) {
                        String grades = result.getString("grade");
                        totalGrades += grades;
                    }
                    System.out.println(totalGrades);

                    char[] letterGrades = totalGrades.toCharArray();
                    for (char letter : letterGrades) {
                        if (g.containsKey(letter)) {
                            g.put(letter, g.get(letter) + 1);
                        } else {
                            g.put(letter, (float) 1);
                        }
                    }

                    System.out.println(g);

                    for (Map.Entry<Character, Float> e : g.entrySet()){
                        numStudent += e.getValue();
                        allLetters += 1;

                    }
                    System.out.println(numStudent);
                    for (Map.Entry<Character, Float> e: g.entrySet()) {
                        //divides hash by total
                        float prob = e.getValue()/numStudent;
                        //replace values in hash map with result
                        probability_map.put(e.getKey(), prob);
                    }
                    System.out.println(probability_map);




                } catch (SQLException e) {
                    e.printStackTrace();
                }





            }
        }
    }
}