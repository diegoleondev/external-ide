import java.io.Serializable;
import java.util.Scanner;

public class DetalleVenta implements Serializable {
  private int cantidad;
  private float precio;
  private Producto producto;

  public DetalleVenta(Producto producto) {
    this.producto = producto;

    capturar();
    inicializar();
  }

  private void inicializar() {
    precio = producto.getPrecio();
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

  public float calcularTotal() {
    return precio * cantidad;
  }

  public void mostrar() {
    System.out.println("Precio : " + precio);
    System.out.println("Cantidad : " + cantidad);
    System.out.println("Total : " + calcularTotal());
  }

  public void capturar() {
    System.out.print("Cantidad : ");

    while (true) {
      int valor = leerInt();

      if (valor > producto.getStock()) {
        System.out.println("[~] No hay suficiente stock, ingrese una cantidad menor o igual a " + producto.getStock());
      } else if (valor < 1) {
        System.out.println("[~] La cantidad no puede ser menor a uno");
      } else {
        cantidad = valor;
        break;
      }

      System.out.print("Cantidad : ");
    }
  }

  public String toString() {
    return cantidad + " " + producto.getNombre() + " " + precio;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public boolean equals(String nombre) {
    return producto.getNombre().equals(nombre);
  }

  public int getCantidad() {
    return cantidad;
  }

  public void setPrecio(float precio) {
    this.precio = precio;
  }

  public Float getPrecio() {
    return precio;
  }

  public void setProducto(Producto producto) {
    this.producto = new Producto(producto);
  }

  public Producto getProducto() {
    return producto;
  }
}
