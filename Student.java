// package src;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.*;


    public class Kushi {
    private static final String URL = "jdbc:mysql://localhost:3306/sarika";
    private static final String USER = "root";
    private static final String PASSWORD = "Sarika@357";
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
       while(true){
        System.out.println("\n ====STUDENT MANAGEMENT SYSSTEM ====");
        System.out.println("1.Add Student");
         System.out.println("2.View all students");
          System.out.println("3.Search student");
           System.out.println("4.Update student");
            System.out.println("5.Delete student");
             System.out.println("6.Exit");
              System.out.println("Enter your choice:");
              int Choice=sc.nextInt();
              switch(Choice){
                case 1:
                    addStudent();
                    break;
                    case 2:
                        viewStudent();
                        break;
                        case 3:
                            searchStudent();
                            break;
                            case 4:
                                updateStudent();
                                break;
                                case 5:
                                    deleteStudent();
                                    break;
                                    case 6:
                                        System.out.println("Thank you");
                                         System.exit(0);
                                    default:
                                         System.out.println("Invalid choice");
              }
       }
}
static void addStudent(){
 System.out.println("Enter student id: ");
 int id=sc.nextInt();
 sc.nextLine();
  System.out.println("Enter name");
  String name=sc.nextLine();
   System.out.println("Enter age");
   int age=sc.nextInt();
   sc.nextLine();
    System.out.println("Enter course");
    String course =  sc.nextLine();
     System.out.println("Enter email");
     String eamil=sc.nextLine();
     String sql="insert into students values(?,?,?,?,?)";
     try(Connection con=DriverManager.getConnection(URL,USER,PASSWORD);
     PreparedStatement ps= con.prepareStatement(sql))
     {
        ps.setInt(1,id);
        ps.setString(2,name);
        ps.setInt(3,age);
        ps.setString(4,course);
        ps.setString(5,eamil);
        int rows = ps.executeUpdate();
        if(rows>0){
            System.out.println("student added successfully");
        }
     }
      catch(SQLException e){
        System.out.println("error:"+e.getMessage());
      }
    }
    static void viewStudent(){
             String sql="select * from students";  
             try (Connection con=DriverManager.getConnection(URL,USER,PASSWORD);
            Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(sql)){
            System.out.println("\n ID\tNAME\tAGE\tcourse\temail");
            System.out.println("---------------------");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t");
            }
        }     
        catch(SQLException e){
             System.out.println("error:"+e.getMessage());
        }     
      }
      static void searchStudent(){
        System.out.println("enter student id");
        int id=sc.nextInt();
        String sql="select * from students where id=?";
         try (Connection con=DriverManager.getConnection(URL,USER,PASSWORD);
         PreparedStatement ps= con.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
         if (rs.next()) {

                System.out.println("\nStudent Found!");
                System.out.println("ID     : " + rs.getInt("id"));
                System.out.println("Name   : " + rs.getString("name"));
                System.out.println("Age    : " + rs.getInt("age"));
                System.out.println("Course : " + rs.getString("course"));
                System.out.println("Email  : " + rs.getString("email"));

            } else {

                System.out.println("Student not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }


    // 4. UPDATE STUDENT
    static void updateStudent() {

        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Age: ");
        int age = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter New Course: ");
        String course = sc.nextLine();

        System.out.print("Enter New Email: ");
        String email = sc.nextLine();

        String sql =
                "UPDATE students SET name=?, age=?, course=?, email=? WHERE id=?";

        try (Connection con =
                     DriverManager.getConnection(URL, USER, PASSWORD);

             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setString(4, email);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Student updated successfully!");

            } else {

                System.out.println("Student ID not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }


    // 5. DELETE STUDENT
    static void deleteStudent() {

        System.out.print("Enter Student ID to delete: ");
        int id = sc.nextInt();

        String sql =
                "DELETE FROM students WHERE id=?";

        try (Connection con =
                     DriverManager.getConnection(URL, USER, PASSWORD);

             PreparedStatement ps =
                     con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Student deleted successfully!");

            } else {

                System.out.println("Student ID not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
