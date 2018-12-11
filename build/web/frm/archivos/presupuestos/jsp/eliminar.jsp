
<%@page import="controladores.PresupuestosControlador"%>
<%@page import="modelos.Presupuestos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presupuesto = Integer.parseInt(request.getParameter("id_presupuesto"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Presupuestos presupuesto = new Presupuestos();
    presupuesto.setId_presupuesto(id_presupuesto);
    
    if (PresupuestosControlador.eliminar(presupuesto)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>