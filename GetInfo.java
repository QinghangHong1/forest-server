import com.google.gson.*; //for JSON
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
 
@SuppressWarnings("serial")
public class GetInfo extends HttpServlet {
 
    public GetInfo() { }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                                            throws ServletException, IOException{
        try{
            System.out.println("Connect to getInfo");
            Connection myConn = DriverManager.getConnection("jdbc:mysql://forest1.ccryyxtawuoq.us-west-1.rds.amazonaws.com/innodb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "admin", "cs48rubber");
            String userName = null;
            JsonObject result = null;
       
            try {
                if (request.getParameterMap().containsKey("user_name")) {
                    userName= request.getParameter("user_name");
                    result= getQuery(myConn, userName);
                    if(result != null){
                        response.setStatus(HttpServletResponse.SC_OK);
                    }else if(result == null){
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                        System.out.println("Not found");
                    }
                }else{
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    System.out.println("bad request");
                }
            } catch (Exception ex) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
 
            } finally {
                response.addHeader("Access-Control-Allow-Origin", "*");      
                response.setContentType("application/json; charset=utf-8");
                response.getWriter().println(result);
                response.getWriter().close();
            }
        }catch(SQLException e){
            System.out.println("Failed to connect to database");
        }
    }
    
    public static JsonObject getQuery(Connection myConn, String userName) throws SQLException{
        
        String  getStatement= "SELECT * FROM user_data WHERE user_name=?";
        PreparedStatement statement= myConn.prepareStatement(getStatement);
        statement.setString(1, userName);
       
        ResultSet myRs = statement.executeQuery();
        JsonObject result = new JsonObject();
        if(myRs.next()){ 
            result.addProperty("user_name", myRs.getString("user_name"));
            result.addProperty("user_email",myRs.getString("user_email"));
            result.addProperty("money", myRs.getInt("money"));
            result.addProperty("attack", myRs.getInt("attack"));
            result.addProperty("health", myRs.getInt("health"));
            result.addProperty("game_level", myRs.getInt("game_level"));

            return result;
        }
        return null;
    }
 
}
