package controladores;

import modelos.Productos;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Colores;
import modelos.Marcas;
import modelos.Proveedores;

public class ProductosControlador {
    public static boolean agregar(Productos producto){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into productos (nombre_producto, precio_producto, "
                    + "id_color, id_marca, id_proveedor)" 
                    + "values ('" + producto.getNombre_producto() + "','"
                    + producto.getPrecio_producto() + "','"
                    + producto.getColor().getId_color() + "','"
                    + producto.getMarca().getId_marca() + "','"
                    + producto.getProveedor().getId_proveedor() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ProductosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean modificar(Productos producto){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "update productos set nombre_producto='" + producto.getNombre_producto() + "', "
                    + "precio_producto='" + producto.getPrecio_producto() + "', "
                    + "id_color='" + producto.getColor().getId_color() + "', "
                    + "id_marca='" + producto.getMarca().getId_marca() + "', "
                    + "id_proveedor='" + producto.getProveedor().getId_proveedor() + "' "
                    + "where id_producto=" + producto.getId_producto();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ProductosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean eliminar(Productos producto){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from productos where id_producto=" + producto.getId_producto();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ProductosControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static Productos buscarId(Productos producto) {
        if (Conexion.conectar()){
            String sql = "select * from productos p, colores c, marcas m, proveedores pe where "
                    + "p.id_color = c.id_color and "
                    + "p.id_marca = m.id_marca and "
                    + "p.id_proveedor = pe.id_proveedor and "
                    + "id_producto='" +producto.getId_producto()+"'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    Colores color = new Colores();
                    color.setId_color(rs.getInt("id_color"));
                    color.setNombre_color(rs.getString("nombre_color"));
                    producto.setColor(color);
                    
                    Marcas marca = new Marcas();
                    marca.setId_marca(rs.getInt("id_marca"));
                    marca.setNombre_marca(rs.getString("nombre_marca"));
                    producto.setMarca(marca);
                    
                    Proveedores proveedor = new Proveedores();
                    proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                    producto.setProveedor(proveedor);
                    
                    producto.setId_producto(rs.getInt("id_producto"));
                    producto.setNombre_producto(rs.getString("nombre_producto"));
                    producto.setPrecio_producto(rs.getInt("precio_producto"));
                } else {
                    producto.setId_producto(0);
                    producto.setNombre_producto("");
                    producto.setPrecio_producto(0);
                    
                    Colores color = new Colores();
                    color.setId_color(0);
                    color.setNombre_color("");
                    producto.setColor(color);
                    
                    Marcas marca = new Marcas();
                    marca.setId_marca(0);
                    marca.setNombre_marca("");
                    producto.setMarca(marca);
                    
                    Proveedores proveedor = new Proveedores();
                    proveedor.setId_proveedor(0);
                    proveedor.setNombre_proveedor("");
                    producto.setProveedor(proveedor);
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return producto;
    }

    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from productos where upper(nombre_producto) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_producto offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_producto") + "</td>"
                                + "<td class='centrado'>" + rs.getString("nombre_producto") + "</td>"
                                + "<td class='centrado'>" + rs.getString("precio_producto") + "</td>"
                                + "<td class='centrado'>" + rs.getString("id_color") + "</td>"
                                + "<td class='centrado'>" + rs.getString("id_marca") + "</td>"
                                + "</tr>";
                    }   
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2> No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
}
