package br.com.alura.loja.dao;

import br.com.alura.loja.models.Cliente;
import br.com.alura.loja.models.Pedido;

import javax.persistence.EntityManager;

public class ClienteDao {
    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

//    public void atualizar(Pedido pedido){
//        this.em.merge(pedido);
//    }
//    public void deletar(Pedido pedido){
//        pedido = em.merge(pedido);
//        this.em.remove(pedido);
//    }
//
    public Cliente buscarPorID(Long id){
        return em.find(Cliente.class, id);
    }
//
//    public List<Pedido> buscarTodos(){
//        String jpql = "SELECT p FROM Pedido p";
//        return  em.createQuery(jpql, Pedido.class).getResultList();
//    }
//    public List<Pedido> buscarPorNome(String nome){
//        String jpql = "SELECT p FROM Pedido p WHERE p.nome = :nome";
//        return  em.createQuery(jpql, Pedido.class)
//                .setParameter("nome", nome)
//                .getResultList();
//    }
//    public List<Pedido> buscarPorNomeCategoria(String nome){
//        String jpql = "SELECT p FROM Pedido p WHERE p.categoria.nome = :nome";
//        return  em.createQuery(jpql, Pedido.class)
//                .setParameterParameter("nome", nome)
//                .getResultList();
//    }
//
//    public BigDecimal buscarPrecoDoProdutoComNome(String nome){
//        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
//        return  em.createQuery(jpql, BigDecimal.class)
//                .setParameter("nome", nome)
//                .getSingleResult();
//    }
}
