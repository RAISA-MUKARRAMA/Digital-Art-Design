document.addEventListener("DOMContentLoaded", function () {
    const makeRequest = async (config) => {
        return await axios({
            ...config,
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        });
    };

    let id = sessionStorage.getItem("id");
    let name = sessionStorage.getItem("DesignerName");
    if (id == null && name == null) {
        window.location.href = "/home";
        // alert("Sorry! You are not signed in");
    }
    sessionStorage.setItem("Designer_id", id);
    console.log(id);

    // let name = sessionStorage.getItem("DesignerName");
    document.querySelector(".user-name").innerHTML = '<i class="fa fa-user icon"></i>' + " " + name;
    console.log(name);

    DisplayDesigners = (DESIGNER,res) =>{
        const contestTable = document.querySelector(".contestTable");
        contestTable.innerHTML=`<tr>
                                    <th>Designer-ID</th>
                                    <th>Designer Name</th>
                                    <th>Number of Design</th>
                                    <th>Number of Participated Contests</th>
                                    <th>Rating</th>
                                    <th>Details</th>
                                </tr>`

        DESIGNER.forEach(designer=>{
            console.log(designer);
            const newRow= document.createElement("tr");
            newRow.innerHTML= `<td>${designer.designerId}</td>
                                       <td>${designer.designerName}</td>
                                       <td>${res.data.tdesigns[designer.designerId]}</td>
                                       <td>${res.data.nocontests[designer.designerId]}</td>
                                       <td>${res.data.rating[designer.designerId]}</td>
                                       <td class="seeDetails${designer.designerId}"><u>See</u></td>`;
            contestTable.appendChild(newRow);


            let categories = res.data.itsCategory[designer.designerId].map(category => `${category}`).join(',');
            let base64Image;
            if (designer.profilePhoto != null) {
                base64Image = `data:image/jpeg;base64, ${designer.profilePhoto}`;
            } else {
                base64Image = "/Raisa/default Profile Picture.jpg";
            }
            //to visit one contest
            const seeDetails = document.querySelector(`.seeDetails${designer.designerId}`);
            console.log(seeDetails);
            seeDetails.addEventListener("click",()=>{
                const oneContest = document.querySelector(".one-contest");
                oneContest.style.display = "block";
                oneContest.innerHTML= `<div class="details">
                                                    <h6>${designer.designerId}</h6><br>
                                                    <h4 style="color: #967d00">${designer.designerName}</h4> <br>
                                                    <h3><i class="fa fa-heart" aria-hidden="true" style="color:red;"></i> ${res.data.rating[designer.designerId]}</h3><br>
                                                    <h5 style="color:darkgreen;">Works for==> ${categories}</h5><br>
                                                    <h4 style="color: darkblue">Uploaded ${res.data.tdesigns[designer.designerId]} designs</h4><br>
                                                    <h4 style="color: purple">Participated in ${res.data.nocontests[designer.designerId]} contests</h4><br>
                                                    <h4 style="color: darkred"><i class="fa fa-envelope"></i> ${designer.emailAddress}</h4><br>
                                                    <h4 style="color: dodgerblue"><i class="fa fa-phone"></i> ${designer.contactNo}</h4><br>
                                                    <h4 style="color: darkolivegreen"><i class="fas fa-home"></i> ${designer.address}</h4><br>
                                                    <h4 style="color: #332a02">About</h4><br>
                                                    <p style="color: #332a02;margin-left:2.8rem;max-width: 500px;max-height: 200px;word-wrap: break-word;overflow: auto;">${designer.bio}</p><br>
                                               </div>
                                                               
                                               <div class="contest-status-box">
                                               <img class="onePic" src="${base64Image}">
                                               </div>

                                               <button class="crossOneContest"><i class="fa fa-times" aria-hidden="true"></i></button>`

                const crossOneContest = document.querySelector(".crossOneContest");
                crossOneContest.addEventListener("click",()=>{
                    oneContest.style.display="none";
                })
            })
        })
    }
    const getData = () => {
        makeRequest({
            method: 'get',
            url: `/designerGetDesignerList?id=${id}`, // Include the id in the URL
        })
            .then((res) => {
                console.log(res);

                DisplayDesigners(res.data.designers,res);

                const pressSearch = document.querySelector(".press-search");
                const searchBox = document.querySelector(".search-box");

                const performSearch = () => {
                    const inputValue = searchBox.value.toLowerCase();

                    const filteredDesigners = res.data.designers.filter(designer => {
                        return `${designer.address}`.toLowerCase().includes(inputValue) ||
                            `${designer.bio}`.toLowerCase().includes(inputValue) ||
                            `${designer.designerId}`.toLowerCase().includes(inputValue) ||
                            `${designer.designerName}`.toLowerCase().includes(inputValue) ||
                            `${res.data.itsCategory[designer.designerId]}`.toLowerCase().includes(inputValue);
                    });

                    DisplayDesigners(filteredDesigners,res);
                };


                pressSearch.addEventListener("click",performSearch);
                searchBox.addEventListener("keypress", (event) => {
                    if (event.key === "Enter") {
                        performSearch();
                    }
                });
                searchBox.addEventListener("input", () => {
                    if (searchBox.value === "") {
                        DisplayDesigners(res.data.designers,res); // Show all designs when search box is cleared
                    }
                    else{
                        performSearch();
                    }
                });

            })
            .catch((err)=> console.error(err));
    }

    getData();
})