<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Teste DATACOPER">
    <meta name="author" content="Daniel Defante">

    
    
    <link rel="stylesheet" href="http://localhost:8080/E-COMMERCE_LITE-war/css/font-awesome-4.7.0/css/font-awesome.min.css">

    
    
    <title>E-COMMERCE LITE</title>

    <!-- Bootstrap core CSS -->
    <link href="http://localhost:8080/E-COMMERCE_LITE-war/css/bootstrap.min.css" rel="stylesheet">

  </head>

  <body >


        <div class="container" >



            <div class="row" style="margin-top: 100px;">

                  <div class="col-md-8 col-md-offset-2 col-sm-10 col-sm-offset-1 col-lg-4 col-lg-offset-4 text-center">

                  <div class="panel panel-default">
                      <div class="panel-body">

                          <span class="fa fa-sign-out amarelo" aria-hidden="true" style="font-size: 100px;">
                              
                          </span>
                          <hr>
                          <h1>Logout realizado com sucesso!</h1>

                      </div>

                  </div>

              </div>

          </div>


        </div> <!-- /container -->
        
        <div class="col-lg-offset-2 text-left" >
            <a style="font-size: 20px; color: #ccc;" href="http://localhost:8080/E-COMMERCE_LITE-war/"><i class="fa fa-arrow-left"></i></a>
        </div>
<!--        <div class="panel-footer navbar-fixed-bottom text-center"><a href="http://localhost:8080/E-COMMERCE_LITE-war/" style="color: #fff;"><span class="fa fa-reply" aria-hidden="true"></span>&nbsp;Início</a></div>-->

 
    <script type="text/javascript">
        window.onload = function(){
            sessionStorage.clear();
        };
    </script>
    
  </body>
  
    <%
        request.getSession().invalidate();
    %>
</html>