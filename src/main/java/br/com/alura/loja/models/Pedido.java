package br.com.alura.loja.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    private LocalDate data = LocalDate.now();

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL) //mappedby deve ser utilizado no lado inverso, do lado que relacionamento que tem to Many
    private List<ItemPedido> itens = new ArrayList<>(); //lista instanciada e vazia

    // Esse mapeamento não funciona, pois necessita de atributos como quantidade e preco unitário
    //@ManyToMany
    //@JoinTable(name = "itens_pedidos")
    //private List<Produto> produtos;

    public Pedido(Cliente cliente) {
        this.valorTotal = BigDecimal.ZERO;
        this.data = LocalDate.now();
        this.cliente = cliente;

    }

    //método para vincular itens ao pedido
    public void adicionarItem(ItemPedido item){
        item.setPedido(this);
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }

    public Pedido() {
    }

    public Long getId() {
        return id;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
