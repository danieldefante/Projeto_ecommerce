/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicosRest;

import bo.BOFactory;
import dao.DAOProduto;
import fw.redimencionarImage;
import java.sql.Blob;
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

    /**
     * Creates a new instance of ServicoProduto
     */
    public ServicoProduto() {
    }


    @POST
    @Path("inserir")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String inserir(String dataJson) throws Exception{
        
        JSONObject j = new JSONObject();
        JSONObject k = new JSONObject(dataJson);
        
        TOProduto t = new TOProduto();
        
        
//        Blob blob = null;
//         
//        byte[] decodedByte = k.getString("IMAGEM").getBytes();
//        
//        String s = new String(decodedByte);
//        System.out.println(s); 
//
//        blob = new javax.sql.rowset.serial.SerialBlob(decodedByte);
//        t.setIMAGEM(blob);

        t.setNOME(k.getString("NOME"));

        redimencionarImage r = new redimencionarImage();
        t.setIMAGEM(r.redimensionaImg(k.getString("IMAGEM")));
        
        t.setVALOR_ENTRADA(k.getDouble("VALOR_ENTRADA"));
        t.setVALOR_SAIDA(k.getDouble("VALOR_SAIDA"));
        t.setDESCRICAO(k.getString("DESCRICAO"));
        
        
        if(BOFactory.inserir(new DAOProduto(), t) > -1){
            j.put("sucesso", true);
            j.put("mensagem", "Produto cadastrado com sucesso!");
        }else{
            j.put("sucesso", false);
            j.put("mensagem", "Erro em cadastrar o produto!");
        }
        
  
        return j.toString();
    }

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
            j.put("mensagem", "Erro em listar os produtos!");
        }
        
        
        return j.toString();
    }
    
    
    @POST
    @Path("testep")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String teste(String dataJson) throws Exception{
    
        JSONObject k = new JSONObject(dataJson);
        
        redimencionarImage r = new redimencionarImage();
        
        r.redimensionaImg(k.getString("IMAGEM"));
        return "teste";
    }
    
    

}
