

$(function() {
    $("#formCadastroProduto").find("input,textarea").jqBootstrapValidation(
        {
            preventSubmit: true,
            submitError: function($form, event, errors) {
                // Here I do nothing, but you could do something like display 
                // the error messages to the user, log, etc.
            },
            submitSuccess: function($form, event) {
//                requisicao(function(dadosRetorno) {
//                   if(dadosRetorno.sucesso){
//                        $("#NovoNomeProduto").val("");
//                        $("#NovoValorCusto").val("");
//                        $("#novoDescricao").val("");
//                        $("#inputImagemProduto").val("");
//                        $("#novoImagemProduto").prop("src", "").hide();
//                        alert(dadosRetorno.mensagem);
//                    }else{
//                        alert(dadosRetorno.mensagem); 
//                    }
//                     
//                });

                alert("proceguir");
                event.preventDefault();
            },
            filter: function() {
                return $(this).is(":visible");
            }
        }
    );
});


function requisicao(callback) {
  
    produto = { nome:$("#NovoNomeProduto").val(),
                valor_custo:$("#NovoValorCusto").val(),
                descricao:$("#novoDescricao").val(),
                imagem:$("#novoImagemProduto").prop("src"),
                despesas_totais: $("#despesaTotais").val(),
                margem_lucro: $("#margemLucro").val()
            };


    $.ajax({
       type: 'POST',
       url: "http://localhost:8080/E-COMMERCE_LITE-war/servicos/produto/inserir",
       data: JSON.stringify(produto),
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


$(document).on("click", "#novoProduto", function(){
    $(this).addClass("active");
    
    if($("#formCadastroProduto").is(":visible")){
        
        
            $("#formCadastroProduto").submit();
        
  
        
    }else{
        $("#formCadastroProduto").fadeIn(400);
    }
    
});


$(document).on("change", "#inputImagemProduto", function(){


    readImage($(this)).done(function(base64Data){

        $("#novoImagemProduto").attr("src", base64Data);   
        $("#novoImagemProduto").removeClass("hidden");   
    });

    return false;
});

//conversao imagem em base64
function readImage(inputElement) {
    var deferred = $.Deferred();

    var files = inputElement.get(0).files;
    if (files && files[0]) {
        var fr= new FileReader();
        fr.onload = function(e) {
            deferred.resolve(e.target.result);
        };
        fr.readAsDataURL( files[0] );
    } else {
        deferred.resolve(undefined);
    }

    return deferred.promise();
}