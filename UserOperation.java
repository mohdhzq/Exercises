import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOperation {
    public String getOldPassword(String user_name) throws SQLException, ClassNotFoundException {
	String query = "SELECT password FROM user_data WHERE user_name = ?";
	PreparedStatement ps = DBUtility.getDBConnection().prepareStatement(query);
	ps.setString(1, user_name);
	
	ResultSet resultSet = ps.executeQuery();
	if(!resultSet.next()) {
            return null;
	}
	else
            return resultSet.getString(1);
    }
    public int setNewPassword(String user_name,String password_new) throws SQLException, ClassNotFoundException {
		
	String query = "UPDATE user_data SET password = ? WHERE user_name = ?";
	PreparedStatement ps = DBUtility.getDBConnection().prepareStatement(query);
	ps.setString(1, password_new);
	ps.setString(2, user_name);
		
	int i = ps.executeUpdate();
	return i;	
    }
}
