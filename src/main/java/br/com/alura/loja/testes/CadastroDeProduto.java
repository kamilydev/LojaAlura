package br.com.alura.loja.testes;
import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.models.Categoria;
import br.com.alura.loja.models.Produto;
import br.com.alura.loja.util.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        Long id = 1l;

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto p = produtoDao.buscarPorID(1l);
        //System.out.println(p.getPreco());

        //List<Produto> todos = produtoDao.buscarTodos();
        //todos.forEach(p2 -> System.out.println(p.getNome()));

        //List<Produto> todos = produtoDao.buscarPorNomeCategoria("CELULARES");
        //todos.forEach(p2 -> System.out.println(p.getNome()));

        BigDecimal precoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
        System.out.println("Preco do produto" + precoProduto);
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES");

        Produto celular = new Produto(
                "Xiaomi Redmi",
                "Muito legal",
                new BigDecimal("800"),
                celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.getTransaction().commit();
        em.close();
    }
}