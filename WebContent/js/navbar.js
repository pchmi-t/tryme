var windw = this;

$.fn.followTo = function ( pos ) {
    var $this = this,
        $window = $(windw);

    $window.scroll(function(e){
        if ($window.scrollTop() > pos) {
            $this.attr('style', 'position: absolute !important');
            console.log("absolute");
        }
        else {
            $this.attr('style', 'position: fixed !important');
            console.log("fixed");
        }
    });
};

$('.navbar-fixed-top').followTo(200);
