package controllers;

import models.Fornecedor;
import play.data.Form;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.List;

/**
 * Created by alysson on 23/10/15.
 */
public class FornecedorController extends Controller implements Controlador{

    private static final Form<Fornecedor> fornecedorForm =
            Form.form(Fornecedor.class);

    //Lista os fornecedors cadastrados no banco
    public static Result lista(){
        List<Fornecedor> fornecedors = Fornecedor.find.findList();
        return ok(views.html.fornecedorMain.render(fornecedors));
    }

    //Efetua novo cadastro
    public static Result novoFornecedor() {
        return ok(views.html.novoFornecedor.render(fornecedorForm));
    }

    //Grava nova Fornecedor no banco
    public static Result gravar() {
        Form<Fornecedor> form = fornecedorForm.bindFromRequest();

        //Auxiliar para validação
        //ValidationError validationError = new ValidationError();

        //Verifica se e-mail está no padrão

        if (form.hasErrors()) { //Algum erro ocorreu
            String mensagemErro = "Verifique o(s) campo(s) a seguir: ";

            java.util.Map<String, List<ValidationError>> errorsAll = form.errors(); //armazena os erros do formulário

            for (String campo : errorsAll.keySet()) {
                mensagemErro += " - " + campo;
            }

            flash("erro","Foram identificados problemas no cadastro." + mensagemErro);
            return ok(views.html.novoFornecedor.render(fornecedorForm));
        }

        Fornecedor Fornecedor = form.get();
        Fornecedor.save();

        flash("sucesso","Registro gravado com sucesso");
        return redirect(routes.FornecedorController.lista());
    }

    //Exibe informações de um Fornecedor
    public static Result detalhar(Long codigo) {
        Form<Fornecedor> fabForm =
                Form.form(Fornecedor.class).fill(Fornecedor.find.byId(codigo));
        return ok(views.html.alterarFornecedor.render(codigo,fabForm));
    }

    //Alterar as informações de um Fornecedor
    public static Result alterar(Long codigo) {
        Form.form(Fornecedor.class).fill(Fornecedor.find.byId(codigo));
        Form<Fornecedor> alterarForm = Form.form(Fornecedor.class).bindFromRequest();
        if (alterarForm.hasErrors()) {
            return badRequest(
                    views.html.alterarFornecedor.render(codigo,alterarForm));
        }
        alterarForm.get().update(codigo);
        flash("sucesso","Fornecedor "
                + alterarForm.get().getNome() + " alterado com sucesso");
        return redirect(routes.FornecedorController.lista());
    }

    //Remove um Fornecedor
    public static Result remover(Long codigo) {
        try{
            Fornecedor.find.ref(codigo).delete();
            flash("sucesso","Fornecedor removido com sucesso");
        } catch (Exception e){
            flash("erro", "Houve um erro ao remover o fornecedor. Verifique se o mesmo não está vinculado a algum produto.");
        }

        return lista();
    }

}