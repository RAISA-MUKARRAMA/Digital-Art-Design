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
    if(id==null && name==null){
        window.location.href="/home";
        // alert("Sorry! You are not signed in");
    }
    sessionStorage.setItem("Designer_id", id);
    console.log(id);

    // let name = sessionStorage.getItem("DesignerName");
    document.querySelector(".user-name").innerHTML = '<i class="fa fa-user icon"></i>'+ " " + name;
    console.log(name);

    const DisplayOrders = (ORDER,res) =>{
        const past = document.querySelector("#past");
        const running = document.querySelector("#running");
        const coming = document.querySelector("#coming");

        const current = sessionStorage.getItem("currentOrder");
        if(!current){
            sessionStorage.setItem("currentOrder",running.id);
            running.classList.add("active");
            handleButtonClick(running);
        }
        else{
            console.log(current);
            if(current=="past"){
                handleButtonClick(past);
            }
            else if(current=="running"){
                handleButtonClick(running);
            }
            else{
                handleButtonClick(coming);
            }
        }

        function handleButtonClick(clickedButton) {
            // Remove "active" class from all buttons
            document.querySelectorAll(".contestOption").forEach(button => {
                button.classList.remove("active");
            });

            // Add "active" class to the clicked button
            clickedButton.classList.add("active");

            // Store the id of the active button in session storage
            sessionStorage.setItem("currentOrder", clickedButton.id);

            const contestTable = document.querySelector(".contestTable");
            contestTable.innerHTML=`<tr>
                                            <th>Order-ID</th>
                                            <th>Order Title</th>
                                            <th>Ordered by</th>
                                            <th>Ordered on</th>
                                            <th>Details</th>
                                            </tr>`

            // Your logic to show/hide contents based on which button is active
            // For example:

            let userName = {};
            res.data.users.forEach(user=>{
                userName[user.user_id]=user.user_name;
            })

            let categoryName = {};
            res.data.categories.forEach(category=>{
                categoryName[category.ctgr_code]=category.ctgr_Title;
            })


            if (clickedButton.id === "past") {
                // Show contents for button 1
                ORDER.forEach(order=>{
                    if(order.approved==0){
                        console.log(order);
                        const newRow= document.createElement("tr");
                        newRow.innerHTML= `<td>${order.order_code}</td>
                                                   <td>${order.title}</td>
                                                   <td>${userName[order.user_id]}</td>
                                                   <td>${new Date(order.date).toLocaleString()}</td>
                                                   <td class="seeDetails${order.order_code}"><u>See</u></td>`;
                        contestTable.appendChild(newRow);


                        //to visit one contest
                        const seeDetails = document.querySelector(`.seeDetails${order.order_code}`);
                        console.log(seeDetails);
                        seeDetails.addEventListener("click",()=>{
                            const oneContest = document.querySelector(".one-contest")
                            oneContest.style.display = "block";
                            oneContest.innerHTML= `<div class="details">
                                                               <h6 style="color: black">${order.order_code}</h6><br>
                                                               <h2 style="color: green"><u>${order.title}</u></h2><br>
                                                               <h4 style="color: mediumpurple">Ordered by ${userName[order.user_id]}</h4><br>
                                                               <h3 style="color: deepskyblue">Ordered on ${new Date(order.date).toLocaleString()}</h3><br>
                                                               <h5><u>Description</u></h5><br>
                                                               <p style="color: darkslategray" class="oneContestDes">${order.description}</p><br>
                                                               </div>
                                                               
                                                               <div class="contest-status-box">
                                                               <button class="acceptOrder">ACCEPT</button>
                                                               </div>

                                                               <button class="crossOneContest"><i class="fa fa-times" aria-hidden="true"></i></button>`

                            postdata= {};
                            postdata["order_code"]=order.order_code;
                            console.log(postdata);
                            const acceptOrder = document.querySelector(".acceptOrder");
                            acceptOrder.addEventListener("click",()=>{
                                makeRequest({
                                    method: 'post',
                                    url: `/designerAcceptOrder?id=${id}`,
                                    data: postdata // Include the id in the URL
                                })
                                    .then((res) =>{
                                        sessionStorage.setItem("currentOrder", running.id);
                                        window.location.href="Designer_My_Orders.html";
                                    })
                                    .catch((err) => console.error(err));
                            })

                            const crossOneContest = document.querySelector(".crossOneContest");
                            crossOneContest.addEventListener("click",()=>{
                                oneContest.style.display="none";
                            })
                        })
                    }
                })
            } else if (clickedButton.id === "running") {
                // Show contents for button 2
                ORDER.forEach(order=>{
                    if(order.approved==1){
                        console.log(order);
                        const newRow= document.createElement("tr");
                        newRow.innerHTML= `<td>${order.order_code}</td>
                                                   <td>${order.title}</td>
                                                   <td>${userName[order.user_id]}</td>
                                                   <td>${new Date(order.date).toLocaleString()}</td>
                                                   <td class="seeDetails${order.order_code}"><u>See</u></td>`;
                        contestTable.appendChild(newRow);

                        //to visit one contest
                        const seeDetails = document.querySelector(`.seeDetails${order.order_code}`);
                        console.log(seeDetails);
                        seeDetails.addEventListener("click",()=>{
                            const oneContest = document.querySelector(".one-contest")
                            oneContest.style.display = "block";
                            oneContest.innerHTML= `<div class="details">
                                                               <h6 style="color: black">${order.order_code}</h6><br>
                                                               <h2 style="color: green"><u>${order.title}</u></h2><br>
                                                               <h4 style="color: mediumpurple">Ordered by ${userName[order.user_id]}</h4><br>
                                                               <h3 style="color: deepskyblue">Ordered on ${new Date(order.date).toLocaleString()}</h3><br>
                                                               <h5><u>Description</u></h5><br>
                                                               <p style="color: darkslategray" class="oneContestDes">${order.description}</p><br>
                                                               </div>
                                                               
                                                               <div class="contest-status-box">
                                                               <form class="participateForm">
                                                                    <h3 style="color: #967d00"><u>Complete The Order</u></h3><br>
                                                                    <select class="selectDesigns" name="selectedDesign",required>
                                                                            <option value='' selected>Select Design</option>
                                                                    </select><br>
                                                                    <button class="takePart">Complete</button>
                                                                    <h5>OR</h5>
                                                                    <button class="uploadFirst">Upload the Design first</button>
                                                               </form>
                                                               </div>

                                                               <button class="crossOneContest"><i class="fa fa-times" aria-hidden="true"></i></button>`

                            const selectDesigns = document.querySelector(".selectDesigns");
                            res.data.myDesigns.forEach(myDesign=>{
                                const optionElement = document.createElement("option");
                                optionElement.text = myDesign.design_Code+'-'+myDesign.design_Title;
                                optionElement.value = myDesign.design_Code;
                                selectDesigns.add(optionElement);
                            })

                            document.querySelector(".takePart").addEventListener("click",()=>{
                                event.preventDefault();
                                const form = document.querySelector('.participateForm');
                                const formData = new FormData(form);
                                const postData = {};
                                formData.forEach((value, key) => {
                                    postData[key] = value;
                                });
                                postData["order_code"]=order.order_code;
                                console.log(postData);

                                makeRequest({
                                    method: 'post',
                                    url: `/designerCompleteAnOrder?id=${id}`,
                                    data: postData,
                                })
                                    .then((res) => {
                                        console.log(res);
                                        const succespopup = document.querySelector(".successPopup");
                                        succespopup.style.display= "block";
                                        succespopup.innerHTML=
                                            `<h1><i class="fa fa-check" aria-hidden="true" style="color: #967d00"></i></h1><br>
                                                                 <h3>${res.data}</h3><br>
                                                                 <button class="ok">Ok</button>`
                                        document.querySelector(".ok").addEventListener("click",()=>{
                                            sessionStorage.setItem("currentOrder", coming.id);
                                            window.location.href="Designer_My_Orders.html";
                                        })
                                    })
                                    .catch((err) => console.error(err));
                            })

                            document.querySelector(".uploadFirst").addEventListener("click",()=>{
                                event.preventDefault();
                                console.log("upload first clicked");
                                window.location.href="/Raisa/html/Designer_Upload_Design.html";
                            })

                            const crossOneContest = document.querySelector(".crossOneContest");
                            crossOneContest.addEventListener("click",()=>{
                                oneContest.style.display="none";
                            })
                        })
                    }
                })
            } else if (clickedButton.id === "coming") {
                // Show contents for button 3
                ORDER.forEach(order=>{
                    if(order.approved==2){
                        console.log(order);
                        const newRow= document.createElement("tr");
                        newRow.innerHTML= `<td>${order.order_code}</td>
                                                   <td>${order.title}</td>
                                                   <td>${userName[order.user_id]}</td>
                                                   <td>${new Date(order.date).toLocaleString()}</td>
                                                   <td class="seeDetails${order.order_code}"><u>See</u></td>`;
                        contestTable.appendChild(newRow);

                        //to visit one contest
                        const seeDetails = document.querySelector(`.seeDetails${order.order_code}`);
                        console.log(seeDetails);
                        seeDetails.addEventListener("click",()=>{
                            const oneContest = document.querySelector(".one-contest")
                            oneContest.style.display = "block";
                            oneContest.innerHTML= `<div class="details">
                                                               <h6 style="color: black">${order.order_code}</h6><br>
                                                               <h2 style="color: green"><u>${order.title}</u></h2><br>
                                                               <h4 style="color: mediumpurple">Ordered by ${userName[order.user_id]}</h4><br>
                                                               <h3 style="color: deepskyblue">Ordered on ${new Date(order.date).toLocaleString()}</h3><br>
                                                               <h5><u>Description</u></h5><br>
                                                               <p style="color: darkslategray" class="oneContestDes">${order.description}</p><br>
                                                               </div>
                                                               
                                                               <div class="contest-status-box">
                                                               <button class="orderDesign">See The Design</button><br><br>
                                                               <button class="dldpdf">Download PDF</button>
                                                               </div>

                                                               <button class="crossOneContest"><i class="fa fa-times" aria-hidden="true"></i></button>`

                            const orderDesign = document.querySelector(".orderDesign");
                            orderDesign.addEventListener("click",()=>{
                                const onedesign = document.querySelector(".one-design");
                                onedesign.style.display="block";
                                let base64Image;
                                if (order.designD.content != null) {
                                    base64Image = `data:image/jpeg;base64, ${order.designD.content}`;
                                } else {
                                    base64Image = "/Raisa/no-content.jpg";
                                }

                                onedesign.innerHTML= `<div class="oneImage">
                                                                          <img class="onePic" src="${base64Image}">
                                                                          </div>
                                                                          <div class="oneDescription">
                                                                          <h5><u>Design code</u></h5>
                                                                          <h5>${order.designD.design_Code}</h5><br>
                                                                          <h5><u>Title</u></h5>
                                                                          <h2 style="color: #967d00">${order.designD.design_Title}</h2><br>
                                                                          <h5><u>Category</u></h5>
                                                                          <h3 style="color: darkgreen">${categoryName[order.designD.ctgr_Code]}</h3><br>
                                                                          <h5><u>Designer</u></h5>
                                                                          <h4 style="color: rosybrown">${name}</h4><br>
                                                                          <h5><u>Rating</u></h5>
                                                                          <h3><i class="fa fa-heart" aria-hidden="true"
                                                                          style="color:red;"></i>: ${order.designD.rating}</h3><br>
                                                                          <h5 style="margin-bottom: 5px;"><u>Description</u></h5>
                                                                          <p style="color: blue" class="oneDesignDes">${order.designD.description}</p><br>
                                                                          </div>
                                                                          <button class="crossOneDesign"><i class="fa fa-times" aria-hidden="true"></i></button>`

                                const crossOneDesign = document.querySelector(".crossOneDesign");
                                crossOneDesign.addEventListener("click",()=>{
                                    const onedesign = document.querySelector(".one-design");
                                    onedesign.style.display="none";
                                })
                            })

                            const printBtn = document.querySelector(".dldpdf");
                            printBtn.addEventListener("click", () => {
                                const { jsPDF } = window.jspdf;
                                const ordersData = res.data.orders;
                                const doc = new jsPDF();
                                doc.setProperties({
                                    title: `Order-${order.order_code}`
                                });
                                doc.setFontSize(12);

                                let yOffset = 10;
                                const lineHeight = 10;
                                const maxLinesPerPage = 27; // Adjust based on your page layout

                                const addTextWithCheck = (text, yOffset, color = [0, 0, 0], style = 'normal') => {
                                    if (yOffset > 270) { // Adjust if necessary for your layout
                                        doc.addPage();
                                        yOffset = 10; // Reset yOffset for new page
                                    }
                                    doc.setTextColor(...color); // Set text color
                                    doc.setFont('helvetica', style); // Set font style
                                    doc.text(text, 10, yOffset);
                                    return yOffset + lineHeight;
                                };

                                yOffset = addTextWithCheck(`Order ID: ${order.order_code}`, yOffset, [0, 0, 255], 'bold'); // Blue color, bold text
                                yOffset = addTextWithCheck(`Title: ${order.title}`, yOffset, [255, 0, 0]); // Red color, normal text
                                yOffset = addTextWithCheck(`Ordered by: ${userName[order.user_id]}`, yOffset, [0, 128, 0], 'bold'); // Green color, bold text
                                yOffset = addTextWithCheck(`Date: ${new Date(order.date).toLocaleString()}`, yOffset, [128, 0, 128]); // Purple color, normal text
                                yOffset = addTextWithCheck(`Description: ${order.description}`, yOffset, [0, 0, 0]); // Black color, normal text

                                let base64Image;
                                if (order.designD.content != null) {
                                    base64Image = `data:image/jpeg;base64, ${order.designD.content}`;
                                } else {
                                    base64Image = "/Raisa/no-content.jpg";
                                }

                                const imageHeight = 150; // Adjust as needed
                                const imageWidth = 150; // Adjust as needed

                                // Check if a new page is needed before adding the image
                                if (yOffset + imageHeight > 290) { // Adjust if necessary for your layout
                                    doc.addPage();
                                    yOffset = 10; // Reset yOffset for new page
                                }

                                doc.addImage(base64Image, 'jpeg', 30, yOffset, imageWidth, imageHeight);
                                doc.save(`orderDetails for order-${order.order_code}.pdf`);
                            });




                            const crossOneContest = document.querySelector(".crossOneContest");
                            crossOneContest.addEventListener("click",()=>{
                                oneContest.style.display="none";
                            })
                        })
                    }
                })
            }
        }

        past.addEventListener("click", function () {
            handleButtonClick(this);
        });

        running.addEventListener("click", function () {
            handleButtonClick(this);
        });

        coming.addEventListener("click", function () {
            handleButtonClick(this);
        });
    }

    const getData = () => {
        makeRequest({
            method: 'get',
            url: `/designerGetMyOrders?id=${id}`, // Include the id in the URL
        })
            .then((res) => {
                console.log(res);

                // Sort the array based on the "id" property
                // arrayOfObjects.sort((a, b) => a.id - b.id);

                const sortedOrderList= res.data.orders;
                sortedOrderList.sort((a,b) => new Date(b.date) - new Date(a.date));
                console.log(sortedOrderList);

                let userName = {};
                res.data.users.forEach(user=>{
                    userName[user.user_id]=user.user_name;
                })

                let categoryName = {};
                res.data.categories.forEach(category=>{
                    categoryName[category.ctgr_code]=category.ctgr_Title;
                })

                DisplayOrders(sortedOrderList,res);

                const pressSearch = document.querySelector(".press-search");
                const searchBox = document.querySelector(".search-box");

                const performSearch = () => {
                    const inputValue = searchBox.value.toLowerCase();

                    const filteredOrder = sortedOrderList.filter(order => {
                        return `${order.order_code}`.toLowerCase().includes(inputValue) ||
                            `${order.title}`.toLowerCase().includes(inputValue) ||
                            `${order.date}`.toLowerCase().includes(inputValue) ||
                            `${order.description}`.toLowerCase().includes(inputValue) ||
                            `${userName[order.user_id]}`.toLowerCase().includes(inputValue);
                    });

                    DisplayOrders(filteredOrder,res);
                };


                pressSearch.addEventListener("click",performSearch);
                searchBox.addEventListener("keypress", (event) => {
                    if (event.key === "Enter") {
                        performSearch();
                    }
                });
                searchBox.addEventListener("input", () => {
                    if (searchBox.value === "") {
                        DisplayOrders(sortedOrderList,res) // Show all designs when search box is cleared
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


