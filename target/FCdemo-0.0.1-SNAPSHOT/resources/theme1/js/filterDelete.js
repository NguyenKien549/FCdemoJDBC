 var deleted=[];

 
 function myFunction() {
	 		deleted.splice(0,deleted.length);
		  var input, filter, table, tr, td, i,check;
		  input = document.getElementById("myInput");
		  
		  
		  filter = input.value.toUpperCase();
		  
		  
		  table = document.getElementById("myTable");
		  tr = table.getElementsByTagName("tr");
		  for (i = 0; i < tr.length; i++) {
              td = tr[i].getElementsByTagName("td")[1];
              if (td) {
                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
                  tr[i].style.display ="";
                  deleted.push(td.innerHTML);
                } else {
                  tr[i].style.display = "none";
                }
              }       
            }
		  
		  console.log(deleted);
//		  $('#select_all').change(function() {
//			  for (i = 0; i < tr.length; i++) {
//	              td = tr[i].getElementsByTagName("td")[1];
//	              check = tr[i].getElementsByName("td")[5];
//	              if (td) {
//	                if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
//	                	
//	                  tr[i].style.display ="";
//	                  	check.prop('checked', true);
//	                } else {
//	                  tr[i].style.display = "none";
//	                }
//	              }       
//	            }
//		  });
		  
		}