package com.shx.locacao.veiculos.rest;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.shx.locacao.veiculos.model.entity.Aluguel;
import com.shx.locacao.veiculos.model.entity.Cliente;
import com.shx.locacao.veiculos.model.entity.Veiculo;
import com.shx.locacao.veiculos.model.repository.AluguelRepository;
import com.shx.locacao.veiculos.model.repository.ClienteRepository;
import com.shx.locacao.veiculos.model.repository.VeiculoRepository;
import com.shx.locacao.veiculos.rest.dto.AluguelDTO;

@RestController
@RequestMapping(path = "/alugueis")
@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class AluguelController {

    private final AluguelRepository aluguelRepository;
    private final ClienteRepository clienteRepository;
    private final VeiculoRepository veiculoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluguel salvar(@RequestBody @Valid AluguelDTO dto) {
        //Pega cliente e veiculo
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(dto.getIdVeiculo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veículo não encontrado"));

        LocalDate data = LocalDate.parse(dto.getDataLocacao(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        //Atualiza status do veículo para alugado
        veiculo.setStatus("Alugado");
        veiculoRepository.save(veiculo);

        Aluguel aluguel = new Aluguel();
        aluguel.setCliente(cliente);
        aluguel.setVeiculo(veiculo);
        aluguel.setDataLocacao(data);

        return aluguelRepository.save(aluguel);
    }

    @GetMapping
    public List<Aluguel> listar(@RequestParam(value = "filtro", required = false, defaultValue = "") String filtro,
                                @RequestParam(value = "idCliente", required = false, defaultValue = "") String idCliente) {
        if (idCliente.equals("")) {
            if (filtro.equals("")) {
                return aluguelRepository.findAll();
            } else if (filtro.equals("alugados")) {
                return aluguelRepository.findyByActiveAluguel();
            } else {
                return aluguelRepository.findyByFinishedAluguel();
            }
        } else {
            if (filtro.equals("")) {
                return aluguelRepository.findyByAluguelCliente(Integer.parseInt(idCliente));
            } else if (filtro.equals("alugados")) {
                return aluguelRepository.findyByActiveAluguelCliente(Integer.parseInt(idCliente));
            } else {
                return aluguelRepository.findyByFinishedAluguelCliente(Integer.parseInt(idCliente));
            }
        }
    }

    @GetMapping(path = "/{id}")
    public Aluguel acharPorId(@PathVariable Integer id) {
        return aluguelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluguel com o código especificado não encontrado"));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluguel devolver(@RequestBody @Valid AluguelDTO dto) {
        //Pega cliente e veiculo
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(dto.getIdVeiculo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veículo não encontrado"));

        LocalDate dataLocacao = LocalDate.parse(dto.getDataLocacao(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataDevolucao = LocalDate.parse(dto.getDataDevolucao(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        //Atualiza status do veículo para alugado
        veiculo.setStatus("Disponível");
        veiculoRepository.save(veiculo);

        Aluguel aluguel = new Aluguel();
        aluguel.setIdAluguel(dto.getIdAluguel());
        aluguel.setCliente(cliente);
        aluguel.setVeiculo(veiculo);
        aluguel.setDataLocacao(dataLocacao);
        aluguel.setDataDevolucao(dataDevolucao);
        aluguel.setValorAluguel(dto.getValorAluguel());

        return aluguelRepository.save(aluguel);
    }
}