package models;

import jdk.nashorn.internal.ir.annotations.Reference;
import play.db.ebean.Model;
import javax.persistence.*;
import play.data.validation.Constraints;

/**
 * Created by alysson on 26/10/15.
 */

@Entity
public class Produto extends Model{
    private static final long serialVersionUID = 1L;

    @Id
    private Long codigo;

    @OneToMany
    private Long codigoFabricante;

    @OneToMany
    private Long codigoCategoria;

    @Constraints.Required
    private String nome;

    private String descricao;
    private String modelo;

    @Constraints.Required
    private Double preco;

    @Constraints.Required
    private Double quantidade;

    private Integer altura;
    private Integer lagura;
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

    public Long getCodigoFabricante() {
        return codigoFabricante;
    }

    public void setCodigoFabricante(Long codigoFabricante) {
        this.codigoFabricante = codigoFabricante;
    }

    public Long getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(Long codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
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

    public Integer getLagura() {
        return lagura;
    }

    public void setLagura(Integer lagura) {
        this.lagura = lagura;
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
}
