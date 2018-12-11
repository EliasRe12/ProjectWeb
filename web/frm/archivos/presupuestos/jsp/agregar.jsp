<%@page import="modelos.Clientes"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.PresupuestosControlador"%>
<%@page import="modelos.Presupuestos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presupuesto = Integer.parseInt(request.getParameter("id_presupuesto"));
    int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
    String nombre_cliente = request.getParameter("nombre_cliente");
    String sfecha_presupuesto = request.getParameter("fecha_presupuesto");
    
    java.sql.Date fecha_presupuesto = Utiles.stringToSqlDate(sfecha_presupuesto);
        
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Presupuestos presupuesto = new Presupuestos();
    presupuesto.setId_presupuesto(id_presupuesto);
    presupuesto.setFecha_presupuesto(fecha_presupuesto); 
    
    Clientes cliente = new Clientes();
    cliente.setId_cliente(id_cliente);
    cliente.setNombre_cliente(nombre_cliente);
    presupuesto.setCliente(cliente);
    
    if (PresupuestosControlador.agregar(presupuesto)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>