

$(function() {
    $("#formCadastroProduto").find("input,textarea").jqBootstrapValidation(
        {
            preventSubmit: true,
            submitError: function($form, event, errors) {
                // Here I do nothing, but you could do something like display 
                // the error messages to the user, log, etc.
            },
            submitSuccess: function($form, event) {
                requisicao(function(dadosRetorno) {
                   alert(dadosRetorno.mensagem); 
                });
                event.preventDefault();
            },
            filter: function() {
                return $(this).is(":visible");
            }
        }
    );
});


function requisicao(callback) {
  
    produto = {NOME:$("#NovoNomeProduto").val(),
             VALOR_ENTRADA:$("#NovoValorEntrada").val(),
             DESCRICAO:$("#novoDescricao").val(),
             IMAGEM:$("#novoImagemProduto").prop("src")};


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