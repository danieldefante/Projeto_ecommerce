/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicosRest;

import bo.BOFactory;
import dao.DAOUsuario;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;
import to.TOUsuario;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("login")
public class ServicoLogin {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServicoLogin
     */
    public ServicoLogin() {
    }

    @POST
    @Path("validacao")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String validacao(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        TOUsuario t = new TOUsuario();
        //atribui valores ao TOUsuario
        t.setUsuario(k.getString("USUARIO"));
        t.setSenha(k.getString("SENHA"));
        
        
        t = (TOUsuario) BOFactory.buscar(new DAOUsuario(), t);
        
        //Chama a classe de persistencia de dados no banco
        if(t != null){
            j.put("data", t.buscarJson());
            j.put("sucesso", true);
        }else{
            j.put("sucesso", false);
            j.put("mensagem", "Usuário ou senha incorretos!");
        }
        
        return j.toString();
    }
}
