$(document).ready(function() {
  $("#permet").on("change", function() {
    $("#graded").show();
    
    // clear options in #category
    $('#category').empty();
    $('#category').append($('<option>', { 
        value: "",
        text : "Тестове" 
    }));
    
  });
  
  $("#graded").on("change", function() {

	    // clear options in #category
	    $('#category').empty();
	    $('#category').append($('<option>', { 
	        value: "",
	        text : "Тестове" 
	    }));
	  
	  var subject = $('#permet').find('option:selected').val();
	  var subjectText = $('#permet').find('option:selected').text();
	  var klass = $('#graded').find('option:selected').val();
	  var klassText = $('#graded').find('option:selected').text();
	  
	  $.ajaxSetup({async: false});
	  $.ajax({
			url : '/tryme/api/v1.0/tests/' + subject +'/grades/' + klass,
			type : "GET",
			success : function (data){
				data.forEach(function(item, index){
					  $('#category').append($('<option>', { 
					        value: item.id,
					        text : item.title 
				      }));
				  });
				$("#catd").show();
				$("#validationErrors").css({"display":"none"});
			},
			error : function (jqXHR, textStatus, errorThrown){
				$("#validationErrors").css({"display":"block"})
					.append("<div>Няма тестове за " + subjectText +", " + klassText +"!</div>");
			}
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
