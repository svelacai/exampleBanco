package com.example.demo.respository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.entity.Usuario;

public interface UsuarioRespository  extends CrudRepository<Usuario, Integer> {

}
