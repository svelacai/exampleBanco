package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Usuario;
import com.example.demo.respository.BancoRespository;
import com.example.demo.respository.UsuarioRespository;

@Service
public class BancoService {

	private static final String AES_ALGORITHM = "AES";
	private static final String ENCRYPTION_KEY = "YourEncryptionKey"; // Clave de cifrado

	@Autowired
	BancoRespository bancoRespository;

	@Autowired
	UsuarioRespository usuarioRespository;

	public Optional<Cliente> consultaClientes(Cliente cliente) {

		Optional<Cliente> consulta = bancoRespository.findById(cliente.id);
		return consulta;

	}

	public Cliente consultaClientesPorcedula(Cliente cliente) {

		Cliente consulta = bancoRespository.getCliente(cliente.numeroDocumento);
		return consulta;

	}

	public List<Cliente> getClientes() {

		List<Cliente> consulta = bancoRespository.getClientes();
		return consulta;

	}

	public static String encrypt(String plaintext) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8), AES_ALGORITHM);
			Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes(StandardCharsets.UTF_8));
			return Base64.getEncoder().encodeToString(encryptedBytes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decrypt(String ciphertext) {
		try {
			SecretKeySpec secretKey = new SecretKeySpec(ENCRYPTION_KEY.getBytes(StandardCharsets.UTF_8), AES_ALGORITHM);
			Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(ciphertext));
			return new String(decryptedBytes, StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Usuario createUsuario(Usuario usuario) {

		String name = encrypt(usuario.getUsername());
		String pass = encrypt(usuario.getPassword());
		Usuario usuario2 = new Usuario();
		usuario2.setUsername(name);
		usuario2.setPassword(pass);
		Usuario create = usuarioRespository.save(usuario2);
		return create;

	}

}
