package db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {

    public static Connection getConnection(){

        try{
            String url="jdbc:mysql://localhost:3306/kasir_db";
            String user="root";
            String password="";

            Connection conn=
                DriverManager.getConnection(
                    url,
                    user,
                    password
                );

            return conn;

        }catch(Exception e){
            System.out.println(e);
            return null;
        }

    }

}