<%@page import="modelos.Presupuestos_Detalles"%>
<%@page import="controladores.Presupuestos_SubdetallesControlador"%>
<%@page import="modelos.Presupuestos_Subdetalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presupuestosubdetalle = Integer.parseInt(request.getParameter("id_presupuestosubdetalle"));
    int costovidrio_presupuestosubdetalle = Integer.parseInt(request.getParameter("costovidrio_presupuestosubdetalle"));
    int totalvidrio_presupuestosubdetalle = Integer.parseInt(request.getParameter("totalvidrio_presupuestosubdetalle"));
    int largop_presupuestosubdetalle = Integer.parseInt(request.getParameter("largop_presupuestosubdetalle"));
    int anchop_presupuestosubdetalle = Integer.parseInt(request.getParameter("anchop_presupuestosubdetalle"));
    int totalperfil_presupuestosubdetalle = Integer.parseInt(request.getParameter("totalperfil_presupuestosubdetalle"));
    int costoherrajes_presupuestosubdetalle = Integer.parseInt(request.getParameter("costoherrajes_presupuestosubdetalle"));
    int mo_presupuestosubdetalle = Integer.parseInt(request.getParameter("mo_presupuestosubdetalle"));
    int colocacion_presupuestosubdetalle = Integer.parseInt(request.getParameter("colocacion_presupuestosubdetalle"));
    
    int id_presupuestodetalle = Integer.parseInt(request.getParameter("id_presupuestodetalle"));
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
    Presupuestos_Subdetalles presupuestosubdetalle = new Presupuestos_Subdetalles();
    presupuestosubdetalle.setId_presupuestosubdetalle(id_presupuestosubdetalle);
    presupuestosubdetalle.setCostovidrio_presupuestosubdetalle(costovidrio_presupuestosubdetalle);
    presupuestosubdetalle.setTotalvidrio_presupuestosubdetalle(totalvidrio_presupuestosubdetalle);
    presupuestosubdetalle.setLargop_presupuestosubdetalle(largop_presupuestosubdetalle);
    presupuestosubdetalle.setAnchop_presupuestosubdetalle(anchop_presupuestosubdetalle);
    presupuestosubdetalle.setTotalperfil_presupuestosubdetalle(totalperfil_presupuestosubdetalle);
    presupuestosubdetalle.setCostoherrajes_presupuestosubdetalle(costoherrajes_presupuestosubdetalle);
    presupuestosubdetalle.setMo_presupuestosubdetalle(mo_presupuestosubdetalle);
    presupuestosubdetalle.setColocacion_presupuestosubdetalle(colocacion_presupuestosubdetalle);
    
    Presupuestos_Detalles presupuestodetalle = new Presupuestos_Detalles();
    presupuestodetalle.setId_presupuestodetalle(id_presupuestodetalle);
    presupuestosubdetalle.setPresupuestodetalle(presupuestodetalle);
    
    if (Presupuestos_SubdetallesControlador.agregar(presupuestosubdetalle)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>