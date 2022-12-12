import java.util.Scanner;

public class Proveedor extends Persona {
  private String razonSocial;
  private String nombreComercial;

  public Proveedor() {
    super();
  }

  public Proveedor(Persona persona, String razonSocial, String nombreComercial) {
    super(persona);
    this.razonSocial = razonSocial;
    this.nombreComercial = nombreComercial;
  }

  public Proveedor(String nombre, String rfc, char genero, String telefono, String correo,
      String direccion, String razonSocial, String nombreComercial) {
    super(nombre, rfc, genero, telefono, correo, direccion);
    this.razonSocial = razonSocial;
    this.nombreComercial = nombreComercial;
  }

  @Override
  public void mostrar() {
    super.mostrar();
    System.out.println("Razón Social: " + razonSocial);
    System.out.println("Nombre Comercial: " + nombreComercial);
  }

  @Override
  public boolean buscar(String s) {
    return (razonSocial + nombreComercial).indexOf(s) != -1
        ? true
        : super.buscar(s);
  }

  @Override
  public void modificar() {
    while (true) {
      System.out.println("\n1) Nombre 2) Género 3) Teléfono 4) Correo 5) Dirección");
      System.out.println("6) RFC 7) Razón Social 8) Nombre Comercial 0) Cancelar");
      System.out.print("Indique una opción : ");
      int opcion = leerIntEnRango(8);

      if (opcion == -1)
        return;

      System.out.print("Introduzca el nuevo valor : ");
      switch (opcion) {
        case 0:
          nombre = leerString();
          break;
        case 1:
          genero = leerChar();
          break;
        case 2:
          telefono = leerString();
          break;
        case 3:
          correo = leerString();
          break;
        case 4:
          direccion = leerString();
          break;
        case 5:
          rfc = leerString();
          break;
        case 6:
          razonSocial = leerString();
          break;
        case 7:
          nombreComercial = leerString();
          break;
      }
    }
  }

  @Override
  public void capturar() {
    super.capturar();

    System.out.print("Razón Social: ");
    razonSocial = leerString();

    System.out.print("Nombre Comercial: ");
    nombreComercial = leerString();
  }

  public boolean equals(String rfc) {
    return this.rfc.equals(rfc);
  }

  public String toString() {
    return nombreComercial;
  }

  @Override
  public boolean is(String nombre) {
    return "Proveedor".equals(nombre);
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public void setNombreComercial(String nombreComercial) {
    this.nombreComercial = nombreComercial;
  }

  public String getNombreComercial() {
    return nombreComercial;
  }
}
