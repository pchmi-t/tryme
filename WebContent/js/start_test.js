$(document).ready(function() {
	console.log("tuk saaaaaaaaam");
	function getURLParameter(url, name) {
	    return (RegExp(name + '=' + '(.+?)(&|$)').exec(url)||[,null])[1];
	}
	
	console.log("location.search " + location);
	var id = getURLParameter(location, 'id');
	console.log("id: " + id);
	
	$.ajaxSetup({async: false});
	  $.get('/tryme/api/v1.0/tests/' + id,
		  function (data){
		  $('.qTitle').text(data.title);
		  $('.qDescription').text(data.description);
    });
	
	$('.start').click(function (){
		  $(this).attr('href', $(this).attr('href') + '?id=' + id);
	  });
	  
});