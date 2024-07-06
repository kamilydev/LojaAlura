package br.com.alura.modelo;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @EmbeddedId
    private CategoriaId id;

    public Categoria() {
    }

    public Categoria(String tipo, String nome) {
        this.id = new CategoriaId(tipo, nome);
    }

    public CategoriaId getId() {
        return id;
    }

    public String getNome() {
        return id.getNome();
    }

    public String getTipo() {
        return id.getTipo();
    }

    public void setNome(String nome) {
        this.id.setNome(nome);
    }

    public void setTipo(String tipo) {
        this.id.setTipo(tipo);
    }
}
