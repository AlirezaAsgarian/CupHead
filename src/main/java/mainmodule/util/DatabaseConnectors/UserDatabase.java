package mainmodule.util.DatabaseConnectors;
import java.sql.*;
public class UserDatabase {
    static UserDatabase instance;
    static  final  String DB_URL = "jdbc:mysql://localhost/alireza";
    static final String USER = "alireza";
    static final String PASS = "alireza";
    public static UserDatabase getInstance() {
        if(instance == null) {instance = new UserDatabase();}
        return instance;
    }
    UserDatabase() {
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isRegisterPossible(String userName,String password,String nickName) {
        try(ResultSet rs = getUsersResultSet();) {
            while (rs.next()){
                if(userName.equals(rs.getString("username")) || nickName.equals(rs.getString("nickname")))
                    return false;
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    private ResultSet getUsersResultSet() throws SQLException {
        Statement stm = createStatement();
        return stm.executeQuery("SELECT * FROM alireza.user_table");
    }
    private Statement createStatement() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        return conn.createStatement();
    }

    public void registerUser(String userName,String password,String nickName)  {
        try {
            Statement stm = createStatement();
            stm.executeUpdate(getSqlCommand(userName, password, nickName));
        }catch (SQLException s){
            s.printStackTrace();
        }
    }

    private String getSqlCommand(String userName, String password, String nickName) {
        System.out.println();
        System.out.println("register user");
        String command = "INSERT INTO alireza.user_table VALUES(\"" + userName + "\",\"" + nickName + "\",\"" + password + "\");";
        System.out.println(command);
        return command;
    }

    public boolean isLoginPossible(String username,String password) {
        try(ResultSet rs = getUsersResultSet();) {
            while (rs.next()){
                if(username.equals(rs.getString("username"))) {
                    return isCorrectPassword(password, rs);
                }
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean isCorrectPassword(String password, ResultSet rs) throws SQLException {
        if (password.equals(rs.getString("password"))) return true;
        return false;
    }

}
