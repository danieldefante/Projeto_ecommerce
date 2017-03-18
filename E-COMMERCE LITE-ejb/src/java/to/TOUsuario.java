/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

import java.sql.Blob;
import java.sql.ResultSet;
import org.json.JSONObject;;

/**
 *
 * @author daniel
 */
public class TOUsuario extends TOBase{
    
    public String usuario;
    public String senha;
    public String email;
    public String nome;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    

    public TOUsuario() {
    }
    
    public TOUsuario (ResultSet rs) throws Exception{
        this.usuario = rs.getString("usuario");
        this.nome = rs.getString("nome");
        this.email = rs.getString("email");
        this.senha = rs.getString("senha");
        
    }
    
    public JSONObject buscarJson()throws Exception {
        //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
  
        //populando o objeto j
        j.put("usuario", usuario);
        j.put("nome", nome);
        j.put("email", email);
        j.put("senha", senha);
        
        return j;
    }
    
}
