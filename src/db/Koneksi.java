package db;

import java.sql.*;

public class Koneksi {

public static Connection getConnection(){

try{

Class.forName(
"com.mysql.cj.jdbc.Driver"
);

Connection conn =
DriverManager.getConnection(
"jdbc:mysql://127.0.0.1:3306/kasir_db?useSSL=false&serverTimezone=UTC",
"root",
"adit10"
);

System.out.println(
"CONNECTED MYSQL"
);

return conn;

}
catch(Exception e){

e.printStackTrace();

return null;

}

}

}