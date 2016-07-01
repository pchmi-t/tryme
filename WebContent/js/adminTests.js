$(document).ready(function() {
	$.ajaxSetup({async: false});
	  $.get('/tryme/api/v1.0/tests/',
		  function (tests){
			console.log(tests);
		  var groups = tests.reduce(function(result, current) {
		        result[current.subject] = result[current.subject] || [];
		        result[current.subject].push(current);
		        return result;
		    }, {});
		  console.log(groups);
		  groups.forEach(function(gr, index){
			  index = index + 1;
			  $('#myTable tr:last').after(
					  '<tr> <td>' + index + 
					  '</td> <td>' +  gr.subject
					  +'</td> <td>' + gr.Value 
					  + '</td> </tr>');
		  });
   });
});