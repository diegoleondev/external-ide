import java.io.Serializable;
import java.util.Scanner;

public class Persona implements Acciones, Serializable {
  protected String nombre;
  protected String rfc;
  protected char genero;
  protected String telefono;
  protected String correo;
  protected String direccion;
  protected boolean eliminada = false;

  public Persona() {
    capturar();
  }

  public Persona(String nombre, String rfc, char genero, String telefono, String correo,
      String direccion) {
    this.nombre = nombre;
    this.rfc = rfc;
    this.genero = genero;
    this.telefono = telefono;
    this.correo = correo;
    this.direccion = direccion;
  }

  public Persona(Persona p) {
    this.nombre = p.getNombre();
    this.rfc = p.getRfc();
    this.genero = p.getGenero();
    this.telefono = p.getTelefono();
    this.correo = p.getCorreo();
    this.direccion = p.getDireccion();
  }

  public boolean buscar(String s) {
    return (nombre + rfc + genero + telefono + correo + direccion + eliminada).indexOf(s) != -1 ? true : false;
  };

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

  public void capturar() {
    System.out.print("Nombre : ");
    nombre = leerString();

    System.out.print("RFC : ");
    rfc = leerString();

    System.out.print("Género : ");
    genero = leerChar();

    System.out.print("Teléfono : ");
    telefono = leerString();

    System.out.print("Correo : ");
    correo = leerString();

    System.out.print("Dirección : ");
    direccion = leerString();
  };

  public void eliminar() {
    eliminada = true;
  };

  public void modificar() {
  };

  public void mostrar() {
    System.out.println("Nombre : " + nombre);
    System.out.println("RFC : " + rfc);
    System.out.println("Género : " + genero);
    System.out.println("Teléfono : " + telefono);
    System.out.println("Correo : " + correo);
    System.out.println("Dirección : " + direccion);
  };

  protected boolean isEliminada() {
    return eliminada;
  }

  public String toString() {
    return nombre;
  }

  public boolean is(String nombre) {
    return "Persona".equals(nombre);
  }

  protected void setNombre(String nombre) {
    this.nombre = nombre;
  }

  protected String getNombre() {
    return nombre;
  }

  protected void setRfc(String rfc) {
    this.rfc = rfc;
  }

  protected String getRfc() {
    return rfc;
  }

  protected void setGenero(char genero) {
    this.genero = genero;
  }

  protected char getGenero() {
    return genero;
  }

  protected void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  protected String getTelefono() {
    return telefono;
  }

  protected void setCorreo(String correo) {
    this.correo = correo;
  }

  protected String getCorreo() {
    return correo;
  }

  protected void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  protected String getDireccion() {
    return direccion;
  }
}
