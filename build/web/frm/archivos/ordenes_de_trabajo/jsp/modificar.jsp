<%@page import="modelos.Ciudades"%>
<%@page import="modelos.Fiscales_De_Obras"%>
<%@page import="utiles.Utiles"%>
<%@page import="controladores.Ordenes_De_TrabajoControlador"%>
<%@page import="modelos.Ordenes_De_Trabajo"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_ordendetrabajo = Integer.parseInt(request.getParameter("id_ordendetrabajo"));
    int id_fiscaldeobra = Integer.parseInt(request.getParameter("id_fiscaldeobra"));
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    String estado_ordendetrabajo = request.getParameter("estado_ordendetrabajo");
    String descripcion_ordendetrabajo = request.getParameter("descripcion_ordendetrabajo");
    String direccion_ordendetrabajo = request.getParameter("direccion_ordendetrabajo");
    String sfechainicio_ordendetrabajo = request.getParameter("fechainicio_ordendetrabajo");
    String sfechaentrega_ordendetrabajo = request.getParameter("fechaentrega_ordendetrabajo");
    
    java.sql.Date fechainicio_ordendetrabajo = Utiles.stringToSqlDate(sfechainicio_ordendetrabajo);
    java.sql.Date fechaentrega_ordendetrabajo = Utiles.stringToSqlDate(sfechaentrega_ordendetrabajo);
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Ordenes_De_Trabajo ordendetrabajo = new Ordenes_De_Trabajo();
    ordendetrabajo.setId_ordendetrabajo(id_ordendetrabajo);
    ordendetrabajo.setDescripcion_ordendetrabajo(descripcion_ordendetrabajo);
    ordendetrabajo.setDireccion_ordendetrabajo(direccion_ordendetrabajo);
    ordendetrabajo.setEstado_ordendetrabajo(estado_ordendetrabajo);
    ordendetrabajo.setFechainicio_ordendetrabajo(fechainicio_ordendetrabajo);
    ordendetrabajo.setFechaentrega_ordendetrabajo(fechaentrega_ordendetrabajo);
    
    Fiscales_De_Obras fiscaldeobra = new Fiscales_De_Obras();
    fiscaldeobra.setId_fiscaldeobra(id_fiscaldeobra);
    ordendetrabajo.setFiscaldeobra(fiscaldeobra);
    
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    ordendetrabajo.setCiudad(ciudad);
       
    if (Ordenes_De_TrabajoControlador.modificar(ordendetrabajo)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
