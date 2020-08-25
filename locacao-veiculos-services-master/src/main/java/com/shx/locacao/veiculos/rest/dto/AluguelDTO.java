package com.shx.locacao.veiculos.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class AluguelDTO {

    private Integer idAluguel;

    private Integer idCliente;

    private Integer idVeiculo;

    @NotEmpty(message = "{campo.data.obrigatorio}")
    private String dataLocacao;

    private String dataDevolucao;

    private BigDecimal valorAluguel;
}
