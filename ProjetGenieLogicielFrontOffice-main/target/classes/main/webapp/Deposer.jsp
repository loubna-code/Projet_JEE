<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Deposer une demande</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://raw.githubusercontent.com/OussamaGoauiche/E-commerce/main/forme.css" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
   <style>
     <%@ include file="./css/style.css"%>
     <%@ include file="./css/forme.css"%>
     
    </style>
    
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
               <li><a href="Acceuil.jsp">Home</a></li>
                <li><a href="Deposer.jsp">D&eacute;poser</a></li>
                <li><a href="<%=request.getContextPath()%>/listerDemandeByCin">Suivre</a></li>
              
                <li><a class="butn" href="#"><% 
                if(session.getAttribute("nom")!=null){
                	out.println((String) session.getAttribute("nom"));
                	%>
                	<li><a class="butn" href="<%=request.getContextPath()%>/logout">
                	logout
                	</a></li>
                	<% 
                }else
                	response.sendRedirect("login.jsp");
                	
                	%>
                	</a></li>
            </ul>
        </nav>
    </header>
  <div class="container">
    
    <div class="row">
        <div class="col-lg-12 col-md-9">
            <h1 style="font-size:bold ">Creer demande</h1>
        </div>
    </div>
	
    <p style="color:red;font-size:20px">${message2}</p>

 
       <form action="<%=request.getContextPath()%>/creerDemande" class="sign-up-form" method="POST" enctype='multipart/form-data'>
             <div class='container'>

         
            <div class='row'>

                
                    <!--<form class="form-horizontal" role="form">-->
                
                    <div class="form-group">
                        <label for="description" class="col-sm-2 control-label">DESCRIPTION :</label>
                        <div class="col-sm-8">
                           <input type="text" placeholder="Description" name="description"required/>
                        </div>
                    </div>
                   <div class="form-group">
                            <label for="origine" class="col-sm-2 control-label">PROCEDURE :</label>
                            <div class="col-sm-8">
                                <select class="form-control" name="choix2">
                                  <option>Procedure1</option>
                                    <option>Procedure2</option>
                                    <option>Procedure3</option>
                                    <option>Procedure4</option>
                                </select>
                            </div>
                        </div>
                    

                </div>
            
            <div class="row">
               

                <div class="col-lg-6">     
                    <div class="form-group">
                        <label for="nam" class="col-sm-2 control-label">Documents:</label>
                        <div class="col-sm-8">
                            <input type="file" class="form -control" name="files" id="nam" required>
                        </div>
                    </div>
                    <br>
                </div>
            <div class="col-xs-12">
                  <button type="submit" class="btn btn-primary">Submit</button>
                </div>
        </div>
        
        </div>
          </form>
          
    </div>
      <script type="text/javascript">
    <%@ include file="./javascript/app.js"%>

    </script>
</body>
</html>