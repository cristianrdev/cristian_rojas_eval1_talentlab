package com.tiendavirtual.data.controllers;

import javax.validation.Valid;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


import com.tiendavirtual.data.models.Usuario;
import com.tiendavirtual.data.services.UsuarioService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
   
    private final UsuarioService uservice;
	public UsuarioController(UsuarioService usuarioService) {
		this.uservice = usuarioService;
	}
	
	@RequestMapping("")
	public String indexUsuario(@ModelAttribute("usuario") Usuario usuario,Model model ) {
		List<Usuario> lista_usuarios = uservice.findAll();
		model.addAttribute("lista_usuarios", lista_usuarios);
		
		return "usuario.jsp";
	}
	
	@RequestMapping(value="/crear", method = RequestMethod.POST)
	public String crearUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, Model model) {
		if(usuario.getNombre().isBlank() || usuario.getApellido().isBlank() || usuario.getEmail().isBlank()) {
			model.addAttribute("error", "Todos los campos son requeridos!");
			List<Usuario> lista_usuario = uservice.findAll();
			model.addAttribute("lista_usuarios", lista_usuario);
			return "usuario.jsp";
		}
		System.out.println("crear "+ usuario.getNombre() + " " + usuario.getApellido() + " " + usuario.getEmail());
		Usuario user = uservice.insertarUsuario(usuario);
		return "redirect:/usuario";
	}
	
	@RequestMapping(value="/actualizar/{id}", method = RequestMethod.GET)
	public String actualizarUsuario(@PathVariable("id") Long id, Model model) {
		Usuario usuario = uservice.buscarUsuario(id);
		
		model.addAttribute("usuario", usuario);
		return "editar_usuario.jsp";
	}
	
	@RequestMapping(value="/modificar",method= RequestMethod.PUT)
	public String modificar(@Valid @ModelAttribute("usuario") Usuario usuario) {
		
		System.out.println("el Id a modificar es: "+usuario.getId());
		uservice.modificarUsuario(usuario);
		
		return "redirect:/usuario";
	}
	
	@RequestMapping(value="/eliminar/{id}", method = RequestMethod.DELETE)
	public String eliminar(@PathVariable("id") Long id) {
		System.out.println("Eliminar id: "+ id);
		uservice.eliminarUsuario(id);
		
		return "redirect:/usuario";
	}
	
}
