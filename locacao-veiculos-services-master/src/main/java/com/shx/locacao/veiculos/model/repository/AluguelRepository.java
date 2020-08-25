package com.shx.locacao.veiculos.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shx.locacao.veiculos.model.entity.Aluguel;

@Repository
public interface AluguelRepository extends JpaRepository<Aluguel, Integer> {

    @Query("SELECT a FROM Aluguel a WHERE a.dataDevolucao IS NULL")
    List<Aluguel> findyByActiveAluguel();

    @Query("SELECT a FROM Aluguel a WHERE a.dataDevolucao IS NOT NULL")
    List<Aluguel> findyByFinishedAluguel();

    @Query("SELECT a FROM Aluguel a JOIN a.cliente c WHERE c.idCliente = :id")
    List<Aluguel> findyByAluguelCliente(@Param("id") Integer idCliente);

    @Query("SELECT a FROM Aluguel a JOIN a.cliente c WHERE a.dataDevolucao IS NULL AND c.idCliente = :id")
    List<Aluguel> findyByActiveAluguelCliente(@Param("id") Integer idCliente);

    @Query("SELECT a FROM Aluguel a JOIN a.cliente c WHERE a.dataDevolucao IS NOT NULL AND c.idCliente = :id")
    List<Aluguel> findyByFinishedAluguelCliente(@Param("id") Integer idCliente);
}
