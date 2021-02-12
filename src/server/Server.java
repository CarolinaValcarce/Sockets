package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    /* Declaramos las variables servidor, cliente y establecemos el puerto*/
    private final ServerSocket servidor;
    private Socket cliente;
    private final int puerto=9876;

    public Server() throws IOException {
        servidor=new ServerSocket(puerto);
        cliente= new Socket();
    }
    public void connect() throws IOException{
        /* Abrimos el servidor pero todavía no se a conectado el cliente*/
        System.out.println ("Iniciando servidor.");
        System.out.println("Esperando al Cliente");
        while (true){
            /* El cliente se conecta*/
            cliente=servidor.accept();
            /* Creamos entrada y salida como mensajes del servidor de la clase DataInputStream y DataOutputStream.*/
            DataInputStream entrada= new DataInputStream (cliente.getInputStream());
            DataOutputStream salida= new DataOutputStream (cliente.getOutputStream());

            try{
               
                salida.writeUTF("Bienvenido, cómo te llamas?");
                /*La entrada del servidor le llamamos MensajeDeCliente.
                Y tras recibirlo le pedimos que conteste preguntándole por las Tareas.
                El mensaje se ecribe en la consola del servidor.*/
                String MensajeDeCliente=entrada.readUTF();
                System.out.println("Encantado de verte, "+MensajeDeCliente);
                salida.writeUTF("Cuántas tareas has de realizar?");
                /* Al siguiente mensaje de entrada del cliente llamado Mensaje2,escribimos en consola servidor.
                 Y volvemos a enviar mensaje de salida preguntándole.*/

                String Mensaje2=entrada.readUTF();

                System.out.println("Se han recibido "+ Mensaje2 + " tareas.");

                /*Creamos dos ArrayList, uno llamado Descripciones donde recogeremos todas las descripciones.
                Y otro llamado Tareas donde recogeremos todas las tareas.*/

                ArrayList<String> Descripciones = new ArrayList<>();
                ArrayList<String> Estados= new ArrayList<>();

                /* Creamos un bucle para recibir todas las descripciones y estados de tareas que nos envía el cliente*/
                for (int i=1;i<=(Integer.parseInt(Mensaje2));i++){

                    salida.writeUTF("Introducción de la Tarea: "+i);
                    salida.writeUTF("Introduce la descripción:");
                    String Mensaje3=entrada.readUTF();
                    Descripciones.add(Mensaje3);
                    System.out.println("Descripción recibida: "+ Mensaje3);

                    salida.writeUTF("Introduce el estado");
                    String Mensaje4=entrada.readUTF();
                    Estados.add(Mensaje4);
                    System.out.println("Estado recibido: "+ Mensaje4);
                }

                 /* Recorremos de nuevo el bucle, mostrando los valores de los Arraylist Descripciones y Estados.
                 Estos valores se han incorporado previamente con add, como respuesta del bucle anterior.
                 Pero dado que un ArrayList empieza en posición 0, recorremos el bucle desde el principio.
                  Ya que la posicion 0 del ArrayList ha quedado reflejada como tarea 1.*/

                System.out.println(entrada.readUTF());
                salida.writeUTF("Listado de tareas: ");
                for (int i=0; i<Integer.parseInt(Mensaje2);i++){
                    salida.writeUTF ( " Tarea: "+ Descripciones.get(i)+" " + "con estado "+ Estados.get(i));
                }

                /* Capturamos la Excepción*/
            } catch (EOFException ex){
                System.out.println("El cliente ha finalizado.");
            }
            System.out.println("Esperando un nuevo cliente.");
        }

    }
}



