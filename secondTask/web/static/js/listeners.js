function changeR() {
    console.log("change r ")
    r = getR();
    draw_plot(plot_canvas, plot_context);

    var allX = document.getElementsByClassName("x_coord");
    var allY = document.getElementsByClassName("y_coord");
    var allR = document.getElementsByClassName("r");
    var allResults = document.getElementsByClassName("result");

    for(i = 0; i < allX.length; i++){
        if(parseFloat(allR.item(i).innerHTML) == r){
            console.log("radius is equal" + " expected result is" + " " + allResults.item(i).innerHTML);
            let isInBounds = false;
            if(allResults.item(i).innerHTML.length == 11){
                console.log("point is in bounds");
                isInBounds = true;
            }
            console.log(allResults.item(i).innerHTML.length);
            let pointDrawingData = getPointDrawingData( +getFloatFromTable(allX, i), +getFloatFromTable(allY, i), isInBounds, +allR.item(i).innerHTML);
            draw_point( +pointDrawingData[0], +pointDrawingData[1], pointDrawingData[2], +pointDrawingData[3]);
        }

    }
}

function getR(){
    return +parseFloat(document.querySelector('[name="r"]').value);
}

function initRListener(){
    r_selector.addEventListener('change', changeR, false);
    changeR();

}


function onPlotClick(e) {
    amountOfDotsBeforeReload++;
    getCursorPosition(e);
    var graphX = x;
    var graphY = y;

    console.log(x + " = x preresult");
    console.log(y + " = y preresult");
    console.log(r + " =  R preresult");

    x -= 250;
    x = x/190*r;

    y -= 250;
    y *= -1;
    y = y/190*r;


    $.ajax({
        type: "GET",
        url: "controllerServlet",
        data: { 'x': x, 'y': y, 'r': r, 'isAjax': true},
        success: function (data,textStatus, xhr) { ajaxGetPointOnSuccess(data, graphX, graphY, r) },
        error: function (a, jqXHR, exception) { }
    });

    console.log(x + " = result");
    console.log(y + " = result");
}


function ajaxGetPointOnSuccess(data, graphX, graphY, radius) {
    var stringsResponse = data.split('#');
    if (stringsResponse[0] === "1"){
        draw_point(graphX,graphY,"green", radius);
    } else {
        draw_point(graphX,graphY,"red", radius);
    }
    var table  = document.getElementById('history');
    var newRow = table.insertRow(-1);
    insertDataToRow(stringsResponse, newRow);
}

function insertDataToRow(data, row){
    for(var i = 1; i < data.length; i++){
        if(i == 5) {
            if (data[5] === "true") {
                data[5] = "In Bounds";
            } else {
                data[5] = "Ouf of Bounds";
            }
        }
        var newCell;
        newCell = row.insertCell(i-1);
        var newText = document.createTextNode(data[i]);
        newCell.appendChild(newText);
    }
}

function getFloatFromTable( allValues, index){
    return +allValues.item(index).innerHTML.replace(",",".");
}

function getCursorPosition(e) {
    if (e.pageX !== undefined && e.pageY !== undefined) {
        x = e.clientX;
        y = e.clientY;
    }
    // for old IE
    else {
        x = e.clientX;
        x += document.body.scrollLeft;
        x += document.documentElement.scrollLeft;

        y = e.clientY;
        y += document.body.scrollTop;
        y += document.documentElement.scrollTop;
    }
    //x -= plot_canvas.offsetLeft;
    var rect = plot_canvas.getBoundingClientRect();
    console.log(rect);
    x -= rect.left;
    x -= 5;
    //y -= plot_canvas.offsetTop;
    y -= rect.top;
    y -= 5;
    console.log(x + " = got cursor position");
    console.log(y + " = got cursor position");
}

function getPointDrawingData(x, y, isInBounds, r){
    var color = "green";
    if( !isInBounds ) color = "red";
    var returnedArray = [
        (+x * 190)/r + 250,
        (+y * 190)/r * (-1) + 250,
        color,
        r
    ];
    console.log( returnedArray );
    return returnedArray;
}