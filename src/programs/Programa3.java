import java.util.Scanner;
public class Programa3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el Primer Numero: ");
        int num1 = sc.nextInt();
        System.out.print("Ingrese el Segundo Numero: ");
        int num2 = sc.nextInt();

        int suma = num1 + num2;
        System.out.println("La Suma es: " + suma);

        sc.close();
    }
}