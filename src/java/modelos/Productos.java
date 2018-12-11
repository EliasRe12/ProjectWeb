package modelos;

/**
 *
 * @author Elias
 */
public class Productos {
    private int id_producto;
    private String nombre_producto;
    private int precio_producto;
    private Colores color;
    private Marcas marca;
    private Proveedores proveedor;

    public Productos() {
    }

    public Productos(int id_producto, String nombre_producto, int precio_producto, Colores color, Marcas marca, Proveedores proveedor) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio_producto = precio_producto;
        this.color = color;
        this.marca = marca;
        this.proveedor = proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public int getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(int precio_producto) {
        this.precio_producto = precio_producto;
    }

    public Colores getColor() {
        return color;
    }

    public void setColor(Colores color) {
        this.color = color;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public Proveedores getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedores proveedor) {
        this.proveedor = proveedor;
    }
   
}
