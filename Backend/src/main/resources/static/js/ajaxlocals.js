var page = 1

function cargarMasS2() {

    $("#locals").append($("<div class='row d-flex'>").load("/locals/?page=" + (page) + "&size=4 .eldiv", function () {
        $("#hasNextPage2").load("/locals/?page=" + page + "&size=4 #button-replace", function () {
            $("#load-more1").click(cargarMasS2)
            page++
        })
    }))
}

$("#load-more1").click(cargarMasS2)