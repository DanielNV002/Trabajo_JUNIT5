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
                        eleccion = 100;
                        break;
                    case 2:
                        registrarse();
                        eleccion = 100;
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
        nombre = BBDD.comprobarDNINombre(dni);

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

        if(BBDD.comprobarDNI(dni).equalsIgnoreCase(dni)){
            System.out.println("DNI O USUARIO EXISTENTE");
        }else{
            BBDD.insertData(dni,nombre,saldo);
            System.out.println("USUARIO " + nombre + " REGISTRADO CORRECTAMENTE");
        }
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
                    break;
                case 1:
                    hacerIngreso();
                    eleccion = 100;
                    break;
                case 2:
                    hacerGasto();
                    eleccion = 100;
                    break;
                default:
                    System.out.println("ELECCION NO VALIDA");
            }
        }
    }

    public static void hacerIngreso(){
        Scanner in = new Scanner(System.in);

        double cantidad = 0;
        saldo = BBDD.comprobarSaldo(dni);

        while(eleccion != 0){

            System.out.println("Que tipo de Ingreso desea realizar?");

            System.out.println("""
                      ╔═════════════════════════════════════════╗
                      ║  1. Ingresar Nomina                     ║
                      ║  2. Ingresar Ventas de Segunda Mano     ║
                      ║  0. Salir                               ║
                      ╚═════════════════════════════════════════╝
                """);

            eleccion = in.nextInt();

            switch (eleccion){
                case 0:
                    System.out.println("VOLVIENDO");
                    break;
                case 1:
                    System.out.println("Su saldo es de " + BBDD.comprobarSaldo(dni));
                    System.out.print("Ingrese su Nomina: ");
                    cantidad = in.nextDouble();
                    saldo = saldo + (cantidad - (cantidad * 0.15) );
                    BBDD.updateSaldo(dni, saldo);
                    System.out.println("Su saldo es de " + saldo);
                    break;
                case 2:
                    System.out.println("Su saldo es de " + BBDD.comprobarSaldo(dni));
                    System.out.print("Ingrese la cantidad: ");
                    cantidad = in.nextDouble();
                    saldo = saldo + cantidad;
                    BBDD.updateSaldo(dni, saldo);
                    System.out.println("Su saldo es de " + saldo);
                    break;
                default:
                    System.out.println("ELECCION NO VALIDA");
            }
        }
    }

    public static void hacerGasto(){
        Scanner in = new Scanner(System.in);

        double cantidad = 0;
        saldo = BBDD.comprobarSaldo(dni);

        while(eleccion != 0){

            System.out.println("Que tipo de Gasto desea realizar?");

            System.out.println("""
                      ╔═════════════════════════════════════════╗
                      ║  1. Vacaciones                          ║
                      ║  2. Alquiler                            ║
                      ║  3. Vicios varios                       ║
                      ║  0. Salir                               ║
                      ╚═════════════════════════════════════════╝
                """);

            eleccion = in.nextInt();

            switch (eleccion){
                case 0:
                    System.out.println("VOLVIENDO");
                    break;
                case 1:
                    System.out.println("Su saldo es de " + BBDD.comprobarSaldo(dni));
                    System.out.println("Ingrese cuanto va a gastar en vacaciones: ");
                    cantidad = in.nextDouble();
                    if(cantidad > BBDD.comprobarSaldo(dni)){
                        System.out.println("SALDO INFERIOR AL GASTO");
                        break;
                    }else{
                        saldo = saldo - cantidad;
                        BBDD.updateSaldo(dni, saldo);
                        System.out.println("Su saldo es de " + BBDD.comprobarSaldo(dni));
                        break;
                    }
                case 2:
                    System.out.println("Su saldo es de " + BBDD.comprobarSaldo(dni));

                    break;
                case 3:
                    System.out.println("Su saldo es de " + BBDD.comprobarSaldo(dni));

                    break;
                default:
                    System.out.println("ELECCION NO VALIDA");
            }
        }
    }
}