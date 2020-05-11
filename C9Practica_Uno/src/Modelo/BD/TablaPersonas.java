package Modelo.BD;

import Modelo.UML.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TablaPersonas {

    public static void registrarP(Persona p1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Connection con;

    //  Constructor
    public TablaPersonas(Connection con) {
        this.con = con;
    }

    public void insertar(Persona p) throws Exception {
        // Preparar e executar a sentenca sql.
        String prepararSentencia = "INSERT INTO tpersonas VALUES (?,?,?,?);";
        PreparedStatement sentencia = con.prepareStatement(prepararSentencia);

        sentencia.setString(1, p.getNombre());
        sentencia.setString(2, p.getEdad());
        sentencia.setString(3, p.getProfesion());
        sentencia.setString(4, p.getTelefono());

        int linha = sentencia.executeUpdate();

        sentencia.close();
        if (linha != 1) {
            throw new Exception("El número de filas actualizadas no es uno");
        }
    }

    public Persona consultarP(String nombre) throws Exception {
        Persona p = null;

        PreparedStatement consulta = con.prepareStatement("SELECT * FOM tpersonas where nombre=?");
        consulta.setString(1, nombre);

        ResultSet r = consulta.executeQuery();
        //existirá apenas una persona con ese nombre
        if (r.next()) {
            p = crearObjeto(r);
        } else {
            throw new Exception("Ya existe la persona " + p);
        }
        return p;

    }

    //creando el objeto
    public Persona crearObjeto(ResultSet r) throws Exception {
        Persona persona = new Persona();
        persona.setNombre(r.getString("nombre"));
        persona.setEdad(r.getString("edad"));
        persona.setProfesion(r.getString("profesion"));
        persona.setTelefono(r.getString("telefono"));

        return persona;

    }

    public ArrayList<Persona> verTodo() throws Exception {

        ArrayList<Persona> lista = new ArrayList();

        //seleciono tudo da tabla da minha base de dados
        String tablaDaBase = "select * from tpersonas;";
        // Prepara a sentencia
        PreparedStatement ejecutar = con.prepareStatement(tablaDaBase);

        // Executa a sentencia
        ResultSet r = ejecutar.executeQuery();
        // .next passa para a linha senguinte e devolve true. 
        //E se é a ultima pois  vai retornar um falso.
        while (r.next()) {
            // Tenho que criar outro objeto pra guardar os dados 
            //das  pessoas e me devolver um objeto completo.
            Persona p = new Persona();
            // r.get é pra pegar os registros dos  campos (nombre,edad...) e guardar
            p.setNombre(r.getString("nombre"));
            p.setEdad(r.getString("edad"));
            p.setProfesion(r.getString("profesion"));
            p.setTelefono(r.getString("telefono"));
            // Meter todos os objetos na lista
            lista.add(p);
        }
        return lista;
    }

}
