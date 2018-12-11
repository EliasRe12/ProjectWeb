<%@page import="utiles.Utiles"%>
<%@page import="controladores.Presupuestos_DetallesControlador"%>
<%@page import="modelos.Clientes"%>
<%@page import="controladores.PresupuestosControlador"%>
<%@page import="modelos.Presupuestos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presupuesto = Integer.parseInt(request.getParameter("id_presupuesto"));
    //String sfecha_presupuesto = request.getParameter("fecha_presupuesto");
    //java.sql.Date fecha_presupuesto = Utiles.stringToSqlDate(sfecha_presupuesto);
    java.sql.Date fecha_presupuesto = new java.sql.Date(new java.util.Date().getTime());
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    
    Presupuestos presupuestos = PresupuestosControlador.buscarId(id_presupuesto);
    if (presupuestos != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        presupuestos = new Presupuestos();
        presupuestos.setId_presupuesto(0);
        presupuestos.setFecha_presupuesto(fecha_presupuesto);
        
        Clientes cliente = new Clientes();
        cliente.setId_cliente(0);
        presupuestos.setCliente(cliente);
        
    }
    
    String contenido_detalle = Presupuestos_DetallesControlador.buscarIdPresupuesto(id_presupuesto);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_presupuesto", presupuestos.getId_presupuesto());
    obj.put("id_cliente", String.valueOf(presupuestos.getCliente().getId_cliente()));
    obj.put("nombre_cliente", presupuestos.getCliente().getNombre_cliente());
    obj.put("apellido_cliente", presupuestos.getCliente().getApellido_cliente());
    obj.put("ci_cliente", presupuestos.getCliente().getCi_cliente());
    obj.put("ruc_cliente", presupuestos.getCliente().getRuc_cliente());
    obj.put("fecha_presupuesto",String.valueOf(presupuestos.getFecha_presupuesto()));
    obj.put("contenido_detalle", contenido_detalle);
    //System.out.println("--> id_presupuesto" + presupuestos.getId_presupuesto()); UTILIZar en caso de Emergencia
    out.print(obj);
    out.flush();
%>
