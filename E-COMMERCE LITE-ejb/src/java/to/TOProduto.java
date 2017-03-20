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
    
    public long id;
    public String nome;
    public String imagem_string;
    public double valor_saida;
    public double valor_custo;
    public String descricao;
    public Blob imagem;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor_saida() {
        return valor_saida;
    }

    public void setValor_saida(double valor_saida) {
        this.valor_saida = valor_saida;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor_custo() {
        return valor_custo;
    }

    public void setValor_custo(double valor_custo) {
        this.valor_custo = valor_custo;
    }

    public Blob getImagem() {
        return imagem;
    }

    public void setImagem(Blob imagem) {
        this.imagem = imagem;
    }

    public String getImagem_string() {
        return imagem_string;
    }

    public void setImagem_string(String imagem_string) {
        this.imagem_string = imagem_string;
    }

    public TOProduto() {
    }
    
    
    //metodo retorna um TOProduto apartir do ResultSet
    public TOProduto (ResultSet rs) throws Exception{
        this.id = rs.getLong("ID");
        this.nome = rs.getString("NOME");
        
        //pega o retorno Blob do result set e transforma em String
        Blob blob = rs.getBlob("IMAGEM");
        InputStream out = blob.getBinaryStream(); 
        String decodedImg = Base64.getEncoder().encodeToString(IOUtils.toByteArray(out));
        this.imagem_string = "data:image/png;base64,"+ decodedImg;
        

        this.valor_saida = rs.getDouble("VALOR_SAIDA");
        this.valor_custo = rs.getDouble("VALOR_CUSTO");
        this.descricao = rs.getString("DESCRICAO");
        
    }
    
    
    //metodo de retorno json
    public JSONObject buscarJson()throws Exception {
        
        JSONObject j = new JSONObject();
  
        //populando o objeto j
        j.put("ID", id);
        j.put("NOME", nome);
        j.put("IMAGEM", imagem_string);
        j.put("VALOR_SAIDA", valor_saida);
        j.put("VALOR_ENTRADA", valor_custo);
        j.put("DESCRICAO", descricao);
        
        return j;
    }
    
}
