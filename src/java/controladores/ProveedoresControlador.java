package controladores;

import modelos.Proveedores;
import utiles.Conexion;
import utiles.Utiles;
import java.util.logging.Level;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import modelos.Ciudades;

public class ProveedoresControlador {
    public static boolean agregar(Proveedores proveedor){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into proveedores (nombre_proveedor, direccion_proveedor,"
                    + "telefono_proveedor, email_proveedor, ruc_proveedor, id_ciudad)" 
                    + "values ('" + proveedor.getNombre_proveedor() + "','"
                    + proveedor.getDireccion_proveedor() + "','"
                    + proveedor.getTelefono_proveedor() + "','"
                    + proveedor.getEmail_proveedor() + "','"
                    + proveedor.getRuc_proveedor() + "','"
                    + proveedor.getCiudad().getId_ciudad() + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ProveedoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean modificar(Proveedores proveedor){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update proveedores set nombre_proveedor='" + proveedor.getNombre_proveedor() + "', "
                    + "direccion_proveedor='" + proveedor.getDireccion_proveedor() + "', "
                    + "telefono_proveedor='" + proveedor.getTelefono_proveedor() + "', " 
                    + "email_proveedor='" + proveedor.getEmail_proveedor() + "', "
                    + "ruc_proveedor='" + proveedor.getRuc_proveedor() + "', "
                    + "id_ciudad='" + proveedor.getCiudad().getId_ciudad() + "' "
                    + "where id_proveedor=" + proveedor.getId_proveedor();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ProveedoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean eliminar(Proveedores proveedor){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from proveedores where id_proveedor=" + proveedor.getId_proveedor();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ProveedoresControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static Proveedores buscarId(Proveedores proveedor) {
        if (Conexion.conectar()){
            String sql = "select * from proveedores p, ciudades c where "
                    + "p.id_ciudad = c.id_ciudad and "
                    + "id_proveedor='" +proveedor.getId_proveedor() + "'";
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    proveedor.setId_proveedor(rs.getInt("id_proveedor"));
                    proveedor.setNombre_proveedor(rs.getString("nombre_proveedor"));
                    proveedor.setDireccion_proveedor(rs.getString("direccion_proveedor"));
                    proveedor.setTelefono_proveedor(rs.getString("telefono_proveedor"));
                    proveedor.setEmail_proveedor(rs.getString("email_proveedor"));
                    proveedor.setRuc_proveedor(rs.getString("ruc_proveedor"));

                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    proveedor.setCiudad(ciudad);
                } else {
                    proveedor.setId_proveedor(0);
                    proveedor.setNombre_proveedor("");
                    proveedor.setDireccion_proveedor("");
                    proveedor.setTelefono_proveedor("");
                    proveedor.setEmail_proveedor("");
                    proveedor.setRuc_proveedor("");
                    
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");
                    proveedor.setCiudad(ciudad);
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return proveedor;
    }
    
   /* public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                  System.out.println(nombre);
                String sql = "select * from proveedores where upper(nombre_proveedor) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_proveedor offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_proveedor") + "</td>"
                                + "<td class='centrado'>" + rs.getString("nombre_proveedor") + "</td>"
                                + "<td class='centrado'>" + rs.getString("telefono_proveedor") + "</td>"
                                + "<td class='centrado'>" + rs.getString("email_proveedor") + "</td>"
                                + "<td class='centrado'>" + rs.getString("nombre_ciudad") + "</td>"
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
    }*/
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                  System.out.println(nombre);
                String sql = "select * from proveedores where upper(nombre_proveedor) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_proveedor offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_proveedor") + "</td>"
                                + "<td class='centrado'>" + rs.getString("nombre_proveedor") + "</td>"
                                + "<td class='centrado'>" + rs.getString("telefono_proveedor") + "</td>"
                                + "<td class='centrado'>" + rs.getString("email_proveedor") + "</td>"
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
