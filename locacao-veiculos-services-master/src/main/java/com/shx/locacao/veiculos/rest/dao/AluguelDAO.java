package com.shx.locacao.veiculos.rest.dao;

import java.util.List;

import com.shx.locacao.veiculos.model.entity.Aluguel;
import com.shx.locacao.veiculos.model.entity.Cliente;

public interface AluguelDAO {

    Aluguel inserir(Aluguel aluguel);

    Aluguel atualizar(Aluguel aluguel);

    List<Aluguel> listarTodos();

    Aluguel buscarPorId(Integer id);

    List<Aluguel> buscarPorAluguelAtivo();

    List<Aluguel> buscarPorAluguelFinalizado();

    List<Aluguel> buscarPorAluguelCliente(Integer idCliente);

    List<Aluguel> buscarPorAluguelClienteAtivo(Integer idCliente);

    List<Aluguel> buscarPorAluguelClienteFinalizado(Integer idCliente);
}
