import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Producto implements Acciones, Serializable {
  private String identificador;
  private String nombre;
  private String descripcion;
  private String categoria;
  private float precio;
  private int stock = 0;

  private boolean eliminada = false;

  public Producto() {
    capturar();

    inicializar();
  }

  public Producto(String nombre, String descripcion, String categoria, float precio, int stock) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.categoria = categoria;
    this.stock = stock;

    inicializar();
  }

  public Producto(Producto producto) {
    this.identificador = producto.getIdentificador();
    this.nombre = producto.getNombre();
    this.descripcion = producto.getDescripcion();
    this.precio = producto.getPrecio();
    this.categoria = producto.getCategoria();
    this.stock = producto.getStock();

    inicializar();
  }

  private void inicializar() {
    identificador = alfanumerico();
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

  private int leerIntEnRango(int opciones) {
    while (true) {

      int opcion = leerInt() - 1;
      boolean range = (opcion == -1 || (opcion >= 0 && opcion < opciones));

      if (range)
        return opcion;

      System.err.print("[!] Opción fuera de rango, vuelva a seleccionar : ");
    }
  }

  private String[] getCadenas(String path) {
    BufferedReader bReader = null;

    try {
      FileReader file = new FileReader(path);
      bReader = new BufferedReader(file);

      List<String> lista = new ArrayList<String>();

      String linea;

      while ((linea = bReader.readLine()) != null)
        lista.add(linea);

      String[] areglo = new String[lista.size()];

      lista.toArray(areglo);

      return areglo;
    } catch (FileNotFoundException e) {
      System.out.println("[!!] Ocurrió un error, el archivo '" + path + "' no existe. ");
    } catch (Exception e) {
      System.err.println("[!!] Ocurrió un error, no podemos leer el archivo. ");
    } finally {
      if (bReader == null)
        return null;

      try {
        bReader.close();
      } catch (Exception e) {
        System.out.println("[!!] Ocurrió un error: no podemos cerrar el archivo : " + path);
      }

    }

    return null;
  };

  private String seleccionarCategoria() {
    String[] CATEGORIAS = getCadenas("db/categorias.ponyfile");

    System.out.println("\nCategorías : ");
    int i = 1;
    for (String cat : CATEGORIAS) {
      System.out.print((i++) + ") " + cat + " ");
      System.out.println("");
    }
    System.out.print("\nIndique la Categoría: ");
    int valor;

    while ((valor = leerIntEnRango(CATEGORIAS.length)) == -1) {
      System.out.print("No puede dejar este campo vacío, vuelva a seleccionar : ");
    }

    return CATEGORIAS[valor];
  }

  public void mostrar() {
    System.out.println("Identificador : " + identificador);
    System.out.println("Nombre : " + nombre);
    System.out.println("Descripción : " + descripcion);
    System.out.println("Precio : " + precio);
    System.out.println("Categoría : " + categoria);
    System.out.println("Stock : " + stock);
  }

  public boolean buscar(String s) {
    return (identificador + nombre + descripcion + precio + categoria + stock).indexOf(s) != -1 ? true
        : false;
  }

  public void modificar() {
    while (true) {
      System.out.println("\n1) Nombre 2) Descripción 3) Precio");
      System.out.println("4) Categoría 5) Stock 0) Cancelar");
      System.out.print("Indique una opción : ");
      int opcion = leerIntEnRango(5) + 1;

      if (opcion == 0)
        return;

      System.out.print("Introduzca el nuevo valor : ");
      switch (opcion) {
        case 1:
          nombre = leerString();
          break;
        case 2:
          descripcion = leerString();
          break;
        case 3:
          precio = leerFloat();
          break;
        case 4:
          categoria = seleccionarCategoria();
          break;
        case 5:
          stock = leerInt();
          break;
      }
    }
  }

  public void capturar() {
    System.out.print("Nombre : ");
    nombre = leerString();

    System.out.print("Descripción : ");
    descripcion = leerString();

    System.out.print("Precio : ");
    precio = leerFloat();

    categoria = seleccionarCategoria();
  }

  public void eliminar() {
    eliminada = true;
  }

  public boolean isEliminada() {
    return eliminada;
  }

  public boolean equals(String identificador) {
    return this.identificador.equals(identificador);
  }

  public String toString() {
    return stock + " $" + precio + " " + nombre;
  }

  public void setIdentificador(String identificador) {
    this.identificador = identificador;
  }

  public String getIdentificador() {
    return identificador;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setPrecio(float precio) {
    this.precio = precio;
  }

  public float getPrecio() {
    return precio;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getStock() {
    return stock;
  }
}
