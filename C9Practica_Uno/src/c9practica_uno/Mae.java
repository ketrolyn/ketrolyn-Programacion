/*
 Nuestro primer ejercicio sera una aplicacion basica (sin validaciones)
en la que nos conectaremos a una base de datos donde tendremos
una unica tabla para guardar los datos de un conjunto de personas y 
mediante nuestro proyecto vamos a acceder a ella para poder dar de
alta y consultar datos. En la aplicacion tendremos un especie de menu
principal con estas opciones.

 */
package c9practica_uno;

import Modelo.BD.BaseDatos;
import Modelo.UML.Persona;
import Modelo.BD.TablaPersonas;
import Vistas.Opciones;
import Vistas.Personas;
import java.util.ArrayList;

public class Mae {

// Declaro as variaveis globais
// Uma para para aconexao com a bd
    private static BaseDatos bd;
// Variavel para as operacoes na tabla personas 
    private static TablaPersonas tp;

// Variavel para a ventana Menu e outra pra ventana personas
    private static Opciones menu;
    private static Personas vpersonas;

//variaveis para os butoes de anterior e proximo(< >)
    private static ArrayList<Persona> lista;
    private static int posicion;

    public static void main(String[] args) {
        try {
            // Novo objeto para conectar a classe BASE DE DATOS
            bd = new BaseDatos();
            //Abrindo a conexao
            bd.conectar();

            //crio um objeto que me permite aceder a bbdd
            tp = new TablaPersonas(bd.getCon());

            menu = new Opciones();
            menu.setVisible(true);

        } catch (Exception gnr) {
            System.out.println("Problemas en main");
        }
    }

    public static void alta() {
        // Para dar de alta temos que deixar a ventana opciones oculta pq
        // o usuario ja escolheu a opcao "alta"
        menu.setVisible(false);
        // Aparecera para o usuario a ventana para introducir os datos 
        vpersonas = new Personas();
        vpersonas.setVisible(true);
    }

    // Depois de dar alta teremos que criar um objeto pra poder guardar os datos
    public void registrarP(String n, String e, String p, String t) throws Exception {
        Persona p1 = new Persona();
        p1.setNombre(n);
        p1.setEdad(e);
        p1.setProfesion(p);
        p1.setTelefono(t);

        // Registrar na base de datos as pessoas criadas
        TablaPersonas.registrarP(p1);
    }

    public static void consultarP(String nombre) {
        Persona persona = tp.consultarP(nombre);
        consultarP(persona);//Como  faco essa parte?
    }

    public static void obtenerDatos() throws Exception {
        // Lista de personas
        lista = tp.verTodo();

        //comprovamos si relamente tem informacao nesse metodo
        if (lista.size() > 0) {
            posicion = 0;
            vpersonas = new Personas( 
            lista.get(0).getNombre(),
            lista.get(0).getEdad(),
            lista.get(0).getProfesion(),
            lista.get(0).getTelefono());
            vpersonas.setVisible(true);
        }
        else
            throw new Exception("No hay personas");
    }
   
    public static boolean siguinte(){
        if(posicion == lista.size()-1)
            return false;
        return true;
    }
    public static boolean anterior(){
        if(posicion == 0)
            return false;
        return true;
        
    }
    
    public static String siguinteNombre(){
    posicion = posicion +1;
    return lista.get(posicion).getNombre();
    }
    public static String siguinteEdad(){
    posicion = posicion +1;
    return lista.get(posicion).getEdad();
    }
    public static String siguinteProfesion(){
    posicion = posicion +1;
    return lista.get(posicion).getProfesion();
    }
    public static String siguinteTelefono(){
    posicion = posicion +1;
    return lista.get(posicion).getTelefono();
    }
    
    public static String anteriorNombre(){
    posicion = posicion -1;
    return lista.get(posicion).getNombre();
    }
    public static String anteriorEdad(){
    posicion = posicion -1;
    return lista.get(posicion).getEdad();
    }
    public static String anteriorProfesion(){
    posicion = posicion -1;
    return lista.get(posicion).getProfesion();
    }
    public static String anteriorTelefono(){
    posicion = posicion -1;
    return lista.get(posicion).getTelefono();
    }
    
    public static void terminar()throws Exception{
    bd.desconectar();
    System.exit(0);
    }
}
