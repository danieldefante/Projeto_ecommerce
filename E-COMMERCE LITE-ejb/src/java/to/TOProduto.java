/*
 * Classe de mapeamento do banco de dados
 */
package to;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.util.Base64;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/**
 *
 * @author daniel
 */
public class TOProduto extends TOBase{
    
    public long ID;
    public String NOME;
    public String IMAGEMString;
    public double VALOR_SAIDA;
    public double VALOR_ENTRADA;
    public String DESCRICAO;
    public Blob IMAGEM;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getNOME() {
        return NOME;
    }

    public void setNOME(String NOME) {
        this.NOME = NOME;
    }

    public double getVALOR_SAIDA() {
        return VALOR_SAIDA;
    }

    public void setVALOR_SAIDA(double VALOR_SAIDA) {
        this.VALOR_SAIDA = VALOR_SAIDA;
    }

    public String getDESCRICAO() {
        return DESCRICAO;
    }

    public void setDESCRICAO(String DESCRICAO) {
        this.DESCRICAO = DESCRICAO;
    }

    public double getVALOR_ENTRADA() {
        return VALOR_ENTRADA;
    }

    public void setVALOR_ENTRADA(double VALOR_ENTRADA) {
        this.VALOR_ENTRADA = VALOR_ENTRADA;
    }

    public Blob getIMAGEM() {
        return IMAGEM;
    }

    public void setIMAGEM(Blob IMAGEM) {
        this.IMAGEM = IMAGEM;
    }

    public String getIMAGEMString() {
        return IMAGEMString;
    }

    public void setIMAGEMString(String IMAGEMString) {
        this.IMAGEMString = IMAGEMString;
    }

    public TOProduto() {
    }
    
    
    //metodo retorna um TOProduto apartir do ResultSet
    public TOProduto (ResultSet rs) throws Exception{
        this.ID = rs.getLong("ID");
        this.NOME = rs.getString("NOME");
        
        //pega o retorno Blob do result set e transforma em String
        Blob blob = rs.getBlob("IMAGEM");
        InputStream out = blob.getBinaryStream(); 
        String decodedImg = Base64.getEncoder().encodeToString(IOUtils.toByteArray(out));
        this.IMAGEMString = "data:image/png;base64,"+ decodedImg;
        

        this.VALOR_SAIDA = rs.getDouble("VALOR_SAIDA");
        this.VALOR_ENTRADA = rs.getDouble("VALOR_ENTRADA");
        this.DESCRICAO = rs.getString("DESCRICAO");
        
    }
    
    //metodo de retorno json
    public JSONObject buscarJson()throws Exception {
        
        JSONObject j = new JSONObject();
  
        //populando o objeto j
        j.put("ID", ID);
        j.put("NOME", NOME);
        j.put("IMAGEM", IMAGEMString);
        j.put("VALOR_SAIDA", VALOR_SAIDA);
        j.put("VALOR_ENTRADA", VALOR_ENTRADA);
        j.put("DESCRICAO", DESCRICAO);
        
        return j;
    }
    
}
