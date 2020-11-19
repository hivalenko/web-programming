function draw_plot(canvas,context) {
    fill_white(canvas, context);
    context.beginPath();
    draw_circle(context);
    draw_square(context);
    draw_triangle(context);
    context.closePath();
    context.beginPath();
    draw_ox(context);
    draw_oy(context);
    context.closePath();
    context.stroke();
}

function draw_oy(context) {
    context.moveTo(250 , 30 );
    context.lineTo(240 , 40);
    context.moveTo(250, 30);
    context.lineTo(260, 40);
    context.moveTo(250, 30);
    context.lineTo(250, 470);
    context.moveTo(30, 250);
}

function draw_ox(context){
    context.moveTo(30, 250);
    context.lineTo(470, 250);
    context.lineTo(460, 240);
    context.moveTo(470, 250);
    context.lineTo(460, 260);
}

function draw_triangle(context){

    context.moveTo(250, 250);
    context.lineTo(155, 250);
    context.lineTo(250, 60);
    context.lineTo(250, 250);
    context.fillStyle = '#3399ff';
    context.fill();
}

function draw_square(context){
    context.rect(250, 250, 190, 95);
    context.fillStyle = '#3399ff';
    context.fill();
}

function draw_circle(context){
    context.arc(250, 250, 95, Math.PI/2, Math.PI);
    context.lineTo(250, 250);
    context.fillStyle = '#3399ff';
    context.fill();
}

function fill_white(canvas,context) {
    context.beginPath();
    context.fillStyle = "white";
    context.fillRect(0, 0, canvas.width, canvas.height);
}

function draw_measurments() {
    
}
function draw_point(x,y,color, radius){
    if( r == radius) {

        plot_context.beginPath();
        plot_context.arc(x, y, 4, 0, 2*Math.PI);
        plot_context.fillStyle = 'black';
        plot_context.fill();
        plot_context.closePath();

        plot_context.beginPath();
        plot_context.arc(x, y, 3, 0, 2 * Math.PI);
        plot_context.fillStyle = color;
        console.log("color of dot " + color + "  x " + x + " y " +  y);
        plot_context.fill();
        plot_context.closePath();
    }
}