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

    const DisplayUser = (USER,res) =>{
        const contestTable = document.querySelector(".contestTable");
        contestTable.innerHTML=`<tr>
                                    <th>User-ID</th>
                                    <th>User Name</th>
                                    <th>Total Contest Requested</th>
                                    <th>Total Design Ordered</th>
                                    <th>Details</th>
                                </tr>`
        USER.forEach(user=>{
            console.log(user);
            const newRow= document.createElement("tr");
            newRow.innerHTML= `<td>${user.user_id}</td>
                                       <td>${user.user_name}</td>
                                       <td>${res.data.contestRequested[user.user_id]}</td>
                                       <td>${res.data.designOrdered[user.user_id]}</td>
                                       <td class="seeDetails${user.user_id}"><u>See</u></td>`;
            contestTable.appendChild(newRow);


            //to visit one contest
            const seeDetails = document.querySelector(`.seeDetails${user.user_id}`);
            console.log(seeDetails);
            seeDetails.addEventListener("click",()=>{
                const oneContest = document.querySelector(".one-contest");
                oneContest.style.display = "block";
                oneContest.innerHTML= `<h6>${user.user_id}</h6><br>
                                               <h2 style="color: #967d00">${user.user_name}</h2> <br><br>
                                               <h3 style="color:darkgreen;">Requested for ${res.data.contestRequested[user.user_id]} contests</h3><br>
                                               <h3 style="color:darkslateblue;">Ordered ${res.data.designOrdered[user.user_id]} times</h3><br>
                                               <h4 style="color: darkred"><i class="fa fa-envelope"></i> ${user.email_address}</h4><br>
                                               <h4 style="color: dodgerblue"><i class="fa fa-phone"></i> ${user.contact_no}</h4><br>
                                               <h4 style="color: darkolivegreen"><i class="fas fa-home"></i> ${user.address}</h4><br>
                                               <h4 style="color: #332a02">Bio</h4><br>
                                               <p style="color: darkslategray" class="oneContestDes">${user.bio}</p><br>
                                               
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
            url: `/designerGetUserList?id=${id}`, // Include the id in the URL
        })
            .then((res) => {
                console.log(res);

                DisplayUser(res.data.users,res);

                const pressSearch = document.querySelector(".press-search");
                const searchBox = document.querySelector(".search-box");

                const performSearch = () => {
                    const inputValue = searchBox.value.toLowerCase();

                    const filteredUser = res.data.users.filter(user => {
                        return `${user.address}`.toLowerCase().includes(inputValue) ||
                            `${user.user_id}`.toLowerCase().includes(inputValue) ||
                            `${user.user_name}`.toLowerCase().includes(inputValue);
                    });

                    DisplayUser(filteredUser,res);
                };


                pressSearch.addEventListener("click",performSearch);
                searchBox.addEventListener("keypress", (event) => {
                    if (event.key === "Enter") {
                        performSearch();
                    }
                });
                searchBox.addEventListener("input", () => {
                    if (searchBox.value === "") {
                        DisplayUser(res.data.users,res) // Show all designs when search box is cleared
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