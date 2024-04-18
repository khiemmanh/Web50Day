const jokesContent = document.querySelector(".jokes");
const btn = document.querySelector(".btn");

// btn.addEventListener("click", () => {
//     const jokeJSON = fetchJokesData();
//     jokesContent.innerText = jokeJSON.joke;
// });

btn.addEventListener("click", fetchJokesData);

fetchJokesData();
async function fetchJokesData() {
    try {
        const httpResponseHeader = {
            headers: {
                Accept: 'application/json'
            },
        }
        
        const reponse = await fetch('https://icanhazdadjoke.com', httpResponseHeader);
        const data = await reponse.json();
        jokesContent.innerText = data.joke;
        // console.log(data);
        
    } catch (error) {
        console.error(error);
    }
}


// Code ChatGPT {
    //     const jokesContent = document.querySelector(".jokes");
    //     const btn = document.querySelector(".btn");
    
    //     btn.addEventListener("click", async () => {
    //         try {
    //             const data = await fetchJokesData();
    //             if (data && data.joke) {
    //                 jokesContent.innerText = data.joke;
    //             } else {
    //                 jokesContent.innerText = "Failed to load joke";
    //             }
    //         } catch (error) {
    //             console.error("Failed to fetch joke: ", error);
    //             jokesContent.innerText = "Error loading joke";
    //         }
    //     });
    
    //     async function fetchJokesData() {
    //         try {
    //             const httpResponseHeader = {
    //                 headers: {
    //                     Accept: 'application/json'
    //                 },
    //             };
                
    //             const response = await fetch('https://icanhazdadjoke.com', httpResponseHeader);
    //             if (!response.ok) {
    //                 throw new Error(`HTTP error! status: ${response.status}`);
    //             }
    //             return await response.json();
    //         } catch (error) {
    //             console.error(error);
    //             throw error; // Re-throw to handle it in the calling function
    //         }
    //     }

//}

    
    // function fetchJokesData() {
        //     /*
        //     JS_NOTE: HTTP header reponse
        //     Content-Type: Specifies the type of data you're sending to the server. 
        //     It's about the request body's format.
        //     Accept: Specifies the type of data you expect back from the server. 
        //     It's about the response's content format.
    
//     */
//     const httpResponseHeader = {
//        // headers: {
//            //     'Content-Type': 'application/json'
//            // },
           
//            headers: {
//                Accept: 'application/json'
//             },
//     }
        
//     fetch('https://icanhazdadjoke.com', httpResponseHeader)
//     .then((res) => res.json())
//     .then(data => {
//         console.log(data);
//         return jokesContent.innerHTML = data.joke;  
//     })
//     .catch(error => console.error("Error", error));
    // }
    
