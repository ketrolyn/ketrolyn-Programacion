package Modelo.BD;

import java.sql.*;

public class BaseDatos {
    // Objeto que permite conectarse com a base de datos
    private Connection con;

    public BaseDatos() {
    }

    public void conectar() {
        try {
            // Conection
            String bd = "Practica9";
            String url = "jdbc:mysql://localhost:3307/" + bd;
            String login = "root";
            String password = "usbw";
            con = DriverManager.getConnection(url, login, password);
            
            // Conctar, Drive que permite la conection
            Class.forName("com.mysql.jdbc.Driver");

            if (con == null) {
                throw new Exception("Problemas con la conexión");
            }
        } catch (Exception gnr) {
            //Para mostrar el error
            System.out.println(gnr.getMessage());
        }
    }
    // Obtener a conexao
    public Connection getCon() {
        return con;

    }
    // Fechar a Conexao
    public void desconectar()throws Exception{
        con.close();
    }
    
}
