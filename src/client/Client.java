package client;

import server.Tarea;

import java.io.*;
import java.net.Socket;
import java.util.IllegalFormatException;


public class Client {

    private final Socket socket;
    public Client () throws IOException {
        socket =new Socket ("localhost",9876);
    }
    public void connect() {
        try{
            /* En el método connect establecemos entrada y salida de mensajes desde cliente como de la clase DataInputStream e DataOutputStream.*/
            DataInputStream entrada= new DataInputStream  (socket.getInputStream());
            DataOutputStream salida= new DataOutputStream(socket.getOutputStream());
            /* Apenas se conecta, escribe en consola cliente la primera pregunta*/
            //System.out.println("Recibiendo mensaje del servidor");//
            System.out.println (entrada.readUTF());
            /* Creamos un teclado que lee lo escrito en la consola del sistema. Y llamamos persona, a la lectura de lo escrito en teclado*/
            BufferedReader teclado= new BufferedReader(new InputStreamReader(System.in));
            String persona= teclado.readLine();
            /* Mensaje de salida de cliente a servidor con el contenido de persona*/
            //System.out.println("Enviado mensaje al servidor: "+ persona);//
            salida.writeUTF (persona);
            //System.out.println("Recibiendo mensaje del servidor");//
            System.out.println (entrada.readUTF());
            /* Cuando recibamos una entrada de servidor respondemos por consola cliente.
            Hacemos uso otra vez del teclado para recoger la info escrita, y le llamamos nTareas*/
            try{
                String nTareas= teclado.readLine();
                /* nTareas será un número en formato string, creamos la variable numero para recoger el valor numérico en int del string.
                Y pasamos esa info como mensaje nTareas al servidor. El servidor lo podrá pasar también a entero*/
                int numero=Integer.parseInt(nTareas);
                //System.out.println("Enviado mensaje al servidor: "+ nTareas);//
                salida.writeUTF(nTareas);

                /* Constructores que crean una tarea del tipo de la clase Tarea().*/
                Tarea tarea=new Tarea ();

                /*Creamos un bucle en donde llamaremos al método TareaDescripcion() y TareaEstado() de la clase Tarea.
                 * Y enviaremos al servidor como salida de cliente, tanto la variable descripción como la variable tarea.
                 * Estas han sido conseguidas a traves de los getters y setters de la clase Tarea.
                 * Cuando recibamos una entrada del servidor, responderemos. */

                for (int i=1; i<=numero;i++){
                    //System.out.println("Leyendo mensaje del servidor");//
                    System.out.println (entrada.readUTF());
                    System.out.println(entrada.readUTF());
                    tarea.TareaDescripcion(i);
                    //System.out.println ("Enviando mensaje al servidor: "+ tarea.getDescripcion());//
                    salida.writeUTF(tarea.getDescripcion());

                    //System.out.println("Leyendo mensaje del servidor");//
                    System.out.println(entrada.readUTF());
                    tarea.TareaEstado();
                    //System.out.println ("Enviando mensaje al servidor: "+ tarea.getEstado());//
                    salida.writeUTF(tarea.getEstado());
                }
                salida.writeUTF("Confirmación del listado de tareas:");
                /* Imprimimos el listado de tareas global enviado por el servidor, tras pedir al servidor que nos confirme el listado.*/

                System.out.println (entrada.readUTF());

                for (int i=0; i<numero;i++){
                /*System.out.println("Leyendo mensaje del servidor");
                Hay un mensaje por cada i del Array*/
                System.out.println(entrada.readUTF());
                }
            }catch (NumberFormatException e){
                System.out.println("Si no introduces un número al pedir tareas, dejaré de trabajar y buscaré otro cliente.");
            }
            teclado.close();
            entrada.close();
            salida.close();
            socket.close();
        }
        catch (IOException e){
            System.err.println("No hemos podido abrir la conexión correctamente.");
        }
    }
}



