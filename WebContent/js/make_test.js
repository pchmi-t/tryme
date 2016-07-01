$(document).ready(function() {
    function getURLParameter(url, name) {
        return (RegExp(name + '=' + '(.+?)(&|$)').exec(url)||[,null])[1];
    }

    var endTest = new Date(new Date().getTime() + 30 * 60000);

    $('#clock').countdown(endTest, function(event) {
        $(this).html('<h1>' + event.strftime('%M:%S') + '</h1>');
    });

    totalScore = 0;
    var currentQuestion = 0;
    var totalScore = 0;
    var id = getURLParameter(location, 'id');
    var currentQuestion = 0;
    var questions;

    console.log(id);
      // =============

    $.ajaxSetup({async: false});
    $.get('/tryme/api/v1.0/tests/' + id, function(data){
         questions = data.questions;
         console.log(questions);
         var qHtml = buildQuestion(questions[currentQuestion]);
         $(".question").html(qHtml);
    });

    $('#next').on('click', getNext);
    $('#prev').on('click', getPrev);
    $('#submit').on('click', submit);

    function updateProgress() {
        var percentage = Math.floor((currentQuestion/questions.length)*100);
        $('.progress-bar').css('width', percentage+'%');
        $('.progress-bar').html(percentage+'%');
    }

        // ===============================

    function getNext() {
        var q = questions[currentQuestion];
        var selected = $('input[name=ans]:checked', '#question');
        if (selected.length > 0) {
         checkIfCorrect(selected.val(), q)
        } else {
            $("#errormsg").show();
            $("#errormsg").slideToggle(3000);
            return;
        }

        currentQuestion++;
        if (currentQuestion >= questions.length) {
            currentQuestion = questions.length - 1;
            return;
        }

        var qHtml = buildQuestion(questions[currentQuestion]);
        $(".question").html(qHtml);
        $("#prev").removeAttr('disabled');

        updateProgress();

        if (currentQuestion >= questions.length - 1) {
            $('#next').attr('disabled','disabled');

        }
    }

    function submit() {
        var q = questions[currentQuestion];
        var selected = $('input[name=ans]:checked', '#question');
        if (selected.length > 0) {
         checkIfCorrect(selected.val(), q)
         sessionStorage.setItem("totalScore", totalScore);
         sessionStorage.setItem("result",  Math.floor((totalScore / (questions.length * 10))*100) );
         sessionStorage.setItem("timeRemaining", $('#clock h1').text());
         sessionStorage.setItem("maxScore", questions.length * 10);

         var username = sessionStorage.getItem('accountUser');
         $.ajaxSetup({async:false});
         $.ajax({
              type: "POST",
              url: '/tryme/api/v1.0/scores',
              data: JSON.stringify({ "username": username, "score" : totalScore }),
              contentType : 'application/json',
            });

         window.location.href= "completeTest.html";
         
        } else {
            $("#errormsg").show();
            $("#errormsg").slideToggle(3000);
            return;
        }
    }

    function checkIfCorrect(selectedVal, q) {
        isCorrect = false;
        q.answers.forEach(function(item) { if (item.content === selectedVal && !isCorrect) {
            isCorrect = item.correct;
        } });

        if (isCorrect) {
            totalScore += 10;
        }
    }

    function updateProgress() {
        var percentage = Math.floor((currentQuestion/questions.length)*100);
        $('.progress-bar').css('width', percentage+'%');
        $('.progress-bar').html(percentage+'%');
    }

    function getPrev() {
        currentQuestion--;
        if (currentQuestion < 0 ) {
            currentQuestion = 0;
            return;
        }

        var qHtml = buildQuestion(questions[currentQuestion]);
        $(".question").html(qHtml);
        $("#next").removeAttr('disabled');
        updateProgress();
        if (currentQuestion == 0 ) {
            $('#prev').attr('disabled','disabled');
        }
    }

    function buildQuestion(question) {
        var qHtml = '<h2>' + question.text + '</h2>';
        qHtml += ' <br/><form id="question">';
        question.answers.forEach(function(answer) {
                qHtml += '<input type="radio" name="ans" value="' + answer.content + '">   ' + answer.content +'<br>';
            }
        );
        qHtml += '<br/></form>';
        return qHtml;
    }
});
