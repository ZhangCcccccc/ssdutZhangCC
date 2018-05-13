//  Andy Langton's show/hide/mini-accordion @ http://andylangton.co.uk/jquery-show-hide

// this tells jquery to run the function below once the DOM is ready
$(document).ready(function() {

// choose text for the show/hide link - can contain HTML (e.g. an image)
var showText='查看过去的记录';
var hideText='隐藏过去的记录';

// initialise the visibility check
var is_visible =false;

// append show/hide links to the element directly preceding the element with a class of "toggle"
$('.hide').prev().append(' <a href="javascript:void(0);" class="hideLink"><font color=red>'+showText+'</font></a>');

// hide all of the elements with a class of 'toggle'
$('.hide').hide();

// capture clicks on the toggle links
$('a.hideLink').click(function() {

// switch visibility
is_visible = !is_visible;

// change the link text depending on whether the element is shown or hidden
if ($(this).text()==showText) {
$(this).html('<font color=red>'+hideText+'</font>');
$(this).parent().next('.hide').slideDown('slow');
}
else {
$(this).html('<font color=red>'+showText+'</font>');
$(this).parent().next('.hide').slideUp('slow');
}

// return false so any link destination is not followed
return false;

});
});