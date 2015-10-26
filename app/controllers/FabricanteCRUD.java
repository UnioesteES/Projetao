package controllers;

import ch.qos.logback.classic.util.ContextInitializer;
import models.Fabricante;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import sun.rmi.runtime.Log;

import java.util.List;

/**
 * Created by alysson on 23/10/15.
 */
public class FabricanteCRUD extends Controller{

    private static final Form<Fabricante> fabricanteForm =
            Form.form(Fabricante.class);

    //Lista os fabricantes cadastrados no banco
    public static Result lista(){
        List<Fabricante> fabricantes = Fabricante.find.findList();
        return ok(views.html.fabricanteMain.render(fabricantes));
    }

    //Efetua novo cadastro
    public static Result novoFabricante() {
        return ok(views.html.novoFabricante.render(fabricanteForm));
    }

    //Grava nova Fabricante no banco
    public static Result gravar() {
        Form<Fabricante> form = fabricanteForm.bindFromRequest();

        if (form.hasErrors()) {
            flash("erro","Foram identificados problemas no cadastro");
            return ok(views.html.novoFabricante.render(fabricanteForm));
        }

        Fabricante Fabricante = form.get();
        Fabricante.save();

        flash("sucesso","Registro gravado com sucesso");
        return redirect(routes.FabricanteCRUD.lista());
    }

    //Exibe informações de um Fabricante
    public static Result detalhar(String cnpj) {
        Form<Fabricante> fabForm =
                Form.form(Fabricante.class).fill(Fabricante.find.byId(cnpj));
        return ok(views.html.alterarFabricante.render(cnpj,fabForm));
    }

    //Alterar as informações de um Fabricante
    public static Result alterar(String cnpj) {
        Form.form(Fabricante.class).fill(Fabricante.find.byId(cnpj));
        Form<Fabricante> alterarForm = Form.form(Fabricante.class).bindFromRequest();
        if (alterarForm.hasErrors()) {
            return badRequest(
                    views.html.alterarFabricante.render(cnpj,alterarForm));
        }
        alterarForm.get().update(cnpj);
        flash("sucesso","Fabricante "
                + alterarForm.get().getNome() + " alterado com sucesso");
        return redirect(routes.FabricanteCRUD.lista());
    }

    //Remove um Fabricante
    public static Result remover(String cnpj) {
        try{
            Fabricante.find.ref(cnpj).delete();
            flash("sucesso","Fabricante removido com sucesso");
        } catch (Exception e){
            flash("erro", play.i18n.Messages.get("global.erro"));
        }

        return lista();
    }

}
