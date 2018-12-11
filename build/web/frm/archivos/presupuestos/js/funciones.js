function validarFormulario() {
    var valor = true;
    if ($("#id_cliente").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("'ID Cliente' No puede estar vacío.");
        $("#id_presupuesto").focus();
    }
    return valor;
}

function validarFormularioDetalle() {
    var valor = true;
    //DETALLE
    if ($("#id_sector").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("'Campo ID Sector' No puede estar vacío.");
        $("#id_presupuestodetalle").focus();
    }
    if ($("#id_ubicacion").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("'Campo ID Ubicacion' No puede estar vacío.");
        $("#id_presupuestodetalle").focus();
    }
    if ($("#color_presupuestodetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Color' No puede estar vacío.");
        $("#id_presupuestodetalle").focus();
    }
    if ($("#mm_presupuestodetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Milímetro' No puede estar vacío.");
        $("#id_presupuestodetalle").focus();
    }
    if ($("#m2_presupuestodetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo MetroCuadrado' No puede estar vacío.");
        $("#id_presupuestodetalle").focus();
    }
    if ($("#alto_presupuestodetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Alto' No puede estar vacío.");
        $("#id_presupuestodetalle").focus();
    }
    if ($("#ancho_presupuestodetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Ancho' No puede estar vacío.");
        $("#id_presupuestodetalle").focus();
    }
    if ($("#precio_presupuestodetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Precio' No puede estar vacío.");
        $("#id_presupuestodetalle").focus();
    }
    if ($("#cantidad_presupuestodetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Cantidad' No puede estar vacío.");
        $("#id_presupuestodetalle").focus();
    }
    if ($("#detalle_presupuestodetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Detalle' No puede estar vacío.");
        $("#id_presupuestodetalle").focus();
    }
    return valor;
}

function validarFormularioSubDetalle() {
    var valor = true;
    //SUBDETALLE
    if ($("#colocacion_presupuestosubdetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Costo Colocacion' No puede estar vacío.");
        $("#id_presupuestosubdetalle").focus();
    }
    if ($("#mo_presupuestosubdetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Costo Mano De Obra' No puede estar vacío.");
        $("#id_presupuestosubdetalle").focus();
    }
    if ($("#costoherrajes_presupuestosubdetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Costo Herrajes' No puede estar vacío.");
        $("#id_presupuestosubdetalle").focus();
    }
    if ($("#totalperfil_presupuestosubdetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Total Perfil' No puede estar vacío.");
        $("#id_presupuestosubdetalle").focus();
    }
    if ($("#anchop_presupuestosubdetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Ancho Perfil' No puede estar vacío.");
        $("#id_presupuestosubdetalle").focus();
    }
    if ($("#largop_presupuestosubdetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Largo Perfil' No puede estar vacío.");
        $("#id_presupuestosubdetalle").focus();
    }
    if ($("#totalvidrio_presupuestosubdetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Total Vidrio' No puede estar vacío.");
        $("#id_presupuestosubdetalle").focus();
    }
    if ($("#costovidrio_presupuestosubdetalle").val().trim() === "") {
        valor = false;
        $("#mensajes").html("'Campo Costo Vidrio' No puede estar vacío.");
        $("#id_presupuestosubdetalle").focus();
    }
    return valor;
}

function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function formatoMiles(){
    var totalp = document.getElementById('totalperfil_presupuestosubdetalle')
        .innerHTML = new Intl.NumberFormat('es-MX').format(numero);
    var anchop = document.getElementById('anchop_presupuestosubdetalle')
        .innerHTML = new Intl.NumberFormat('es-MX').format(numero);
}


$("#number").on({
  "focus": function(event) {
    $(event.target).select();
  },
  "keyup": function(event) {
    $(event.target).val(function(index, value) {
      return value.replace(/\D/g, "")
        .replace(/([0-9])([0-9]{2})$/, '$1.$2')
        .replace(/\B(?=(\d{3})+(?!\d)\.?)/g, ",");
    });
  }
});
//////////////////////////////////////DETALLE//////////////////////////////////
function Area() {
    ancho_pre = document.getElementById("ancho_presupuestodetalle").value;
    alto_pre = document.getElementById("alto_presupuestodetalle").value;
    resultado = ancho_pre * alto_pre;
    document.getElementById("m2_presupuestodetalle").value = resultado.toFixed(2);
}

//////////////////////////////////////SUBDETALLE///////////////////////////////
function Perfil() {
    ancho_pre1 = document.getElementById("ancho_presupuestodetalle1").value;
    alto_pre1 = document.getElementById("alto_presupuestodetalle1").value;
    largo_perfil = document.getElementById("largop_presupuestosubdetalle").value;
    ancho_perfil = document.getElementById("anchop_presupuestosubdetalle").value;
    tanchop = ancho_pre1 * ancho_perfil;
    tlargop = alto_pre1 * largo_perfil;
    totalperfil = parseInt(tanchop) + parseInt(tlargop);
    document.getElementById("totalperfil_presupuestosubdetalle").value = totalperfil;
}
function Vidrio() {
    vidrio = document.getElementById("costovidrio_presupuestosubdetalle").value;
    m2s = document.getElementById("m2_presupuestodetalle1").value;
    cant = document.getElementById("cantidad_presupuestodetalle1").value;
    div = m2s/cant; 
    mul = div * vidrio; 
    document.getElementById("totalvidrio_presupuestosubdetalle").value = mul.toFixed(0);
}

function Mo() {
    mo = document.getElementById("moo_presupuestosubdetalle").value;
    m2s = document.getElementById("m2_presupuestodetalle1").value;
    cant = document.getElementById("cantidad_presupuestodetalle1").value;
    div = m2s/cant;
    mul = mo * div;
    document.getElementById("mo_presupuestosubdetalle").value = mul;
}
function TotalSub() {
    tvidrio = document.getElementById("totalvidrio_presupuestosubdetalle").value;
    tperfil = document.getElementById("totalperfil_presupuestosubdetalle").value;
    therrajes = document.getElementById("costoherrajes_presupuestosubdetalle").value;
    mobra = document.getElementById("mo_presupuestosubdetalle").value;
    cant = document.getElementById("cantidad_presupuestodetalle1").value;
    sum = parseInt(tvidrio) + parseInt(tperfil) + parseInt(therrajes) + parseInt(mobra);
    xcant = cant * sum;
    document.getElementById("colocacion_presupuestosubdetalle").value = xcant;
    
}

function TotalPresupuesto() {
    m2_pre = document.getElementById("m2_presupuestodetalle").value;
    cant_pre = document.getElementById("cantidad_presupuestodetalle").value;
    costo_total = m2_pre * cant_pre;
    document.getElementById("precio_presupuestodetalle").value = costo_total;
    
}
///////////////////////////////////////////////////////////////////////////////


function buscarIdPresupuesto() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_presupuesto").val(json.id_presupuesto);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#apellido_cliente").val(json.apellido_cliente);
            $("#ci_cliente").val(json.ci_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);
            $("#fecha_presupuesto").val(json.fecha_presupuesto);
            $("#id_ordendetrabajo").val(json.id_ordendetrabajo);
            var fecha = $("fecha_presupuesto").serialize();
            
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#id_tipopresupuesto", "#botonAgregar", true);
               $("#detalle").prop('hidden', true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#id_tipopresupuesto", "#botonModificar", true);
               $("#detalle").prop('hidden', false);
           }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombrePresupuesto() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombre.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function() {
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_presupuesto").val(id);
              $("#nombre_cliente").focus();
              buscarIdPresupuesto();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
           }
       }
    });
}
function agregarPresupuesto() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_presupuesto").val(json.id_presupuesto);
            //buscarIdPresupuesto();
            //$("#id_presupuesto").focus;
            //$("#id_presupuesto").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}

function modificarPresupuesto() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_presupuesto").focus();
            $("#id_presupuesto").select();
            buscarIdPresupuesto();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}

function eliminarPresupuesto() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            eliminarPresupuesto_Detalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_presupuesto").focus();
            $("#id_presupuesto").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}


function buscarIdCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCliente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#apellido_cliente").val(json.apellido_cliente);
            $("#ci_cliente").val(json.ci_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombreCliente() {
    var datosFormulario = $("#formBuscar").serialize();
  
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreCliente.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_cliente").val(id);
              $("#nombre_cliente").focus();
              buscarIdCliente();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
           }
       }
    });
}

 function limpiarFormulario() {
     $("#id_presupuesto").val("0");
     $("#id_cliente").val("0");
     $("#nombre_tipopresupuesto").val("");
     $("#nombre_cliente").val("");
     $("#apellido_cliente").val("");
     $("#ci_cliente").val("0");     
     $("#ruc_cliente").val("0");     
     $("#fecha_presupuesto").val("");
     $("#id_ordendetrabajo").val("0");
}
function agregarLinea() {
    $("#id_presupuestodetalle").val("0");
    $("#detalle_presupuestodetalle").val("0");
    $("#id_ubicacion").val("0");
    $("#nombre_ubicacion").val("");
    $("#id_sector").val("0");
    $("#nombre_sector").val("");
    $("#color_presupuestodetalle").val("");
    $("#m2_presupuestodetalle").val("");
    $("#mm_presupuestodetalle").val("0");
    $("#cantidad_presupuestodetalle").val("0");
    $("#precio_presupuestodetalle").val("0");
    $("#ancho_presupuestodetalle").val("");
    $("#alto_presupuestodetalle").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detallepedido", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_presupuestodetalle").val(id);
    $("#detalle_presupuestodetalle").val("0");
    $("#id_ubicacion").val("0");
    $("#nombre_ubicacion").val("");
    $("#id_sector").val("0");
    $("#nombre_sector").val("");
    $("#color_presupuestodetalle").val("");
    $("#m2_presupuestodetalle").val("");
    $("#mm_presupuestodetalle").val("0");
    $("#cantidad_presupuestodetalle").val("0");
    $("#precio_presupuestodetalle").val("0");
    $("#ancho_presupuestodetalle").val("");
    $("#alto_presupuestodetalle").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdPresupuesto_Detalle();
    //siguienteCampo("#cantidad_presupuestodetalle", "#botonModificarLinea", true);
}
//SUBDETALLES
function agregarLinea1() {
    var id_presupuestodetalle = $("#id_presupuestodetalle").val();
    $("#id_presupuestosubdetalle").val(id_presupuestodetalle);
    $("#costovidrio_presupuestosubdetalle").val("0");
    $("#totalvidrio_presupuestosubdetalle").val("0");
    $("#largop_presupuestosubdetalle").val("0");
    $("#anchop_presupuestosubdetalle").val("0");
    $("#totalperfil_presupuestosubdetalle").val("0");
    $("#mo_presupuestosubdetalle").val("0");
    $("#colocacion_presupuestosubdetalle").val("0");
    $("#ganancia_presupuestosubdetalle").val("0");
    $("#iva_presupuestosubdetalle").val("0");
    $("#total_presupuestosubdetalle").val("0");
    $("#panelLinea1").fadeIn("slow");
    $("#panelLinea").fadeOut("slow");
    $("#botonAgregarLinea1").prop('disabled', false);
    $("#botonModificarLinea1").prop('disabled', true);
    $("#botonEliminarLinea1").prop('disabled', true);
    //siguienteCampo("#horas_detallepedido", "#botonAgregarLinea", true);
}
function editarLinea1(id) {
    $("#id_presupuestosubdetalle").val(id);
    $("#costovidrio_presupuestosubdetalle").val("0");
    $("#totalvidrio_presupuestosubdetalle").val("0");
    $("#costolargoperfil_presupuestosubdetalle").val("0");
    $("#costoanchoperfil_presupuestosubdetalle").val("0");
    $("#totalperfil_presupuestosubdetalle").val("0");
    $("#mo_presupuestosubdetalle").val("0");
    $("#colocacion_presupuestosubdetalle").val("0");
    $("#ganancia_presupuestosubdetalle").val("0");
    $("#iva_presupuestosubdetalle").val("0");
    $("#total_presupuestosubdetalle").val("0");
    $("#panelLinea1").fadeIn("slow");
    $("#panelLinea").fadeOut("slow");
    $("#botonAgregarLinea1").prop('disabled', true);
    $("#botonModificarLinea1").prop('disabled', false);
    $("#botonEliminarLinea1").prop('disabled', false);
    buscarIdPresupuesto_Subdetalle();
    //siguienteCampo("#cantidad_presupuestodetalle", "#botonModificarLinea", true);
}

//Presupuestos Detalles
function buscarIdPresupuesto_Detalle() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPresupuesto_Detalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_presupuestodetalle").val(json.id_presupuestodetalle);
            //$("#id_presupuesto").val(json.id_presupuesto);
            $("#detalle_presupuestodetalle").val(json.detalle_presupuestodetalle);
            $("#cantidad_presupuestodetalle").val(json.cantidad_presupuestodetalle);
            $("#precio_presupuestodetalle").val(json.precio_presupuestodetalle);
            $("#ancho_presupuestodetalle").val(json.ancho_presupuestodetalle);
            $("#alto_presupuestodetalle").val(json.alto_presupuestodetalle);
            $("#m2_presupuestodetalle").val(json.m2_presupuestodetalle);
            $("#mm_presupuestodetalle").val(json.mm_presupuestodetalle);
            $("#id_sector").val(json.id_sector);
            $("#nombre_sector").val(json.nombre_sector);
            $("#id_ubicacion").val(json.id_ubicacion);
            $("#nombre_ubicacion").val(json.nombre_ubicacion);
            $("#color_presupuestodetalle").val(json.color_presupuestodetalle);
            
            //***SUB-DETALLE***//
            $("#id_presupuestosubdetalle").val(json.id_presupuestosubdetalle);
            $("#costovidrio_presupuestosubdetalle").val(json.costovidrio_presupuestosubdetalle);
            $("#totalvidrio_presupuestosubdetalle").val(json.totalvidrio_presupuestosubdetalle);
            $("#largop_presupuestosubdetalle").val(json.largop_presupuestosubdetalle);
            $("#anchop_presupuestosubdetalle").val(json.anchop_presupuestosubdetalle);
            $("#totalperfil_presupuestosubdetalle").val(json.totalperfil_presupuestosubdetalle);
            $("#costoherrajes_presupuestosubdetalle").val(json.costoherrajes_presupuestosubdetalle);
            $("#mo_presupuestosubdetalle").val(json.mo_presupuestosubdetalle);
            $("#colocacion_presupuestosubdetalle").val(json.colocacion_presupuestosubdetalle);
            //Referencias a Formulario SUBDETALLE//
            $("#cantidad_presupuestodetalle1").val(json.cantidad_presupuestodetalle);
            $("#ancho_presupuestodetalle1").val(json.ancho_presupuestodetalle);
            $("#alto_presupuestodetalle1").val(json.alto_presupuestodetalle);
            $("#precio_presupuestodetalle1s").val(json.precio_presupuestodetalle);
            $("#m2_presupuestodetalle1").val(json.m2_presupuestodetalle);
            
            $("#contenidoDetalle1").html(json.contenido_detalle1);
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#id_tipopresupuesto", "#botonAgregar", true);
               $("#detalle1").prop('hidden', true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#id_tipopresupuesto", "#botonModificar", true);
               $("#detalle1").prop('hidden', false);
           }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarIdPresupuestoPresupuesto_Detalle() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPresupuestoPresupuesto_Detalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoDetalle").html(json.contenido_detalle);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}
function agregarPresupuesto_Detalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_presupuesto = $("#id_presupuesto").val();
    datosFormulario += "&id_presupuesto=" + id_presupuesto;

    $.ajax({
        type: 'POST',
        url: 'jsp/agregarPresupuesto_Detalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdPresupuesto();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if(exito === "success") {
            }
        }
    });
}

function modificarPresupuesto_Detalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_presupuesto = $("#id_presupuesto").val();
    datosFormulario += "&id_presupuesto=" + id_presupuesto;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarPresupuesto_Detalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdPresupuesto();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}

function eliminarPresupuesto_Detalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_presupuesto = $("#id_presupuesto").val();
    datosFormulario += "&id_presupuesto=" + id_presupuesto;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarPresupuesto_Detalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdPresupuesto();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}

function buscarIdSector() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdSector.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_sector").val(json.id_sector);
            $("#nombre_sector").val(json.nombre_sector);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarIdUbicacion() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdUbicacion.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ubicacion").val(json.id_ubicacion);
            $("#nombre_ubicacion").val(json.nombre_ubicacion);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarIdColor() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdColor.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_color").val(json.id_color);
            $("#nombre_color").val(json.nombre_color);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function buscarNombreColor() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreColor.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_color").val(id);
              $("#nombre_color").focus();
              buscarIdColor();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function buscarNombreSector() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreSector.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_sector").val(id);
              $("#nombre_sector").focus();
              buscarIdSector();
              $("#buscar").fadeOut("slow");
              $("#panelLinea").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo buscar registros.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function buscarNombreUbicacion() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreUbicacion.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_ubicacion").val(id);
              $("#nombre_ubicacion").focus();
              buscarIdUbicacion();
              $("#buscar").fadeOut("slow");
              $("#panelLinea").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo modificar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
           }
       }
    });
}

/******SUBDETALLES******/

function buscarIdPresupuesto_Subdetalle() {
    var datosFormulario = $("#formLinea1").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPresupuesto_Subdetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            //var id_presupuestosubdetalle= $("#id_presupuestosubdetalle").serialize();
            //alert(id_presupuestosubdetalle)
            //$("#id_presupuestodetalle").val(json.id_presupuestodetalle);
            $("#id_presupuestosubdetalle").val(json.id_presupuestosubdetalle);
            //***SUB-DETALLE***//
            $("#costovidrio_presupuestosubdetalle").val(json.costovidrio_presupuestosubdetalle);
            $("#totalvidrio_presupuestosubdetalle").val(json.totalvidrio_presupuestosubdetalle);
            $("#largop_presupuestosubdetalle").val(json.largop_presupuestosubdetalle);
            $("#anchop_presupuestosubdetalle").val(json.anchop_presupuestosubdetalle);
            $("#totalperfil_presupuestosubdetalle").val(json.totalperfil_presupuestosubdetalle);
            $("#costoherrajes_presupuestosubdetalle").val(json.costoherrajes_presupuestosubdetalle);
            $("#mo_presupuestosubdetalle").val(json.mo_presupuestosubdetalle);
            $("#colocacion_presupuestosubdetalle").val(json.colocacion_presupuestosubdetalle);
            
            //Referencias del Formulario DETALLE//
            $("#cantidad_presupuestodetalle1").val(json.cantidad_presupuestodetalle);
            $("#ancho_presupuestodetalle1").val(json.ancho_presupuestodetalle);
            $("#alto_presupuestodetalle1").val(json.alto_presupuestodetalle);
            $("#precio_presupuestodetalle1s").val(json.precio_presupuestodetalle);
            $("#m2_presupuestodetalle1").val(json.m2_presupuestodetalle);
            
            $("#contenidoDetalle1").html(json.contenido_detalle1);
            if (json.nuevo === "true") {
               $("#botonAgregar").prop('disabled', false);
               $("#botonModificar").prop('disabled', true);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#id_tipopresupuesto", "#botonAgregar", true);
               $("#detalle1").prop('hidden', true);
           } else {
               $("#botonAgregar").prop('disabled', true);
               $("#botonModificar").prop('disabled', false);
               $("#botonEliminar").prop('disabled', true);
               siguienteCampo("#id_tipopresupuesto", "#botonModificar", true);
               $("#detalle1").prop('hidden', false);
           }
        },
        error: function (e) {
            $("#mensajes").html("Datos no encontrados.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}

function agregarPresupuesto_SubDetalle() {
    var datosFormulario = $("#formLinea1").serialize();
    var id_presupuestodetalle = $("#id_presupuestodetalle").val();
    datosFormulario += "&id_presupuestodetalle=" + id_presupuestodetalle;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarPresupuesto_Subdetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea1").fadeOut("slow");
            $("#panelLinea").fadeIn("slow");
            buscarIdPresupuesto_Subdetalle();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo Agregar los datos.");
        },
        complete: function (objeto, exito, error){
            if(exito === "success") {
            }
        }
    });
}
function modificarPresupuesto_SubDetalle() {
    var datosFormulario = $("#formLinea1").serialize();
    var id_presupuestodetalle = $("#id_presupuestodetalle").val();
    datosFormulario += "&id_presupuestodetalle=" + id_presupuestodetalle;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarPresupuesto_Subdetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea1").fadeOut("slow");
            $("#panelLinea").fadeIn("slow");
            buscarIdPresupuesto_Detalle();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}

function eliminarPresupuesto_SubDetalle() {
    var datosFormulario = $("#formLinea1").serialize();
    var id_presupuestodetalle = $("#id_presupuestodetalle").val();
    datosFormulario += "&id_presupuestodetalle=" + id_presupuestodetalle;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarPresupuesto_Subdetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea1").fadeOut("slow");
            $("#panelLinea").fadeIn("slow");
            buscarIdPresupuesto_Detalle();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo Eliminar los datos.");
        },
        complete: function (objeto, exito, error){
            if (exito === "success") {
            }
        }
    });
}