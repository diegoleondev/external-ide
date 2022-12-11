import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Venta implements Acciones, Serializable {
  private String folio;
  private String fecha;
  private String hora;
  private Cliente cliente;
  private Empleado empleado;
  private List<DetalleVenta> detallesVenta;
  private float efectivo;
  private boolean eliminada;

  private Leer leer = new Leer();
  private Archivo archivo = new Archivo();

  public Venta(Empleado empleado, Cliente cliente, List<DetalleVenta> detallesVenta) {
    this.empleado = empleado;
    this.cliente = cliente;
    this.detallesVenta = detallesVenta;

    inicializar();
    capturar();
  }

  private void inicializar() {
    Date date = new Date();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMMM-yyyy", new Locale("es_ES"));
    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss", new Locale("es_ES"));

    folio = crearFolio();
    fecha = formatoFecha.format(date);
    hora = formatoHora.format(date);
  }

  public float calcularTotal() {
    float acomulador = 0;
    for (DetalleVenta dv : detallesVenta) {
      acomulador += dv.calcularTotal();
    }

    return acomulador;
  }

  public void agregarDetalleVenta(DetalleVenta detalleVenta) {
    detallesVenta.add(detalleVenta);
    System.out.println("[+] Detalle de venta agregado.");
  }

  private void listarDetalleVenta() {
    System.out.println("\n Detalles de la Venta: ");

    String format = "%-4s %-10s  %-10s  %-15s \n";

    System.out.printf(format, "#", "Precio", "Total", "Nombre");
    int i = 1;
    for (DetalleVenta d : detallesVenta) {
      System.out.printf(format, (i++), d.getPrecio(), d.calcularTotal(), d.getProducto().getNombre());
    }
  }

  private DetalleVenta seleccionarDetalleVenta() {
    listarDetalleVenta();
    System.out.println("Seleccione un detalle de venta: ");
    int valor = leer.unIntEnRango(detallesVenta.size());

    if (valor == -1)
      return null;

    return detallesVenta.get(valor);
  }

  public DetalleVenta eliminarDetalleVenta() {
    DetalleVenta detalleVenta = seleccionarDetalleVenta();

    if (detalleVenta == null)
      return null;

    detallesVenta.remove(detalleVenta);
    System.out.println("[+] Detalle de venta eliminado.");
    return detalleVenta;
  }

  private float calcularCambio() {
    return efectivo - calcularTotal();
  }

  private String crearFolio() {
    String path = "./db/folioVenta.ponyfile";

    String[] folio = archivo.getCadenas(path);

    if (folio == null) {
      System.err.println("[!!] Error al leer el archivo:" + path);
      return "#ERROR";
    }

    int convertirFolio = Integer.parseInt(folio[0]);

    String[] nuevoFolio = { convertirFolio + 1 + "" };

    archivo.setCadenas(nuevoFolio, path);

    return nuevoFolio[0];
  }

  public void mostrar() {
    System.out.println("Folio: " + folio);
    System.out.println("Fecha: " + fecha);
    System.out.println("Hora: " + hora);
    System.out.println("Empleado: " + empleado.toString());
    System.out.println("Cliente: " + (cliente == null ? "No registrado" : cliente.getNombre()));
    System.out.println("Total: " + calcularTotal());
    System.out.println("Efectivo: " + efectivo);
    System.out.println("Detalle de la venta: ");
    listarDetalleVenta();
  }

  public void capturar() {
    System.out.println("Total a Pagar: " + calcularTotal());
    System.out.print("Efectivo: ");
    while ((efectivo = leer.unFloat()) < calcularTotal()) {
      System.out.print("[~] Dinero insuficiente: ");
    }

    System.out.println("Cambio: " + calcularCambio());
  }

  public boolean buscar(String s) {
    return (folio + fecha + hora + calcularTotal() + efectivo).equals(s)
        ? true
        : cliente.buscar(s)
            ? true
            : empleado.buscar(s)
                ? true
                : false;
  }

  public void eliminar() {
    eliminada = true;
  }

  public String toString() {
    return folio + " " + fecha + " " + hora;
  }

  public void modificar() {
  }

  public boolean isEliminada() {
    return eliminada;
  }

  public void setFolio(String folio) {
    this.folio = folio;
  }

  public String getFolio() {
    return folio;
  }

  public void setFecha(String fecha) {
    this.fecha = fecha;
  }

  public String getFecha() {
    return fecha;
  }

  public void setHora(String hora) {
    this.hora = hora;
  }

  public String getHora() {
    return hora;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setEmpleado(Empleado empleado) {
    this.empleado = empleado;
  }

  public Empleado getEmpleado() {
    return empleado;
  }

  public void setDetallesVenta(List<DetalleVenta> detallesVenta) {
    this.detallesVenta = detallesVenta;
  }

  public List<DetalleVenta> getDetallesVenta() {
    return detallesVenta;
  }

  public void setEfectivo(float efectivo) {
    this.efectivo = efectivo;
  }

  public float getEfectivo() {
    return efectivo;
  }

}
