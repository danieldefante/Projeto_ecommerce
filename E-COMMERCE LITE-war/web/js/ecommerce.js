/* 
 * Script pagina inicial com eventos e metodos
 */


$(".thumb")
    .on( "mouseenter", function() {

        $(this).children("a").children(".icones").addClass("fa-blink");

    })
    .on( "mouseleave", function() {

        $(this).children("a").children(".icones").removeClass("fa-blink");

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
                $("#listaProdutos").append('<div class="text-center col-lg-3 col-md-4 col-xs-6  portfolio-item"><a value="'+valor.ID+'" class="itemProduto thumbnail portfolio-link" data-toggle="modal"><div class="caption"><div class="caption-content"><i class="fa fa-search-plus fa-3x"></i></div></div><div style="height: 30px;" ></div><img style="margin: auto;" src="'+valor.IMAGEM+'" height="50" width="50" alt="Cabin"><h3 class="text-muted">'+valor.NOME+'</h3><p class="text-muted">'+valor.VALOR_SAIDA.toFixed(2)+'</p><div hidden="" class="descricaoProduto">'+valor.DESCRICAO+'</div></a> <button style="margin-top: 15px;" type="button" id="buttonAdcionarCesta" class="btn btn-info"><i class="fa fa-shopping-basket"></i> &nbsp;<i class="fa fa-plus"></i></button>   </div>');
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

$(document).on("click", "#pagamento", function(evt){
    alert("Continuar ...");
});

$(document).on("click", ".excluirProdutoCesta", function(evt){

    
    var id = $(this).parent("spam").parent("td").parent("tr").attr("id");
    var listaCesta = JSON.parse(sessionStorage.getItem("cestaCompras"));
    var newListaCesta = [];
    $.each(listaCesta, function(i, valor){
 
        if(valor.id != id){
            newListaCesta.push(valor);
        }
    });


        if(newListaCesta.length > 0){
            sessionStorage.setItem("cestaCompras", JSON.stringify(newListaCesta));
        }else{
            sessionStorage.removeItem("cestaCompras");
            $("#corpoModalAmostragem").empty().append('<p>Sua cesta esta vazia!</p>');
        }
    

    var tr = $(this).closest('tr');

        tr.fadeOut(400, function() {
          tr.remove();  
    });
});

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

$(document).on("click", ".subtrairQtd", function(evt){
    var linha = $(this).parent("td").parent("tr");
    var id = linha.attr("id");
    var listaCesta = JSON.parse(sessionStorage.getItem("cestaCompras"));
    var newlistaCesta = [];  
        $.each(listaCesta, function(i, valor){
            if(valor.id == id){
                
                if(parseInt(valor.qtdProduto) > 1){
                    valor.qtdProduto = (parseInt(valor.qtdProduto) - 1).toString();
                    newlistaCesta.push(valor);
                    linha.children("td:nth-child(2)").text(valor.qtdProduto);
                    linha.children("td:nth-child(4)").text((parseFloat(valor.qtdProduto)* parseFloat(valor.valor_saida)).toFixed(2));
                }else{
            

                    linha.fadeOut(400, function() {
                        linha.remove();  
                    });
                }
            }else{
                newlistaCesta.push(valor);
            }
            
        });



        if(newlistaCesta.length > 0){
            sessionStorage.setItem("cestaCompras", JSON.stringify(newlistaCesta));
        }else{
            sessionStorage.removeItem("cestaCompras");
            $("#corpoModalAmostragem").empty().append('<p>Sua cesta esta vazia!</p>');
        }

    return false;
});

$(document).on("click", ".somarQtd", function(evt){
    var linha = $(this).parent("td").parent("tr");
    var id = linha.attr("id");
    var listaCesta = JSON.parse(sessionStorage.getItem("cestaCompras"));
    var newlistaCesta = [];  
        $.each(listaCesta, function(i, valor){
            if(valor.id == id){
                valor.qtdProduto = (parseInt(valor.qtdProduto) + 1).toString();
                newlistaCesta.push(valor);
                linha.children("td:nth-child(2)").text(valor.qtdProduto);
                linha.children("td:nth-child(4)").text((parseFloat(valor.qtdProduto)* parseFloat(valor.valor_saida)).toFixed(2));
            }else{
                newlistaCesta.push(valor);
            }
            
        });

        sessionStorage.setItem("cestaCompras", JSON.stringify(newlistaCesta));

    return false;
});

$(document).on("click", "#cesta", function(evt){
 
    $('#tituloModalAmostragem').empty().append('<i class="fa fa-shopping-basket" aria-hidden="true"></i>');


    
    if(sessionStorage.getItem("cestaCompras")){
        
        var listaCesta = [];
        var itens = '';
        listaCesta = JSON.parse(sessionStorage.getItem("cestaCompras"));
        $.each(listaCesta, function(i, valor){
            itens += '<tr id="'+valor.id+'"><td>'+valor.nome+'</td><td>'+valor.qtdProduto+'</td><td>'+valor.valor_saida+'</td><td>'+(parseFloat(valor.valor_saida) * parseFloat(valor.qtdProduto)).toFixed(2)+'</td><td style="color:#000; cursor:pointer"><spam><i class="excluirProdutoCesta fa fa-trash" aria-hidden="true"></i></spam><spam class="subtrairQtd" style="margin-left:20px; margin-right:20px;"><i class=" fa fa-minus" aria-hidden="true"></i></spam><spam class="somarQtd"><i class=" fa fa-plus" aria-hidden="true"></i></spam></td></tr>';

        });

        $("#corpoModalAmostragem").empty().append(' <div class="table-responsive text-left"><table class="table table-striped"><thead><tr><th>Produto</th><th>Unidade</th><th>Valor unitário</th><th>Valor Total</th><th></th></tr></thead><tbody>'+itens+'</tbody></table><button id="pagamento" type="button" class="btn btn-info"><i class="fa fa-money" aria-hidden="true"></i>&nbsp;Proceguir pagamento</button></div> ');
  
    }else{
        $("#corpoModalAmostragem").empty().append('<p>Sua cesta esta vazia!</p>');
    }
      
    $("#modalAmostragem").modal("toggle");
    return false;
});
}
