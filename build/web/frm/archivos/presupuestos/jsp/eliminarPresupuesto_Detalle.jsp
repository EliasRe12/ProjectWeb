
<%@page import="controladores.Presupuestos_DetallesControlador"%>
<%@page import="modelos.Presupuestos_Detalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presupuestodetalle = Integer.parseInt(request.getParameter("id_presupuestodetalle"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Presupuestos_Detalles presupuestodetalle = new Presupuestos_Detalles();
    presupuestodetalle.setId_presupuestodetalle(id_presupuestodetalle);
    
    if (Presupuestos_DetallesControlador.eliminar(presupuestodetalle)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>