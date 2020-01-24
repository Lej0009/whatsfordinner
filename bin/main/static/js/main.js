$(document).ready(function() {
    $.ajax({
        url: "http://rest-service.guides.spring.io/greeting"
    }).then(function(data) {
       $('.title').append(data.title);
       $('.version').append(data.version);
       $('.href').append(data.href);
       $('.results').append(data.results);
    });
});
