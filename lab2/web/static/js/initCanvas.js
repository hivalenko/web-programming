var plot_canvas = document.getElementById("myCanvas");
var plot_context = plot_canvas.getContext("2d");
var r_selector = document.getElementById("r_selector");
draw_plot(plot_canvas,plot_context);
var x;
var y;
var r = 1;
var amountOfDotsBeforeReload = 0;

initRListener();
plot_canvas.addEventListener("click", onPlotClick, false);