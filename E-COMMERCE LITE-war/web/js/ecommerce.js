/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(".thumb")
    .on( "mouseenter", function() {
//        $(this).css("filter", "alpha(opacity:0.5)");
//        $(this).css("-khtml-opacity", ".50");
//        $(this).css("-ms-filter", "alpha(opacity=50)");
//        $(this).css("-moz-opacity", ".50");
//        $(this).css("filter", "alpha(opacity=50)");
//        $(this).css("opacity", ".50");
//        
        $(this).children("a").children(".icones").addClass("fa-blink");
//        $(this).addClass("fa-blink");

    })
    .on( "mouseleave", function() {
//        $(this).css("filter", "alpha(opacity:1)");
//        $(this).css("-khtml-opacity", ".1");
//        $(this).css("-ms-filter", "alpha(opacity=100)");
//        $(this).css("-moz-opacity", "1");
//        $(this).css("filter", "alpha(opacity=100)");
//        $(this).css("opacity", "1");
        

        $(this).children("a").children(".icones").removeClass("fa-blink");
//        $(this).removeClass("fa-blink");
    });
    
    
    
    //function requisicao(url, envio, metodo){
function requisicaoGet(url, callback) {
  
    $.ajax({
         type: 'GET',
         url: "http://localhost:8080/E-COMMERCE_LITE-war/servicos/"+ url,
         headers: { 
         'Accept': 'application/json',
         'Content-Type': 'application/json'
         },
         success: function(retorno){
             callback(retorno);
         },
         error: function() {
            
             alert("Sem conexão com o servidor!");
         }
     });
             
 
}
    
    
function listarProdutos(){
    requisicaoGet('produto/listar', function(dadosRetorno){
        if(dadosRetorno.sucesso){
            
            $("#listaProdutos").empty();
            var listaProdutos = [];
            
           
            listaProdutos = dadosRetorno.data;

            $.each(listaProdutos, function(i, valor){
                $("#listaProdutos").append('<div class="text-center col-lg-3 col-md-4 col-xs-6  portfolio-item"><a value="'+valor.ID+'" class="itemProduto thumbnail portfolio-link" data-toggle="modal"><div class="caption"><div class="caption-content"><i class="fa fa-search-plus fa-3x"></i></div></div><div style="height: 30px;" ></div><img style="margin: auto;" src="'+valor.IMAGEM+'" height="50" width="50" alt="Cabin"><h3 class="text-muted">'+valor.NOME+'</h3><p class="text-muted">'+valor.VALOR_SAIDA+'</p><div hidden="" class="descricaoProduto">'+valor.DESCRICAO+'</div></a> <button style="margin-top: 15px;" type="button" id="buttonAdcionarCesta" class="btn btn-info"><i class="fa fa-shopping-basket"></i> &nbsp;<i class="fa fa-plus"></i></button>   </div>');
            });
        }else{
            alert(dadosRetorno.mensagem);
        }
    });

$(document).on("click", "#buttonAdcionarCesta", function(evt){
    abrirProduto($(this).parent("div").children("a"));
    return false;
});

$(document).on("click", ".itemProduto", function(evt){
    
    abrirProduto($(this));
    
    return false;
});

function abrirProduto(produto){
    
    $('#tituloModalAmostragem').empty().text(produto.children('h3').text()).attr("value", produto.attr("value"));
    
    $("#corpoModalAmostragem").empty().append('<img style="margin: auto;" src="'+produto.children("img").attr("src")+'" class="img-responsive" alt="Cabin"><p value="'+produto.children("p").text()+'" id="valorSaidaProduto"> Valor: '+produto.children("p").text()+'</p><p> Descrição: '+produto.children(".descricaoProduto").text()+'</p><div class=" text-left control-group col-lg-8 col-lg-offset-2 controls" style="padding-bottom:15px;"><label class="form-label" for="qtdProduto">Quantidade:</label><input type="number" class="form-control" value="1" id="qtdProduto" min="1"></div><button type="button" id="addCesta" class="btn btn-info"><i class="fa fa-shopping-basket"></i> Adicionar à cesta</button>');
    
    $("#modalAmostragem").modal('toggle');
}
$(document).on("click", "#addCesta", function(evt){
   
    var produto = {id: $('#tituloModalAmostragem').attr("value"),
                   nome: $('#tituloModalAmostragem').text(),
                   valor_saida: $("#valorSaidaProduto").attr("value"),
                   qtdProduto: $("#qtdProduto").val()};
    var listaCesta = [];
    if(sessionStorage.getItem("cestaCompras")){
        
        listaCesta = JSON.parse(sessionStorage.getItem("cestaCompras"));
        var contem = false;
        $.each(listaCesta, function(i, valor){
            if(valor.id == produto.id){
                    valor.qtdProduto = (parseInt(valor.qtdProduto) + parseInt(produto.qtdProduto)).toString(); 
                contem = true;
                return false;
            }
        });
            
        if(!contem){
            listaCesta.push(produto);   
        }
        
        
        sessionStorage.setItem("cestaCompras", JSON.stringify(listaCesta));
    }else{
        listaCesta.push(produto);
        sessionStorage.setItem("cestaCompras", JSON.stringify(listaCesta));
    }
    
   
    $("#modalAmostragem").modal("toggle");
    return false;
});

$(document).on("click", "#cesta", function(evt){
 
    $('#tituloModalAmostragem').empty().append('<i class="fa fa-shopping-basket" aria-hidden="true"></i>');


    
    if(sessionStorage.getItem("cestaCompras")){
        var listaCesta = [];
        var itens = '';
        listaCesta = JSON.parse(sessionStorage.getItem("cestaCompras"));
        
        $.each(listaCesta, function(i, valor){
            itens += '<tr><td>'+valor.nome+'</td><td>'+valor.qtdProduto+'</td><td>'+valor.valor_saida+'</td><td>'+parseFloat(valor.valor_saida) * parseFloat(valor.qtdProduto)+'</td><td><i class="fa fa-trash" aria-hidden="true"></i></td></tr>';
    
        });
    
        $("#corpoModalAmostragem").empty().append(' <div class="table-responsive text-left"><table class="table table-striped"><thead><tr><th>Produto</th><th>Unidade</th><th>Valor unitário</th><th>Valor Total</th><th></th></tr></thead><tbody>'+itens+'</tbody></table></div> ');
   
   
 

    }else{
        $("#corpoModalAmostragem").empty().append('<p>Sua cesta esta vazia!</p>');
    }
      
    $("#modalAmostragem").modal("toggle");
    return false;
});
}
