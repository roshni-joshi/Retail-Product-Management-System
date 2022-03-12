<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand" href="#">ShopMart</a>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <form method="post" action="searchById.html" class="form-inline " >
			
			 <div class="md-form ml-5">
				<input type="number" class="form-control"  name="id" placeholder="Enter product id" /> <br/>
				</div>
				<button type="submit" class="btn btn-outline-success my-1 my-sm-0">Search By Id</button>
			
		</form>
		
		<br/><br/>
		<form method="post" action="searchByName.html" class="form-inline my-2 my-lg-0">
			<div class="md-group ">
				<input type="text" class="form-control mr-sm-2"  name="name" placeholder="Enter product name" /><br/> 
			</div>
				<button type="submit" class="btn btn-outline-success my-1 my-sm-0">Search By Name</button>
			
		</form> 
		
        <a href="./wishlist.html">
          <button
            class="btn btn-outline-warning my-2 mx-1 my-sm-0"
            type="submit"
          >
            <i style="font-size: 20px" class="fa">WishList</i>
          </button>
        </a>
        
        <a href="./logout.html">
          <button
            class="btn btn-outline-warning my-2 mx-1 mr-3 my-sm-0"
            type="submit"
          >
            <i style="font-size: 20px" class="fa">Logout</i>
          </button>
        </a>
       
      </div>
      <a class="navbar-brand" href="#">${sessionScope.username}</a>
    </nav>
</body>
</html>