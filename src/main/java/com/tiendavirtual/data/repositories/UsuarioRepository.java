package com.tiendavirtual.data.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.tiendavirtual.data.models.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	List<Usuario> findAll();
	Usuario findByEmail(String email);
	

	
}


