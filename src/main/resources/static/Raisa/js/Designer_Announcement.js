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
    if(id == null && name == null){
        window.location.href = "/home";
    }
    sessionStorage.setItem("Designer_id", id);
    console.log(id);

    document.querySelector(".user-name").innerHTML = '<i class="fa fa-user icon"></i>' + " " + name;
    console.log(name);

    const DisplayAnnouncement = (ANNOUNCEMENT,res) =>{

        document.querySelector(".contestTable").innerHTML=`<tr>
                                                                        <th>Code</th>
                                                                        <th>Title</th>
                                                                        <th>Announced By</th>
                                                                        <th>Announced on</th>
                                                                        <th>Details</th>
                                                                    </tr>`
        ANNOUNCEMENT.forEach((annc, i) => {
            console.log(annc);
            const newRow = document.createElement("tr");
            newRow.innerHTML = `<td>${annc.anc_Code}</td>
                                        <td>${annc.annc_Title}</td>
                                        <td>${annc.admin.admin_Name}</td>
                                        <td>${new Date(annc.date).toLocaleString()}</td>
                                        <td class="seeDetails${i}"><u>See</u></td>`;

            document.querySelector(".contestTable").appendChild(newRow);

            const seeDetails = document.querySelector(`.seeDetails${i}`);
            seeDetails.addEventListener("click", () => {
                const oneContest = document.querySelector(".one-contest");
                oneContest.style.display = "block";
                oneContest.innerHTML = `<div class="details">
                                                            <h4 style="color: #525151">${annc.description}</h4>
                                                       </div>
                                                       <button class="crossOneContest"><i class="fa fa-times" aria-hidden="true"></i></button>`;

                document.querySelector(".crossOneContest").addEventListener("click", () => {
                    oneContest.style.display = "none";
                });
            });
        });
    }
    const getData = () => {
        makeRequest({
            method: 'get',
            url: `/DesignergetAnnouncement?id=${id}`,
        })
            .then((res) => {
                console.log(res);

                const sortedAnnouncementList = res.data.announcements;
                sortedAnnouncementList.sort((a, b) => new Date(b.date) - new Date(a.date));
                console.log(sortedAnnouncementList);

                DisplayAnnouncement(sortedAnnouncementList,res);

                const pressSearch = document.querySelector(".press-search");
                const searchBox = document.querySelector(".search-box");

                const performSearch = () => {
                    const inputValue = searchBox.value.toLowerCase();

                    const filteredAnnouncement = sortedAnnouncementList.filter(annc => {
                        return `${annc.admin.admin_Id}`.toLowerCase().includes(inputValue) ||
                            `${annc.admin.admin_Name}`.toLowerCase().includes(inputValue) ||
                            `${annc.anc_Code}`.toLowerCase().includes(inputValue) ||
                            `${annc.date}`.toLowerCase().includes(inputValue) ||
                            `${annc.annc_Title}`.toLowerCase().includes(inputValue) ||
                            `${annc.description}`.toLowerCase().includes(inputValue);
                    });

                    DisplayAnnouncement(filteredAnnouncement,res);
                };


                pressSearch.addEventListener("click",performSearch);
                searchBox.addEventListener("keypress", (event) => {
                    if (event.key === "Enter") {
                        performSearch();
                    }
                });
                searchBox.addEventListener("input", () => {
                    if (searchBox.value === "") {
                        DisplayAnnouncement(sortedAnnouncementList,res); // Show all designs when search box is cleared
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
