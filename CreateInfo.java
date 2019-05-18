import org.json.simple.*; //for JSON parsing
import org.json.simple.parser.*; //for JSON parsing
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
 
@SuppressWarnings("serial")
public class CreateInfo extends HttpServlet {
 
    public CreateInfo() {}
    public static final int money = 50;
    public static final int health = 90;
    public static final int attack = 1;
    public static final int gameLevel = 1;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                                             throws ServletException, IOException{
        try{  
            Connection myConn = DriverManager.getConnection("jdbc:mysql://forest1.ccryyxtawuoq.us-west-1.rds.amazonaws.com/innodb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "admin", "cs48rubber");
            String userName = null;
            String userEmail = null;
            if (request.getParameterMap().containsKey("user_name") && request.getParameterMap().containsKey("user_email")) {
                userName = request.getParameter("user_name");
                userEmail = request.getParameter("user_email");
                if(UpdateInfo.checkExist(myConn, userName, userEmail)){
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                }else{
                    createData(myConn, userName, userEmail);
                    response.setStatus(HttpServletResponse.SC_OK);
                }
            }else{
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

            
 
        } catch (Exception ex) {
            System.out.println("\tError");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 
        } finally {
            response.setContentType("application/json; charset=utf-8");
            response.getWriter().println();
            response.getWriter().close();
        }
    }
    public static void createData(Connection myConn, String userName, String userEmail) throws SQLException{
        String init = "SET SQL_SAFE_UPDATES=0;";
        PreparedStatement statement= myConn.prepareStatement(init);
        statement.executeUpdate();
        String createStatement = "INSERT INTO user_data (user_name, user_email, health, attack, game_level, money) VALUES (?, ?, ?, ?, ?, ?);";
        statement = myConn.prepareStatement(createStatement);
        statement.setString(1, userName);
        statement.setString(2, userEmail);
        statement.setInt(3, health);
        statement.setInt(4, attack);
        statement.setInt(5, gameLevel);
        statement.setInt(6, money);
        statement.executeUpdate();
    }
 
}
