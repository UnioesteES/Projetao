package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.Categoria;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by alysson on 23/10/15.
 */
public class CategoriaController extends Controller implements Controlador{

    private static final Form<Categoria> categoriaForm =
            Form.form(Categoria.class);

    //Lista as categorias cadastradas no banco
    public static Result lista(){
        List<Categoria> categorias = Categoria.find.findList();
        return ok(views.html.categoriaMain.render(categorias));
    }

    public static Result listaJson(){
        List<Categoria> categorias = Categoria.find.findList();
        return ok(Json.toJson(categorias));
    }

    public static Result novaCategoriaJson() {
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
            String nome = json.findPath("nome").asText();
            String descricao = json.findPath("descricao").asText();

            if(nome == null) {
                return badRequest("Missing parameter [codigo]");
            } else {
                Categoria categoria = new Categoria(nome, descricao);
                categoria.save();
                return ok();
            }
        }
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
        return redirect(routes.CategoriaController.lista());
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
        return redirect(routes.CategoriaController.lista());
    }

    //Remove uma categoria
    public static Result remover(Long codigo) {
        try{
            Categoria.find.ref(codigo).delete();
            flash("sucesso","Categoria removida com sucesso");
        } catch (Exception e){
            flash("erro", "Houve um erro ao remover a categoria. Verifique se a mesma não está vinculada a algum produto.");
        }

        return lista();
    }
}
