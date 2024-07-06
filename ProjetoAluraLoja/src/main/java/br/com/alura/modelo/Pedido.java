package br.com.alura.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;
    private LocalDate data = LocalDate.now();

    //carregamento "rápido" - Eager
    //carregamento apenas se houver o acesso - LAZY
    //Gera efeito colateral
    @ManyToOne (fetch = FetchType.LAZY)
    private br.com.alura.modelo.Cliente cliente;

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<br.com.alura.modelo.ItemPedido> itens = new ArrayList<>();

    public Pedido() {
    }

    public Pedido(br.com.alura.modelo.Cliente cliente) {
        this.cliente = cliente;
    }

    public void adicionarItem(br.com.alura.modelo.ItemPedido item) {
        item.setPedido(this);
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public br.com.alura.modelo.Cliente getCliente() {
        return cliente;
    }

    public void setCliente(br.com.alura.modelo.Cliente cliente) {
        this.cliente = cliente;
    }

}
