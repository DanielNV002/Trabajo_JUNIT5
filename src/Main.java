import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int dni = 0;
        String nombre = "";

        System.out.println("Bienvenido a su Banca personal");

        try{
            System.out.println("Introduzca su DNI sin LETRA: ");
            dni = in.nextInt();



        }catch (Exception e){
            System.out.println(e);
        }
    }
}