import java.sql.*;

public class BBDD {

    private static final String URL = "jdbc:sqlite:BancaBBDD.db";

    // Establece la conexión con la base de datos
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Crear la tabla empleados si no existe
    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "dni VARCHAR(9) PRIMARY KEY, " +
                "nombre TEXT NOT NULL, " +
                "saldo DECIMAL NOT NULL);";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            //System.out.println("Tabla creada.");
        } catch (SQLException e) {
            System.out.println("Error creando la tabla: " + e.getMessage());
        }
    }

    // Insertar datos en la tabla empleados
    public static void insertData(String dni, String nombre, double saldo) {
        String sql = "INSERT INTO usuarios(dni, nombre, saldo) VALUES(?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            pstmt.setString(2, nombre);
            pstmt.setDouble(3, saldo);
            pstmt.executeUpdate();
            System.out.println("Usuario "+ nombre + " insertado.");
        } catch (SQLException e) {
            System.out.println("Error insertando datos: " + e.getMessage());
        }
    }

    // Actualizar el saldo de un usuario en la tabla usuarios
    public static void updateSaldo(String dni, double nuevoSaldo) {
        String sql = "UPDATE usuarios SET saldo = ? WHERE dni = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer los valores de los parámetros
            pstmt.setDouble(1, nuevoSaldo);
            pstmt.setString(2, dni);

            // Ejecutar la actualización
            int rowsAffected = pstmt.executeUpdate();

            // Comprobar si se actualizó algún registro
            if (rowsAffected > 0) {
                System.out.println("Saldo actualizado correctamente");
            } else {
                System.out.println("No se encontró un usuario con el DNI: " + dni);
            }
        } catch (SQLException e) {
            System.out.println("Error actualizando el saldo: " + e.getMessage());
        }
    }

    // Metodo para comprobar el DNI y obtener el nombre
    public static String comprobarDNINombre(String dni) {
        String nombre = null;
        String sql = "SELECT nombre FROM usuarios WHERE dni = ?";

        try (Connection conn = BBDD.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer el valor del DNI en el PreparedStatement
            pstmt.setString(1, dni);

            // Ejecutar la consulta y obtener los resultados
            ResultSet rs = pstmt.executeQuery();

            // Si encontramos un usuario con ese DNI, obtenemos su nombre
            if (rs.next()) {
                nombre = rs.getString("nombre");
            }

        } catch (SQLException e) {
            System.out.println("Error consultando el DNI: " + e.getMessage());
        }

        return nombre;
    }

    // Metodo para comprobar el DNI y obtener el nombre
    public static String comprobarDNI(String dni) {
        String nombre = null;
        String sql = "SELECT dni FROM usuarios WHERE dni = ?";

        try (Connection conn = BBDD.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer el valor del DNI en el PreparedStatement
            pstmt.setString(1, dni);

            // Ejecutar la consulta y obtener los resultados
            ResultSet rs = pstmt.executeQuery();

            // Si encontramos un usuario con ese DNI, obtenemos su nombre
            if (rs.next()) {
                nombre = rs.getString("dni");
            }

        } catch (SQLException e) {
            System.out.println("Error consultando el DNI: " + e.getMessage());
        }

        return nombre;
    }

    public static Double comprobarSaldo(String dni) {
        Double saldo = null;
        String sql = "SELECT saldo FROM usuarios WHERE dni = ?";

        try (Connection conn = BBDD.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Establecer el valor del DNI en el PreparedStatement
            pstmt.setString(1, dni);

            // Ejecutar la consulta y obtener los resultados
            ResultSet rs = pstmt.executeQuery();

            // Si encontramos un usuario con ese DNI, obtenemos su nombre
            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }

        } catch (SQLException e) {
            System.out.println("Error consultando el DNI: " + e.getMessage());
        }

        return saldo;
    }
}
