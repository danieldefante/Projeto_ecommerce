/*
 * Classe responsavel pelos servicos rest do produto
 */
package servicosRest;

import bo.BOFactory;
import dao.DAOProduto;
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
        JSONArray listaProdutos = k.getJSONArray("data");
        
        System.out.println("tamanho--->"+listaProdutos.length());
        System.out.println("tamanho--->"+((JSONObject) k.getJSONArray("data").get(0)).getString("nome"));

        
        double rateio = k.getDouble("despesasGerais") / listaProdutos.length();
        double margem = 1+(k.getDouble("margemLucro")/100);
      
        //chama a classe que redimenciona a imagem antes de atribuir ao TOProduto
        redimencionarImage r = new redimencionarImage();
        
        TOProduto t = new TOProduto();
        double a;
        
        for (int i = 0; i < listaProdutos.length(); i++) {
            System.out.println(i);
            
            //atribui valores ao TOProduto
            t.setNome(((JSONObject) listaProdutos.get(i)).getString("nome"));
            t.setDescricao(((JSONObject) listaProdutos.get(i)).getString("descricao"));
            t.setValor_custo(((JSONObject) listaProdutos.get(i)).getDouble("valor_custo"));
            
            a = ((JSONObject) listaProdutos.get(i)).getDouble("valor_custo") + rateio;
            
            t.setValor_saida(a*margem);
            t.setImagem(r.redimensionaImg(((JSONObject) listaProdutos.get(i)).getString("imagem")));
            
            BOFactory.inserir(new DAOProduto(), t);
        }
        
   
        //Chama a classe de persistencia de dados no banco
//        if(BOFactory.inserir(new DAOProduto(), t) > -1){
            j.put("sucesso", true);
            j.put("mensagem", "Produto cadastrado com sucesso!");
//        }else{
//            j.put("sucesso", false);
//            j.put("mensagem", "Erro em cadastrar o produto!");
//        }
        
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
