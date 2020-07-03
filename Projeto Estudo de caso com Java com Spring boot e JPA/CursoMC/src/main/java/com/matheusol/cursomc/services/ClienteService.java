package com.matheusol.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusol.cursomc.domain.Cliente;
import com.matheusol.cursomc.exceptions.ObjectNotFoundException;
import com.matheusol.cursomc.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
}
