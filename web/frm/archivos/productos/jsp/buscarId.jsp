<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    
   ProductosControlador.buscarId(producto);
    if (producto.getId_producto()!=0){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_producto", producto.getId_producto());
    obj.put("nombre_producto", producto.getNombre_producto());
    obj.put("precio_producto", producto.getPrecio_producto());
    obj.put("id_color", producto.getColor().getId_color());
    obj.put("nombre_color", producto.getColor().getNombre_color());
    obj.put("id_marca", producto.getMarca().getId_marca());
    obj.put("nombre_marca", producto.getMarca().getNombre_marca());
    obj.put("id_proveedor", producto.getProveedor().getId_proveedor());
    obj.put("nombre_proveedor", producto.getProveedor().getNombre_proveedor());
    
    out.print(obj);
    out.flush();
%>
