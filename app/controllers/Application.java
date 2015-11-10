package controllers;

import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result indexAdmin() {
        return ok(index.render(""));
    }

}
