var page = 1

function cargarMasS() {

    $("#events").append($("<div class='row'>").load("/events/?page=" + (page) + "&size=9 .eldiv", function () {
        $("#hasNextPage").load("/events/?page=" + page + "&size=9 #button-replace", function () {
            $("#load-more").click(cargarMasS)
            page++
        })
    }))
}

$("#load-more").click(cargarMasS)