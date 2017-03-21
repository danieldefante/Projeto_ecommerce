$(function() {
    $("#cadastroForm").find("input,textarea").jqBootstrapValidation(
        {
            preventSubmit: true,
            submitError: function($form, event, errors) {
            },
            submitSuccess: function($form, event) {
                cadastrar();
                event.preventDefault();
            },
            filter: function() {
                return $(this).is(":visible");
            }
        }
    );
});


function requisicao(envio, callback) {

    $.ajax({
       type: 'POST',
       url: "http://localhost:8080/E-COMMERCE_LITE-war/servicos/login/inserir",
       data: JSON.stringify(envio),
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

function cadastrar(){
    var envio = {usuario: $("#usuario").val(),
                senha: $.sha256($("#senha").val()),
                email:$("#email").val(),
                nome:$("#nome").val()
                };
    requisicao(envio, function(retorno){
        
        if(retorno.sucesso){
            $("#usuario").val("");
            $("#senha").val("");
            $("#email").val("");
            $("#nome").val("");
            location.href = "http://localhost:8080/E-COMMERCE_LITE-war/login.html";
        }
        
        alert(retorno.mensagem);
            
        
    });
}
