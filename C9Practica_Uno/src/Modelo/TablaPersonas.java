package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TablaPersonas {
    private Connection con;
    
    public TablaPersonas(Connection con) {
        this.con = con;
    }
    
    public void insertar(Persona p) throws Exception
    {
        // Preparar e executar a sentenca sql.
        String prepararSentencia = "INSERT INTO tpersonas VALUES (?,?,?,?);";
        PreparedStatement sentencia = con.prepareStatement(prepararSentencia);
        sentencia.setString(1, p.getNombre());
        sentencia.setString(2, p.getEdad());
        sentencia.setString(3, p.getProfesion());
        sentencia.setString(4, p.getTelefono());
      
        int linha = sentencia.executeUpdate();
        sentencia.close();
        if (linha != 1)
            throw new Exception("El número de filas actualizadas no es uno");
    }
    public ArrayList<Persona>  verTodo() throws Exception
    {
        ArrayList<Persona> lista = new ArrayList(); 
        //seleciono tudo da tabla da minha base de dados
        String tablaDaBase = "select * from tpersonas;";
        // Prepara a sentencia
        PreparedStatement ejecutar = con.prepareStatement(tablaDaBase);
       
// Executa a sentencia
      ResultSet r = ejecutar.executeQuery();
      // .next passa para a linha senguinte e devolve true. 
      //E se é a ultima pois  vai retornar um falso.
       while(r.next())
       {
                // Tenho que criar outro objeto pra guardar os dados 
                //das  pessoas e me devolver um objeto completo.
                Persona p = new Persona();
                // r.get é pra pegar os registros dos  campos (nombre,edad...) e guardar
                p.setNombre(r.getString("nombre"));
                p.setEdad(r.getString("edad"));
                p.setProfesion(r.getString("profesion"));
                p.setTelefono(r.getString("telefono"));
                lista.add(p);
       }
       return lista;
    }
    public Persona p(String nombre) throws SQLException{
        String prepararSentencia = "SELECT * FROM tpersonas where nombre=?;";
        PreparedStatement sentencia= con.prepareStatement(prepararSentencia);
        sentencia.setString(1, nombre);
        // Si é para modificar sera  uma executeUpdate
        // Si é para selecionar sera uma execute query
        ResultSet fila =sentencia.executeQuery();
        if(fila.next()){
           Persona p =new Persona();
           p.setNombre(fila.getString("nombre"));
           p.setEdad(fila.getString("edad"));
           p.setProfesion(fila.getString("profesion"));
           p.setTelefono(fila.getString("telefono"));
           // Si encontra vai a retornar o objeto 
           return p;
        }//si nao encontra vai a retornar nulo.
        else
        
    return null;
    }
}


