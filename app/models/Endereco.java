package models;


import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.db.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BE MEAN on 26/10/15.
 */

@Entity
public class Endereco extends Model{
    private static final long serialVersionUID = 1L;

    @Id
    private Long codigo;

    @Constraints.Required
    private String destinatario;

    @Constraints.Required
    private String telefoneContato;

    @Constraints.Required
    private String logradouro;

    @Constraints.Required
    private int numero;

    private String complemento;

    @Constraints.Required
    private String bairro;

    @Constraints.Required
    private String cidade;

    @Constraints.Required
    private String estado;


    //Auxilia nas consultas por produto
    private static Model.Finder<Long, Endereco> find =
            new Model.Finder(Long.class, Endereco.class);


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static Finder<Long, Endereco> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, Endereco> find) {
        Endereco.find = find;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
