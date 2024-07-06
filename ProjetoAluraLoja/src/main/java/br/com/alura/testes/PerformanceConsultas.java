package br.com.alura.testes;

import br.com.alura.dao.CategoriaDao;
import br.com.alura.dao.ClienteDao;
import br.com.alura.dao.PedidoDao;
import br.com.alura.dao.ProdutoDao;
import br.com.alura.modelo.Categoria;
import br.com.alura.modelo.Cliente;
import br.com.alura.modelo.Pedido;
import br.com.alura.modelo.Produto;
import br.com.alura.util.JPAUtil;

import javax.persistence.EntityManager;

import java.math.BigDecimal;


public class PerformanceConsultas {

    public static void main(String[] args){
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDao pedidoDao = new PedidoDao(em);
        Pedido pedido = pedidoDao.buscarPedidoComCliente(1l);
        em.close();
        //Carregamento lento: Lazy
        System.out.println(pedido.getCliente().getNome());
        //Carregamento antecipado: Eager
        //System.out.println(pedido.getData());
        //sempre em relacionamento ManytoOne e OnetoOne, ele carrega as relações da entidade, sobrecarregandoa consulta. Exemplo de saída:
        //left outer join
        //        clientes cliente1_
        //            on pedido0_.cliente_id=cliente1_.id
        //OnetoMany não é carregada automaticamente.
    }

    private static void popularBancoDeDados(){
        Categoria celulares = new Categoria("CELULARES","smartphones");
        Categoria videogames = new Categoria("VIDEOGAMES", "consoles");
        Categoria informatica = new Categoria("INFORMATICA","computadores");

        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
        Produto macbook = new Produto("Macbook", "Macboo pro retina", new BigDecimal("14000"), informatica);

        Cliente cliente = new Cliente("Rodrigo", "123456");

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);

        clienteDao.cadastrar(cliente);

        em.getTransaction().commit();
        em.close();
    }
}
