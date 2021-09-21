package com.tiendavirtual.data.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.tiendavirtual.data.models.Usuario;
import com.tiendavirtual.data.repositories.UsuarioRepository;


@Service
public class UsuarioService {


	@Autowired
	UsuarioRepository urepository;
	
		
	public void insertarUsuario(@Valid Usuario usuario) {
		// hash password
        String hashed = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
        usuario.setPassword(hashed);
        urepository.save(usuario);
	}

	
	public List<Usuario> findAll() {
		// retorna una lista de usuarios
		return urepository.findAll();
	}
	
	public void eliminarUsuario(Long id) {
		urepository.deleteById(id);
	}
	
	public Usuario buscarUsuario(Long id) {

		Optional<Usuario> oUsuario= urepository.findById(id);
		
		if(oUsuario.isPresent()) {
			return oUsuario.get();
		}
		
		return null;
	}
	
	public void modificarUsuario(@Valid Usuario empleado) {
		urepository.save(empleado);
		
	}

	public boolean validarUsuario(String email, String password) {
		Usuario usuario = urepository.findByEmail(email);
		//siempre validar si es null
		if(usuario == null) {
			return false;
		}else {
			//comparar los password
			if (BCrypt.checkpw(password, usuario.getPassword())) {
			    System.out.println("Password iguales");
				return true;
				
			}else {
			    System.out.println("Password Distintos");
			    return false;
			}
		}
		
	}


	public Usuario findByEmail(String email) {
		return urepository.findByEmail(email);
	}



	




	
}