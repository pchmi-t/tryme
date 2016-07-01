$(document).ready(function() {
  $("#permet").on("change", function() {
    $("#graded").show();
    
    // clear options in #category
    $('#category').empty();
    $('#category').append($('<option>', { 
        value: "",
        text : "Тестове" 
    }));
    
//    console.log($(this).find('option:selected').val());
  });
  
  $("#graded").on("change", function() {

	    // clear options in #category
	    $('#category').empty();
	    $('#category').append($('<option>', { 
	        value: "",
	        text : "Тестове" 
	    }));
	  
	  var subject = $('#permet').find('option:selected').val();
	  var klass = $('#graded').find('option:selected').val();
//	  console.log($(this).find('option:selected').val());
	  
	  $.ajaxSetup({async: false});
	  $.get('/tryme/api/v1.0/tests/' + subject +'/grades/' + klass,
		  function (data){
//		  console.log(data);
		  data.forEach(function(item, index){
//			  console.log(item);
//			  console.log(item.id);
//			  console.log(item.title);
			  $('#category').append($('<option>', { 
			        value: item.id,
			        text : item.title 
		      }));
		  });
//		  console.log("the classsssssssss");
		  $("#catd").show();
	  });
    
  });
  var themeID;
  $("#catd").on("change", function() {
	  themeID = $('#catd').find('option:selected').val();
	  console.log(themeID);
    $("#starttd").show();
  });


  $('.start').click(function (){
	  $(this).attr('href', $(this).attr('href') + '?id=' + themeID);
  });
});
