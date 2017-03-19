/*
 * Classe responsavel pelos servicos rest do produto
 */
package servicosRest;

import bo.BOFactory;
import dao.DAOProduto;
import dao.DAORateio;
import fw.redimencionarImage;
import to.TOProduto;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import to.TORateio;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("produto")
public class ServicoProduto {

    @Context
    private UriInfo context;

    public ServicoProduto() {
    }

    //metodo Rest que insere no banco de dados um novo produto
    @POST
    @Path("inserir")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String inserir(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        TOProduto t = new TOProduto();
        //atribui valores ao TOProduto
        t.setNOME(k.getString("NOME"));
        t.setVALOR_ENTRADA(k.getDouble("VALOR_ENTRADA"));
        
        TORateio tr = new TORateio();
        tr = (TORateio)BOFactory.buscar(new DAORateio());
        
        
        
        t.setVALOR_SAIDA(tr.retornoRateio());
        t.setDESCRICAO(k.getString("DESCRICAO"));
        //chama a classe que redimenciona a imagem antes de atribuir ao TOProduto
        redimencionarImage r = new redimencionarImage();
        t.setIMAGEM(r.redimensionaImg(k.getString("IMAGEM")));
        
        //Chama a classe de persistencia de dados no banco
        if(BOFactory.inserir(new DAOProduto(), t) > -1){
            j.put("sucesso", true);
            j.put("mensagem", "Produto cadastrado com sucesso!");
        }else{
            j.put("sucesso", false);
            j.put("mensagem", "Erro em cadastrar o produto!");
        }
        
        return j.toString();
    }

    //metodo que lista os produtos do bando de dados
    @GET
    @Path("listar")
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() throws Exception{
        JSONObject j = new JSONObject();
        
        JSONArray ja = BOFactory.listar(new DAOProduto());
        
        if(ja.length() > 0){
        
            j.put("sucesso", true);
            j.put("data", ja);
        }else{
            j.put("sucesso", false);
            j.put("mensagem", "Não contém lista de produtos!");
        }
        
        return j.toString();
    }
   
}
