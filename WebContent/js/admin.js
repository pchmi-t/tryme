$(document).ready(function() {
	console.log("admiiiiiiiin");
	$.ajaxSetup({async: false});
	  $.get('/tryme/api/v1.0/scores/',
		  function (accounts){
		  accounts.forEach(function(account, index){
			  $('#myTable tr:last').after(
					  '<tr> <td>' + index + 
					  '</td> <td>' +  account.user.username
					  +'</td> <td>' + account.user.email 
					  + '</td> <td>' + account.totalScore 
					  + '</td> </tr>');
		  });
  });
});