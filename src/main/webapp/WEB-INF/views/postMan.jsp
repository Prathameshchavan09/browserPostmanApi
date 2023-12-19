<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PostMan</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>

</head>
<body>

	<div class="container">
		<nav class="navbar navbar-light bg-light" style="margin-top: 100px;">
			<form class="form-inline">
				<div class="dropdown" style="margin-right: 10px;">
					<button class="btn btn-secondary dropdown-toggle" type="button"
						id="options" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">Choose option</button>
					<ul class="dropdown-menu" id="select_httpMethods"
						aria-labelledby="options">
						<li><a class="dropdown-item" id="insertMethod" href="#">Post</a></li>
						<li><a class="dropdown-item" id="insertMethod" href="#">Get</a></li>
						<li><a class="dropdown-item" id="insertMethod" href="#">Update</a></li>
						<li><a class="dropdown-item" id="insertMethod" href="#">Delete</a></li>
			            <li><a class="dropdown-item" id="insertMethod" href="#">getById</a></li>
					
					</ul>
				</div>

				<input class="form-control" type="search" id="userInput"
					placeholder="Search" aria-label="Search"
					style="width: 800px; height: 50px; margin-right: 10px;">

				<button class="btn btn-outline-success my-2 my-sm-0" type="button"
					id="userInput" onclick="updateURLAndHitAndAddUser()"
					style="height: 50px;">Send</button>

			</form>
		</nav>
	</div>

	<!-- Insert Student -->

	<div class="container"
		style="justify-content: center; align-items: center; margin-left: 800px; margin-top: 100px;">
		<form id="addStudent">
			  <input type="hidden" value="${studentId}" id="userr" /> 
			<label for="jsonData">Enter JSON Data:</label><br>
			<textarea id="jsonData" name="jsonData" rows="4" cols="50"></textarea>
			<br>
		</form>
	</div>
	<!-- Insert Student -->


	<script>
	
	$(document).ready(function() {
	    $(".dropdown-menu a").click(function() {
	        $("#options").text($(this).text());
	    });
	});



	function updateURLAndHitAndAddUser() {
		updateURL();
		addUser();

	}
	
	const userInputField = document.getElementById('userInput');
	userInputField.addEventListener('input', updateURL);

	function updateURL() {
	    const userInput = userInputField.value;
	    const sanitizedInput = userInput.replace(/%2F/g, '/');
	    const parts = sanitizedInput.split('/');
	    const action = parts[0];
	    const value = parts[1];
	    const numericValue = value ? '/' + parseInt(value, 10) : '';

	    const newURL = '/browserPostmanApi/' + action + numericValue;

	    window.history.pushState({ path: newURL }, '', newURL);
	}


	
	function addUser() {
	    var httpMethod = document.getElementById('options').innerText.trim().toLowerCase();
	    const jsonData = document.getElementById('jsonData').value;

	    if (httpMethod === 'post') {
	       
	        var studentData = JSON.parse(jsonData);
               $.ajax({
	            type: 'POST',
	            url: 'insertStudent',
	            contentType: 'application/json',
	            Accept: 'application/json',
	            data: JSON.stringify(studentData),
	            success: function(response) {
	                alert('Data successfully sent to the server!');
	                console.log("User Added Successfully")
	                
	            },
	            error: function(xhr, status, error) {
	                console.error('Error:', error);
	                alert('Failed to send data. Please try again.');
	            }
	        });
	    }
	    
	    if (httpMethod === "get") {

	    	 $.ajax({
	             type: 'GET',
	             url: 'users',
	             contentType: 'application/json',
	             dataType: 'json',
	             success: function(response) {
	                 const studentsData = JSON.stringify(response, null, 2);
	                 $('#jsonData').val(studentsData);
	                 console.log("Students fetched successfully");
	             },
	             error: function(xhr, status, error) {
	                 console.error('Error:', error);
	             }
	         });
	     }
	    
	    if (httpMethod === 'update') {
	    	 var studentIdd = document.getElementById("userInput").value;
	    	 var studentData = JSON.parse(jsonData);
	        $.ajax({
	            type: 'PUT',
	            url: 'update/' + studentIdd,	
	            contentType: 'application/json',
	            Accept: 'application/json',
	            data: JSON.stringify(studentData),
	            success: function(response) {
	                console.log("User updated Successfully")
	                
	            },
	            error: function(xhr, status, error) {
	                console.error('Error:', error);
	                alert('User updated Successfully');
	                
	            }
	        });
	    }
	    
	    if (httpMethod === "delete") {
	    	 var studentIdd = document.getElementById("userInput").value;
	    	$.ajax({
	            type: 'DELETE',
	            url: 'delete/' + studentIdd,	
	            success: function(response) {
	                console.log("User Deleted Successfully");
	                alert("User Deleted Successfully")
	            },
	            error: function(xhr, status, error) {
	                console.error("Error:", error);
	                alert("User Deleted Successfully")
	  	             
	            }
	        });
	    }
	    
	    if (httpMethod === "getbyid") {
	    	 var studentIdd = document.getElementById("userInput").value;
	    	 $.ajax({
	             type: 'GET',
	             url: 'getStudentById/' + studentIdd,
	             contentType: 'application/json',
	             dataType: 'json',
	             success: function(response) {
	                 const studentsData = JSON.stringify(response, null, 2);
	                 $('#jsonData').val(studentsData);
	                 console.log("Students fetched successfully");
	             },
	             error: function(xhr, status, error) {
	                 console.error('Error:', error);
	                 alert("Student Not Present");
	             }
	         });
	    }
	    
	}
		

</script>





</body>
</html>
