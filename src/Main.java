import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static String dni = "";
    static String nombre = "";
    static double saldo;
    static int eleccion = 1000;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        try {
            // Conectar a la base de datos
            BBDD.connect();
            BBDD.createTable();  // Suponiendo que tienes una función para crear la tabla

            System.out.println("Bienvenido a su Banca personal");

            while(eleccion != 0){
                System.out.println("""
                      ╔══════════════════════╗
                      ║  1. Iniciar Sesion   ║
                      ║  2. Registrate       ║
                      ║  0. Salir            ║
                      ╚══════════════════════╝
                    """);

                eleccion = in.nextInt();

                switch (eleccion){
                    case 0:
                        System.out.println("GRACIAS POR USAR NUESTROS SERVICIOS");
                        System.exit(0);
                        break;
                    case 1:
                        iniciaSesion();
                        break;
                    case 2:
                        registrarse();
                        break;
                    default:
                        System.out.println("ELECCION NO VALIDA");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            in.close();
        }
    }

    public static void iniciaSesion(){

        Scanner in = new Scanner(System.in);

        // Solicitar DNI al usuario
        System.out.print("Introduzca su DNI: ");
        dni = in.next();

        // Comprobar si el DNI está registrado en la base de datos
        nombre = BBDD.comprobarDNI(dni);

        if (nombre != null) {
            System.out.println("Bienvenido " + nombre);
            mainScreen();  // Suponiendo que tienes una pantalla principal con opciones
        } else {
            System.out.println("DNI NO REGISTRADO O ERRONEO.");
        }
    }

    public static void registrarse(){
        Scanner in = new Scanner(System.in);

        //Solicitar DNI
        System.out.print("INTRODUCE TU DNI: ");
        dni = in.nextLine();
        //Solicitar nombre
        System.out.print("INTRODUCE TU Nombre: ");
        nombre = in.nextLine();
        //Solicitar saldo
        System.out.print("INTRODUCE TU Saldo: ");
        saldo = in.nextDouble();

        BBDD.insertData(dni,nombre,saldo);

        System.out.println("USUARIO " + nombre + " REGISTRADO CORRECTAMENTE");
    }

    public static void mainScreen(){

        Scanner in = new Scanner(System.in);

        while(eleccion != 0){
            System.out.println("""
                      ╔════════════════════════╗
                      ║  1. Hacer un Ingreso   ║
                      ║  2. Hacer un Gasto     ║
                      ║  0. Salir              ║
                      ╚════════════════════════╝
                """);

            eleccion = in.nextInt();

            switch (eleccion){
                case 0:
                    System.out.println("VOLVIENDO");
                    System.exit(0);
                    break;
                case 1:
                    iniciaSesion();
                    break;
                case 2:
                    registrarse();
                    break;
                default:
                    System.out.println("ELECCION NO VALIDA");
            }
        }

    }
}