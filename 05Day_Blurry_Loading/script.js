const loading = document.querySelector('.loading');

console.log(loading);

let count = 0;
let interval = setInterval(loadingFunction, 30);

function loadingFunction() {
    count++;

    if(count > 99){
        clearInterval(interval);
    }

    // loading.textContent = `${count}%`
    
    loading.innerText = `${count}%`
    console.log(count);
}
