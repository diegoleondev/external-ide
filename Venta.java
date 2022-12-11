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

public class Venta implements Acciones, Serializable {
  private String folio;
  private String fecha;
  private String hora;
  private Cliente cliente;
  private Empleado empleado;
  private List<DetalleVenta> detallesVenta;
  private float efectivo;
  private boolean eliminada;

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
    int valor = leerIntEnRango(detallesVenta.size());

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
    String path = "db/folioVenta.ponyfile";

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

  private int leerIntEnRango(int opciones) {
    while (true) {

      int opcion = leerInt() - 1;
      boolean range = (opcion == -1 || (opcion >= 0 && opcion < opciones));

      if (range)
        return opcion;

      System.err.print("[!] Opción fuera de rango, vuelva a seleccionar : ");
    }
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
    while ((efectivo = leerFloat()) < calcularTotal()) {
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
