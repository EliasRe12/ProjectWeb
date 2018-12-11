<%@page import="controladores.Presupuestos_SubdetallesControlador"%>
<%@page import="modelos.Presupuestos_Subdetalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presupuestosubdetalle = Integer.parseInt(request.getParameter("id_presupuestosubdetalle"));
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Presupuestos_Subdetalles presupuestosubdetalle = new Presupuestos_Subdetalles();
    presupuestosubdetalle.setId_presupuestosubdetalle(id_presupuestosubdetalle);
    
    if (Presupuestos_SubdetallesControlador.eliminar(presupuestosubdetalle)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>