package models;


import play.data.validation.ValidationError;
import play.db.ebean.Model;
import javax.persistence.*;
import play.data.validation.Constraints;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alysson on 26/10/15.
 */

@Entity
public class Produto extends Model{
    private static final long serialVersionUID = 1L;

    @Id
    private Long codigo;

    @ManyToOne
    public Fabricante fabricante;

    @ManyToOne
    public Categoria categoria;

    @Constraints.Required
    private String nome;

    private String descricao;
    private String modelo;

    @Constraints.Required
    private Double preco;

    @Constraints.Required
    private Double quantidade;

    private Integer altura;
    private Integer largura;
    private Integer comprimento;
    private Double peso;

    //Auxilia nas consultas por produto
    public static Model.Finder<Long, Produto> find =
            new Model.Finder(Long.class, Produto.class);


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static Finder<Long, Produto> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, Produto> find) {
        Produto.find = find;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Integer getLargura() {
        return largura;
    }

    public void setLargura(Integer largura) {
        this.largura = largura;
    }

    public Integer getComprimento() {
        return comprimento;
    }

    public void setComprimento(Integer comprimento) {
        this.comprimento = comprimento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<ValidationError> validacao() {
        List<ValidationError> erros = new ArrayList<ValidationError>();
        /* Verifica se algum produto cadastrado com esse nome
        if (Produto.byNome(nome) != null) {
            erros.add(new ValidationError("email", "This e-mail is already registered."));
        }
        */
        return erros.isEmpty() ? null : erros;
    }
}
