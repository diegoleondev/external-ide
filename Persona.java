import java.io.Serializable;

public class Persona implements Acciones, Serializable {
  protected String nombre;
  protected String rfc;
  protected char genero;
  protected String telefono;
  protected String correo;
  protected String direccion;
  protected boolean eliminada = false;

  private Leer leer = new Leer();

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

  public void capturar() {
    System.out.print("Nombre : ");
    nombre = leer.unString();

    System.out.print("RFC : ");
    rfc = leer.unString();

    System.out.print("Género : ");
    genero = leer.unChar();

    System.out.print("Teléfono : ");
    telefono = leer.unString();

    System.out.print("Correo : ");
    correo = leer.unString();

    System.out.print("Dirección : ");
    direccion = leer.unString();
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
