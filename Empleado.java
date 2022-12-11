import java.util.Scanner;

public class Empleado extends Persona {
  private String fechaAdmision;
  private String fechaNacimiento;
  private String nSeguroSocial;
  private String personaContacto;
  private String departamento;
  private String posicion;
  private float salario;

  public Empleado() {
    super();
  };

  public Empleado(Persona persona, String rfc, String fechaAdmicion, String fechaNacimiento, String nSeguroSocial,
      String personaContacto, String departamento, String posicion, float salario) {
    super(persona);
    this.fechaAdmision = fechaAdmicion;
    this.fechaNacimiento = fechaNacimiento;
    this.nSeguroSocial = nSeguroSocial;
    this.personaContacto = personaContacto;
    this.departamento = departamento;
    this.posicion = posicion;
    this.salario = salario;
  }

  public Empleado(String nombre, String rfc, char genero, String telefono, String correo,
      String direccion, String fechaAdmicion, String fechaNacimiento, String nSeguroSocial,
      String personaContacto, String departamento, String posicion, float salario) {
    super(nombre, rfc, genero, telefono, correo, direccion);
    this.fechaAdmision = fechaAdmicion;
    this.fechaNacimiento = fechaNacimiento;
    this.nSeguroSocial = nSeguroSocial;
    this.personaContacto = personaContacto;
    this.departamento = departamento;
    this.posicion = posicion;
    this.salario = salario;
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

    System.out.println("Fecha de admision : " + fechaAdmision);
    System.out.println("Fecha de nacimiento : " + fechaNacimiento);
    System.out.println("Número de seguro social : " + nSeguroSocial);
    System.out.println("Persona de contacto : " + personaContacto);
    System.out.println("Departamento : " + departamento);
    System.out.println("Posición : " + posicion);
    System.out.println("Salario : " + salario);
  }

  @Override
  public boolean buscar(String s) {
    return (fechaAdmision
        + fechaNacimiento
        + nSeguroSocial
        + personaContacto
        + departamento
        + posicion
        + salario).indexOf(s) != -1
            ? true
            : super.buscar(s);
  }

  @Override
  public void modificar() {
    while (true) {
      System.out.println("\n1) Nombre 2) Género 3) Teléfono 4) Correo");
      System.out.println("5) Dirección 6) RFC 7) Fecha de admision 8) Fecha de nacimiento ");
      System.out.println("9) Número de seguro social 10) Persona de contacto");
      System.out.println("11) Departamento 12) Posición 13) Salario 0) Cancelar");
      System.out.print("Indique una opción : ");
      int opcion = leerIntEnRango(11) + 1;

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
          rfc = leerString();
          break;
        case 7:
          fechaAdmision = leerString();
          break;
        case 8:
          fechaNacimiento = leerString();
          break;
        case 9:
          nSeguroSocial = leerString();
          break;
        case 10:
          personaContacto = leerString();
          break;
        case 11:
          departamento = leerString();
          break;
        case 12:
          posicion = leerString();
          break;
        case 13:
          salario = leerInt();
          break;
      }
    }
  }

  @Override
  public void capturar() {

    super.capturar();

    System.out.print("Fecha de admision : ");
    fechaAdmision = leerString();

    System.out.print("Fecha de nacimiento : ");
    fechaNacimiento = leerString();

    System.out.print("Número de seguro social : ");
    nSeguroSocial = leerString();

    System.out.print("Persona de contacto : ");
    personaContacto = leerString();

    System.out.print("Departamento : ");
    departamento = leerString();

    System.out.print("Posición : ");
    posicion = leerString();

    System.out.print("Salario : ");
    salario = leerFloat();
  }

  public boolean equals(String rfc) {
    return this.rfc.equals(rfc);
  }

  public String toString() {
    return nombre;
  }

  @Override
  public boolean is(String nombre) {
    return "Empleado".equals(nombre);
  }

  public void setFechaAdmision(String fechaIngreso) {
    this.fechaAdmision = fechaIngreso;
  }

  public String getFechaAdmision() {
    return fechaAdmision;
  }

  public void setFechaNacimiento(String fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setNSeguroSocial(String nSeguroSocial) {
    this.nSeguroSocial = nSeguroSocial;
  }

  public String getNSeguroSocial() {
    return nSeguroSocial;
  }

  public void setPersonaContacto(String personaContacto) {
    this.personaContacto = personaContacto;
  }

  public String getPersonaContacto() {
    return personaContacto;
  }

  public void setDepartamento(String departamento) {
    this.departamento = departamento;
  }

  public String getDepartamento() {
    return departamento;
  }

  public void setPosicion(String cargo) {
    this.posicion = cargo;
  }

  public String getPosicion() {
    return posicion;
  }

  public void setSalario(float sueldo) {
    this.salario = sueldo;
  }

  public float getSalario() {
    return salario;
  }
}
