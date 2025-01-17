public class ComprobarDNI {
    private int dni;
    private String dniDado;
    private char letraDNI;
    private boolean validado = false;
    private static final String LETRAS = "TRWAGMYFPDXBNJZSQVHLCKE"; // Letras asociadas a cada número del DNI

    public ComprobarDNI(String dni) {
        this.dniDado = dni;

        // Validar formato del DNI (8 dígitos seguidos de una letra)
        if (dni.length() != 9 || !dni.substring(0, 8).matches("\\d{8}") || !Character.isLetter(dni.charAt(8))) {
            validado = false;
            System.out.println("El DNI tiene un formato incorrecto.");
            return;
        }

        this.dni = Integer.parseInt(dni.substring(0, 8)); // Extraer el número de DNI
        comprobarLetra(this.dni, dni.charAt(8)); // Comprobar si la letra es correcta
    }

    // Método para comprobar la letra del DNI
    private void comprobarLetra(int dni, char letraDada) {
        letraDNI = LETRAS.charAt(dni % 23); // Calcular la letra correcta

        // Comprobar si la letra dada es la correcta
        if (letraDNI == letraDada) {
            validado = true;
        } else {
            validado = false;
            System.out.println("La letra del DNI es incorrecta.");
        }
    }

    // Método para saber si el DNI es válido
    public boolean esValido() {
        return validado;
    }
}
