const container = document.querySelector(".container"),
selectBtn = container.querySelector(".select-btn"),
searchInp = container.querySelector("input"),
options = container.querySelector(".options");

let categories = ["Logo", "Business Card", "Website Design", "App Design", "Book cover/Magazine/Brochure", "Flyer / Poster", "Packaging & Label", "Clothing", "Concept Art", "Character Design", "Digital Art", "Animation"];

function addCategory() {
  options.innerHTML = "";
  categories.forEach(category => {
    let li = `<li onclick="updateName(this)">${category}</li>`;
    options.insertAdjacentHTML("beforeend", li);
  });
}

addCategory();

function updateName(selectedLi){
  searchInp.value = "";
  addCategory();
  container.classList.remove("active");
  selectBtn.firstElementChild.innerText = selectedLi.innerText;
}

searchInp.addEventListener("keyup", () =>{
  let arr = [];
  let searchedVal = searchInp.value.toLowerCase();
  arr = categories.filter(data => {
    return data.toLowerCase().startsWith(searchedVal);
  }).map(data => `<li onclick="updateName(this)">${data}</li>`).join("");
  options.innerHTML = arr ? arr : `<p>Not Found</p>`;
});
selectBtn.addEventListener("click", () => {
  container.classList.toggle("active");
});