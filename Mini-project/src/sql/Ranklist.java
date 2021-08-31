package sql;
import java.sql.*;
public class Ranklist{
	@SuppressWarnings("resource")
	public int generate(){
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini-project", "root", "root");
			PreparedStatement Prestmt = myConn.prepareStatement("select * from rank");
			ResultSet myRes = Prestmt.executeQuery();
			if(myRes.next()) {
                            return 1;
			}
			else {
				int rank = 0;
				myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini-project", "root", "root");
				String stmt = "select * from application order by cut_off desc, name asc";
				String stmt1 = "insert into rank(name, cut_off, college) values(?,?,?)";
				Prestmt = myConn.prepareStatement(stmt);
				myRes = Prestmt.executeQuery();
				ResultSet myRes1;
				while(myRes.next()){
					rank++;
					Prestmt = myConn.prepareStatement("select close_rank from colleges where sno = ?");
					Prestmt.setInt(1,myRes.getInt("choice1"));
					myRes1 = Prestmt.executeQuery();
					myRes1.next();
					if(rank <= myRes1.getInt("close_rank")) {
						Prestmt = myConn.prepareStatement(stmt1);
						Prestmt.setString(1, myRes.getString("name")); 
						Prestmt.setDouble(2, myRes.getDouble("cut_off"));
						Prestmt.setInt(3, myRes.getInt("choice1"));
						Prestmt.executeUpdate();
						continue;
					}
					Prestmt = myConn.prepareStatement("select close_rank from colleges where sno = ?");
					Prestmt.setInt(1,myRes.getInt("choice2"));
					myRes1 = Prestmt.executeQuery();
					myRes1.next();
				    if(rank <= myRes1.getInt("close_rank")) {
				    	Prestmt = myConn.prepareStatement(stmt1);
						Prestmt.setString(1, myRes.getString("name")); 
						Prestmt.setDouble(2, myRes.getDouble("cut_off"));
						Prestmt.setInt(3, myRes.getInt("choice2"));
						Prestmt.executeUpdate();
						continue;
					}
				    Prestmt = myConn.prepareStatement("select close_rank from colleges where sno = ?");
					Prestmt.setInt(1,myRes.getInt("choice3"));
					myRes1 = Prestmt.executeQuery();myRes1.next();
					if(rank <= myRes1.getInt("close_rank")){
						Prestmt = myConn.prepareStatement(stmt1);
						Prestmt.setString(1, myRes.getString("name")); 
						Prestmt.setDouble(2, myRes.getDouble("cut_off"));
						Prestmt.setInt(3, myRes.getInt("choice3"));
						Prestmt.executeUpdate();
					}
					else {
						Prestmt = myConn.prepareStatement(stmt1);
						Prestmt.setString(1, myRes.getString("name")); 
						Prestmt.setDouble(2, myRes.getDouble("cut_off"));
						Prestmt.setInt(3, -1);
						Prestmt.executeUpdate();
					}
				}
				myConn.close();
			}
		}
		catch(SQLException e) {
			System.out.println(e);
		}
                return 0;
	}
}