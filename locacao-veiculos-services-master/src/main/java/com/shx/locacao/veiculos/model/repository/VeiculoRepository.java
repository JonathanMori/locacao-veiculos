package com.shx.locacao.veiculos.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shx.locacao.veiculos.model.entity.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {

    @Query("SELECT v FROM Veiculo v WHERE v.status LIKE 'Disponível'")
    List<Veiculo> findyByAvailableVeiculos();
}
