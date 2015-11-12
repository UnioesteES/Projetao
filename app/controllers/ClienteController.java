package controllers;

import models.Cliente;
import models.Endereco;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class ClienteController extends Controller implements Controlador{

    private static final Form<Cliente> clienteForm =
            Form.form(Cliente.class);



    //Lista os clientes cadastrados no banco
    public static Result lista(){
        List<Cliente> clientes = Cliente.find.findList();
        return ok(views.html.clienteMain.render(clientes));
    }

    //Efetua novo cadastro
    public static Result novoCliente() {
        return ok(views.html.novoCliente.render(clienteForm));
    }

    //Grava novo Cliente no banco
    public static Result gravar() {
        Form<Cliente> form = clienteForm.bindFromRequest();
        System.out.println(form);

        String mensagemErro = "Verifique o(s) campo(s) a seguir: \n";

        java.util.Map<String, List<ValidationError>> errorsAll = form.errors(); //armazena os erros do formulário

        for (String campo : errorsAll.keySet()) {
            mensagemErro += " - " + campo;
        }

        //Verifica se há algum erro no formulário
        if (form.hasErrors()) {
            flash("erro","Foram identificados problemas no cadastro. " +  mensagemErro);
            return ok(views.html.novoCliente.render(clienteForm));
        }

        Cliente clienteFormu = form.get();
        clienteFormu.save();

        flash("sucesso","Registro gravado com sucesso");
        return redirect(routes.ClienteController.lista());
    }

    //Exibe informações de um Cliente
    public static Result detalhar(Long codigo) {
        Form<Cliente> cliForm =
                Form.form(Cliente.class).fill(Cliente.find.byId(codigo));
        return ok(views.html.alterarCliente.render(codigo,cliForm));
    }

    //Alterar as informações de um Produto
    public static Result alterar(Long codigo) {
        Form.form(Cliente.class).fill(Cliente.find.byId(codigo));
        Form<Cliente> alterarForm = Form.form(Cliente.class).bindFromRequest();
        if (alterarForm.hasErrors()) {
            return badRequest(
                    views.html.alterarCliente.render(codigo,alterarForm));
        }
        alterarForm.get().update(codigo);
        flash("sucesso","Cliente "
                + alterarForm.get().getCpf() + " alterado com sucesso");
        return redirect(routes.ClienteController.lista());
    }

    //Remove um Cliente
    public static Result remover(Long codigo) {
        try{
            Cliente.find.ref(codigo).delete();
            flash("sucesso","Cliente removido com sucesso");
        } catch (Exception e){
            flash("erro", play.i18n.Messages.get("global.erro"));
        }

        return lista();
    }

}
