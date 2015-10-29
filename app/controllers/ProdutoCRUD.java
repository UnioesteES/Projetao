package controllers;

import models.Categoria;
import models.Fabricante;
import models.Produto;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.List;

public class ProdutoCRUD extends Controller{

    private static final Form<Produto> produtoForm =
            Form.form(Produto.class);

    //Lista os produtos cadastrados no banco
    public static Result lista(){
        List<Produto> produtos = Produto.find.findList();
        return ok(views.html.produtoMain.render(produtos));
    }

    //Efetua novo cadastro
    public static Result novoProduto() {
        List<Fabricante> fabricantes = Fabricante.find.findList();
        List<Categoria> categorias = Categoria.find.findList();

        return ok(views.html.novoProduto.render(produtoForm, fabricantes, categorias));
    }

    //Grava novo Produto no banco
    public static Result gravar() {
        Form<Produto> form = produtoForm.bindFromRequest();
        List<Fabricante> fabricantes = Fabricante.find.findList();
        List<Categoria> categorias = Categoria.find.findList();
    /*
        vali.required(name);
        validation.required(name);
        validation.required(name);

        //Realiza validação verificando se há algum problema
        if(valid)
        */
        //Verifica se há algum erro no formulário
        if (form.hasErrors()) {
            flash("erro","Foram identificados problemas no cadastro");
            return ok(views.html.novoProduto.render(produtoForm, fabricantes, categorias));
        }

        Produto produtoFormu = form.get();
        produtoFormu.save();

        flash("sucesso","Registro gravado com sucesso");
        return redirect(routes.ProdutoCRUD.lista());
    }

    //Exibe informações de um Produto
    public static Result detalhar(Long codigo) {
        Form<Produto> fabForm =
                Form.form(Produto.class).fill(Produto.find.byId(codigo));
        return ok(views.html.alterarProduto.render(codigo,fabForm));
    }

    //Alterar as informações de um Produto
    public static Result alterar(Long codigo) {
        Form.form(Produto.class).fill(Produto.find.byId(codigo));
        Form<Produto> alterarForm = Form.form(Produto.class).bindFromRequest();
        if (alterarForm.hasErrors()) {
            return badRequest(
                    views.html.alterarProduto.render(codigo,alterarForm));
        }
        alterarForm.get().update(codigo);
        flash("sucesso","Produto "
                + alterarForm.get().getNome() + " alterado com sucesso");
        return redirect(routes.ProdutoCRUD.lista());
    }

    //Remove um Produto
    public static Result remover(Long codigo) {
        try{
            Produto.find.ref(codigo).delete();
            flash("sucesso","Produto removido com sucesso");
        } catch (Exception e){
            flash("erro", play.i18n.Messages.get("global.erro"));
        }

        return lista();
    }

}
