package sql;
import java.sql.*;
public class Applicationlist{
	public void displaycod(String colname) {
            
	}
        public String displaystud(String name){
            try{
                Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini-project", "root", "root");
                PreparedStatement Prestmt = myConn.prepareStatement("select * from rank where name = ?");
                ResultSet myRes1;
                Prestmt.setString(1, name);
                ResultSet myRes = Prestmt.executeQuery();
                if(myRes.next()){
                    Prestmt = myConn.prepareStatement("select * from colleges where sno = ?");
                    Prestmt.setInt(1, myRes.getInt("college"));
                    myRes1 = Prestmt.executeQuery();
                    if(myRes1.next()){
                        return myRes1.getString("name");
                    }
                }
                else{
                    return "Ranklist not generated";
                }
            }
            catch(SQLException e){
                System.out.println(e);
                return null;
            }
            return null;
        }
}