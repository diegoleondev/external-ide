import java.io.Serializable;

public class DetalleVenta implements Serializable {
  private int cantidad;
  private float precio;
  private Producto producto;

  Leer leer = new Leer();

  public DetalleVenta(Producto producto) {
    this.producto = producto;

    capturar();
    inicializar();
  }

  private void inicializar() {
    precio = producto.getPrecio();
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
      int valor = leer.unInt();

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
