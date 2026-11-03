package com.baozistore.demo.controller;
import com.baozistore.demo.model.Cliente;
import com.baozistore.demo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos os clientes (GET)
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Buscar cliente por ID (GET)
    @GetMapping("/{id}")
    public Optional<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteRepository.findById(id);
    }

    // Criar novo cliente (POST)
    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Atualizar cliente existente (PUT)
    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(clienteAtualizado.getNome());
            cliente.setEmail(clienteAtualizado.getEmail());
            cliente.setTelefone(clienteAtualizado.getTelefone());
            return clienteRepository.save(cliente);
        }).orElseGet(() -> {
            clienteAtualizado.setId(id);
            return clienteRepository.save(clienteAtualizado);
        });
    }

    // Apagar cliente (DELETE)
    @DeleteMapping("/{id}")
    public void apagarCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}