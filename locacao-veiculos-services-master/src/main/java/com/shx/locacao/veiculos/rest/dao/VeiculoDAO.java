package com.shx.locacao.veiculos.rest.dao;

import java.util.List;

import com.shx.locacao.veiculos.model.entity.Cliente;
import com.shx.locacao.veiculos.model.entity.Veiculo;

public interface VeiculoDAO {

    Veiculo inserir(Veiculo veiculo);

    Veiculo atualizar(Veiculo veiculo);

    void remover(Veiculo veiculo);

    List<Veiculo> listarTodos();

    Veiculo buscarPorId(Integer id);

    List<Veiculo> buscarPorVeiculosDisponiveis();
}
