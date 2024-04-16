// my code
const btn = document.querySelectorAll(".btn1");
const audio1 = document.getElementById("audio1");


btn.forEach((el) => {
    el.addEventListener("click", function(event) {
        audio1.play();
    });

});


// follow

const btnNames = ['audio01', 'audio02', 'audio03',  'audio04', 'audio05', 'audio06'];
// JS_NOTE: Different between HTMLCollection and Node
const buttons = document.querySelector(".buttons");
// console.log(btnNames);

btnNames.forEach(el => {
    console.log(el);
    const btn = document.createElement("button");
    btn.innerText = el;
    btn.classList.add("btn");
    btn.classList.add(`${el}`);

    //play audio
    btn.addEventListener("click", (ev) => {
        // console.log(el);
        const audio = document.querySelector("." + el);

        resetAllAudio(buttons);
        audio.play();
        // console.log(audio);
    });

    buttons.appendChild(btn);
    // console.log(btn);
});

function resetAllAudio() {
    btnNames.forEach(el => {
        const audio = document.querySelector("." + el);
        audio.pause();
        audio.currentTime = 0;
    });
}

// console.log(buttons);

