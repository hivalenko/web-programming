Array.from(document.querySelectorAll('input[type="radius"]')).forEach((element) => {
    //Array.from(document.querySelectorAll('selector'))
    element.addEventListener('input', replaceY);
element.addEventListener('change', replaceY);

function replaceY(){
    this.value = this.value.replace(/[^0-9.,]/, "");
}

});

document.getElementById('form').onsubmit = () =>{
    const radiusY = document.querySelector('[name="y"]');
    radiusY.value = radiusY.value.replace(/,/, '.');

    const valueOfX = parseFloat(document.querySelector('[name="x"]').value),
        valueOfY = parseFloat(radiusY.value),
        valueOfR = parseFloat(document.querySelector('[name="r"]').value);
    let result = true;


    if(isNaN(valueOfX) || valueOfX < -3 || valueOfX > 5){
        document.getElementById("a").innerHTML="Please, choose x value, not less than -3 and not greater than 5";
        result = false;
    }

    if(isNaN(valueOfY) || valueOfY <= -3.0 || valueOfY >= 5){
        document.getElementById("a").innerHTML="Please, choose y value, greater than -3 and less than 5";
        result = false;
    }
    if(isNaN(valueOfR) || valueOfR < 1 || valueOfR > 5){
        document.getElementById("a").innerHTML="Please, choose r value, not less than 1 and not greater than 5";
        result = false;
    }
    return result;
};

// document.querySelector('canvas').addEventListener('click', () => {
//     let radius = parseFloat(document.querySelector('input[name="r"]').value);
// if (radius && radius >= 1 && radius <= 4) {
//     document.querySelector('form').submit();
// }
// else{
//     document.getElementById("a").innerHTML="Please, enter r value";
// }
//
// });