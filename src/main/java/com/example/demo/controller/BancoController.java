package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Usuario;
import com.example.demo.service.BancoService;

@RestController
@RequestMapping("/banco")
public class BancoController {

	@Autowired
	BancoService bancoService;

	@GetMapping("/getCliente")
	public ResponseEntity<?> consultar(@RequestBody Cliente cliente) {
		Optional<Cliente> consulta = bancoService.consultaClientes(cliente);
		if (consulta.isEmpty() || !consulta.isPresent()) {
			String messager = "registro no encontrado";
			return new ResponseEntity<>(messager, HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>(consulta, HttpStatus.OK);
	}

	@GetMapping("/getClienteCedula")
	public ResponseEntity<?> consultarCedula(@RequestBody Cliente cliente) {
		Cliente consulta = bancoService.consultaClientesPorcedula(cliente);
		if (consulta == null) {
			String messager = "registro no encontrado";
			return new ResponseEntity<>(messager, HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<>(consulta, HttpStatus.OK);
	}

	@GetMapping("/consul")
	public ResponseEntity<?> consultarCedula() {
		List<Cliente> consulta = bancoService.getClientes();

		return new ResponseEntity<>(consulta, HttpStatus.OK);
	}

	@PostMapping("/crearUsuario")
	public ResponseEntity<?> crearUsuario(Usuario usuario) {
		if (usuario.getUsername() == null || usuario.getUsername().isBlank()) {
			String messager = "Datos vacios o en null";
			return new ResponseEntity<>(messager, HttpStatus.BAD_REQUEST);

		}
		Usuario response = bancoService.createUsuario(usuario);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
