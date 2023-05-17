
function loadProducts() {
		$.ajax({
	        url: '/FactoryApplication/products/getProductListJSON.action',
	        type: 'GET',
	        headers: {
	        	Accept : "application/json;charset=utf-8","Content-type" : "application/json;charset=utf-8"
	        },
	        success: function(response) {
	        	console.log("response - ",response);
	            var tableData = document.getElementById("bodyContent");
	            var table = "<center><table><th>Product Id</th><th>Product Name</th><th>Category</th><th>Option</th>";
	            $.each(response,function(i,product){
	            	var prodId=product.productId;
	            	table += "<tr>";
	                table += "<td>" + product.productId + "</td>";
	                table += "<td>" + product.productName + "</td>";
	                table += "<td>" + product.category + "</td>";
	                //console.log(product.productId);
	                table += "<td><a href='/FactoryApplication/products/viewProduct.action?productId=" + product.productId + "'>view</a></td></tr>";
	            })
	            table += "</table></center>";
	            tableData.innerHTML = table;
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.log(jqXHR,"\n",textStatus,"\n", errorThrown);
	        }
	    });
	}
	
	
function loadSkills() {
		console.log("hello");
		var urlParams = new URLSearchParams(window.location.search); // Get URL parameters
    	var productId = urlParams.get('productId');
		$.ajax({
	        url: '/FactoryApplication/products/getSkillListJSON.action',
	        data:{productId: productId},
	        type: 'GET',
	        headers: {
	        	Accept : "application/json;charset=utf-8","Content-type" : "application/json;charset=utf-8"
	        },
	        success: function(response) {
				var list = document.getElementById("menuChoice");
	        	console.log("response - ",response);
	        	var htmlString="<ul>";
	        	$.each(response,function(i,skill){
					htmlString+="<li>"+skill+"</li>";
				});
				htmlString+="</ul>";
				list.innerHTML=htmlString;
	            
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.log(jqXHR,"\n",textStatus,"\n", errorThrown);
	        }
	    });
	}
	
	
function loadSpare() {
		console.log("hello");
		var urlParams = new URLSearchParams(window.location.search); // Get URL parameters
    	var productId = urlParams.get('productId');
		$.ajax({
	        url: '/FactoryApplication/products/getSpareListJSON.action',
	        data:{productId: productId},
	        type: 'GET',
	        headers: {
	        	Accept : "application/json;charset=utf-8","Content-type" : "application/json;charset=utf-8"
	        },
	        success: function(response) {
				var tableData = document.getElementById("menuChoice");
	        	console.log("response - ",response);
	        	var htmlString="<center><table><th>Spare Id</th><th>Spare Name</th><th>Price</th><th>Quantity required for single Product</th><th>Available Quantity</th>";
	        	$.each(response,function(i,spare){
					htmlString+="<tr><td>"+spare.spareId+"</td>";
					htmlString+="<td>"+spare.spareName+"</td>";
					htmlString+="<td>"+spare.price+"</td>";
					htmlString+="<td>"+spare.quantityToProduceSingleProd+"</td>";
					htmlString+="<td>"+spare.availableQuantity+"</td></tr>";
				});
				htmlString+="</table></center>";
				tableData.innerHTML=htmlString;
	            
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.log(jqXHR,"\n",textStatus,"\n", errorThrown);
	        }
	    });
	}
	
function loadCapacityPlan(){
		var urlParams = new URLSearchParams(window.location.search); 
    	var productId = urlParams.get('productId');
		$.ajax({
	        url: '/FactoryApplication/products/getCapacityPlanJSON.action',
	        data:{productId: productId},
	        type: 'GET',
	        headers: {
	        	Accept : "application/json;charset=utf-8","Content-type" : "application/json;charset=utf-8"
	        },
	        success: function(response) {
				var divTag = document.getElementById("menuChoice");
	        	console.log("response - ",response);
	        	var htmlString="<center><div class='flex-container'><div class='flex-row'>";
	        	htmlString+="<div class='flex-item'><h3>Total Orders</h3><h2>"+response.totalOrders+"</h2></div>";
	        	htmlString+="<div class='flex-item'><h3>Number of Days Required</h3><h2>"+response.DaysRequired+"</h2></div>";
	        	htmlString+="<div class='flex-item'><h3>Products Per Day</h3><h2>"+response.productPerDay+"</h2></div>";
	        	//htmlString+="<h4>Total Orders : "+response.totalOrders+"</h4>";
	        	/*$.each(response,function(key,value){
					htmlString+="<div class='flex-item'>"
					htmlString+="<h4>"+key+" : "+value+"</h4></div>";
				});*/
				htmlString+="</div></div></center>";
				divTag.innerHTML=htmlString;
	            
	        },
	        error: function(jqXHR, textStatus, errorThrown) {
	            console.log(jqXHR,"\n",textStatus,"\n", errorThrown);
	        }
	    });
}


  


