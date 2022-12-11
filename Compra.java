import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Compra implements Acciones, Serializable {
  private String folio;
  private String fecha;
  private String hora;
  private Proveedor proveedor;
  private List<DetalleCompra> detalles = new ArrayList<DetalleCompra>();
  private boolean eliminada;

  Leer leer = new Leer();
  Archivo archivo = new Archivo();

  public Compra(Proveedor proveedor, List<DetalleCompra> detalles) {
    this.proveedor = proveedor;
    this.detalles = detalles;

    capturar();
    inicializar();
  }

  private void inicializar() {
    Date date = new Date();
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMMM-yyyy", new Locale("es_MX"));
    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss", new Locale("es_MX"));

    folio = crearFolio();
    fecha = formatoFecha.format(date);
    hora = formatoHora.format(date);
  }

  private String crearFolio() {
    String path = "./db/folioCompra.ponyfile";

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

  public void agregarDetalleCompra(DetalleCompra detalleCompra) {
    detalles.add(detalleCompra);
    System.out.println("[+] Detalle de compra agregado.");
  }

  private void listarDetalleCompra() {
    System.out.println(" Detalles de la Compra: ");

    String format = "  %-4s %-5s %-10s  %-15s \n";

    System.out.printf(format, "#", "Cantidad", "Total", "Nombre");
    int i = 1;
    for (DetalleCompra d : detalles) {
      System.out.printf(format, (i++), d.getCantidad(), d.calcularTotal(), d.getProducto().getNombre());
    }
  }

  private DetalleCompra seleccionarDetalleCompra() {
    listarDetalleCompra();
    System.out.println("Seleccione un detalle de venta: ");
    int valor = leer.unIntEnRango(detalles.size());

    if (valor == -1)
      return null;

    return detalles.get(valor);
  }

  public DetalleCompra eliminarDetalleCompra() {
    DetalleCompra detalle = seleccionarDetalleCompra();

    if (detalle == null)
      return null;

    detalles.remove(detalle);
    System.out.println("[+] Detalle de compra eliminado.");
    return detalle;
  }

  public float calcularTotal() {
    float acomulador = 0;
    for (DetalleCompra detalle : detalles) {
      acomulador += detalle.calcularTotal();
    }

    return acomulador;
  }

  public void capturar() {
  }

  public void mostrar() {
    System.out.println(" Folio: " + folio);
    System.out.println(" Fecha: " + fecha);
    System.out.println(" Hora: " + hora);
    System.out.println(" Nombre: " + proveedor.getNombre());
    System.out.println(" Razon Social: " + proveedor.getRazonSocial());
    System.out.println(" Total: " + calcularTotal());
    listarDetalleCompra();
  }

  public void modificar() {
  }

  public boolean buscar(String s) {
    return (folio + fecha + hora + proveedor.getNombre()).indexOf(s) != -1 ? true : false;
  }

  public void eliminar() {
    eliminada = true;
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

  public void setProveedor(Proveedor proveedor) {
    this.proveedor = proveedor;
  }

  public Proveedor getProveedor() {
    return proveedor;
  }

  public void setDetalles(ArrayList<DetalleCompra> detalles) {
    this.detalles = detalles;
  }

  public List<DetalleCompra> getDetalles() {
    return detalles;
  }

}
