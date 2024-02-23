const panels = document.getElementsByClassName("panel");

for (let i = 0; i < panels.length; i++) {
    // console.log(panels[i]);
    panels[i].addEventListener("click", () => {
        clearAllActiveClass();

        // after click then need to add "active class name for document"
        panels[i].classList.add("active");
    } );
}

function clearAllActiveClass() {
    for (let i = 0; i < panels.length; i++) {
        panels[i].classList.remove("active");
    }
}
