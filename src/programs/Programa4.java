// Importa la clase Scanner para leer datos desde la consola
import java.util.Scanner;

// Define la clase Programa4
public class Programa4 {

    // Método principal, punto de entrada del programa
    public static void main(String[] args) {

        // Crea un objeto Scanner llamado 'sc' para leer datos del usuario
        Scanner sc = new Scanner(System.in);

        // Muestra un mensaje para que el usuario ingrese la base
        System.out.print("Ingrese la Base: ");

        // Lee un número entero corto ingresado por el usuario y lo guarda en 'base'
        short base = sc.nextShort();

        // Muestra un mensaje para que el usuario ingrese la potencia
        System.out.print("Ingrese la Potencia: ");

        // Lee un número entero corto ingresado por el usuario y lo guarda en 'potencia'
        short potencia = sc.nextShort();

        // Calcula la potencia usando Math.pow, convierte el resultado a int
        int resultado = (int)Math.pow((double)base,(double)potencia);

        // Imprime el resultado con formato: base elevado a la potencia es resultado
        System.out.printf("El resultado de %d elevado a la %d es: %d\n", base, potencia, resultado);

        // Cierra el Scanner para liberar recursos
        sc.close();

    // Fin del metodo principal
    }

// Fin de la clase principal
}