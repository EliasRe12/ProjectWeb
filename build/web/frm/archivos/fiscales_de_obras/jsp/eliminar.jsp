
<%@page import="controladores.Fiscales_De_ObrasControlador"%>
<%@page import="modelos.Fiscales_De_Obras"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_fiscaldeobra = Integer.parseInt(request.getParameter("id_fiscaldeobra"));
    
    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Fiscales_De_Obras fiscaldeobra = new Fiscales_De_Obras();
    fiscaldeobra.setId_fiscaldeobra(id_fiscaldeobra);
    
    if (Fiscales_De_ObrasControlador.eliminar(fiscaldeobra)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>