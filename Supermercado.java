import java.util.ArrayList;
import java.util.List;

public class Supermercado {
  private String nombre;
  private String direccion;

  private Persona[] personas = new Persona[500];
  private int cPersonas = 0;
  private Producto[] productos = new Producto[500];
  private int cProductos = 0;
  private Venta[] ventas = new Venta[500];
  private int cVentas = 0;
  private Compra[] compras = new Compra[500];
  private int cCompras = 0;
  private String[] categorias = new String[500];
  private int cCategorias = 0;

  Leer leer = new Leer();
  Archivo archivo = new Archivo();

  public Supermercado(String nombre, String direccion) {
    this.nombre = nombre;
    this.direccion = direccion;

    inicializar();
  }

  public void inicializar() {
    cargar();
  }

  private String[] filtrarCategorias() {
    List<String> categorias = new ArrayList<String>();

    for (String categoria : this.categorias) {
      if (categoria == null)
        break;
      if (categoria.isEmpty())
        continue;

      categorias.add(categoria);
    }

    String[] categoriasFiltradas = new String[categorias.size()];
    categorias.toArray(categoriasFiltradas);

    return categoriasFiltradas;
  }

  private String seleccionarCategoria() {
    String[] categorias = filtrarCategorias();

    System.out.println("\nCategorías : ");
    int i = 1;
    for (String categoria : categorias) {
      System.out.print((i++) + ") " + categoria + "\n");
    }
    System.out.print("Indique la Categoría: ");
    int iCategoria;

    while ((iCategoria = leer.unIntEnRango(categorias.length)) == -1) {
      System.out.print("No puede dejar este campo vacío, vuelva a seleccionar : ");
    }

    return categorias[iCategoria];
  }

  public void mostrarCategorias() {
    System.out.println("\n[#] Mostrar categorías");

    for (String categoria : filtrarCategorias()) {
      System.out.println("\n " + categoria);
    }
  }

  public void capturarCategoria() {
    System.out.println("\n[#] Capturar categoría");
    System.out.print("Ingrese el nombre de la categoría : ");
    String categoria = leer.unString();

    categorias[cCategorias++] = categoria;
    System.out.println("[+] Categoría agregada. ");
  }

  public void buscarCategoria() {
    System.out.println("\n[#] Buscar categoría");
    System.out.println("Ingrese el valor a buscar : ");
    String valor = leer.unString();

    for (String categoria : filtrarCategorias()) {
      if (!categoria.equals(valor))
        continue;

      System.out.println(categoria);
      return;
    }
  }

  public void modificarCategoria() {
    System.out.println("\n[#] Modificar categoría");
    String categoria = seleccionarCategoria();

    for (int i = 0; i < categorias.length; i++) {
      String cat = categorias[i];
      if (cat == null)
        break;
      if (cat.isEmpty())
        continue;
      if (!cat.equals(categoria))
        continue;

      System.out.print("Ingrese el nuevo nombre de la categoría : ");
      String nuevoNombre = leer.unString();

      categorias[i] = nuevoNombre;
      System.out.println("[+] Categoría modificada. ");

      for (Producto producto : filtrarProductosValidos()) {
        if (!producto.getCategoria().equals(categoria))
          continue;

        producto.setCategoria(nuevoNombre);
      }

      System.out.println("[+] Categoría modificada en los productos. ");
      return;
    }

  }

  public void eliminarCategoria() {
    System.out.println("\n[#] Eliminar categoría");
    String categoria = seleccionarCategoria();

    for (int i = 0; i < categorias.length; i++) {
      String cat = categorias[i];
      if (cat == null)
        break;
      if (cat.isEmpty())
        continue;
      if (cat.equals(categoria)) {
        categorias[i] = "";
        System.out.println("[+] Categoría eliminada. ");
        return;
      }
    }
  }

  private void actualizarStock(Producto producto, int cantidad) {
    for (Producto productoStock : filtrarProductosValidos()) {
      if (!productoStock.equals(producto.getIdentificador()))
        continue;

      productoStock.setStock(productoStock.getStock() + cantidad);
      System.out.println("[+] Stock actualizado. ");
      return;
    }

    System.err.println("[!!] No se pudo actualizar el stock del producto. ");
  }

  private void actualizarPuntosCliente(Cliente cliente, float puntos) {
    if (cliente == null)
      return;
    for (Persona persona : filtrarPersonas("Cliente")) {
      Cliente clienteDB = (Cliente) persona;
      if (!clienteDB.equals(cliente.getIdentificador()))
        continue;

      clienteDB.setPuntos(clienteDB.getPuntos() + puntos);
      System.out.println("[+] Puntos actualizado. ");
      return;
    }

    System.err.println("[!!] No se pudo actualizar los puntos del cliente. ");
  }

  private Persona[] filtrarPersonas(String filter) {
    List<Persona> acomulador = new ArrayList<Persona>();
    for (Persona persona : personas) {
      if (persona == null)
        break;
      if (!persona.is(filter))
        continue;
      if (persona.isEliminada())
        continue;

      acomulador.add(persona);
    }

    Persona[] filtradas = new Persona[acomulador.size()];
    acomulador.toArray(filtradas);

    return filtradas;
  }

  private Persona seleccionarPersona(String filtro) {
    Persona[] personasFiltradas = filtrarPersonas(filtro);

    int contador = 1;
    for (Persona persona : filtrarPersonas(filtro)) {
      System.out.println((contador++) + ".- " + persona.toString());
    }
    System.out.print("Indique el " + filtro + " : ");

    int valor = leer.unIntEnRango(personasFiltradas.length);

    if (valor == -1)
      return null;

    return personasFiltradas[valor];
  }

  private void mostrarPersonas(String tipo) {
    System.out.println("\n[#] Mostrar " + tipo + "(s)");
    Persona[] personasFiltradas = filtrarPersonas(tipo);

    if (personasFiltradas == null) {
      System.out.println("[~] No hay nada que mostrar. ");
      return;
    }

    for (Persona persona : personasFiltradas) {
      System.out.println("");
      persona.mostrar();
    }
  }

  private void capturarPersona(String tipo) {
    System.out.println("\n[#] Cpaturar " + tipo);
    switch (tipo) {
      case "Cliente":
        personas[cPersonas++] = new Cliente();
        break;
      case "Empleado":
        personas[cPersonas++] = new Empleado();
        break;
      case "Proveedor":
        personas[cPersonas] = new Proveedor();
        break;
    }
    System.out.println("[+] " + tipo + " capturado. ");
  }

  private void buscarPersona(String tipo) {
    System.out.println("\n[#] Buscar " + tipo);
    System.out.print("Ingrese valor a buscar : ");
    String valor = leer.unString();

    for (Persona persona : filtrarPersonas(tipo)) {
      if (!persona.buscar(valor))
        continue;

      System.out.println("");
      persona.mostrar();
    }

  }

  private void modificarPersona(String tipo) {
    System.out.println("\n[#] Modificar " + tipo);

    System.out.println("0.- Cancelar");
    Persona persona = seleccionarPersona(tipo);

    if (persona == null)
      return;

    persona.modificar();
    System.out.println("[+] " + tipo + " modificado. ");
  }

  private void eliminarPersona(String tipo) {
    System.out.println("\n[#] Eliminar " + tipo);

    Persona persona = seleccionarPersona(tipo);

    if (persona == null)
      return;

    persona.eliminar();
    System.out.println("[+] " + tipo + " eliminado. ");
  }

  public void mostrarClientes() {
    mostrarPersonas("Cliente");
  }

  public void capturarCliente() {
    capturarPersona("Cliente");
  }

  public void buscarCliente() {
    buscarPersona("Cliente");
  }

  public void modificarCliente() {
    modificarPersona("Cliente");
  }

  public void eliminarCliente() {
    eliminarPersona("Cliente");
  }

  public void mostrarEmpleados() {
    mostrarPersonas("Empleado");
  }

  public void capturarEmpleado() {
    capturarPersona("Empleado");
  }

  public void buscarEmpleado() {
    buscarPersona("Empleado");
  }

  public void modificarEmpleado() {
    modificarPersona("Empleado");
  }

  public void eliminarEmpleado() {
    eliminarPersona("Empleado");
  }

  public void mostrarProveedores() {
    mostrarPersonas("Proveedor");
  }

  public void capturarProveedor() {
    capturarPersona("Proveedor");
  }

  public void buscarProveedor() {
    buscarPersona("Proveedor");
  }

  public void modificarProveedor() {
    modificarPersona("Proveedor");
  }

  public void eliminarProveedor() {
    eliminarPersona("Proveedor");
  }

  private Producto[] filtrarProductosValidos() {
    List<Producto> lista = new ArrayList<Producto>();

    for (Producto producto : productos) {
      if (producto == null)
        break;
      if (producto.isEliminada())
        continue;

      lista.add(producto);
    }

    Producto[] filtrados = new Producto[lista.size()];
    lista.toArray(filtrados);

    return filtrados;
  }

  private Producto[] getProductosFiltradosPorCategoria() {
    List<Producto> listaFiltrados = new ArrayList<Producto>();

    String categoria = seleccionarCategoria();

    for (Producto producto : filtrarProductosValidos()) {
      if (!producto.buscar(categoria))
        continue;
      listaFiltrados.add(producto);
    }

    Producto[] AregloFiltrados = new Producto[listaFiltrados.size()];
    listaFiltrados.toArray(AregloFiltrados);

    return AregloFiltrados;
  }

  private Producto[] getProductosFiltradosPorMenu() {
    System.out.println("\n¿Cómo quieres listar los productos? ");
    System.out.println("1) Todos los productos. ");
    System.out.println("2) Productos de una Categoría. ");
    System.out.println("0) Continuar.");
    System.out.print("Opción: ");
    int opcionListado = leer.unIntEnRango(2);

    if (opcionListado == -1)
      return null;

    return opcionListado == 0 ? filtrarProductosValidos() : getProductosFiltradosPorCategoria();
  }

  private Producto seleccionarProducto() {
    while (true) {
      Producto[] productosFiltrados = getProductosFiltradosPorMenu();

      if (productosFiltrados == null)
        return null;

      int i = 1;
      System.out.println("\nProductos: ");
      for (Producto producto : productosFiltrados)
        System.out.println((i++) + ".- " + producto.toString());
      System.out.println("0.- Cancelar");

      System.out.print("Seleccione un producto : ");
      int valor = leer.unIntEnRango(productosFiltrados.length);

      if (valor == -1)
        continue;

      return productosFiltrados[valor];
    }
  }

  public void mostrarProductos() {
    System.out.println("\n[#] Mostrar Producto(s)");
    Producto[] productosFiltrados = getProductosFiltradosPorMenu();

    if (productosFiltrados == null) {
      System.out.println("[~] No hay nada que mostrar. ");
      return;
    }

    for (Producto producto : productosFiltrados) {
      System.out.println("");
      producto.mostrar();
    }
  }

  public void capturarProducto() {
    System.out.println("\n[#] Agregar Producto");
    productos[cProductos++] = new Producto();

    System.out.println("[+] Producto agregado.");
  }

  public void buscarProducto() {
    System.out.println("\n[#] Buscar Producto");
    System.out.print("Ingrese valor a buscar : ");
    String valor = leer.unString();

    for (Producto producto : filtrarProductosValidos()) {
      if (!producto.buscar(valor))
        continue;

      System.out.println("");
      producto.mostrar();
    }

  }

  public void modificarProducto() {
    System.out.println("[#] Modificar producto. ");

    Producto producto = seleccionarProducto();

    if (producto == null)
      return;

    producto.modificar();
    System.out.println("[+] Producto modificado.");
  }

  public void eliminarProducto() {
    System.out.println("[#] Eliminar producto. ");

    Producto producto = seleccionarProducto();

    if (producto == null)
      return;

    producto.eliminar();
    System.out.println("[+] Producto eliminado.");
  }

  private Venta[] filtrarVentasValidas() {
    List<Venta> ventasFiltradas = new ArrayList<Venta>();

    for (Venta venta : ventas) {
      if (venta == null)
        break;
      if (venta.isEliminada())
        continue;

      ventasFiltradas.add(venta);
    }
    Venta[] arreglo = new Venta[ventasFiltradas.size()];

    ventasFiltradas.toArray(arreglo);

    return arreglo;
  }

  private Venta seleccionarVenta() {
    Venta[] ventasFiltradas = filtrarVentasValidas();

    int i = 1;
    System.out.println("\nVentas: ");
    for (Venta venta : ventasFiltradas)
      System.out.println((i++) + ".- " + venta.toString());
    System.out.println("0.- Cancelar");

    System.out.print("Seleccione una venta : ");
    int valor = leer.unIntEnRango(ventasFiltradas.length);

    if (valor == -1)
      return null;

    return ventasFiltradas[valor];
  }

  public void mostrarVentas() {
    System.out.println("[#] Mostrar ventas. ");
    for (Venta venta : filtrarVentasValidas()) {
      System.out.println("");
      venta.mostrar();
    }
  }

  public void capturarVenta() {
    System.out.println("[#] Capturar venta.");

    List<DetalleVenta> carrito = new ArrayList<DetalleVenta>();
    System.out.println("\nSeleccionar Cajero:");
    System.out.println("0.- Cancelar");
    Empleado empleado = (Empleado) seleccionarPersona("Empleado");

    if (empleado == null) {
      System.out.println("[+] Saliendo.");
      return;
    }

    System.out.println("\nSeleccionar Cliente: ");
    System.out.println("0.- Cliente no registrado");
    Cliente cliente = (Cliente) seleccionarPersona("Cliente");

    System.out.print("\nSeleccionar productos: ");
    Producto producto;

    do {

      while ((producto = seleccionarProducto()) != null) {
        if (producto.getStock() == 0) {
          System.err.println("[~] No hay stock de este producto.");
          continue;
        }

        DetalleVenta detalleVenta = new DetalleVenta(producto);
        float puntos = detalleVenta.calcularTotal() / 100;

        carrito.add(detalleVenta);
        actualizarStock(detalleVenta.getProducto(), detalleVenta.getCantidad() * -1);
        actualizarPuntosCliente(cliente, puntos);
      }

      if (carrito.size() != 0)
        break;

      System.err.println("[~] Este campo no puede estar vacío.");
    } while (true);

    System.out.println("");
    ventas[cVentas++] = new Venta(empleado, cliente, carrito);
    System.out.println("[+] Venta agregada.");

  }

  public void buscarVenta() {
    System.out.println("\n[#] Buscar Producto");
    System.out.print("Ingrese valor a buscar : ");
    String valor = leer.unString();

    for (Venta venta : filtrarVentasValidas()) {
      if (!venta.buscar(valor))
        continue;

      System.out.println("");
      venta.mostrar();
    }
  }

  public void modificarVenta() {
    System.out.println("\n[#] Modificar Venta");

    Venta venta = seleccionarVenta();

    if (venta == null)
      return;

    while (true) {
      System.out.println("\nAtributos de la venta: ");
      System.out.println("1) Empleado 2) Cliente 3) Carrito 0) Cancelar");
      System.out.print("Seleccione una opción: ");
      int opcion = leer.unIntEnRango(3);

      if (opcion == -1)
        break;

      switch (opcion) {
        case 0:
          System.out.println("\n0.- Cancelar");
          Empleado empleado = (Empleado) seleccionarPersona("Empleado");

          if (empleado == null)
            break;

          venta.setEmpleado(empleado);
          System.out.println("[+] Empleado modificado.");
          break;
        case 1:
          System.out.println("\n0.- Cliente no registrado");
          venta.setCliente((Cliente) seleccionarPersona("Cliente"));
          System.out.println("[+] Cliente modificado.");
          break;
        case 2:
          System.out.println("1) Agregar 2) Remover 0.- Cancelar");
          System.out.print("Opción: ");
          int opcion2 = leer.unIntEnRango(2);

          if (opcion2 == -1)
            break;

          if (opcion2 == 0) {
            Producto producto;

            while ((producto = seleccionarProducto()) == null)
              System.err.println("[~] Este campo no puede estar vacío.");

            DetalleVenta detalle = new DetalleVenta(producto);
            float puntos = detalle.calcularTotal() / 100;

            venta.agregarDetalleVenta(detalle);
            actualizarStock(detalle.getProducto(), detalle.getCantidad() * -1);
            actualizarPuntosCliente(venta.getCliente(), puntos);

          } else {

            DetalleVenta detalle = venta.eliminarDetalleVenta();
            float puntos = detalle.calcularTotal() / 100 * -1;

            actualizarStock(detalle.getProducto(), detalle.getCantidad());
            actualizarPuntosCliente(venta.getCliente(), puntos);
          }
          break;
        default:
          break;
      }
    }
  }

  public void eliminarVenta() {
    Venta venta = seleccionarVenta();

    if (venta == null)
      return;

    venta.eliminar();
    System.out.println("[+] Venta eliminada.");
  }

  private Compra[] filtrarComprasValidas() {
    List<Compra> lista = new ArrayList<Compra>();

    for (Compra compra : compras) {
      if (compra == null)
        break;
      if (compra.isEliminada())
        continue;
      lista.add(compra);
    }

    Compra[] arreglo = new Compra[lista.size()];
    lista.toArray(arreglo);

    return arreglo;
  }

  private void listarCompras() {
    int i = 1;
    for (Compra compra : filtrarComprasValidas()) {
      System.out.println((i++) + ".- " + compra.toString());
    }
  }

  private Compra seleccionarCompra() {
    Compra[] compras = filtrarComprasValidas();

    listarCompras();
    System.out.print("Seleccione una compra: ");
    int opcion = leer.unIntEnRango(compras.length);

    if (opcion == -1)
      return null;

    return compras[opcion];
  }

  public void mostrarCompras() {
    System.out.println("\n[#] Mostrar Compras");
    for (Compra compra : filtrarComprasValidas()) {
      System.out.println("");
      compra.mostrar();
    }
  }

  public void capturarCompra() {
    System.out.println("\n[#] Capturar Compra");

    Proveedor proveedor = (Proveedor) seleccionarPersona("Proveedor");

    if (proveedor == null)
      return;

    List<DetalleCompra> carrito = new ArrayList<DetalleCompra>();

    System.out.print("\nSeleccionar productos: ");

    while (true) {
      Producto producto;

      while ((producto = seleccionarProducto()) != null) {
        DetalleCompra detalle = new DetalleCompra(producto);

        carrito.add(detalle);
        actualizarStock(producto, detalle.getCantidad());
      }

      if (carrito.size() > 0)
        break;

      System.out.println("[~] Este campo no puede estar vacío.");
    }

    compras[cCompras++] = new Compra(proveedor, carrito);
    System.out.println("\n[+] Compra registrada.");

    System.out.println("¿Quieres imprimir la factura? 1) Si 2) No");
    System.out.print("Opción: ");
    if (leer.unIntEnRango(2) == 0) {
      System.out.println("\n[+] Imprimiendo factura...");
      compras[cCompras - 1].mostrar();
    }
  }

  public void buscarCompra() {
    System.out.println("\n[#] Buscar Compra");
    System.out.print("Ingrese valor a buscar: ");
    String valor = leer.unString();

    for (Compra compra : filtrarComprasValidas()) {
      if (!compra.buscar(valor))
        continue;

      System.out.println("");
      compra.mostrar();
    }

  }

  public void modificarCompra() {
    System.out.println("\n[#] Modificar Compra");

    Compra compra = seleccionarCompra();

    if (compra == null)
      return;

    while (true) {
      System.out.println("\nAtributos de la compra: ");
      System.out.println("1) Proveedor 2) Carrito 0) Cancelar");
      System.out.print("Seleccione una opción: ");
      int opcion = leer.unIntEnRango(2);

      if (opcion == -1)
        break;

      switch (opcion) {
        case 0:
          System.out.println("\n0.- Cancelar");
          Proveedor proveedor = (Proveedor) seleccionarPersona("Proveedor");

          if (proveedor == null)
            break;
          compra.setProveedor(proveedor);
          System.out.println("[+] Proveedor actualizado.");
          break;
        case 1:
          System.out.println("1) Agregar 2) Remover 0.- Cancelar");
          System.out.print("Opción: ");
          int opcion2 = leer.unIntEnRango(2);

          if (opcion2 == -1)
            break;

          if (opcion2 == 0) {
            Producto producto;

            while ((producto = seleccionarProducto()) == null)
              System.err.println("[~] Este campo no puede estar vacío.");

            DetalleCompra detalle = new DetalleCompra(producto);

            compra.agregarDetalleCompra(detalle);
            actualizarStock(detalle.getProducto(), detalle.getCantidad());

          } else {

            DetalleCompra detalle = compra.eliminarDetalleCompra();

            actualizarStock(detalle.getProducto(), detalle.getCantidad() * -1);
          }
          break;
        default:
          break;
      }
    }
  }

  public void eliminarCompra() {
    Compra compra = seleccionarCompra();

    if (compra == null)
      return;

    compra.eliminar();
    System.out.println("[+] Compra eliminada.");
  }

  private Persona[] filtrarPersonas() {
    List<Persona> lista = new ArrayList<Persona>();

    for (Persona persona : personas) {
      if (persona == null)
        break;
      if (persona.isEliminada())
        continue;
      lista.add(persona);
    }

    Persona[] arreglo = new Persona[lista.size()];

    lista.toArray(arreglo);

    return arreglo;
  }

  public void guardar() {
    archivo.setObjetos(filtrarPersonas(), "./db/personas.ponyfile");
    archivo.setObjetos(filtrarProductosValidos(), "./db/productos.ponyfile");
    archivo.setObjetos(filtrarVentasValidas(), "./db/ventas.ponyfile");
    archivo.setObjetos(filtrarComprasValidas(), "./db/compras.ponyfile");
    archivo.setCadenas(filtrarCategorias(), "./db/categorias.ponyfile");
    System.out.println("[+] Información guardada");
  }

  private void cargar() {
    for (Object obj : archivo.getObjetos("./db/personas.ponyfile"))
      personas[cPersonas++] = (Persona) obj;
    for (Object obj : archivo.getObjetos("./db/productos.ponyfile"))
      productos[cProductos++] = (Producto) obj;
    for (Object obj : archivo.getObjetos("./db/ventas.ponyfile"))
      ventas[cVentas++] = (Venta) obj;
    for (Object obj : archivo.getObjetos("./db/compras.ponyfile"))
      compras[cCompras++] = (Compra) obj;
    for (String categoria : archivo.getCadenas("./db/categorias.ponyfile"))
      categorias[cCategorias++] = categoria;
    System.out.println("[+] Información cargada");
  }

  public void mostrar() {
    System.out.println("Nombre: " + nombre);
    System.out.println("Dirección: " + direccion);
  }

  public String toString() {
    return nombre;
  }

  public boolean equals(Object obj) {
    if (obj == null)
      return false;
    if (obj == this)
      return true;
    if (!(obj instanceof Supermercado))
      return false;

    Supermercado tienda = (Supermercado) obj;

    return tienda.nombre.equals(nombre) && tienda.direccion.equals(direccion);
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getNombre() {
    return nombre;
  }

  public String getDireccion() {
    return direccion;
  }
}
