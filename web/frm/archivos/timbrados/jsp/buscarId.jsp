<%@page import="controladores.TimbradosControlador"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    java.sql.Date fechainicio_timbrado = new java.sql.Date(new java.util.Date().getTime());
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    
   TimbradosControlador.buscarId(timbrado);
    if (timbrado.getId_timbrado()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_timbrado", timbrado.getId_timbrado());
    obj.put("numero_timbrado", timbrado.getNumero_timbrado());
    obj.put("fechainicio_timbrado", String.valueOf(timbrado.getFechainicio_timbrado()));
    obj.put("fechavencimiento_timbrado", String.valueOf(timbrado.getFechavencimiento_timbrado()));
    obj.put("fechactual_timbrado", String.valueOf(timbrado.getFechactual_timbrado()));
    obj.put("desde_timbrado", timbrado.getDesde_timbrado());
    obj.put("hasta_timbrado", timbrado.getHasta_timbrado());
    out.print(obj);
    out.flush();
%>
