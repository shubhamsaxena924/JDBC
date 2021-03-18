package jdbcoperations;
import java.sql.*;

public class JDBCOperations {
    public static void main(String[] args) {
        try {
            ResultSet rs = selectData();
            System.out.println("Current data in table:");
            processData(rs);
            int rows = updateData(30,"ravi123@gmail.com");
            System.out.println("No. of rows updated: "+rows);
            rs = selectData();
            System.out.println("\nCurrent data in table:");
            processData(rs);
            rows = deleteData(30);
            System.out.println("No. of rows deleted: "+rows);
            rs = selectData();
            System.out.println("\nCurrent data in table:");
            processData(rs);
            rows = insertData(30, "Ravi","ravi924@gmail.com");
            System.out.println("No. of rows inserted: "+rows);
            rs = selectData();
            System.out.println("\nCurrent data in table:");
            processData(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    //connection
    public static Connection getDbConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/secr";
        String user = "root";
        String pass = "";
        Connection con = DriverManager.getConnection(url,user,pass);
        return con;
    }
    //select
    public static ResultSet selectData() throws SQLException {
        Connection con = getDbConnection();
        Statement stmt = con.createStatement();
        String sql = "SELECT * FROM info";
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    //process selected data
    public static void processData(ResultSet rs) throws SQLException {
        if(rs!=null){
            while(rs.next()){
                //get data
                int rollno = rs.getInt("rollno");
                String name = rs.getString("name");
                String email = rs.getString("email");
                System.out.println(rollno + " " +  name + " " + email);
            }
        }
    }
    //delete on the basis of rollno
    public static int deleteData(int rollNo) throws SQLException {
        Connection con = getDbConnection();
        String sql = "DELETE FROM info WHERE rollno = ?;"; //question mark represents parameter
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,rollNo);

        int rows = pstmt.executeUpdate();
        return rows;
    }
    //update on the basis of rollno
    public static int updateData(int rollNo, String updateEmail) throws SQLException{
        Connection con = getDbConnection();
        String sql = "UPDATE info SET email = ? WHERE rollno = ?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,updateEmail);
        pstmt.setInt(2,rollNo);
        int rows = pstmt.executeUpdate();
        return rows;
    }
    //insert data
    public static int insertData(int rollNo, String insertName, String insertEmail ) throws SQLException {
        Connection con = getDbConnection();
        String sql = "INSERT INTO info (rollno, name, email) VALUES (?,?,?);";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,rollNo);
        pstmt.setString(2,insertName);
        pstmt.setString(3,insertEmail);
        int rows = pstmt.executeUpdate();
        return rows;
    }

}
