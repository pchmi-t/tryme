$(document).ready(function() {
	function getURLParameter(url, name) {
	    return (RegExp(name + '=' + '(.+?)(&|$)').exec(url)||[,null])[1];
	}
	
	var id = getURLParameter(location, 'id');
	
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