package com.shx.locacao.veiculos.rest;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shx.locacao.veiculos.model.entity.Cliente;
import com.shx.locacao.veiculos.rest.dao.ClienteDAO;

@RestController
@RequestMapping(path = "/clientes")
@CrossOrigin("http://localhost:4200")
@Transactional
public class ClienteController {

    private final ClienteDAO clienteDAO;

    @Autowired
    public ClienteController(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente inserir(@RequestBody @Valid Cliente cliente) {
        return clienteDAO.inserir(cliente);
    }

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteDAO.listarTodos();
    }

    @GetMapping(path = "/{id}")
    public Cliente buscarPorId(@PathVariable Integer id) {
        Cliente cliente = clienteDAO.buscarPorId(id);
        if (cliente != null) {
            return cliente;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente atualizar(@PathVariable Integer id, @RequestBody Cliente clienteNovo) {
        Cliente cliente = clienteDAO.buscarPorId(id);
        if (cliente != null) {
            clienteNovo.setIdCliente(cliente.getIdCliente());
            return clienteDAO.atualizar(clienteNovo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer id) {
        Cliente cliente = clienteDAO.buscarPorId(id);
        if (cliente != null) {
            clienteDAO.remover(cliente);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
    }


    @GetMapping(path = "/ativos")
    public List<Cliente> buscarPorClientesAtivos() {
        return clienteDAO.buscarPorClientesAtivos();
    }
}
