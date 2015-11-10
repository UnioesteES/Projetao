package models;

import play.db.ebean.Model;
import javax.persistence.*;
import play.data.validation.Constraints;

import java.util.LinkedHashMap;
import java.util.Map;

@Entity
public class Fornecedor extends Model{
    private static final long serialVersionUID = 1L;

    @Id
    public Long codigo;

    @Constraints.Required
    private String cnpj;

    @Constraints.Required
    private String nome;

    @Constraints.Required
    private String rua;

    @Constraints.Required
    private Integer numero;

    @Constraints.Required
    private String bairro;

    @Constraints.Required
    private String cep;

    @Constraints.Required
    private String cidade;

    @Constraints.Required
    private String estado;

    @Constraints.Required
    private String pais;

    private String telefone1;
    private String telefone2;
    private String email;

    //Auxilia nas consultas por fabricantge
    public static Model.Finder<Long, Fornecedor> find =
            new Model.Finder(Long.class, Fornecedor.class);

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public static Finder<Long, Fornecedor> getFind() {
        return find;
    }

    public static void setFind(Finder<Long, Fornecedor> find) {
        Fornecedor.find = find;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for (Fornecedor f : Fornecedor.find.orderBy("nome").findList()) {
            options.put(f.getCodigo().toString(),f.getNome());
        }
        return options;
    }


}