package com.shx.locacao.veiculos.model.entity;

import lombok.Data;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "veiculo")
@Data
public class Veiculo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_veiculo")
    private Integer idVeiculo;

    @Column(name = "marca", nullable = false, length = 30)
    @NotEmpty(message = "{campo.marca.obrigatorio}")
    private String marca;

    @Column(name = "nome", nullable = false, length = 100)
    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "modelo")
    private Integer modelo;

    @Column(name = "combustivel")
    private String combustivel;

    @Column(name = "valor_diaria")
    @NotNull(message = "{campo.valordiaria.obrigatorio}")
    private BigDecimal valorDiaria;

    @Column(name = "status")
    private String status;
}
