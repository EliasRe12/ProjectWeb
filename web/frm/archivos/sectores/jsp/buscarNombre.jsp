<%@page  import="controladores.SectoresControlador"%>
<%@page  import="org.json.simple.JSONObject"%>
<%@page  import="java.sql.ResultSet"%>
<%
    String nombre_sector = request.getParameter("bnombre");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "Busqueda exitosa";
    String contenido = SectoresControlador.buscarNombre(nombre_sector, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>
