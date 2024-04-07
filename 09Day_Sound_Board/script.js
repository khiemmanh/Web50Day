const btn = document.querySelectorAll(".btn1");
const audio1 = document.getElementById("audio1");


btn.forEach((el) => {
    el.addEventListener("click", function(event) {
        audio1.play();
    });

});

