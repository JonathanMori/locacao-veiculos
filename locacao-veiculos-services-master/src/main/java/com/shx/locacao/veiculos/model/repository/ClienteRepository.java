package com.shx.locacao.veiculos.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shx.locacao.veiculos.model.entity.Cliente;
import com.shx.locacao.veiculos.model.entity.Veiculo;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE c.status LIKE 'true'")
    List<Cliente> findyByActiveClients();
}
