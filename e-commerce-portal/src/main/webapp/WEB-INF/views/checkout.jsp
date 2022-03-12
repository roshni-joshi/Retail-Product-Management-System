<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<title>Shopping Site</title>
<head>
<style type="text/css">
#imgs{
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>

</head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" href="./style.css" />

<body>
	<!-- navbar for the pages -->
	 <%@include file="/WEB-INF/views/fragments/header2.jsp"%>
	<!-- body section of the page use jsp -->
	<!-- use loop to fetch the data and display it -->

	<div class="container mt-5">
		<div class="row">
				<div class="col-5">
						<img id="imgs" src="${product.imageName}" />
				</div>
						<div class="col-7">
						<div class="card-body">
							<h3 class="card-title">${product.name}</h3>
							<p class="card-text">${product.description}</p>
						
							<p class="price">
								<b>Price : ${product.price}</b>
							</p>
							
							<span id="rating">&nbsp;${product.rating} <i
								style="font-size: 15px" class="fa">&#xf005;</i>
							</span>
							
					
							<h5 class="card-subtitle my-2 rounded-lg bg-dark text-light">&nbsp;Vendor Details : &nbsp;</h5>
							<p class="card-text">Vendor name : ${cart.vendor.vendorName}</p>
							<p class="card-text">DeliveryCharge : ${cart.vendor.deliveryCharge}</p>
							
							<h5 class="my-2 card-subtitle rounded-sm bg-dark text-light">&nbsp;Order Details : </h5>
							<p class="card-text">Zipcode : ${cart.zipcode}</p>
							<p class="card-text">Delivery Date : <fmt:formatDate value="${cart.deliveryDate}" pattern="dd/MM/yyyy" /></p>
							<p class="card-text">Quantity : ${quantity} </p>
							
						</div>
					</div>
					
				
			
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
		integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
		crossorigin="anonymous"></script>

</body>
</html>
