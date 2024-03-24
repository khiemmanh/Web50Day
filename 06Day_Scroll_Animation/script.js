const boxes = document.querySelectorAll('.box')

window.addEventListener("scroll", () => {
    let hightBrowser = window.innerHeight / 5 * 4;
    
    for (const box of boxes) {
        
        // JS_NOTE: Lấy ra vị trí tương đối của một element trong js
        let rectHight = box.getBoundingClientRect().top;
        console.log(rectHight);

        if(hightBrowser > rectHight){
            //box.style.backgroundColor = "blue";
            box.classList.add("action");
        }else{
            box.classList.remove("action");
            //box.style.backgroundColor = "red";
        }

        // console.log(box);
    }
    
    console.log("---------------------");
    // console.log(hightBrowser);
});


