package com.tiendavirtual.data.controllers;





import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tiendavirtual.data.models.Producto;
import com.tiendavirtual.data.models.Usuario;
import com.tiendavirtual.data.models.Venta;
import com.tiendavirtual.data.services.ProductoService;
import com.tiendavirtual.data.services.UsuarioService;
import com.tiendavirtual.data.services.VentaService;



@Controller

public class IndexController {
	
	@Autowired
	private UsuarioService usuarioservice;
	
	@Autowired
	private VentaService ventaservice;
	
	@Autowired
	private ProductoService pservice;
	
	@RequestMapping("")//Index ==> http://localhost:8080/
	public String indexUsuario(@ModelAttribute("usuario") Usuario usuario, HttpSession session, Model model) {
		if(session.getAttribute("userId")!=null) {
			
			
			return "redirect:/usuario/dashboard"; //si hay sesión activa redirige al dashboard del usuario
		}
		List<Venta> lista_ventas = ventaservice.findAll();
		model.addAttribute("lista_ventas", lista_ventas);
		
		List<Producto> lista_productos = pservice.findAll();
		model.addAttribute("lista_productos", lista_productos);
		
		
		
		System.out.println("Index login");
		return "index.jsp";  //Si no hay sesión activa envía al index 
		
		
				
	}

}