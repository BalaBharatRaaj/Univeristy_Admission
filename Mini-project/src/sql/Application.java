package sql;

import java.sql.*;
public class Application{
	public int app_insert(String[] app) {
            try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini-project", "root", "root");
            PreparedStatement Prestmt = myConn.prepareStatement("select * from rank");
            ResultSet myRes = Prestmt.executeQuery();
            if(myRes.next()) {
                    return 0;
            }
            else {
                    Prestmt = myConn.prepareStatement("select * from application where name = ?");
                    Prestmt.setString(1, app[0]);
                    myRes = Prestmt.executeQuery();
                    if(myRes.next()) {
                            return 1;
                    }
                    else {
                            int ch1, ch2, ch3;
                            String stmt = "select sno from colleges where name = ?";
                            Prestmt = myConn.prepareStatement(stmt); 
                            Prestmt.setString(1, app[7]);
                            myRes = Prestmt.executeQuery();
                            myRes.next(); 
                            ch1 = myRes.getInt("sno");
                            Prestmt = myConn.prepareStatement(stmt);
                            Prestmt.setString(1, app[8]);
                            myRes = Prestmt.executeQuery();
                            myRes.next(); 
                            ch2 = myRes.getInt("sno");
                            Prestmt = myConn.prepareStatement(stmt); 
                            Prestmt.setString(1, app[9]);
                            myRes = Prestmt.executeQuery();
                            myRes.next(); 
                            ch3 = myRes.getInt("sno");
                            Prestmt = myConn.prepareStatement("insert into application(name,gender,year,maths,phy,chem,cut_off,choice1,choice2,choice3) values(?,?,?,?,?,?,?,?,?,?)");
                            Prestmt.setString(1, app[0]);
                            Prestmt.setString(2, app[1]);
                            Prestmt.setInt(3, Integer.parseInt(app[2]));
                            Prestmt.setInt(4, Integer.parseInt(app[3]));
                            Prestmt.setInt(5, Integer.parseInt(app[4]));
                            Prestmt.setInt(6, Integer.parseInt(app[5]));
                            Prestmt.setDouble(7, Double.parseDouble(app[6]));
                            Prestmt.setInt(8, ch1);
                            Prestmt.setInt(9, ch2);
                            Prestmt.setDouble(10, ch3);
                            Prestmt.executeUpdate();
                    }
                    }
            myConn.close();
            return 2;
            }
            catch(SQLException e) {
                    System.out.println(e);
            }
            return -1;
    }  
    public int app_check(String name){
        try{
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mini-project", "root", "root");
            PreparedStatement Prestmt = myConn.prepareStatement("select * from application where name = ?");
            Prestmt.setString(1, name);
            ResultSet myRes = Prestmt.executeQuery();
            if(myRes.next()){
                return 0;
            }
            else{
                Prestmt = myConn.prepareStatement("select * from rank");
                myRes = Prestmt.executeQuery();
                if(myRes.next()){
                    return 0;
                }
                else{
                    return 1;
                }
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return -1;
    }
}