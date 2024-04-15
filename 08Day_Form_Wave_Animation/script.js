const labels = document.querySelectorAll(".form-control label");

// console.log(labels);

labels.forEach(label => {
    // console.log(label.innerHTML);
    // console.log(label.innerText);

    const value = label.innerText.split('')
                    .map((value, index) => `<span 
                    style="transition-delay: ${index*50}ms">${value}</span>`)
                    .join('');
    label.innerHTML = value;
    console.log(value);

});












//use .map() in JS

const arr = [1, 2, 3, 4, 5, 6, 7, 8];

// console.log(["1", "2", "3"].map(parseInt));

const arr1 = arr.map((num) => Math.sqrt(num));

const kvArray = [
    { key: 1, value: 10 },
    { key: 2, value: 20 },
    { key: 3, value: 30 },
  ];

// console.log(kvArray);

const reformattedArray = kvArray.map(({ key, value }) => ({ [key]: value }));
// console.log(reformattedArray);


//Onject in JS

const person = {
    name: "John",
    age: 30,
    city: "New York",

    getName() {
        console.log(this.name);
    }
};

// console.log(person.getName());
