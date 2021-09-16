package com.tiendavirtual.data.controllers;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tiendavirtual.data.models.Producto;
import com.tiendavirtual.data.models.Venta;
import com.tiendavirtual.data.services.VentaService;
@Controller
@RequestMapping("/venta")
public class VentaController {
    private final VentaService vservice;
	public VentaController(VentaService ventaService) {
		this.vservice = ventaService;
	}
	
	@RequestMapping("")
	public String indexUsuario(@ModelAttribute("venta") Venta venta,Model model ) {
		List<Venta> lista_ventas = vservice.findAll();
		model.addAttribute("lista_ventas", lista_ventas);
		
		return "venta.jsp";
	}
	
	@RequestMapping(value="/crear", method = RequestMethod.POST)
	public String crearUsuario(@Valid @ModelAttribute("venta") Venta venta, Model model) {
		
		if(venta.getId_usuario().isBlank() || venta.getId_producto().isBlank() || venta.getDireccion_envio().isBlank() || venta.getCantidad() == null || venta.getTotal()== null ) {
			model.addAttribute("error", "Todos los campos son requeridos!");
			List<Venta> lista_venta = vservice.findAll();
			model.addAttribute("lista_ventas", lista_venta);
			return "venta.jsp";
		}

		Venta vent = vservice.insertarVenta(venta);
		return "redirect:/venta";
	}
	
	@RequestMapping(value="/actualizar/{id}", method = RequestMethod.GET)
	public String actualizarVenta(@PathVariable("id") Long id, Model model) {
		Venta venta = vservice.buscarVenta(id);
		
		model.addAttribute("venta", venta);
		return "editar_venta.jsp";
	}
	
	@RequestMapping(value="/modificar",method= RequestMethod.PUT)
	public String modificar(@Valid @ModelAttribute("venta") Venta venta) {
		
		System.out.println("el Id a modificar es: "+ venta.getId());
		vservice.modificarVenta(venta);
		
		return "redirect:/venta";
	}
	
	@RequestMapping(value="/eliminar/{id}", method = RequestMethod.DELETE)
	public String eliminar(@PathVariable("id") Long id) {
		System.out.println("Eliminar id: "+ id);
		vservice.eliminarVenta(id);
		
		return "redirect:/venta";
	}
}
