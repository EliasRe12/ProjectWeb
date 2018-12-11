package controladores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Ciudades;
import modelos.Clientes;
import modelos.Nacionalidades;
import utiles.Conexion;
import utiles.Utiles;
/**
 *
 * @author Elias
 */
public class ClientesControlador {
    public static boolean agregar(Clientes cliente){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "insert into clientes (nombre_cliente, apellido_cliente, "
                    + "direccion_cliente, telefono_cliente, ci_cliente, ruc_cliente,"
                    + "id_ciudad, id_nacionalidad) values ('" + cliente.getNombre_cliente() +"','"
                    + cliente.getApellido_cliente() + "','"
                    + cliente.getDireccion_cliente() + "','"
                    + cliente.getTelefono_cliente() + "','"
                    + cliente.getCi_cliente() + "','"
                    + cliente.getRuc_cliente() + "','"
                    + cliente.getCiudad().getId_ciudad() + "','"
                    + cliente.getNacionalidad().getId_nacionalidad() + "')";
                                        
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean modificar(Clientes cliente){
        boolean valor = false;
        if (Conexion.conectar()){ 
            String sql = "update clientes set nombre_cliente='" + cliente.getNombre_cliente() + "', "
                    + "apellido_cliente='" + cliente.getApellido_cliente() + "', "
                    + "direccion_cliente='" + cliente.getDireccion_cliente() + "', "
                    + "telefono_cliente='" + cliente.getTelefono_cliente() + "', "
                    + "ci_cliente='" + cliente.getCi_cliente() + "', "
                    + "ruc_cliente='" + cliente.getRuc_cliente() + "', "
                    + "id_ciudad='" + cliente.getCiudad().getId_ciudad() + "', " 
                    + "id_nacionalidad='" + cliente.getNacionalidad().getId_nacionalidad() + "' "
                    + "where id_cliente=" + cliente.getId_cliente();
               System.out.println("sql "+ sql);     
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
                
            } catch (SQLException ex) {
                Logger.getLogger(ClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static boolean eliminar(Clientes cliente){
        boolean valor = false;
        if (Conexion.conectar()){
            String sql = "delete from clientes where id_cliente=" + cliente.getId_cliente();
                    
            try {
                Conexion.getSt().executeUpdate(sql);
                
                valor = true;
            } catch (SQLException ex) {
                Logger.getLogger(ClientesControlador.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
        return valor;
    }
    
    public static Clientes buscarId(Clientes cliente) {
        if (Conexion.conectar()){
            String sql = "select * from clientes cl, nacionalidades n, ciudades ci "
                    + "where cl.id_ciudad = ci.id_ciudad and cl.id_nacionalidad = n.id_nacionalidad and "
                    + "id_cliente=" + cliente.getId_cliente();
            
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                System.out.println("sql"+ sql);
                if (rs.next()){ 
                    Nacionalidades nacionalidad = new Nacionalidades();
                    nacionalidad.setId_nacionalidad(rs.getInt("id_nacionalidad"));
                    nacionalidad.setNombre_nacionalidad(rs.getString("nombre_nacionalidad"));
                    cliente.setNacionalidad(nacionalidad);
                    
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    cliente.setCiudad(ciudad);
                    
                    cliente.setId_cliente(rs.getInt("id_cliente"));
                    cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                    cliente.setApellido_cliente(rs.getString("apellido_cliente"));
                    cliente.setDireccion_cliente(rs.getString("direccion_cliente"));
                    cliente.setTelefono_cliente(rs.getString("telefono_cliente"));
                    cliente.setCi_cliente(rs.getString("ci_cliente"));
                    cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                } else {
                    Nacionalidades nacionalidad = new Nacionalidades();
                    nacionalidad.setId_nacionalidad(0);
                    nacionalidad.setNombre_nacionalidad("");
                    cliente.setNacionalidad(nacionalidad);
                    
                    Ciudades ciudad = new Ciudades();
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");
                    cliente.setCiudad(ciudad);
                    
                    cliente.setId_cliente(0);
                    cliente.setNombre_cliente("");
                    cliente.setApellido_cliente("");
                    cliente.setDireccion_cliente("");
                    cliente.setTelefono_cliente("");
                    cliente.setCi_cliente("");
                    cliente.setRuc_cliente("");
                }
            } catch (SQLException ex) {
                System.out.println("Error: " + ex);
            }
        }
        return cliente;
    }
    
    public static String buscarNombre(String nombre, int pagina) {
      
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            
            try {
                  System.out.println(nombre);
                String sql = "select * from clientes where upper(nombre_cliente) like '%" +
                        nombre.toUpperCase() + "%'"
                        + "order by id_cliente offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
              
                System.out.println("--->" + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_cliente") + "</td>"
                                + "<td class='centrado'>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td class='centrado'>" + rs.getString("apellido_cliente") + "</td>"
                                + "<td class='centrado'>" + rs.getString("telefono_cliente") + "</td>"
                                + "<td class='centrado'>" + rs.getString("ci_cliente") + "</td>"
                                + "<td class='centrado'>" + rs.getString("ruc_cliente") + "</td>"
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
