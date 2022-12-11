public class Aplicacion {

    public static void main(String[] args) throws Exception {
        System.out.println("Panel de Direccion - Bodega Aurrera");
        Supermercado supermercado = new Supermercado("Bodega Aurrera", "Morelia");

        while (true) {
            switch (menu()) {
                case 11:
                    supermercado.mostrarClientes();
                    break;
                case 12:
                    supermercado.capturarCliente();
                    break;
                case 13:
                    supermercado.buscarCliente();
                    break;
                case 14:
                    supermercado.modificarCliente();
                    break;
                case 15:
                    supermercado.eliminarCliente();
                    break;
                case 21:
                    supermercado.mostrarEmpleados();
                    break;
                case 22:
                    supermercado.capturarEmpleado();
                    break;
                case 23:
                    supermercado.buscarEmpleado();
                    break;
                case 24:
                    supermercado.modificarEmpleado();
                    break;
                case 25:
                    supermercado.eliminarEmpleado();
                    break;
                case 31:
                    supermercado.mostrarProveedores();
                    break;
                case 32:
                    supermercado.capturarProveedor();
                    break;
                case 33:
                    supermercado.buscarProveedor();
                    break;
                case 34:
                    supermercado.modificarProveedor();
                    break;
                case 35:
                    supermercado.eliminarProveedor();
                    break;
                case 41:
                    supermercado.mostrarProductos();
                    break;
                case 42:
                    supermercado.capturarProducto();
                    break;
                case 43:
                    supermercado.buscarProducto();
                    break;
                case 44:
                    supermercado.modificarProducto();
                    break;
                case 45:
                    supermercado.eliminarProducto();
                    break;
                case 51:
                    supermercado.mostrarVentas();
                    break;
                case 52:
                    supermercado.capturarVenta();
                    break;
                case 53:
                    supermercado.buscarVenta();
                    break;
                case 54:
                    supermercado.modificarVenta();
                    break;
                case 55:
                    supermercado.eliminarVenta();
                    break;
                case 61:
                    supermercado.mostrarCompras();
                    break;
                case 62:
                    supermercado.capturarCompra();
                    break;
                case 63:
                    supermercado.buscarCompra();
                    break;
                case 64:
                    supermercado.modificarCompra();
                    break;
                case 65:
                    supermercado.eliminarCompra();
                    break;
                case 71:
                    supermercado.mostrarCategorias();
                    break;
                case 72:
                    supermercado.capturarCategoria();
                    break;
                case 73:
                    supermercado.buscarCategoria();
                    break;
                case 74:
                    supermercado.modificarCategoria();
                    break;
                case 75:
                    supermercado.eliminarCategoria();
                    break;

                case 999:
                    supermercado.guardar();
                    return;
            }
        }
    }

    public static int menu() throws Exception {
        Leer leer = new Leer();

        System.out.println("\n[#] Menú Principal");
        System.out.println("1) Cliente 2) Empleado 3) Proveedor 4) Producto 5) Venta 6) Compra 7) Categorías 0) Salir");
        System.out.print("Opción : ");

        int opccion = (leer.unIntEnRango(7) + 1) * 10;
        if (opccion == 0)
            return 999;

        System.out.println("\n[#] Submenú");
        System.out.println("1) Mostrar 2) Agregar 3) Buscar 4) Modificar 5) Eliminar 0) Cancelar");
        System.out.print("Acción : ");

        int accion = leer.unIntEnRango(5) + 1;
        if (accion == 0)
            return 888;

        return opccion + accion;
    }
}
