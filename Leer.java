import java.io.Serializable;
import java.util.Scanner;

public class Leer implements Serializable {
    public static void main(String[] args) throws Exception {
    }

    public int unInt() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                int valor = sc.nextInt();
                sc.nextLine();

                return valor;
            } catch (Exception e) {
                System.err.print("[!] Ocurrió un error, ingrese un número entero : ");
                sc.nextLine();
            }
        }
    }

    public float unFloat() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                float valor = sc.nextFloat();
                sc.nextLine();

                return valor;
            } catch (Exception e) {
                System.err.print("[!] Ocurrió un error, ingrese un número flotante : ");
                sc.nextLine();
            }
        }
    }

    public String unString() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                return sc.nextLine();

            } catch (Exception e) {
                System.err.print("[!] Ocurrió un error, ingrese una cadena de texto : ");
            }
        }
    }

    public char unChar() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                return sc.next().charAt(0);
            } catch (Exception e) {
                System.err.print("[!] Ocurrió un error, ingrese un caracter : ");
            }
        }
    }

    public int unIntEnRango(int opciones) {
        while (true) {

            int opcion = unInt() - 1;
            boolean range = (opcion == -1 || (opcion >= 0 && opcion < opciones));

            if (range)
                return opcion;

            System.err.print("[!] Opción fuera de rango, vuelva a seleccionar : ");
        }
    }

}
