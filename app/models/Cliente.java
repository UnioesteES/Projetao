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
public class Cliente extends Model{
    private static final long serialVersionUID = 1L;

    @Id
    private Long codigo;

    @OnetoOne
    @Constraints.Required
    public Endereco endereco;

    @Constraints.Required
    private String primeiroNome;

    @Constraints.Required
    private String ultimoNome;

    @Constraints.Required
    private String cpf;

    @Constraints.Required
    private String email;

    @Constraints.Required
    private String senha;

    @Constraints.Required
    private String dataNascimento; 

    @Constraints.Required
    private String telefoneFixo;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    //Auxilia nas consultas por produto
    public static Model.Finder<Long, Cliente> find =
            new Model.Finder(Long.class, Cliente.class);

    public static Finder<Long, Cliente> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, Cliente> find) {
        Cliente.find = find;
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

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }
}
