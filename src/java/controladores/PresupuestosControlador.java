package controladores;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Clientes;
import modelos.Ordenes_De_Trabajo;
import modelos.Presupuestos;
import utiles.Conexion;
import utiles.Utiles;
/**
 *
 * @author Elias
 */
public class PresupuestosControlador {
    public static Presupuestos buscarId(int id) {
        Presupuestos presupuestos = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from presupuestos p, clientes c where "
                        + "c.id_cliente=p.id_cliente and "
                        + "id_presupuesto=?";
                System.out.println("sql "+ sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        presupuestos = new Presupuestos();
                        presupuestos.setId_presupuesto(rs.getInt("id_presupuesto"));
                        presupuestos.setFecha_presupuesto(rs.getDate("fecha_presupuesto"));
                                             
                        Clientes cliente = new Clientes();
                        cliente.setId_cliente(rs.getInt("id_cliente"));
                        cliente.setNombre_cliente(rs.getString("nombre_cliente"));
                        cliente.setApellido_cliente(rs.getString("apellido_cliente"));
                        cliente.setCi_cliente(rs.getString("ci_cliente"));
                        cliente.setRuc_cliente(rs.getString("ruc_cliente"));
                        presupuestos.setCliente(cliente);
                    } 
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return presupuestos;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * Utiles.REGISTROS_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from presupuestos p "
                        + "left join clientes c on c.id_cliente=p.id_cliente "
                        + "where upper(nombre_cliente) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by id_presupuesto "
                        + "offset " + offset + " limit " + Utiles.REGISTROS_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td class='centrado'>" + rs.getString("id_presupuesto") + "</td>"
                                + "<td class='centrado'>" + rs.getString("fecha_presupuesto") + "</td>"
                                + "<td class='centrado'>" + rs.getString("nombre_cliente") + "</td>"
                                + "<td class='centrado'>" + rs.getString("apellido_cliente") + "</td>"
                                + "<td class='centrado'>" + rs.getString("ci_cliente") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=7>No existen registros ...</td></tr>";
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

    public static boolean agregar(Presupuestos presupuesto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            Date v1 = presupuesto.getFecha_presupuesto();
            int v2 = presupuesto.getCliente().getId_cliente();
                                                
            String sql = "insert into presupuestos(fecha_presupuesto, id_cliente) "
                    + "values('" + v1 + "','" + v2 + "')";
            System.out.println("--> " + sql);
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_presupuesto = keyset.getInt(1);
                    presupuesto.setId_presupuesto(id_presupuesto);
                    Conexion.getConn().setAutoCommit(false);
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Presupuestos presupuesto) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update presupuestos set fecha_presupuesto=?, "
                    + "id_cliente=?, "
                    + "where id_presupuesto=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setDate(1, presupuesto.getFecha_presupuesto());
                ps.setInt(2, presupuesto.getCliente().getId_cliente());
                ps.setInt(3, presupuesto.getId_presupuesto());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
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

    public static boolean eliminar(Presupuestos presupuesto  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from presupuestos where id_presupuesto=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, presupuesto.getId_presupuesto());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().setAutoCommit(false);
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

