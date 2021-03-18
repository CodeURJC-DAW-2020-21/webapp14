var page = 1

function cargarMasS() {

    $("#events").append($("<div class='row'>").load("/events/?page=" + (page) + "&size=3 .eldiv", function () {
        $("#hasNextPage").load("/events/?page=" + page + "&size=3 #button-replace", function () {
            $("#load-more").click(cargarMasS)
            page++
        })
    }))
}

$("#load-more").click(cargarMasS)