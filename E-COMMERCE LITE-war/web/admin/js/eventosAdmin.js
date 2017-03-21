

$(function() {
    $("#formCadastroProduto").find("input,textarea").jqBootstrapValidation(
        {
            preventSubmit: true,
            submitError: function($form, event, errors) {
                $("#salvarTodos").attr("value","enviarFalse");
            },
            submitSuccess: function($form, event) {
                
                novoProduto();
                
                event.preventDefault();
            },
            filter: function() {
                return $(this).is(":visible");
            }
        }
    );
});


$(document).on("click", ".novoProduto", function(){
//    $(this).addClass("active");
    
    if($("#formCadastroProduto").is(":visible")){
         $("#formCadastroProduto").submit();
        
    }else{
        $("#formCadastroProduto").fadeIn(400);
//        $(".amostragemItem").removeClass("active");
        $("#listaNovosProdutos").append('<li id="produto1" role="presentation" class="active amostragemItem"><a><i class="fa fa-cube" aria-hidden="true"></i>&nbsp;Produto1</a></li>');
        $("#divImgs").append('<img value="produto1" class="imgOculta hidden" width="180" height="180" src=""/>');
    
    }
    
    
});

$(document).on("click", "#salverDespesasTotais", function(){
    if($("#despesaTotais").val() != ""){
        sessionStorage.setItem("despesasGerais", $("#despesaTotais").val());
        $("#modalDespesas").modal("toggle");
    }else{
        $("#despesaTotais").focus();
    }
});

$(document).on("click", "#salvarmargemLucro", function(){
    if($("#margemLucro").val() != ""){
        sessionStorage.setItem("margemLucro", $("#margemLucro").val());
        $("#modalLucro").modal("toggle");
    }else{
        $("#margemLucro").focus();
    }
    
});

$(document).on("click", "#salvarTodos", function(){

    $(this).attr("value", "enviarTrue");
    $("#formCadastroProduto").submit();

    
});


function novoProduto(){
    
    produto = { produto: $(".active").attr("id"),
                nome:$("#NovoNomeProduto").val(),
                valor_custo:$("#NovoValorCusto").val(),
                descricao:$("#novoDescricao").val()
            };
        
    var listaProdutos = [];


    if(sessionStorage.getItem("listaProdutos")){
        listaProdutos = JSON.parse(sessionStorage.getItem("listaProdutos"));
        listaProdutos.push(produto);
    }else{
        listaProdutos.push(produto);
    }

    sessionStorage.setItem("listaProdutos", JSON.stringify(listaProdutos));


    if($("#salvarTodos").attr("value") == "enviarTrue"){
        enviarProdutos();
    }else{
        //limpa os campos
        $("#NovoNomeProduto").val("");
        $("#NovoValorCusto").val("");
        $("#novoDescricao").val("");
        $("#inputImagemProduto").val("");
        $(".imgOculta").addClass("hidden"); 
        //cria nova img oculta
        $(".amostragemItem").removeClass("active");
        var qtdP = $("#listaNovosProdutos > li").length;
        $("#listaNovosProdutos").append('<li id="produto'+qtdP+'" role="presentation" class="active amostragemItem"><a><i class="fa fa-cube" aria-hidden="true"></i>&nbsp;Produto'+qtdP+'</a></li>');
        $("#divImgs").append('<img value="produto'+qtdP+'" class="imgOculta hidden" width="180" height="180" src=""/>');
    }
}

function enviarProdutos(){
    
    var listaProdutos = [];
    var listaProdutosImg = [];
    listaProdutos = JSON.parse(sessionStorage.getItem("listaProdutos"));
    
    listaProdutosImg = [];
    $.each(listaProdutos, function(i, valor){
        listaProdutosImg.push({nome:valor.nome,
                               valor_custo:valor.valor_custo,
                               descricao:valor.descricao,
                               imagem: $('img[value="'+valor.produto+'"]').attr("src")
                                });
    });

    
    var margemLucro;
    if(sessionStorage.getItem("margemLucro")){
        margemLucro = sessionStorage.getItem("margemLucro");
    }else{
        margemLucro = "0";
    }
    
    var despesasGerais;
    if(sessionStorage.getItem("despesasGerais")){
        despesasGerais = sessionStorage.getItem("despesasGerais");
    }else{
        despesasGerais = "400";
    }
 
     var envio = JSON.stringify({data: listaProdutosImg, margemLucro: margemLucro, despesasGerais: despesasGerais});
     console.log(envio);
     
    //envia a requisicao
    requisicao(envio, function(dadosRetorno) {
        if(dadosRetorno.sucesso){
            limparMemoria();
            alert(dadosRetorno.mensagem);
        }else{
            alert(dadosRetorno.mensagem); 
        }

    });
    
}

function limparMemoria(){
    sessionStorage.removeItem("despesasGerais");
    sessionStorage.removeItem("marginLucro");
    sessionStorage.removeItem("listaProdutos");
    $("#salvarTodos").attr("value","enviarFalse");
    $("#divImgs").empty();
    $("#NovoNomeProduto").val("");
    $("#NovoValorCusto").val("");
    $("#novoDescricao").val("");
    $("#inputImagemProduto").val("");
    $(".imgOculta").addClass("hidden"); 
    $("#listaNovosProdutos").empty().append('<li class="novoProduto" role="presentation"><a><i class="fa fa-plus" aria-hidden="true"></i>&nbsp;Novo</a></li>');
    $("#formCadastroProduto").fadeOut(400);   
}
$(document).on("click", "#limparMemoria", function(){
    limparMemoria();
});

function requisicao(envio, callback) {

    $.ajax({
       type: 'POST',
       url: "http://localhost:8080/E-COMMERCE_LITE-war/servicos/produto/inserir",
       data: envio,
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
        var elementoImg = $('img[value="'+$(".active").attr("id")+'"]');

        elementoImg.attr("src", base64Data);   
        elementoImg.removeClass("hidden");   
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