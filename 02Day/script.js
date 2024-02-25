const progress = document.getElementById("progress");
const circles = document.querySelectorAll(".circle");
const prev = document.getElementById("prev");
const next = document.getElementById("next");

let indexActive = 1;

// console.log(next);

next.addEventListener('click', () => {
    indexActive++;

    if(indexActive > circles.length){
        indexActive = circles.length;
    }

    console.log(indexActive);

    update();
});

function update() {
    circles.forEach((element, index) => {
        
        if(index < indexActive){
            element.classList.add("active");
            // console.log("index: ", index, ", indexActive: ", indexActive);
        } else{
            element.classList.remove("active");
        }
        
        let numberActiveCricles = document.getElementsByClassName("active").length;
        
        console.log("active: ", (numberActiveCricles-1), " ", (circles.length-1), " ", ((numberActiveCricles-1) / (circles.length-1)) * 100);
        
        progress.style.width = ((numberActiveCricles-1) / (circles.length-1)) * 100 + "%"
    });
}
