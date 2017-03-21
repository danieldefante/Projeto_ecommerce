$(function() {
    $("#formLogin").find("input,textarea").jqBootstrapValidation(
        {
            preventSubmit: true,
            submitError: function($form, event, errors) {
            },
            submitSuccess: function($form, event) {
                login();
                event.preventDefault();
            },
            filter: function() {
                return $(this).is(":visible");
            }
        }
    );
});


function login(){
    var envio = {USUARIO: $("#inputUsuario").val(),
                SENHA: $.sha256($("#inputSenha").val())};
    $.when(

        $.ajax({
            type: 'POST',
            url: "http://localhost:8080/E-COMMERCE_LITE-war/servicos/login/validacao",
            data: JSON.stringify(envio),
            headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            }
        })


	).then(function(dadosRetorno) {  
        console.log(dadosRetorno);
            if(dadosRetorno.sucesso){
                
                sessionStorage.setItem('logSessao', JSON.stringify(dadosRetorno.data));

                $.ajax({
                    type: 'POST',
                    url : 'http://localhost:8080/E-COMMERCE_LITE-war/j_security_check',
                    data: $('#formLogin').serialize()
                            
                    
                }).done(function (){
                    if(dadosRetorno.data.grupo == "administradores"){
                        location.href = "http://localhost:8080/E-COMMERCE_LITE-war/admin/admin.html";
                    }else{
                        location.href = "http://localhost:8080/E-COMMERCE_LITE-war/";
                    }
                });
                
                
            }else{
                $("#alerta").empty().append('<a href="#" id="fecharAlert" class="close">&times;</a><strong>Erro!</strong> Usuário ou senha incorreta!').fadeIn();
            }
	});

 }
 
$(document).on("click", "#fecharAlert", function(evt){
    $("#alerta").fadeOut(400);

    return false;
});

$(document).on("click", "#esqueceuSenha", function(evt){
    $("#alerta").empty().append('<a href="#" id="fecharAlert" class="close">&times;</a><strong>Suporte!</strong> ecommerce lite@suporte!').fadeIn();
    return false;
});

function verificarSessao(){
    var  sessao = JSON.parse(sessionStorage.getItem("logSessao")); 
    
    
    if(sessao == null){
        $("#painelLogin").empty().append('<div class="col-lg-8 col-lg-offset-2"><div class="modal-body"><div style="font-size: 100px; color:#555;"><i class="fa fa-user" aria-hidden="true"></i></div><hr class="star-primary"><h2>Entrar</h2><form id="formLogin" role="form" method="post" class="login" action="j_security_check" novalidate><div class="text-left row control-group"><div class=" form-group col-xs-12 floating-label-form-group controls"><label>Usuário:</label><input value="admin" id="inputUsuario" type="text" class="form-control" placeholder="Usuário:" name="j_username" required data-validation-required-message="Por favor, digite o usuário."><p class="help-block text-danger"></p></div></div><div class="text-left row control-group"><div class="form-group col-xs-12 floating-label-form-group controls"><label>Senha:</label><input value="admin" id="inputSenha" type="password" class="form-control" placeholder="Senha:" name="j_password" required data-validation-required-message="Por favor, digite a senha."><p class="help-block text-danger"></p></div></div><br><div id="success"></div><div id="alerta" class="alert alert-danger alert-dismissable" hidden=""></div><div class="row"><div class="form-group col-xs-12"><button name="submit" value="Login" class="btn btn-info btn-lg"><i class="fa fa-sign-in" aria-hidden="true"></i>&nbsp;Entrar</button></div></div></form><p class="text-center"><a id="esqueceuSenha">Esqueceu sua senha?</a></p><br><h5><a class="portfolio-link" title="Cadastrar" href="http://localhost:8080/E-COMMERCE_LITE-war/cadastro.html"><i class="fa fa-user-plus" aria-hidden="true"></i>&nbsp;Criar uma conta</a></h5><div style="font-size: 20px;" class="text-left" ><a href="http://localhost:8080/E-COMMERCE_LITE-war/"><i class="fa fa-arrow-left"></i></a></div></div></div>');
    }else{
        
        $("#painelLogin").empty().append('<div class="col-lg-8 col-lg-offset-2"><div class="modal-body"><div style="font-size: 100px; color:#555;"><i class="fa fa-user" aria-hidden="true"></i></div><hr class="star-primary"><h2>Seja bem vindo '+sessao.nome+'</h2><div style="font-size: 20px;" class="text-left" ><a href="http://localhost:8080/E-COMMERCE_LITE-war/"><i class="fa fa-arrow-left"></i>&nbsp;Voltar</a></div><br><hr> <div style="font-size: 20px;" class="text-right" ><a href="http://localhost:8080/E-COMMERCE_LITE-war/logout.jsp"><i class="fa fa-sign-out"></i>&nbsp;Deslogar</a></div></div></div>');
    }
}
    
