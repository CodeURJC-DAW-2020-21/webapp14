var page = 1

function loadMoreLocals() {

    $("#locals").append($("<div class='row d-flex'>").load("/locals/?page=" + (page) + "&size=12 .eldiv", function () {
        $("#hasNextPage2").load("/locals/?page=" + page + "&size=12 #button-replace", function () {
            $("#load-more2").click(loadMoreLocals)
            page++
        })
    }))
}

$("#load-more2").click(loadMoreLocals)