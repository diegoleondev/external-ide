import java.io.Serializable;

public class Producto implements Acciones, Serializable {
  private String identificador;
  private String nombre;
  private String descripcion;
  private String categoria;
  private float precio;
  private int stock = 0;

  private boolean eliminada = false;

  private Id id = new Id();
  private Leer leer = new Leer();
  private Archivo archivo = new Archivo();

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
    identificador = id.alfanumerico();
  }

  private String[] getCategorias() {
    return archivo.getCadenas("./db/categorias.ponyfile");
  }

  private String seleccionarCategoria() {
    String[] CATEGORIAS = getCategorias();

    System.out.println("\nCategorías : ");
    int i = 1;
    for (String cat : CATEGORIAS) {
      System.out.print((i++) + ") " + cat + " ");
      System.out.println("");
    }
    System.out.print("\nIndique la Categoría: ");
    int valor;

    while ((valor = leer.unIntEnRango(CATEGORIAS.length)) == -1) {
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
      int opcion = leer.unIntEnRango(5) + 1;

      if (opcion == 0)
        return;

      System.out.print("Introduzca el nuevo valor : ");
      switch (opcion) {
        case 1:
          nombre = leer.unString();
          break;
        case 2:
          descripcion = leer.unString();
          break;
        case 3:
          precio = leer.unFloat();
          break;
        case 4:
          categoria = seleccionarCategoria();
          break;
        case 5:
          stock = leer.unInt();
          break;
      }
    }
  }

  public void capturar() {
    System.out.print("Nombre : ");
    nombre = leer.unString();

    System.out.print("Descripción : ");
    descripcion = leer.unString();

    System.out.print("Precio : ");
    precio = leer.unFloat();

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
