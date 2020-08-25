package com.shx.locacao.veiculos.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.shx.locacao.veiculos.model.entity.Veiculo;
import com.shx.locacao.veiculos.rest.dao.VeiculoDAO;

@Repository
public class VeiculoRepository implements VeiculoDAO {

    @PersistenceContext
    private EntityManager manager;



    public Veiculo inserir(Veiculo veiculo) {
        //Utilizei o merge() no lugar do persist() para obter o retorno do veiculo
        return manager.merge(veiculo);
    }

    public Veiculo atualizar(Veiculo veiculo) {
        return manager.merge(veiculo);
    }

    public void remover(Veiculo veiculo) {
        manager.remove(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return manager.createQuery("SELECT v FROM Veiculo v").getResultList();
    }

    public Veiculo buscarPorId(Integer id) {
        return manager.find(Veiculo.class, id);
    }

    public List<Veiculo> buscarPorVeiculosDisponiveis() {
        return manager.createQuery("SELECT v FROM Veiculo v WHERE v.status LIKE 'Disponível'").getResultList();
    }

}
