$(document).ready(function() {

  $(window).scroll(function () {
      
    if ($(window).scrollTop() < 81) {
      $('.upper').slideDown("fast");
      $('.navbar').attr("style", "top: 0 !important");
    }
    if ($(window).scrollTop() > 80) {
      $('.upper').slideUp("fast");
      $('.navbar').attr("style", "top:" + $(window).scrollTop() + " !important");
    }
  });
});
