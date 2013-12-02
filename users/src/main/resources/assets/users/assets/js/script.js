$(document).ready(function(){
	var options = {
                       zoom: 16,
                       center: new google.maps.LatLng(37.3356, -121.8811),
                       mapTypeId: google.maps.MapTypeId.ROADMAP
                      };
                       
        var map = new google.maps.Map(document.getElementById('map-canvas'), options);
                                
       	var mkr = new google.maps.Marker({
                            position: map.getCenter(),
                             map: map,
                             title: 'Click to zoom'
                         });

       google.maps.event.addListener(map, 'center_changed', function() {window.setTimeout(function(){map.panTo(mkr.getPosition());}, 3000);
});
$.ajax({
    	    url: "/events",
    	    type: 'GET',
    	    contentType: 'application/json',
    	    success: function(result) 
    	    {
    	          var places[];
    	          places.push(new google.maps.LatLng(37.336186,-121.883493));
    	          var marker = new google.maps.Marker({
    					position: places[i],
    					map: map,
    					animation: google.maps.Animation.BOUNCE,
    					title: 'Location ' + i
    					});
    	    }
    	});
});