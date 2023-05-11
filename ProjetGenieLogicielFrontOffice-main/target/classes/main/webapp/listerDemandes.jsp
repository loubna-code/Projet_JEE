<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.List"%>
<%@page import="Beans.Demande"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>listerDeanndes</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="./css/style.css" />
    <link rel="stylesheet" href="./css/forme.css" />
    <link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
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

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">Liste des demandes</h3>
            <p style="color:red;font-size:20px">${message}</p>
			
			<hr>
		
			
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Description</th>
						<th>Etat</th>
						<th>Nom du procedure</th>
					     <th>Jeton</th>
					      <th>Modifier</th>
					      <th>Supprimer</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="demande" items="${listDemandes}">

						<tr>
							<td><c:out value="${demande.description}" /></td>
							<td><c:out value="${demande.etat}" /></td>
							<td><c:out value="${demande.getProcedureNom().nom_procedure}" /></td>
							<td><c:out value="${demande.jeton}" /></td>

							<td><a  class="btn btn-primary" href="<%=request.getContextPath()%>/modifier?id=<c:out value='${demande.id}' />">Modifier</a>
					        <td><a  class="btn btn-primary" href="<%=request.getContextPath()%>/delete?id=<c:out value='${demande.id}' />">Supprimer</a>
								

							<!--  <td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
          							<button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
    <script type="text/javascript">
    <%@ include file="./javascript/app.js"%>

    </script>
</body>
</html>