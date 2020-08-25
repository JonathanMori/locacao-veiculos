package com.shx.locacao.veiculos.model.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.shx.locacao.veiculos.model.entity.Cliente;
import com.shx.locacao.veiculos.rest.dao.ClienteDAO;

@Repository
public class ClienteRepository implements ClienteDAO {

    @PersistenceContext
    private EntityManager manager;



    public Cliente inserir(Cliente cliente) {
        //Utilizei o merge() no lugar do persist() para obter o retorno do cliente
        return manager.merge(cliente);
    }

    public Cliente atualizar(Cliente cliente) {
        return manager.merge(cliente);
    }

    public void remover(Cliente cliente) {
        manager.remove(cliente);
    }

    public List<Cliente> listarTodos() {
        return manager.createQuery("SELECT c FROM Cliente c").getResultList();
    }

    public Cliente buscarPorId(Integer id) {
        return manager.find(Cliente.class, id);
    }

    public List<Cliente> buscarPorClientesAtivos() {
        return manager.createQuery("SELECT c FROM Cliente c WHERE c.status = true").getResultList();
    }

}
