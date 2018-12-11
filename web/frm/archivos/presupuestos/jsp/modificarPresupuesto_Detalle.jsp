<%@page import="modelos.Colores"%>
<%@page import="modelos.Presupuestos"%>
<%@page import="modelos.Ubicaciones"%>
<%@page import="modelos.Sectores"%>
<%@page import="controladores.Presupuestos_DetallesControlador"%>
<%@page import="modelos.Presupuestos_Detalles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_presupuestodetalle = Integer.parseInt(request.getParameter("id_presupuestodetalle"));
    int cantidad_presupuestodetalle = Integer.parseInt(request.getParameter("cantidad_presupuestodetalle"));
    int precio_presupuestodetalle = Integer.parseInt(request.getParameter("precio_presupuestodetalle"));
    String ancho_presupuestodetalle = request.getParameter("ancho_presupuestodetalle");
    String alto_presupuestodetalle = request.getParameter("alto_presupuestodetalle");
    String m2_presupuestodetalle = request.getParameter("m2_presupuestodetalle");
    String detalle_presupuestodetalle = request.getParameter("detalle_presupuestodetalle");
    String color_presupuestodetalle = request.getParameter("color_presupuestodetalle");
    int mm_presupuestodetalle = Integer.parseInt(request.getParameter("mm_presupuestodetalle"));
        
    int id_presupuesto =  Integer.parseInt(request.getParameter("id_presupuesto"));
    int id_ubicacion =  Integer.parseInt(request.getParameter("id_ubicacion"));
    int id_sector =  Integer.parseInt(request.getParameter("id_sector"));
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Presupuestos_Detalles presupuestodetalle = new Presupuestos_Detalles();
    presupuestodetalle.setId_presupuestodetalle(id_presupuestodetalle);
    presupuestodetalle.setCantidad_presupuestodetalle(cantidad_presupuestodetalle);
    presupuestodetalle.setPrecio_presupuestodetalle(precio_presupuestodetalle);
    presupuestodetalle.setAncho_presupuestodetalle(ancho_presupuestodetalle);
    presupuestodetalle.setAlto_presupuestodetalle(alto_presupuestodetalle);
    presupuestodetalle.setM2_presupuestodetalle(m2_presupuestodetalle);
    presupuestodetalle.setDetalle_presupuestodetalle(detalle_presupuestodetalle);
    presupuestodetalle.setColor_presupuestodetalle(color_presupuestodetalle);
    presupuestodetalle.setMm_presupuestodetalle(mm_presupuestodetalle);

    Presupuestos presupuesto = new Presupuestos();
    presupuesto.setId_presupuesto(id_presupuesto);
    presupuestodetalle.setPresupuesto(presupuesto);
        
    Ubicaciones ubicacion = new Ubicaciones();
    ubicacion.setId_ubicacion(id_ubicacion);
    presupuestodetalle.setUbicacion(ubicacion);
    
    Sectores sector = new Sectores();
    sector.setId_sector(id_sector);
    presupuestodetalle.setSector(sector);
            
    if (Presupuestos_DetallesControlador.modificar(presupuestodetalle)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
