package com.shx.locacao.veiculos.rest.dao;

import java.util.List;

import com.shx.locacao.veiculos.model.entity.Cliente;

public interface ClienteDAO {

    Cliente inserir(Cliente cliente);

    Cliente atualizar(Cliente cliente);

    void remover(Cliente cliente);

    List<Cliente> listarTodos();

    Cliente buscarPorId(Integer id);

    List<Cliente> buscarPorClientesAtivos();
}
