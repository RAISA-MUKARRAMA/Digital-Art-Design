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
        let designerName = {};
        res.data.designers.forEach(designer=>{
            designerName[designer.designerId]=designer.designerName;
        })

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
                                        <p>${designerName[design.designer_Id]}</p><br>
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
                                              <h4 style="color: rosybrown">${designerName[design.designer_Id]}</h4><br>
                                              <h5><u>Rating</u></h5>
                                              <h3><i class="fa fa-heart" aria-hidden="true"
                                              style="color:red;"></i>: ${design.rating}</h3><br>
                                              <h5><u>Description</u></h5><br>
                                              <p style="color: blue" class="oneDesignDes">${design.description}</p><br>
                                              </div>`
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
            url: `/designerAllDesigns?id=${id}`, // Include the id in the URL
        })
            .then((res) => {
                console.log(res);
                let designerName = {};
                res.data.designers.forEach(designer=>{
                    designerName[designer.designerId]=designer.designerName;
                })

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
                            `${designerName[design.designer_Id]}`.toLowerCase().includes(inputValue) ||
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

// <div class="eachIamge">
// <a th:href="'/design/' + ${designer.designerId} + '/' + ${D.Design_Code}"><img class="pic" th:if="${content[D.Design_Code] != null}" th:src="@{'data:image/' + ${content[D.Design_Code].format} + ';base64,' + ${content[D.Design_Code].base64}}"></a>
// <img class="pic" th:if="${content[D.Design_Code] == null}" src="/no-content.jpg">
//     <p th:text="${D.Design_Code}"></p>
//     <p th:text="${D.Design_Title}"></p>
//     <p th:text="${designer.designerName}"></p>
//     <p><i class="fa fa-heart" style="color: red;" aria-hidden="true"></i>: <span th:text="${D.rating}"></span></p>
// //
// </div>