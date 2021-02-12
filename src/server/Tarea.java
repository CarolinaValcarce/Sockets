package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tarea {

    String descripcion;
    String estado;
    /* Creamos un constructor vacío*/
    public Tarea(){
    }

    public Tarea (String descripcion, String estado){

        this.estado=estado;
        this.descripcion=descripcion;
    }
    /* Creamos todos los getters y setters*/
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }


    /*Creamos el primer método para posteriormente llamarlo desde la clase Cliente.
     Con la lectura de lo recogido por teclado llamamos la variable descripción. Devolviendo el valor al cliente.
     Ya que responde a lo preguntado por el servidor.*/

    public void TareaDescripcion (int i) throws IOException {
        BufferedReader teclado= new BufferedReader(new InputStreamReader(System.in));
        descripcion= teclado.readLine();
    }
    /* Creamos el segundo método para recoger la variable estado de la Tarea.*/

    public void TareaEstado () throws IOException {
        BufferedReader teclado= new BufferedReader(new InputStreamReader(System.in));
        estado= teclado.readLine();

    }

}



