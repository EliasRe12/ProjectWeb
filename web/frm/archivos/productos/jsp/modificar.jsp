<%@page import="modelos.Proveedores"%>
<%@page import="modelos.Nacionalidades"%>
<%@page import="modelos.Marcas"%>
<%@page import="modelos.Colores"%>
<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    String nombre_producto = request.getParameter("nombre_producto");
    int precio_producto = Integer.parseInt(request.getParameter("precio_producto"));
    int id_color = Integer.parseInt(request.getParameter("id_color"));
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));
    int id_proveedor = Integer.parseInt(request.getParameter("id_proveedor"));
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setNombre_producto(nombre_producto);
    producto.setPrecio_producto(precio_producto);
    
    Colores color = new Colores();
    color.setId_color(id_color);
    producto.setColor(color);
    
    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);
    producto.setMarca(marca);
    
    Proveedores proveedor = new Proveedores();
    proveedor.setId_proveedor(id_proveedor);
    producto.setProveedor(proveedor);
    
    if (ProductosControlador.modificar(producto)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>
