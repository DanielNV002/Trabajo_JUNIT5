import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static String dni = "";
    static String nombre = "";
    static double saldo;
    static int eleccion = 1000;

    static Gestiones G = new Gestiones();

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
                        G.iniciaSesion();
                        eleccion = 100;
                        break;
                    case 2:
                        G.registrarse();
                        eleccion = 100;
                        break;
                    default:
                        System.out.println("ELECCION NO VALIDA");
                        break;
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
}