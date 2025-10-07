import java.util.Scanner;
public class Programa2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese su Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Hola " + nombre);

        sc.close();
    }
}