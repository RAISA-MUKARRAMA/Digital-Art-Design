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

    const DisplayAdmin = (ADMIN,res) => {
        const contestTable = document.querySelector(".contestTable");
        contestTable.innerHTML=`<tr>
                                    <th>Admin-ID</th>
                                    <th>Admin Name</th>
                                    <th>Total Contest Hold</th>
                                    <th>Details</th>
                                </tr>`
        ADMIN.forEach(admin=>{
            console.log(admin);
            const newRow= document.createElement("tr");
            newRow.innerHTML= `<td>${admin.admin_Id}</td>
                                       <td>${admin.admin_Name}</td>
                                       <td>${res.data.contestHolded[admin.admin_Id]}</td>
                                       <td class="seeDetails${admin.admin_Id}"><u>See</u></td>`;
            contestTable.appendChild(newRow);


            //to visit one contest
            const seeDetails = document.querySelector(`.seeDetails${admin.admin_Id}`);
            console.log(seeDetails);
            seeDetails.addEventListener("click",()=>{
                const oneContest = document.querySelector(".one-contest");
                oneContest.style.display = "block";
                oneContest.innerHTML= `<h6>${admin.admin_Id}</h6><br>
                                               <h2 style="color: #967d00">${admin.admin_Name}</h2> <br><br>
                                               <h3 style="color:darkgreen;">Contest Holded: ${res.data.contestHolded[admin.admin_Id]}</h3><br>
                                               <h4 style="color: darkred"><i class="fa fa-envelope"></i> ${admin.email_Address}</h4><br>
                                               <h4 style="color: dodgerblue"><i class="fa fa-phone"></i> ${admin.contact_No}</h4><br>
                                               <h4 style="color: darkolivegreen"><i class="fas fa-home"></i> ${admin.address}</h4><br>
                                               <h4 style="color: #332a02">Bio</h4><br>
                                               <p style="color: darkslategray" class="oneContestDes">${admin.bio}</p><br>
                                               
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
            url: `/designerGetAdminList?id=${id}`, // Include the id in the URL
        })
            .then((res) => {
                console.log(res);

                DisplayAdmin(res.data.admins,res);

                const pressSearch = document.querySelector(".press-search");
                const searchBox = document.querySelector(".search-box");

                const performSearch = () => {
                    const inputValue = searchBox.value.toLowerCase();

                    const filteredAdmin = res.data.admins.filter(admin => {
                        return `${admin.address}`.toLowerCase().includes(inputValue) ||
                            `${admin.admin_Id}`.toLowerCase().includes(inputValue) ||
                            `${admin.admin_Name}`.toLowerCase().includes(inputValue);
                    });

                    DisplayAdmin(filteredAdmin,res);
                };


                pressSearch.addEventListener("click",performSearch);
                searchBox.addEventListener("keypress", (event) => {
                    if (event.key === "Enter") {
                        performSearch();
                    }
                });
                searchBox.addEventListener("input", () => {
                    if (searchBox.value === "") {
                        DisplayAdmin(res.data.admins,res) // Show all designs when search box is cleared
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