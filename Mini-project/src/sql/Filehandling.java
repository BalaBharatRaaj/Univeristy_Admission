import java.sql.*;
import java.io.*;

class Filehandling{
	public static void display3(String[] app, Connection myConn) {
		try {
			PreparedStatement Prestmt = myConn.prepareStatement("insert into colleges(name,close_rank) values(?,?)");
			Prestmt.setString(1,app[0]);
			Prestmt.setInt(2, Integer.parseInt(app[1]));
			Prestmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void display2(String[] app, Connection myConn) {
		try {
			PreparedStatement Prestmt = myConn.prepareStatement("select * from account where username = ?");
			Prestmt.setString(1, app[1]);
			ResultSet myRes = Prestmt.executeQuery();
			if(myRes.next()) {
				System.out.println("Account already present");
			}
			else {
				Prestmt = myConn.prepareStatement("insert into account(name,username,password,choice) values(?,?,?,?)");
				Prestmt.setString(1,app[0]);
				Prestmt.setString(2,app[1]);
				Prestmt.setString(3, app[2]);
				Prestmt.setInt(4, Integer.parseInt(app[3]));
				Prestmt.executeUpdate();
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void display1(String[] app, Connection myConn) {
		try {
			int ch1, ch2, ch3;
			String stmt = "select sno from colleges where name = ?";
			PreparedStatement Prestmt = myConn.prepareStatement(stmt); Prestmt.setString(1, app[6]);
			ResultSet myRes = Prestmt.executeQuery();
			myRes.next(); ch1 = myRes.getInt("sno");
			Prestmt = myConn.prepareStatement(stmt); Prestmt.setString(1, app[7]);
			myRes = Prestmt.executeQuery();
			myRes.next(); ch2 = myRes.getInt("sno");
			Prestmt = myConn.prepareStatement(stmt); Prestmt.setString(1, app[8]);
			myRes = Prestmt.executeQuery();
			myRes.next(); ch3 = myRes.getInt("sno");
			Prestmt = myConn.prepareStatement("insert into application(name,year,gender,maths,phy,chem,cut_off,choice1,choice2,choice3) values(?,?,?,?,?,?,?,?,?,?)");
			double cut_off = ((double)Integer.parseInt(app[4]) + (double)Integer.parseInt(app[5]))/4.0 + ((double)Integer.parseInt(app[3]))/2.0;
			Prestmt.setString(1, app[0]);
			Prestmt.setInt(2, Integer.parseInt(app[1]));
			Prestmt.setString(3, app[2]);
			Prestmt.setInt(4, Integer.parseInt(app[3]));
			Prestmt.setInt(5, Integer.parseInt(app[4]));
			Prestmt.setInt(6, Integer.parseInt(app[5]));
	        Prestmt.setDouble(7, cut_off);
	        Prestmt.setInt(8, ch1);Prestmt.setInt(9, ch2);Prestmt.setInt(10, ch3);
			Prestmt.executeUpdate();
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public static void main(String[] args) throws IOException{
		try {
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini-project", "root", "root");
			BufferedReader br = new BufferedReader(new FileReader("application.csv"));
			int flag = 0;
			String line = " ";
			String split = ",";
			while((line = br.readLine())!= null) {
				if(flag == 0) {
					flag = 1;
					continue;
				}
				String[] app = line.split(split);
				for(int i=0;i<9;i++) {
					System.out.print(app[i] + " ");
				}
				System.out.println();
				display1(app,myConn);
			}
			br.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}