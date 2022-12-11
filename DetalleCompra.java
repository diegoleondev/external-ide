import java.io.Serializable;

public class DetalleCompra implements Serializable {
  private int cantidad;
  private float precio;
  private Producto producto;
  private boolean eliminada;

  Leer leer = new Leer();

  public DetalleCompra(Producto producto) {
    this.producto = producto;
    capturar();
    inicializar();
  }

  private void inicializar() {
    eliminada = false;
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
    System.out.print("Cantidad: ");

    while (true) {
      int cantidad = leer.unInt();

      if (cantidad < 1) {
        System.out.println("[~] La cantidad no puede ser menor a uno");
        System.out.print("Cantidad: ");
        continue;
      }

      this.cantidad = cantidad;
      break;

    }
  }

  public void eliminar() {
    eliminada = true;
  }

  public boolean isEliminada() {
    return eliminada;
  }

  public boolean equals(String id) {
    return producto.equals(id);
  }

  public String toString() {
    return cantidad + " " + producto.getNombre() + " " + precio;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
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
