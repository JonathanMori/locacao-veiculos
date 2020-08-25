package com.shx.locacao.veiculos.model.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "aluguel")
@Data
public class Aluguel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_aluguel")
    private Integer idAluguel;

    @ManyToOne
    @JoinColumn(name = "cliente_id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "veiculo_id_veiculo")
    private Veiculo veiculo;

    @Column(name = "data_locacao", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataLocacao;

    @Column(name = "data_devolucao")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDevolucao;

    @Column(name = "valor_aluguel")
    private BigDecimal valorAluguel;
}
