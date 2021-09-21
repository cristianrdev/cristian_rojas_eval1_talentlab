package com.tiendavirtual.data.controllers;
import java.util.List;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiendavirtual.data.models.Producto;
import com.tiendavirtual.data.models.Usuario;
import com.tiendavirtual.data.models.Venta;
import com.tiendavirtual.data.services.ProductoService;
import com.tiendavirtual.data.services.UsuarioService;
import com.tiendavirtual.data.services.VentaService;
@Controller
@RequestMapping("/venta")
public class VentaController {
    /**private final VentaService vservice;
	public VentaController(VentaService ventaService) {
		this.vservice = ventaService;
	}**/
	@Autowired 
	private VentaService vservice;
	
	@Autowired 
	private ProductoService productoservice;
	
	@Autowired 
	private UsuarioService usuarioservice;
	
	@RequestMapping("")//muestra el panel del usuario para comprar
	public String indexUsuario(@ModelAttribute("venta") Venta venta,Model model, HttpSession session) {

		System.out.println(session.getAttribute("userId"));
		Long id_usuario_activo = (Long) session.getAttribute("userId");
		if(session.getAttribute("userId")!=null) { //Pregunta si hay sesión activa
			System.out.println("ENTRA AL DASHBOARD CON SESION" + session.getAttribute("userId"));		
			List<Producto> lista_productos =productoservice.findAll();
			Usuario usuario_activo = usuarioservice.buscarUsuario(id_usuario_activo);//Obtiene el objeto usuario
			//Usuario compras_usuario = (Usuario) usuarioservice.buscarUsuario(id_usuario_activo).getVentas(); //Obtiene las compras del usuario
			
			System.out.println("COMPRAS DE USUARIO==>" + usuario_activo.getVentas());
			
			model.addAttribute("lista_productos", lista_productos);		
			model.addAttribute("usuario_activo", usuario_activo);
			model.addAttribute("lista_de_compras", usuario_activo.getVentas());
			return "venta.jsp";
		}
		
		System.out.println("NO HAY SESION ACTIVA");//hay una sesion activa?
		return "redirect:/";  //Si no hay sesión activa envía al index 
		
		

	}
	
	@RequestMapping(value="/crear", method = RequestMethod.POST) //genera la venta
	public String crearUsuario(@Valid @ModelAttribute("venta") Venta venta, Model model) {

		//Verifica campos vacíos
		if(venta.getDireccion_envio().isBlank() || venta.getProductos()== null) {	
			System.out.println("ERROR CAMPOS VACIOS");
			return "redirect:/venta";
		}

		Float total = (float) 0;
	    for (int i=0;i<venta.getProductos().size();i++) {
	    	
	        System.out.println(venta.getProductos().get(i).getPrecio());
	        total = venta.getProductos().get(i).getPrecio() + total; //suma los totales de cada producto agregado en el carro
	        
	      }
	    
	    model.addAttribute("total_venta", total); //total de la compra
		model.addAttribute("lista_compras", venta.getProductos()); //obtiene los productos comprados
		model.addAttribute("direccion_despacho", venta.getDireccion_envio()); //obtiene la direccion de despacho
	    
		Venta vent = vservice.insertarVenta(venta);
		return "resumen_venta.jsp";
	}

}
