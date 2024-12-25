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
    }
    sessionStorage.setItem("Designer_id", id);
    console.log(id);

    document.querySelector(".user-name").innerHTML = '<i class="fa fa-user icon"></i>' + " " + name;
    console.log(name);

    const displayPastMessages = (messages) => {
        const contestTable = document.querySelector(".contestTable");
        contestTable.innerHTML = `<tr>
            <th>Sent to</th>
            <th>Message</th>
            <th>Time</th>
        </tr>`;

        messages.forEach((message, i) => {
            const newRow = document.createElement("tr");
            newRow.innerHTML = `<td>${message.admin.admin_Name}</td>
                <td class="seeDetails${i}"><u>See Message</u></td>
                <td>${new Date(message.date).toLocaleString()}</td>`;
            contestTable.appendChild(newRow);

            const seeDetails = document.querySelector(`.seeDetails${i}`);
            seeDetails.addEventListener("click", () => {
                const oneContest = document.querySelector(".one-contest");
                oneContest.style.display = "block";
                oneContest.innerHTML = `<div class="details">
                    <h4 style="color: #525151">${message.description}</h4>
                </div>
                <button class="crossOneContest"><i class="fa fa-times" aria-hidden="true"></i></button>`;

                document.querySelector(".crossOneContest").addEventListener("click", () => {
                    oneContest.style.display = "none";
                });
            });
        });
    };

    const displayRunningMessagesForm = (admins) => {
        const sentORsend = document.querySelector(".sentORsend");
        sentORsend.innerHTML = `<form class="upld-form" enctype="multipart/form-data">
            Select Admin:
            <select class="category" name="selectedadm" required>
                <option value='' selected>Select Admin</option>
            </select> <br><br><br>
            Message: <br>
            <textarea name="des" class="des-box"></textarea> <br> <br> <br>
            <button class="sub-but">Send</button>
        </form>`;

        const selectedAdmin = document.querySelector(".category");
        admins.forEach(admin => {
            const newOption = document.createElement("option");
            newOption.text = `${admin.admin_Id}-${admin.admin_Name}`;
            newOption.value = admin.admin_Id;
            selectedAdmin.add(newOption);
        });

        document.querySelector(".sub-but").addEventListener("click", (event) => {
            event.preventDefault();
            const form = document.querySelector('.upld-form');
            const formData = new FormData(form);
            const postData = {};
            formData.forEach((value, key) => {
                postData[key] = value;
            });
            console.log(postData);

            makeRequest({
                method: 'post',
                url: `/sendmessage?id=${id}`,
                data: postData,
            })
                .then((res) => {
                    console.log(res);
                    const successPopup = document.querySelector(".successPopup");
                    successPopup.style.display = "block";
                    successPopup.innerHTML = `<h1><i class="fa fa-check" aria-hidden="true" style="color: #967d00"></i></h1><br>
                    <h3>${res.data}</h3><br>
                    <button class="ok">Ok</button>`;
                    document.querySelector(".ok").addEventListener("click", () => {
                        sessionStorage.setItem("currentMsgOption", "past");
                        window.location.href = "Designer_Messages.html";
                    });
                })
                .catch((err) => console.error(err));
        });
    };

    const displayMessageOption = (sortedMessageList, res) => {
        const past = document.querySelector("#past");
        const running = document.querySelector("#running");

        const current = sessionStorage.getItem("currentMsgOption");
        if (!current) {
            sessionStorage.setItem("currentMsgOption", running.id);
            running.classList.add("active");
            handleButtonClick(running);
        } else {
            if (current === "past") {
                handleButtonClick(past);
            } else if (current === "running") {
                handleButtonClick(running);
            }
        }

        function handleButtonClick(clickedButton) {
            document.querySelectorAll(".contestOption").forEach(button => {
                button.classList.remove("active");
            });
            clickedButton.classList.add("active");
            sessionStorage.setItem("currentMsgOption", clickedButton.id);

            const sentORsend = document.querySelector(".sentORsend");
            if (clickedButton.id === "past") {
                sentORsend.innerHTML = `<div class="search-field">
                    <input type="text" class="search-box" placeholder="Search...">
                    <button class="press-search"><i class="fa fa-search"></i></button>
                </div>
                <table class="contestTable">
                    <tr>
                        <th>Sent to</th>
                        <th>Message</th>
                        <th>Time</th>
                    </tr>
                </table>`;

                displayPastMessages(sortedMessageList);

                const pressSearch = document.querySelector(".press-search");
                const searchBox = document.querySelector(".search-box");

                const performSearch = () => {
                    const inputValue = searchBox.value.toLowerCase();
                    const filteredMessages = sortedMessageList.filter(message => {
                        return `${message.description}`.toLowerCase().includes(inputValue) ||
                            `${message.date}`.toLowerCase().includes(inputValue) ||
                            `${message.admin.admin_Name}`.toLowerCase().includes(inputValue) ||
                            `${message.admin.admin_Id}`.toLowerCase().includes(inputValue);
                    });
                    displayPastMessages(filteredMessages);
                };

                pressSearch.addEventListener("click", performSearch);
                searchBox.addEventListener("keypress", (event) => {
                    if (event.key === "Enter") {
                        performSearch();
                    }
                });
                searchBox.addEventListener("input", () => {
                    if (searchBox.value === "") {
                        displayPastMessages(sortedMessageList);
                    } else {
                        performSearch();
                    }
                });
            } else if (clickedButton.id === "running") {
                displayRunningMessagesForm(res.data.admins);
            }
        }

        past.addEventListener("click", function () {
            handleButtonClick(this);
        });

        running.addEventListener("click", function () {
            handleButtonClick(this);
        });
    };

    const getData = () => {
        makeRequest({
            method: 'get',
            url: `/getMessagesByDesigner?id=${id}`,
        })
            .then((res) => {
                const sortedMessageList = res.data.messages.sort((a, b) => new Date(b.date) - new Date(a.date));
                displayMessageOption(sortedMessageList, res);
            })
            .catch((err) => console.error(err));
    };

    getData();
});
