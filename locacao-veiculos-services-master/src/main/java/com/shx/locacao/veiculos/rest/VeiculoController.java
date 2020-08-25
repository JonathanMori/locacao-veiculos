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

import com.shx.locacao.veiculos.model.entity.Veiculo;
import com.shx.locacao.veiculos.rest.dao.VeiculoDAO;

@RestController
@RequestMapping(path = "/veiculos")
@CrossOrigin("http://localhost:4200")
@Transactional
public class VeiculoController {

    private final VeiculoDAO veiculoDAO;

    @Autowired
    public VeiculoController(VeiculoDAO veiculoDAO) {
        this.veiculoDAO = veiculoDAO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo inserir(@RequestBody @Valid Veiculo veiculo) {
        veiculo.setStatus("Disponível");
        return veiculoDAO.inserir(veiculo);
    }

    @GetMapping
    public List<Veiculo> listarTodos() {
        return veiculoDAO.listarTodos();
    }

    @GetMapping(path = "/{id}")
    public Veiculo buscarPorId(@PathVariable Integer id) {
        Veiculo veiculo = veiculoDAO.buscarPorId(id);
        if (veiculo != null) {
            return veiculo;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado");
        }
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Veiculo atualizar(@PathVariable Integer id, @RequestBody Veiculo veiculoNovo) {
        Veiculo veiculo = veiculoDAO.buscarPorId(id);
        if (veiculo != null) {
            veiculoNovo.setIdVeiculo(veiculo.getIdVeiculo());
            return veiculoDAO.atualizar(veiculoNovo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado");
        }
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer id) {
        Veiculo veiculo = veiculoDAO.buscarPorId(id);
        if (veiculo != null) {
            veiculoDAO.remover(veiculo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado");
        }
    }

    @GetMapping(path = "/disponiveis")
    public List<Veiculo> buscarPorVeiculosDisponiveis() {
        return veiculoDAO.buscarPorVeiculosDisponiveis();
    }
}
