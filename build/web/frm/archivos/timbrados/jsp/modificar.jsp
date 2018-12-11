<%@page import="utiles.Utiles"%>
<%@page import="controladores.TimbradosControlador"%>
<%@page import="modelos.Timbrados"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_timbrado = Integer.parseInt(request.getParameter("id_timbrado"));
    String numero_timbrado = request.getParameter("numero_timbrado");
    String sfechainicio_timbrado = request.getParameter("fechainicio_timbrado");
    String sfechavencimiento_timbrado = request.getParameter("fechavencimiento_timbrado");
    String sfechactual_timbrado = request.getParameter("fechactual_timbrado");
    int desde_timbrado = Integer.parseInt(request.getParameter("desde_timbrado"));
    int hasta_timbrado = Integer.parseInt(request.getParameter("hasta_timbrado"));
    
    java.sql.Date fechainicio_timbrado = Utiles.stringToSqlDate(sfechainicio_timbrado);
    java.sql.Date fechavencimiento_timbrado = Utiles.stringToSqlDate(sfechavencimiento_timbrado);
    java.sql.Date fechactual_timbrado = Utiles.stringToSqlDate(sfechactual_timbrado);
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Timbrados timbrado = new Timbrados();
    timbrado.setId_timbrado(id_timbrado);
    timbrado.setNumero_timbrado(numero_timbrado);
    timbrado.setFechainicio_timbrado(fechainicio_timbrado);
    timbrado.setFechavencimiento_timbrado(fechavencimiento_timbrado);
    timbrado.setFechactual_timbrado(fechactual_timbrado);    
    timbrado.setDesde_timbrado(desde_timbrado);
    timbrado.setHasta_timbrado(hasta_timbrado);   
    
    if (TimbradosControlador.modificar(timbrado)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
