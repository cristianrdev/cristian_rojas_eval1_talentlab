package com.tiendavirtual.data.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.tiendavirtual.data.models.Producto;
import com.tiendavirtual.data.models.Usuario;
import com.tiendavirtual.data.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
   
	@Autowired
    UsuarioService uservice;
			
	@RequestMapping("/registro")//Renderiza formulario de registro
	public String registrar(@Valid @ModelAttribute("usuario") Usuario usuario) {

		return "registro.jsp";
	}
	
	
	@RequestMapping(value="/crear", method = RequestMethod.POST) //Crear usuario nuevo a través de registro.jsp
	public String crearUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, Model model, HttpSession session) {
		//validacion campo vacío
		if(usuario.getNombre().isBlank() || usuario.getApellido().isBlank() || usuario.getEmail().isBlank() || usuario.getPassword().isBlank() || usuario.getPasswordConfirmation().isBlank()){
			model.addAttribute("error", "Todos los campos son requeridos!");
			return "registro.jsp";
		}
		//validacion formato email
		if(usuario.getEmail().matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b") == false){
			model.addAttribute("error", "el formato de email debe ser válido!");
			return "registro.jsp";
		}
		//Compara la contraseña con la contraseña de confirmación
		if(usuario.getPassword().equals(usuario.getPasswordConfirmation())==false) {
			model.addAttribute("error", "la contraseña de confirmación es distinta!");
			return "registro.jsp";
		}
		//Confirma si el email no existe previamente
		System.out.println(uservice.findByEmail(usuario.getEmail()));
		if(uservice.findByEmail(usuario.getEmail())!=null) {
			model.addAttribute("error", "ya existe este email!");
			return "registro.jsp";
		}
		
		uservice.insertarUsuario(usuario);//guarda al usuario con todas las validaciones aprobadas
		session.setAttribute("userId", usuario.getId());//Crea la variable de sesión
		System.out.println("USER ID==>" +  session.getAttribute("userId"));
		System.out.println("Usuario creado "+ usuario.getNombre() + " " + usuario.getApellido() + " " + usuario.getEmail());
		return "redirect:/usuario/dashboard"; //redirige al dashboard del usuario
	}
	
	
	@RequestMapping("/dashboard") //Redirige al dashboard del usuario SI existe la sesión
	public String dashboardUsuario(HttpSession session, Model model) {
		System.out.println("DASHBOARD USUARIO POR ACA");
		if(session.getAttribute("userId")!=null) {
			System.out.println("HAY SESION ACTIVA");
			
			return "redirect:/venta"; //si hay sesión activa renderiza redirige a /venta
		}
		System.out.println("NO HAY SESION ACTIVA");
		return "redirect:/";  //Si no hay sesión activa envía al index 
	}

	
	@RequestMapping(value="/ingresar", method = RequestMethod.POST) //Recibe datos del login y valida los datos
	public String loginUsuario(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
		
		if(password.isBlank() || email.isBlank()) { //campos vacíos?
			System.out.println("CAMPOS VACIOS");
			model.addAttribute("errorlogin", "hay campos vacíos!");
			return "redirect:/"; //si hay campos vacios redirige al index
		}
		
		boolean existeusuario = uservice.validarUsuario(email, password);
		
		if(existeusuario) {
			Usuario usuario = uservice.findByEmail(email);
			//Guardando un elemento en session
			session.setAttribute("userId", usuario.getId());
			System.out.println("DATOS LOGIN CORRECTOS");
			return "redirect:/usuario/dashboard"; //Si existe usuario envia al dashboard del usuario
		}
		model.addAttribute("errorlogin", "datos de login incorrectos!");
		return "redirect:/"; //Si NO existe redirije al index
		
	}
	
	
	@RequestMapping(value="/actualizar/{id}", method = RequestMethod.GET) //Actualiza los datos del usuario
	public String actualizarUsuario(@PathVariable("id") Long id, Model model, HttpSession session) {
		Usuario usuario = uservice.buscarUsuario(id);
		if(session.getAttribute("userId")!=null & session.getAttribute("userId").equals(id)) { //evitamos modificar datos de otros desde la url
			model.addAttribute("usuario", usuario);
			return "editar_usuario.jsp";
		}
		return "redirect:/";  //Si no hay sesión activa envía al index 
	}
	
	@RequestMapping(value="/modificar",method= RequestMethod.PUT)//guarda las modificaciones de datos de usuario
	public String modificar(@Valid @ModelAttribute("usuario") Usuario usuario) {
		uservice.modificarUsuario(usuario);		
		return "redirect:/usuario/dashboard";
	}
	


	@RequestMapping("/logout")//Elimina la variable de sesión en el navegador
	public String logout(HttpSession session) {
		if(session.getAttribute("userId")!=null) {
			session.invalidate();//matamos todas las variables de session
		}
		return "redirect:/";
	}
	
}
