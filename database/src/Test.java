import java.util.*;
import java.sql.*;
public class Test
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://192.168.56.101:4567/madang",
                    "bslee","1234");
            int menu = 0;
            int[] a = new int[2];
            String[] b = new String[2];
            System.out.println("메뉴를 입력하시오(1.도서 추가 2.도서 삭제 3.도서 검색)");
            menu = scanner.nextInt();
            switch (menu) {
                case 1:
                    System.out.println("bookid, bookname, publisher, price를 차례대로 입력하시오.");
                    a[0] = scanner.nextInt();
                    b[0] = scanner.next();
                    b[1] = scanner.next();
                    a[1] = scanner.nextInt();
                    PreparedStatement stmt1 = con.prepareStatement("INSERT INTO Book(bookid, bookname, publisher, price) VALUES(?, ? , ?, ?)");
                    stmt1.setInt(1, a[0]);
                    stmt1.setString(2, b[0]);
                    stmt1.setString(3, b[1]);
                    stmt1.setInt(4, a[1]);
                    int c =stmt1.executeUpdate();
                    if(c!=0)
                        System.out.println("정상적으로 추가되었습니다.");
                    break;
                case 2:
                    System.out.println("삭제할 도서명을 입력하시오");
                    String name = scanner.next();
                    String Query2 = "DELETE FROM Book WHERE bookname = " + "'" +name + "'";
                    PreparedStatement stmt2 = con.prepareStatement(Query2);
                    int d =stmt2.executeUpdate(Query2);
                    if(d!=0)
                        System.out.println("정상적으로 삭제되었습니다.");
                    break;
                case 3:
                    System.out.println("검색할 도서명을 입력하시오");
                    scanner.nextLine();
                    String search = scanner.nextLine();
                    Statement stmt3 = con.createStatement();
                    ResultSet rs3=stmt3.executeQuery("SELECT * FROM Book WHERE bookname IN ("+ "'"+search+"')");
                    while(rs3.next())
                        System.out.println(rs3.getString("bookname") + " " + rs3.getString("publisher") +
                                " " + rs3.getInt("price"));
                    break;
            }
            con.close();

        }catch(Exception e){ System.out.println(e);}
        scanner.close();
    }
}