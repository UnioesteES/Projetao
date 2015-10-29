package models;

import play.data.validation.ValidationError;
import play.db.ebean.Model;
import javax.persistence.*;
import play.data.validation.Constraints;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alysson on 23/10/15.
 */
@Entity
public class Categoria extends Model{
    private static final long serialVersionUID = 1L;

    @Id
    public Long codigo;

    @Constraints.Required
    private String nome;

    private String descricao;

    //Auxilia nas consultas por categoria
    public static Model.Finder<Long, Categoria> find =
            new Model.Finder(Long.class, Categoria.class);

    public static Model.Finder<String, Categoria> findNome =
            new Model.Finder(String.class, Categoria.class);

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

    public static Map<String,String> options() {
        LinkedHashMap<String,String> options = new LinkedHashMap<String,String>();
        for (Categoria c : Categoria.find.orderBy("nome").findList()) {
            options.put(c.getCodigo().toString(),c.getNome());
        }
        return options;
    }

    //Verifica se há erros e retorna mensagem sobre os erros encontrados
    public List<ValidationError> validacao() {
        List<ValidationError> erros = new ArrayList<ValidationError>();
        //Verifica se há alguma categoria cadastrada com esse nome
        /*
        if (Categoria.findNome(nome) != null) { //Existe
            erros.add(new ValidationError("categoria", "Já existe uma categoria cadastrada com esse nome."));
        } */

        //Campos nulos

        return erros.isEmpty() ? null : erros;
    }
}
