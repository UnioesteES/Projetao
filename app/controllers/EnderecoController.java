/*package controllers;

import models.Cliente;
import models.Endereco;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class EnderecoController extends Controller implements Controlador{

    private static final Form<Endereco> enderecoForm =
            Form.form(Endereco.class);

//    //Lista os clientes cadastrados no banco
//    public static Result lista(){
//        List<Endereco> enderecos = Endereco.find.findList();
//        return ok(views.html.clienteMain.render(enderecos));
//    }

//    //Efetua novo cadastro
//    public static Result novoEndereco() {
//        return ok(views.html.novoEndereco.render(enderecoForm));
//    }

    //Grava novo Cliente no banco
    public static Result gravar() {
        Form<Endereco> form = enderecoForm.bindFromRequest();

        String mensagemErro = "Verifique o(s) campo(s) a seguir: \n";

        java.util.Map<String, List<ValidationError>> errorsAll = form.errors(); //armazena os erros do formulário

        for (String campo : errorsAll.keySet()) {
            mensagemErro += " - " + campo;
        }

        //Verifica se há algum erro no formulário
        if (form.hasErrors()) {
            flash("erro","Foram identificados problemas no cadastro. " +  mensagemErro);
            return ok(views.html.novoCliente.render(enderecoForm));
        }

        Endereco enderecoFormu = form.get();
        enderecoFormu.save();

        flash("sucesso","Registro gravado com sucesso");
        return redirect(routes.EnderecoController.lista());
    }

//    //Exibe informações de um Endereco
//    public static Result detalhar(Long codigo) {
//        Form<Endereco> endForm =
//                Form.form(Endereco.class).fill(Endereco.find.byId(codigo));
//        return ok(views.html.alterarEndereco.render(codigo,endForm));
//    }
//
//    //Alterar as informações de um Endereco
//    public static Result alterar(Long codigo) {
//        Form.form(Endereco.class).fill(Endereco.find.byId(codigo));
//        Form<Endereco> alterarForm = Form.form(Endereco.class).bindFromRequest();
//        if (alterarForm.hasErrors()) {
//            return badRequest(
//                    views.html.alterarEndereco.render(codigo,alterarForm));
//        }
//        alterarForm.get().update(codigo);
//        flash("sucesso","Endereco "
//                + alterarForm.get().getCpf() + " alterado com sucesso");
//        return redirect(routes.EnderecoController.lista());
//    }
//
//    //Remove um Endereco
//    public static Result remover(Long codigo) {
//        try{
//            Endereco.find.ref(codigo).delete();
//            flash("sucesso","Endereco removido com sucesso");
//        } catch (Exception e){
//            flash("erro", play.i18n.Messages.get("global.erro"));
//        }
//
//        return lista();
//    }

}
*/