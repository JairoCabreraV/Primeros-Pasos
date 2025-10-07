import java.util.Scanner;
public class Programa4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese la Base: ");
        short base = sc.nextShort();
        System.out.print("Ingrese la Potencia: ");
        short potencia = sc.nextShort();

        int resultado = (int)Math.pow((double)base,(double)potencia);
        System.out.printf("El resultado de %d elevado a la %d es: %d\n", base, potencia, resultado);

        sc.close();
    }
}