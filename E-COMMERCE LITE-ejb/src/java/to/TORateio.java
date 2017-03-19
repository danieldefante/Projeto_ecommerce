package to;

import java.sql.ResultSet;

/**
 *
 * @author daniel
 */
public class TORateio extends TOBase{
    
    private double valor_total;
    private int qtd_produto;

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public int getQtd_produto() {
        return qtd_produto;
    }

    public void setQtd_produto(int qtd_produto) {
        this.qtd_produto = qtd_produto;
    }

    public TORateio() {
    }
    
    //metodo retorna um TORateio apartir do ResultSet
    public TORateio (ResultSet rs) throws Exception{

        this.valor_total = rs.getDouble("VALOR_TOTAL");
        this.qtd_produto = rs.getInt("QTD_PRODUTOS");
        
    }
    
    public double retornoRateio(){
        
        return valor_total/qtd_produto;
    }
    
}
