package controllers;

import models.Fabricante;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Result;
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


        if (form.hasErrors()) { //Algum erro ocorreu
            String mensagemErro = "O(s) campo(s) a seguir precisam ser preenchidos: \n";

            java.util.Map<String, List<ValidationError>> errorsAll = form.errors(); //armazena os erros do formulário

            for (String campo : errorsAll.keySet()) {
                mensagemErro += " - " + campo;

                /*
                for (ValidationError error : errorsAll.get(campo)) {
                    //mensagemErro += "necessita ser preenchido\n";
                }*/
            }
            flash("erro","Foram identificados problemas no cadastro.\n" + mensagemErro);
            return ok(views.html.novoFabricante.render(fabricanteForm));
        }

        Fabricante Fabricante = form.get();
        Fabricante.save();

        flash("sucesso","Registro gravado com sucesso");
        return redirect(routes.FabricanteCRUD.lista());
    }

    //Exibe informações de um Fabricante
    public static Result detalhar(Long codigo) {
        Form<Fabricante> fabForm =
                Form.form(Fabricante.class).fill(Fabricante.find.byId(codigo));
        return ok(views.html.alterarFabricante.render(codigo,fabForm));
    }

    //Alterar as informações de um Fabricante
    public static Result alterar(Long codigo) {
        Form.form(Fabricante.class).fill(Fabricante.find.byId(codigo));
        Form<Fabricante> alterarForm = Form.form(Fabricante.class).bindFromRequest();
        if (alterarForm.hasErrors()) {
            return badRequest(
                    views.html.alterarFabricante.render(codigo,alterarForm));
        }
        alterarForm.get().update(codigo);
        flash("sucesso","Fabricante "
                + alterarForm.get().getNome() + " alterado com sucesso");
        return redirect(routes.FabricanteCRUD.lista());
    }

    //Remove um Fabricante
    public static Result remover(Long codigo) {
        try{
            Fabricante.find.ref(codigo).delete();
            flash("sucesso","Fabricante removido com sucesso");
        } catch (Exception e){
            flash("erro", "Houve um erro ao remover o fabricante. Verifique se o mesmo não está vinculado a algum produto.");
        }

        return lista();
    }

}