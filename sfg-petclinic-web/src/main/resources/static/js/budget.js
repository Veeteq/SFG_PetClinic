$(document).ready(function(){
	var wrapper = $(".doc-container");
	var addElement = $(".add-element");
	
	$(addElement).click(function(e){
		e.preventDefault();
		$(wrapper).append('<div><input type="text" name="mytext[]"/></div>');
    
		console.log("Element added");
	})
});