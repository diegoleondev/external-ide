import java.util.Random;
import java.util.Scanner;

public class Cliente extends Persona {
  private String identificador;
  private float puntos;

  public Cliente() {
    super();

    inicializar();
  }

  public Cliente(Persona p) {
    super(p);

    inicializar();
  }

  public Cliente(String nombre, String rfc, char genero, String telefono, String correo,
      String direccion) {
    super(nombre, rfc, genero, telefono, correo, direccion);

    inicializar();
  }

  private void inicializar() {
    identificador = alfanumerico();
    puntos = 0f;
  }

  private String alfanumerico() {
    Random ran = new Random();

    char[] banco = { 'a', 'b', 'c', 'x', 'y' };
    StringBuilder clave = new StringBuilder();

    for (int i = 1; i <= 5; i++) {
      char caracter = banco[ran.nextInt(5)];

      clave.append(caracter);
      clave.append(ran.nextInt(10));
    }

    return clave.toString();
  }

  private int leerInt() {
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

  private float leerFloat() {
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

  private String leerString() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      try {
        return sc.nextLine();

      } catch (Exception e) {
        System.err.print("[!] Ocurrió un error, ingrese una cadena de texto : ");
      }
    }
  }

  private char leerChar() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      try {
        return sc.next().charAt(0);
      } catch (Exception e) {
        System.err.print("[!] Ocurrió un error, ingrese un caracter : ");
      }
    }
  }

  private int leerIntEnRango(int opciones) {
    while (true) {

      int opcion = leerInt() - 1;
      boolean range = (opcion == -1 || (opcion >= 0 && opcion < opciones));

      if (range)
        return opcion;

      System.err.print("[!] Opción fuera de rango, vuelva a seleccionar : ");
    }
  }

  @Override
  public void mostrar() {
    super.mostrar();

    System.out.println("Puntos : " + puntos);
    System.out.println("Identificador : " + identificador);
  }

  @Override
  public boolean buscar(String s) {
    return (puntos + identificador).indexOf(s) != -1
        ? true
        : super.buscar(s);
  }

  @Override
  public void modificar() {
    while (true) {
      System.out.println("\n1) Nombre 2) Género 3) Teléfono 4) Correo");
      System.out.println("5) Dirección 6) Puntos 7) RFC 0) Cancelar");
      System.out.print("Indique una opción : ");
      int opcion = leerIntEnRango(7) + 1;

      if (opcion == 0)
        return;

      System.out.print("Introduzca el nuevo valor : ");
      switch (opcion) {
        case 1:
          nombre = leerString();
          break;
        case 2:
          genero = leerChar();
          break;
        case 3:
          telefono = leerString();
          break;
        case 4:
          correo = leerString();
          break;
        case 5:
          direccion = leerString();
          break;
        case 6:
          puntos = leerFloat();
          break;
		case 7:
		  rfc = leerString();
		  break;
      }
    }
  }

  @Override
  public void capturar() {
    super.capturar();
  }

  public boolean equals(String identificador) {
    return this.identificador.equals(identificador);
  }

  public String toString() {
    return nombre;
  }

  @Override
  public boolean is(String nombre) {
    return "Cliente".equals(nombre);
  }

  public void setPuntos(float puntos) {
    this.puntos = puntos;
  }

  public float getPuntos() {
    return puntos;
  }

  public String getIdentificador() {
    return identificador;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }
}