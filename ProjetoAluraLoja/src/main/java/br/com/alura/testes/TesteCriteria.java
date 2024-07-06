package br.com.alura.testes;

import br.com.alura.dao.CategoriaDao;
import br.com.alura.dao.ClienteDao;
import br.com.alura.dao.PedidoDao;
import br.com.alura.dao.ProdutoDao;
import br.com.alura.modelo.*;
import br.com.alura.util.JPAUtil;
import br.com.alura.vo.RelatorioDeVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TesteCriteria {

    public static void main(String[] args) {
        popularBancoDeDados();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        produtoDao.buscarPorParametrosComCriteria(null,null, LocalDate.now());

    }

    private static void popularBancoDeDados() {
        Categoria celulares = new Categoria("CELULARES","smartphones");
        Categoria videogames = new Categoria("VIDEOGAMES", "consoles");
        Categoria informatica = new Categoria("INFORMATICA","computadores");

        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Produto videogame = new Produto("PS5", "Playstation 5", new BigDecimal("8000"), videogames);
        Produto macbook = new Produto("Macbook", "Macboo pro retina", new BigDecimal("14000"), informatica);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        categoriaDao.cadastrar(videogames);
        categoriaDao.cadastrar(informatica);

        produtoDao.cadastrar(celular);
        produtoDao.cadastrar(videogame);
        produtoDao.cadastrar(macbook);

        em.getTransaction().commit();
        em.close();
    }

}
