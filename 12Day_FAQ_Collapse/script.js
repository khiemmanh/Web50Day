const buttons = document.querySelectorAll(".btn");

// buttons.forEach(btn => {
//     btn.addEventListener("click", () => {
//         btn.parentElement.classList.toggle("active");
//     });
// });



// Loop through each child
buttons.forEach(child => {
    // Initialize toggle state
    let toggled = false;
    
    // Add click event listener to each child
    child.addEventListener('click', () => {
        // Access the parent element of the clicked child
        const parent = child.parentElement;
        
        // console.log(child);
      // Toggle the highlight class based on toggled state
      if (!toggled) {
        parent.classList.add('active');
      } else {
        parent.classList.remove('active');
      }
      
      // Update toggle state
      toggled = !toggled;
    });
    console.log(toggled);
  });
