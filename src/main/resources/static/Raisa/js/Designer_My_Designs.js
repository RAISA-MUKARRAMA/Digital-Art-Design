document.addEventListener("DOMContentLoaded", function () {
    const makeRequest = async (config) => {
        return await axios({
            ...config,
            headers: {
                'Content-Type': 'multipart'
            }
        });
    };

    let id = sessionStorage.getItem("id");
    let name = sessionStorage.getItem("DesignerName");
    if(id==null && name==null){
        window.location.href="/home";
    }
    sessionStorage.setItem("Designer_id", id);
    console.log(id);

    // let name = sessionStorage.getItem("DesignerName");
    document.querySelector(".user-name").innerHTML = '<i class="fa fa-user icon"></i>'+ " " + name;
    console.log(name);


    const DisplayDesigns = (DESIGNS,res) =>{
        console.log("Called");
        const designbox = document.querySelector(".design-box");
        designbox.innerHTML="";

        DESIGNS.forEach(design => {
            // Create a container div for each design
            const showDesign = document.createElement("div");
            showDesign.classList.add("eachIamge");

            // Create an image element for the design
            const pic = document.createElement("img");
            pic.classList.add("pic");

            // Set the src attribute of the image
            if (design.content != null) {
                let base64Image = `data:image/jpeg;base64, ${design.content}`;
                pic.src = base64Image;
            } else {
                pic.src = "/Raisa/no-content.jpg";
            }

            // Append the image to the container div
            showDesign.appendChild(pic);

            // Append the container div to the designbox
            designbox.appendChild(showDesign);

            const overlay= document.createElement("div");
            overlay.classList.add("overlay");

            overlay.innerHTML= `<p>${design.design_Code}</p><br>
                                        <p>${design.design_Title}</p><br>
                                        <p>${name}</p><br>
                                        <p><i class="fa fa-heart" aria-hidden="true" style="color:red;">
                                        </i>: ${design.rating}</p>`

            showDesign.appendChild(overlay);

            showDesign.addEventListener("click",()=>{
                event.stopPropagation();
                const onedesign = document.querySelector(".one-design");
                onedesign.style.display="block";
                let base64Image;
                if (design.content != null) {
                    base64Image = `data:image/jpeg;base64, ${design.content}`;
                } else {
                    base64Image = "/Raisa/no-content.jpg";
                }
                onedesign.innerHTML= `<div class="oneImage">
                                              <img class="onePic" src="${base64Image}">
                                              </div>
                                              <div class="oneDescription">
                                              <h5><u>Design code</u></h5>
                                              <h5>${design.design_Code}</h5><br>
                                              <h5><u>Title</u></h5>
                                              <h2 style="color: #967d00">${design.design_Title}</h2><br>
                                              <h5><u>Category</u></h5>
                                              <h3 style="color: darkgreen">${res.data.ctData[design.ctgr_Code]}</h3><br>
                                              <h5><u>Designer</u></h5>
                                              <h4 style="color: rosybrown">${name}</h4><br>
                                              <h5><u>Rating</u></h5>
                                              <h3><i class="fa fa-heart" aria-hidden="true"
                                              style="color:red;"></i>: ${design.rating}</h3><br>
                                              <h5><u>Description</u></h5><br>
                                              <div>
                                              <p style="color: blue" class="oneDesignDes">${design.description}</p><br>
                                              </div>
                                              <button class="delete">Delete</button>
                                              </div>`

                document.querySelector(".delete").addEventListener("click",()=>{
                    const succespopup = document.querySelector(".successPopup");
                    succespopup.style.display= "block";
                    succespopup.innerHTML=
                        `<h2>Do you want to delete this design?</h2><br>
                         <button class="ok">Yes</button><br><br>
                         <button class="dont">Cancel</button>`
                    document.querySelector(".ok").addEventListener("click",()=>{
                        makeRequest({
                            method: 'post',
                            url: `/designerDeleteDesign?id=${id}&design_code=${design.design_Code}`, // Include the id in the URL
                        })
                            .then((res) => {
                                console.log(res);
                                succespopup.innerHTML=
                                    `<h2>Design deleted succesfully!</h2><br>
                                     <button class="ok">Ok</button><br><br>`
                                document.querySelector(".ok").addEventListener("click",()=>{
                                    succespopup.style.display="none";
                                    window.location.href="Designer_My_Designs.html";
                                })
                            })
                            .catch((err) => console.error(err));
                    })
                    document.querySelector(".dont").addEventListener("click",()=>{
                        succespopup.style.display="none";
                    })

                })
            })
            document.addEventListener("click",(event)=>{
                const targetElement = event.target;
                const onedesign = document.querySelector(".one-design");
                // Check if the clicked element is outside of the popup
                if (!onedesign.contains(targetElement) && targetElement !== onedesign) {
                    onedesign.style.display = 'none'; // Close the popup
                }
            })
        });
    }
    const getData = () => {
        // Get the designer id from sessionStorage
        let id = sessionStorage.getItem("id");

        makeRequest({
            method: 'get',
            url: `/designerMyDesigns?id=${id}`, // Include the id in the URL
        })
            .then((res) => {
                console.log(res);

                DisplayDesigns(res.data.DSN,res);

                const pressSearch = document.querySelector(".press-search");
                const searchBox = document.querySelector(".search-box");

                const performSearch = () => {
                    const inputValue = searchBox.value.toLowerCase();

                    const filteredDesigns = res.data.DSN.filter(design => {
                        const category = res.data.ctData[design.ctgr_Code] || "";
                        return `${design.design_Code}`.toLowerCase().includes(inputValue) ||
                            `${design.design_Title}`.toLowerCase().includes(inputValue) ||
                            `${category}`.toLowerCase().includes(inputValue) ||
                            `${name}`.toLowerCase().includes(inputValue) ||
                            `${design.ctgr_Code}`.toLowerCase().includes(inputValue) ||
                            `${design.description}`.toLowerCase().includes(inputValue) ||
                            `${design.designer_Id}`.toLowerCase().includes(inputValue) ||
                            `${design.rating}`.toLowerCase().includes(inputValue) ;
                    });

                    DisplayDesigns(filteredDesigns,res);
                };


                pressSearch.addEventListener("click",performSearch);
                searchBox.addEventListener("keypress", (event) => {
                    if (event.key === "Enter") {
                        performSearch();
                    }
                });
                searchBox.addEventListener("input", () => {
                    if (searchBox.value === "") {
                        DisplayDesigns(res.data.DSN,res); // Show all designs when search box is cleared
                    }
                    else{
                        performSearch();
                    }
                });
            })
            .catch((err) => console.error(err));
    };


    getData();

});
