package com.shx.locacao.veiculos.rest;

import java.util.List;

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
import com.shx.locacao.veiculos.model.repository.VeiculoRepository;

@RestController
@RequestMapping(path = "/veiculos")
@CrossOrigin("http://localhost:4200")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository;

    @Autowired
    public VeiculoController(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo salvar(@RequestBody @Valid Veiculo veiculo) {
        veiculo.setStatus("Disponível");
        return veiculoRepository.save(veiculo);
    }

    @GetMapping
    public List<Veiculo> acharTodos() {
        return veiculoRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Veiculo acharPorId(@PathVariable Integer id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @RequestBody Veiculo veiculoNovo) {
        veiculoRepository.findById(id)
                .map(veiculo -> {
                    veiculoNovo.setIdVeiculo(veiculo.getIdVeiculo());
                    return veiculoRepository.save(veiculoNovo);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        veiculoRepository.findById(id)
                .map(veiculo -> {
                    veiculoRepository.delete(veiculo);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));
    }

    @GetMapping(path = "/disponiveis")
    public List<Veiculo> acharTodosDisponiveis() {
        return veiculoRepository.findyByAvailableVeiculos();
    }
}
