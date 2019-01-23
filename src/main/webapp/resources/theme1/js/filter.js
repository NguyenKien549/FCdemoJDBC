/**
 * 
 */function myFunction() {
  var input, filter, table, tr, td, i;
  input = document.getElementById("myInput");
  input1 = document.getElementById("myInput1");
  
  filter = input.value.toUpperCase();
  filter1 = input1.value.toUpperCase();
  
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  
  if(filter){
    	for (i = 0; i < tr.length; i++) {
          td = tr[i].getElementsByTagName("td")[0];
          td1=tr[i].getElementsByTagName("td")[2];
          if (td) {
            if (td1.innerHTML.toUpperCase().indexOf(filter1) > -1 && 	td.innerHTML.toUpperCase().indexOf(filter) > -1) {
            	
            	tr[i].style.display = "";
            	
            } else {
              tr[i].style.display = "none";
            }
          }       
      	}
    	
        
  
  }else{
  
        for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[2];
        if (td) {
          if (td.innerHTML.toUpperCase().indexOf(filter1) > -1) {
            tr[i].style.display = "";
          } else {
            tr[i].style.display = "none";
          }
        }       
      }
  }
}
