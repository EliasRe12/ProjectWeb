<%@page import="controladores.SectoresControlador"%>
<%@page import="modelos.Sectores"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_sector = Integer.parseInt(request.getParameter("id_sector"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Sectores sector = new Sectores();
    sector.setId_sector(id_sector);
    
   SectoresControlador.buscarId(sector);
    if (sector.getId_sector()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else {
        sector = new Sectores();
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_sector", sector.getId_sector());
    obj.put("nombre_sector", sector.getNombre_sector());
    out.print(obj);
    out.flush();
%>
