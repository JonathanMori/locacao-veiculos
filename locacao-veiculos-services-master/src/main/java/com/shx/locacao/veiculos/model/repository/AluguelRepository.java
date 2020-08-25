package com.shx.locacao.veiculos.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.shx.locacao.veiculos.model.entity.Aluguel;
import com.shx.locacao.veiculos.rest.dao.AluguelDAO;

@Repository
public class AluguelRepository implements AluguelDAO {

    @PersistenceContext
    private EntityManager manager;



    public Aluguel inserir(Aluguel aluguel) {
        //Utilizei o merge() no lugar do persist() para obter o retorno do aluguel
        return manager.merge(aluguel);
    }

    public Aluguel atualizar(Aluguel aluguel) {
        return manager.merge(aluguel);
    }

    public List<Aluguel> listarTodos() {
        return manager.createQuery("SELECT a FROM Aluguel a").getResultList();
    }

    public Aluguel buscarPorId(Integer id) {
        return manager.find(Aluguel.class, id);
    }

    public List<Aluguel> buscarPorAluguelAtivo() {
        return manager.createQuery("SELECT a FROM Aluguel a WHERE a.dataDevolucao IS NULL").getResultList();
    }

    public List<Aluguel> buscarPorAluguelFinalizado() {
        return manager.createQuery("SELECT a FROM Aluguel a WHERE a.dataDevolucao IS NOT NULL").getResultList();
    }

    public List<Aluguel> buscarPorAluguelCliente(Integer idCliente) {
        return manager.createQuery("SELECT a FROM Aluguel a JOIN a.cliente c WHERE c.idCliente = :id").setParameter("id", idCliente).getResultList();
    }

    public List<Aluguel> buscarPorAluguelClienteAtivo(Integer idCliente) {
        return manager.createQuery("SELECT a FROM Aluguel a JOIN a.cliente c WHERE a.dataDevolucao IS NULL AND c.idCliente = :id").setParameter("id", idCliente).getResultList();
    }

    public List<Aluguel> buscarPorAluguelClienteFinalizado(Integer idCliente) {
        return manager.createQuery("SELECT a FROM Aluguel a JOIN a.cliente c WHERE a.dataDevolucao IS NOT NULL AND c.idCliente = :id").setParameter("id", idCliente).getResultList();
    }
}
