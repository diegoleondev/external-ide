import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Compra implements Acciones, Serializable {
  private String folio;
  private String fecha;
  private String hora;
  private Proveedor proveedor;
  private List<DetalleCompra> detalles = new ArrayList<DetalleCompra>();
  private boolean eliminada;

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

  private void setCadenas(String[] areglo, String path) {
    BufferedWriter bWriter = null;

    try {
      FileWriter file = new FileWriter(path);
      bWriter = new BufferedWriter(file);

      for (String string : areglo) {
        if (string == null)
          break;
        bWriter.write(string);
        bWriter.newLine();
      }

    } catch (FileNotFoundException e) {
      System.out.println("[!!] Ocurrió un error: El archivo '" + path + "' no existe. ");
    } catch (Exception e) {
      System.err.println("[!!] Ocurrió un error: No podemos escribir el archivo : " + path);
    } finally {
      if (bWriter == null)
        return;

      try {
        bWriter.close();
      } catch (Exception e) {
        System.out.println("[!!] Ocurrió un error: no pudimos cerrar el archivo : " + path);
        System.out.println(e.getMessage());
      }

    }
  }

  private String crearFolio() {
    String path = "db/folioCompra.ponyfile";

    String[] folio = getCadenas(path);

    if (folio == null) {
      System.err.println("[!!] Error al leer el archivo:" + path);
      return "#ERROR";
    }

    int convertirFolio = Integer.parseInt(folio[0]);

    String[] nuevoFolio = { convertirFolio + 1 + "" };

    setCadenas(nuevoFolio, path);

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

  private int leerIntEnRango(int opciones) {
    while (true) {

      int opcion = leerInt() - 1;
      boolean range = (opcion == -1 || (opcion >= 0 && opcion < opciones));

      if (range)
        return opcion;

      System.err.print("[!] Opción fuera de rango, vuelva a seleccionar : ");
    }
  }

  private DetalleCompra seleccionarDetalleCompra() {
    listarDetalleCompra();
    System.out.println("Seleccione un detalle de venta: ");
    int valor = leerIntEnRango(detalles.size());

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
