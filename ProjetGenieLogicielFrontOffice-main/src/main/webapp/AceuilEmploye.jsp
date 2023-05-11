<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Acceuil</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
 <style>
     <%@ include file="./css/style.css"%>
    </style>
    
</head>    
    
</head>
<body>

    <header>
        <nav>
            <div class="logo">
                ENSA KHOURIBGA
            </div>
            <div class="toggle">
                <i class="fas fa-bars ouvrir"></i>
                <i class="fas fa-times fermer"></i>
            </div>
            <ul class="menu">
                <li><a href="AceuilEmploye.jsp">Home</a></li>
                <li><a href="<%=request.getContextPath()%>/recevoirDemandeEmploye">Demandes</a></li>
                <li><a class="butn" href="#"><% 
                if(session.getAttribute("nom")!=null){
                	out.println((String) session.getAttribute("nom"));
                	%>
                	<li><a class="butn" href="<%=request.getContextPath()%>/logout">
                	logout
                	</a></li>
                	<% 
                }else
                	response.sendRedirect("loginEmploye.jsp");
                	
                	%>
                	</a></li>
                	
                
            </ul>
        </nav>
    </header>
    <section class="landing">
        <div class="container">
            <div class="text">
              <h1>WELCOME, TO ENSA KHOURIBGA</h1>
              <p>Plateforme de Gestion des processus administrative Chez L Ecole Nationale des Sciences Appliqu&eacute;es de Khouribga.</p>
            </div>  
          </div>
          
    </section>
    
 <script type="text/javascript">
    <%@ include file="./javascript/app.js"%>

    </script></body>
</html>