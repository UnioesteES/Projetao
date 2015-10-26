package models;

import play.db.ebean.Model;
import javax.persistence.*;
import play.data.validation.Constraints;

/**
 * Created by alysson on 23/10/15.
 */
@Entity
public class Categoria extends Model{
    private static final long serialVersionUID = 1L;

    @Id
    private Long codigo;

    @Constraints.Required
    private String nome;

    private String descricao;

    //Auxilia nas consultas por categoria
    public static Model.Finder<Long, Categoria> find =
            new Model.Finder(Long.class, Categoria.class);

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
}
