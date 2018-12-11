package controladores;

import modelos.Clientes;
import modelos.Ordenes_De_Trabajo;
import utiles.Conexion;
import utiles.Utiles;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Ciudades;
import modelos.Fiscales_De_Obras;



public class Ordenes_De_TrabajoControlador {

    public static Ordenes_De_Trabajo buscarId(int id) {
        Ordenes_De_Trabajo ordenes_de_trabajo = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ordenes_de_trabajo ob, fiscales_de_obras fc, "
                        + "ciudades c where "
                        + "fc.id_fiscaldeobra=ob.id_fiscaldeobra and "
                        + "c.id_ciudad=ob.id_ciudad and "
                        + "id_ordendetrabajo=?";
                System.out.println("sql "+ sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        ordenes_de_trabajo = new Ordenes_De_Trabajo();
                        ordenes_de_trabajo.setId_ordendetrabajo(rs.getInt("id_ordendetrabajo"));
                        ordenes_de_trabajo.setDescripcion_ordendetrabajo(rs.getString("descripcion_ordendetrabajo"));
                        ordenes_de_trabajo.setEstado_ordendetrabajo(rs.getString("estado_ordendetrabajo"));
                        ordenes_de_trabajo.setDireccion_ordendetrabajo(rs.getString("direccion_ordendetrabajo"));
                        ordenes_de_trabajo.setFechainicio_ordendetrabajo(rs.getDate("fechainicio_ordendetrabajo"));
                        ordenes_de_trabajo.setFechaentrega_ordendetrabajo(rs.getDate("fechaentrega_ordendetrabajo"));
                        
                        Fiscales_De_Obras fiscaldeobra = new Fiscales_De_Obras();
                        fiscaldeobra.setId_fiscaldeobra(rs.getInt("id_fiscaldeobra"));
                        fiscaldeobra.setNombre_fiscaldeobra(rs.getString("nombre_fiscaldeobra"));
                        fiscaldeobra.setApellido_fiscaldeobra(rs.getString("apellido_fiscaldeobra"));
                        fiscaldeobra.setCi_fiscaldeobra(rs.getString("ci_fiscaldeobra"));
                        ordenes_de_trabajo.setFiscaldeobra(fiscaldeobra);
                        
                        Ciudades ciudad = new Ciudades();
                        ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                        ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                        ordenes_de_trabajo.setCiudad(ciudad);
                    } 
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return ordenes_de_trabajo;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from ordenes_de_trabajo ob "
                        + "left join fiscales_de_obras fc on fc.id_fiscaldeobra=ob.id_fiscaldeobra "
                        + "left join ciudades c on c.id_ciudad=ob.id_ciudad "
                        + "where upper(descripcion_ordendetrabajo) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_ordendetrabajo "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_ordendetrabajo") + "</td>"
                                + "<td class='centrado'>" + rs.getString("descripcion_ordendetrabajo") + "</td>"
                                + "<td class='centrado'>" + rs.getString("estado_ordendetrabajo") + "</td>"
                                + "<td class='centrado'>" + rs.getString("fechainicio_ordendetrabajo") + "</td>"
                                + "<td class='centrado'>" + rs.getString("fechaentrega_ordendetrabajo") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean agregar(Ordenes_De_Trabajo ordendetrabajo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String v1 = ordendetrabajo.getDescripcion_ordendetrabajo();
            String v2 = ordendetrabajo.getEstado_ordendetrabajo();
            String v3 = ordendetrabajo.getDireccion_ordendetrabajo();
            Date v4 = ordendetrabajo.getFechainicio_ordendetrabajo();
            Date v5 = ordendetrabajo.getFechaentrega_ordendetrabajo();
            int v6 = ordendetrabajo.getFiscaldeobra().getId_fiscaldeobra();
            int v7 = ordendetrabajo.getCiudad().getId_ciudad();
            
            String sql = "insert into ordenes_de_trabajo(descripcion_ordendetrabajo, "
                    + "estado_ordendetrabajo, direccion_ordendetrabajo, fechainicio_ordendetrabajo, "
                    + "fechaentrega_ordendetrabajo, id_fiscaldeobra, id_ciudad) "
                    + "values ('" + v1 + "','"+ v2 + "','"+ v3 + "','"+ v4 
                    + "','" + v5 + "','" + v6 + "','" + v7  + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_ordendetrabajo = keyset.getInt(1);
                    ordendetrabajo.setId_ordendetrabajo(id_ordendetrabajo);
                    Conexion.getConn().getAutoCommit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Ordenes_De_Trabajo ordendetrabajo) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update ordenes_de_trabajo set "
                    + "descripcion_ordendetrabajo=?, "
                    + "estado_ordendetrabajo=?, "
                    + "direccion_ordendetrabajo=?, "
                    + "fechainicio_ordendetrabajo=?, "
                    + "fechaentrega_ordendetrabajo=?, "
                    + "id_fiscaldeobra=?, "
                    + "id_ciudad=? "          
                    + "where id_ordendetrabajo=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setString(1, ordendetrabajo.getDescripcion_ordendetrabajo());
                ps.setString(2, ordendetrabajo.getEstado_ordendetrabajo());
                ps.setString(3, ordendetrabajo.getDireccion_ordendetrabajo());
                ps.setDate(4, ordendetrabajo.getFechainicio_ordendetrabajo());
                ps.setDate(5, ordendetrabajo.getFechaentrega_ordendetrabajo());
                ps.setInt(6, ordendetrabajo.getFiscaldeobra().getId_fiscaldeobra());
                ps.setInt(7, ordendetrabajo.getCiudad().getId_ciudad());
                ps.setInt(8, ordendetrabajo.getId_ordendetrabajo());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().getAutoCommit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Ordenes_De_Trabajo ordendetrabajo  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from ordenes_de_trabajo where id_ordendetrabajo=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, ordendetrabajo.getId_ordendetrabajo());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().getAutoCommit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}