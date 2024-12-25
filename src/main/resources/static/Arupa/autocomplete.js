let availableKeywords = [
   'Logo',
   'Business Card',
   'Website Design',
   'App Design',
   'Bookcover',
   'Magazine',
   'Brochure',
   'Flyer',
   'Poster',
   'Packaging & Label',
   'Clothing',
   'Concept Art',
   'Character Design',
   'Digital Art',
   'Animation'
];

const resultsBox = document.querySelector(".result-box");
const inputBox = document.getElementById("input-box");

inputBox.onkeyup = function(){
    let result = [];
    let input = inputBox.value;
    if(input.length){
        result = availableKeywords.filter((keyword)=>{
            return keyword.toLowerCase().includes(input.toLowerCase());
        });
        console.log(result);
    }
    display(result);
}
function display(result){
    const content = result.map((list)=>{
        return "<li onclick=selectInput(this)>" + list + "</li>";
    });

    resultsBox.innerHTML = "<ul>" + content.join('') + "</ul>";
}

function selectInput(list){
    inputBox.value = list.innerHTML;
    resultsBox.innerHTML = '';
}