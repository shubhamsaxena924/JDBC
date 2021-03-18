package jdbcoperations;

import java.sql.*;

public class JDBCOperationResult {
    public static void main(String[] args) {

        int rows = 0;
        try {
            rows = insertData(12,"Vaibhav","vaibhav@gmail.com",5.73);
            System.out.println("No. of rows inserted: " + rows);
            rows = updateData(12,8.91);
            System.out.println("No. of rows updated: " + rows);
            rows = deleteData(9.9);
            System.out.println("No. of rows deleted: " + rows);
            ResultSet rs = selectData();
            if(rs!=null){
                while(rs.next()){
                    int rollno = rs.getInt("rollno");
                    String  name = rs.getString("name");
                    String email = rs.getString("email");
                    double cpi = rs.getDouble("cpi");
                    System.out.println(rollno + " " + name + " " + email + " " + cpi);
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

    }
    //get Connection
    public static Connection getDbconnetion() throws SQLException {
        String url="jdbc:mysql://localhost:3306/secr";
        String user="root";
        String pass="";
        Connection con = DriverManager.getConnection(url,user,pass);
        return con;
    }
    //select
    public static ResultSet selectData() throws SQLException {
        Connection con = getDbconnetion();
        String sql = "SELECT * FROM result;";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
    }
    //insert
    public static int insertData(int rollno, String name, String email, double cpi) throws SQLException {
        Connection con = getDbconnetion();
        String sql = "INSERT INTO result (rollno, name, email, cpi) VALUES (?,?,?,?);";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,rollno);
        pstmt.setString(2,name);
        pstmt.setString(3,email);
        pstmt.setDouble(4,cpi);
        int rows = pstmt.executeUpdate();
        return rows;
    }
    //update cpi
    public static int updateData(int rollno, double cpi) throws SQLException {
        Connection con = getDbconnetion();
        String sql = "UPDATE result SET cpi=? WHERE rollno=?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setDouble(1,cpi);
        pstmt.setInt(2,rollno);
        int rows = pstmt.executeUpdate();
        return rows;
    }
    //delete
    public static int deleteData(double cpi) throws SQLException {
        Connection con = getDbconnetion();
        String sql = "DELETE FROM result WHERE cpi=?;";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setDouble(1,cpi);
        int rows = pstmt.executeUpdate();
        return rows;
    }



}
