package com.shx.locacao.veiculos.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;
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
import com.shx.locacao.veiculos.rest.dao.AluguelDAO;
import com.shx.locacao.veiculos.rest.dao.ClienteDAO;
import com.shx.locacao.veiculos.rest.dao.VeiculoDAO;
import com.shx.locacao.veiculos.rest.dto.AluguelDTO;


@RestController
@RequestMapping(path = "/alugueis")
@CrossOrigin("http://localhost:4200")
@Transactional
public class AluguelController {

    private final AluguelDAO aluguelDAO;
    private final ClienteDAO clienteDAO;
    private final VeiculoDAO veiculoDAO;

    public AluguelController(AluguelDAO aluguelDAO, ClienteDAO clienteDAO, VeiculoDAO veiculoDAO) {
        this.aluguelDAO = aluguelDAO;
        this.clienteDAO = clienteDAO;
        this.veiculoDAO = veiculoDAO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluguel inserir(@RequestBody @Valid AluguelDTO dto) {
        //Pega cliente e veiculo
        Cliente cliente = clienteDAO.buscarPorId(dto.getIdCliente());
        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado");
        }

        Veiculo veiculo = veiculoDAO.buscarPorId(dto.getIdVeiculo());
        if (veiculo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veículo não encontrado");
        }

        LocalDate data = LocalDate.parse(dto.getDataLocacao(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        //Atualiza status do veículo para alugado
        veiculo.setStatus("Alugado");
        veiculoDAO.atualizar(veiculo);

        Aluguel aluguel = new Aluguel();
        aluguel.setCliente(cliente);
        aluguel.setVeiculo(veiculo);
        aluguel.setDataLocacao(data);

        return aluguelDAO.inserir(aluguel);
    }

    @GetMapping
    public List<Aluguel> filtrar(@RequestParam(value = "filtro", required = false, defaultValue = "") String filtro,
                                @RequestParam(value = "idCliente", required = false, defaultValue = "") String idCliente) {
        if (idCliente.equals("")) {
            if (filtro.equals("")) {
                return aluguelDAO.listarTodos();
            } else if (filtro.equals("alugados")) {
                return aluguelDAO.buscarPorAluguelAtivo();
            } else {
                return aluguelDAO.buscarPorAluguelFinalizado();
            }
        } else {
            if (filtro.equals("")) {
                return aluguelDAO.buscarPorAluguelCliente(Integer.parseInt(idCliente));
            } else if (filtro.equals("alugados")) {
                return aluguelDAO.buscarPorAluguelClienteAtivo(Integer.parseInt(idCliente));
            } else {
                return aluguelDAO.buscarPorAluguelClienteFinalizado(Integer.parseInt(idCliente));
            }
        }
    }

    @GetMapping(path = "/{id}")
    public Aluguel buscarPorId(@PathVariable Integer id) {
        Aluguel aluguel = aluguelDAO.buscarPorId(id);
        if (aluguel != null) {
            return aluguel;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluguel com o código especificado não encontrado");
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Aluguel devolver(@RequestBody @Valid AluguelDTO dto) {
        //Pega cliente e veiculo
        Cliente cliente = clienteDAO.buscarPorId(dto.getIdCliente());
        if (cliente == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente não encontrado");
        }

        Veiculo veiculo = veiculoDAO.buscarPorId(dto.getIdVeiculo());
        if (veiculo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Veículo não encontrado");
        }

        LocalDate dataLocacao = LocalDate.parse(dto.getDataLocacao(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate dataDevolucao = LocalDate.parse(dto.getDataDevolucao(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        //Atualiza status do veículo para alugado
        veiculo.setStatus("Disponível");
        veiculoDAO.atualizar(veiculo);

        Aluguel aluguel = new Aluguel();
        aluguel.setIdAluguel(dto.getIdAluguel());
        aluguel.setCliente(cliente);
        aluguel.setVeiculo(veiculo);
        aluguel.setDataLocacao(dataLocacao);
        aluguel.setDataDevolucao(dataDevolucao);
        aluguel.setValorAluguel(dto.getValorAluguel());

        return aluguelDAO.atualizar(aluguel);
    }
}