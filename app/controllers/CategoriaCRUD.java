package controllers;

import models.Categoria;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by alysson on 23/10/15.
 */
public class CategoriaCRUD extends Controller{

    private static final Form<Categoria> categoriaForm =
            Form.form(Categoria.class);

    //Lista as categorias cadastradas no banco
    public static Result lista(){
        List<Categoria> categorias = Categoria.find.findList();
        return ok(views.html.categoriaMain.render(categorias));
    }

    //Efetua novo cadastro
    public static Result novaCategoria() {
        return ok(views.html.novaCategoria.render(categoriaForm));
    }

    //Grava nova categoria no banco
    public static Result gravar() {
        Form<Categoria> form = categoriaForm.bindFromRequest();

        if (form.hasErrors()) {
            flash("erro","Foram identificados problemas no cadastro");
            return ok(views.html.novaCategoria.render(categoriaForm));
        }

        Categoria categoria = form.get();
        categoria.save();

        flash("sucesso","Registro gravado com sucesso");
        return redirect(routes.CategoriaCRUD.lista());
    }

    //Exibe informações de uma categoriaMain
    public static Result detalhar(Long codigo) {
        Form<Categoria> catForm =
                Form.form(Categoria.class).fill(Categoria.find.byId(codigo));
        return ok(views.html.alterarCategoria.render(codigo,catForm));
    }

    //Alterar as informações de uma categoria
    public static Result alterar(Long codigo) {
        Form.form(Categoria.class).fill(Categoria.find.byId(codigo));
        Form<Categoria> alterarForm = Form.form(Categoria.class).bindFromRequest();
        if (alterarForm.hasErrors()) {
            return badRequest(
                    views.html.alterarCategoria.render(codigo,alterarForm));
        }
        alterarForm.get().update(codigo);
        flash("sucesso","Categoria "
                + alterarForm.get().getNome() + " alterada com sucesso");
        return redirect(routes.CategoriaCRUD.lista());
    }

    //Remove uma categoria
    public static Result remover(Long codigo) {
        try{
            Categoria.find.ref(codigo).delete();
            flash("sucesso","Categoria removida com sucesso");
        } catch (Exception e){
            flash("erro", play.i18n.Messages.get("global.erro"));
        }

        return lista();
    }

}
