/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package to;

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
    public String grupo;

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

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    

    public TOUsuario() {
    }
    
    public TOUsuario (ResultSet rs) throws Exception{
        this.usuario = rs.getString("USERNAME");
        this.nome = rs.getString("NOME");
        this.email = rs.getString("EMAIL");
        this.grupo = rs.getString("GRUPO");
        
    }
    
    public JSONObject buscarJson()throws Exception {
        //variavel tipo json para retornar no metodo
        JSONObject j = new JSONObject();
  
        //populando o objeto j
        j.put("usuario", usuario);
        j.put("nome", nome);
        j.put("email", email);
        j.put("grupo", grupo);
        
        return j;
    }
    
}
