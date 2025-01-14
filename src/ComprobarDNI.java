public class ComprobarDNI {

    int dni = 0;
    String letra = "TRWAGMYFPDXBNJZSQVHLCKE";
    char letraDNI = ' ';
    String dniDado = "";
    boolean validado = false;

    public ComprobarDNI(String dni){
        this.dniDado = dni;
        this.dni = Integer.parseInt(dni.substring(0,8));
        comprobarLetra(this.dni);
    }

    public void comprobarLetra(int dni){
        letraDNI = letra.charAt(dni%23);
        //System.out.println(letraDNI);

        if(letraDNI == dniDado.charAt(8)){
            validado = true;
        }else{
            validado = false;
        }
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public char getLetraDNI() {
        return letraDNI;
    }

    public void setLetraDNI(char letraDNI) {
        this.letraDNI = letraDNI;
    }

    public String getDniDado() {
        return dniDado;
    }

    public void setDniDado(String dniDado) {
        this.dniDado = dniDado;
    }

    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }
}
