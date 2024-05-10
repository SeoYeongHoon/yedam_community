jQuery(document).ready(function($) {

  var slideCount = $('#slider_wrapper ul li').length;
  var slideWidth = $('#slider_wrapper ul li').width();
  var slideHeight = $('#slider_wrapper ul li').height();
  var sliderUlWidth = slideCount * slideWidth;

  function moveLeft() {
    $('#slider_wrapper ul').animate({
      left: +500
    }, 200, function() {
      $('#slider_wrapper ul li:last-child').prependTo('#slider_wrapper ul');
      $('#slider_wrapper ul').css('left', '');
    });
  };

  function moveRight() {
    $('#slider_wrapper ul').animate({
      left: -500
    }, 200, function() {
      $('#slider_wrapper ul li:first-child').appendTo('#slider_wrapper ul');
      $('#slider_wrapper ul').css('left', '');
    });
  };

  $('a.control_prev').click(function() {
    moveLeft();
  });

  $('a.control_next').click(function() {
    moveRight();
  });

});