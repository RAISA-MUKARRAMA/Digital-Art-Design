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


    const DisplayContest = (CONTEST,res) =>{
        const past = document.querySelector("#past");
        const running = document.querySelector("#running");
        const coming = document.querySelector("#coming");

        const current = sessionStorage.getItem("currentContest");
        if(!current){
            sessionStorage.setItem("currentContest",running.id);
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
            sessionStorage.setItem("currentContest", clickedButton.id);

            const contestTable = document.querySelector(".contestTable");
            contestTable.innerHTML="<tr>\n" +
                "                    <th>Contest-ID</th>\n" +
                "                    <th>Contest Title</th>\n" +
                "                    <th>Host</th>\n" +
                "                    <th>Start Time</th>\n" +
                "                    <th>End Time</th>\n" +
                "                    <th>Max Submission</th>\n" +
                "                    <th>Total Submission</th>\n" +
                "                    <th>Details</th>\n" +
                "                </tr>"

            // Your logic to show/hide contents based on which button is active
            // For example:
            let adminName = {};
            res.data.admins.forEach(admin=>{
                adminName[admin.admin_Id]=admin.admin_Name;
            })

            let userName = {};
            res.data.users.forEach(user=>{
                userName[user.user_id]=user.user_name;
            })

            let designerName = {};
            res.data.designers.forEach(designer=>{
                designerName[designer.designerId]=designer.designerName;
            })

            let categoryName = {};
            res.data.categories.forEach(category=>{
                categoryName[category.ctgr_code]=category.ctgr_Title;
            })

            let status = {};
            res.data.includedIn.forEach(r => {
                // Concatenate contest code and design code to create the key
                let key = r.contest.contest_Code + '-' + r.design.design_Code;
                status[key] = r.selection;
            });

            const selectStatus = {
                null:"<h2 style=\"border: 2px solid purple;width: 200px;color: purple;padding: 5px;margin-left: 90px;\">PENDING</h2>",
                1:"<h2 style=\"border: 2px solid green;width: 200px;color: green;padding: 5px;margin-left: 90px;\">SELECTED</h2>",
                0:"<h2 style=\"border: 2px solid red;width: 200px;color: red;padding: 5px;margin-left: 90px;\">NOT SELECTED</h2>"
            }

            if (clickedButton.id === "past") {
                // Show contents for button 1
                CONTEST.forEach(contest=>{
                    if(new Date(contest.last_Date_of_Sub)<new Date()){
                        console.log(contest);
                        const newRow= document.createElement("tr");
                        newRow.innerHTML= `<td>${contest.contest_Code}</td>
                                                       <td>${contest.contest_Title}</td>
                                                       <td>${adminName[contest.admin_Id]}</td>
                                                       <td>${new Date(contest.starting_Date).toLocaleString()}</td>
                                                       <td>${new Date(contest.last_Date_of_Sub).toLocaleString()}</td>
                                                       <td>${contest.max_sub}</td>
                                                       <td>${res.data.total[contest.contest_Code]}</td>
                                                       <td class="seeDetails${contest.contest_Code}"><u>See</u></td>`;
                        contestTable.appendChild(newRow);


                        //to visit one contest
                        const seeDetails = document.querySelector(`.seeDetails${contest.contest_Code}`);
                        console.log(seeDetails);
                        seeDetails.addEventListener("click",()=>{
                            const oneContest = document.querySelector(".one-contest")
                            oneContest.style.display = "block";
                            oneContest.innerHTML= `<div class="details">
                                                               <h6 style="color: black">${contest.contest_Code}</h6><br>
                                                               <h2 style="color: green"><u>${contest.contest_Title}</u></h2><br>
                                                               <h4 style="color: coral">Hosted by ${adminName[contest.admin_Id]}</h4><br>
                                                               <h4 style="color: mediumpurple">Requested by ${userName[contest.user_ID]}</h4><br>
                                                               <div style="border: 1px solid black;width: 300px;height: 70px;margin-left: 150px;padding-top: 10px;">
                                                               <h3 style="color: deepskyblue">Started on ${new Date(contest.starting_Date).toLocaleString()}</h3><br>
                                                               <h3 style="color: red">Ended on ${new Date(contest.last_Date_of_Sub).toLocaleString()}</h3><br>
                                                               </div><br>
                                                               <h4 style="color: black"><span><u>Max Submission:</u></span> ${contest.max_sub} <span style="margin-left: 20px;"><u>Total Submission:</u></span> ${res.data.total[contest.contest_Code]} </h4><br>
                                                               <h5><u>Description</u></h5><br>
                                                               <p style="color: darkslategray" class="oneContestDes">${contest.description}</p><br>
                                                               </div>
                                                               
                                                               <div class="contest-status-box">
                                                               <h5><u>Status</u></h5><br>
                                                               <div class="contest-status" style="color: red"><h2>Contest has already Ended</h2>
                                                               </div><br>
                                                               <h4 style="color: blue;cursor: pointer;" class="seeSubmittedDesigns"><u>See all submitted Designs</u></h4>
                                                               </div>

                                                               <button class="crossOneContest"><i class="fa fa-times" aria-hidden="true"></i></button>`



                            const seeSubmittedDesigns = document.querySelector(".seeSubmittedDesigns");
                            seeSubmittedDesigns.addEventListener("click",()=>{
                                const oneContestDesigns = document.querySelector(".one-contest-designs");
                                oneContestDesigns.style.display="block";
                                if(contest.designs.length===0){
                                    oneContestDesigns.innerHTML="<h1 style='color: #525151;text-align: center;margin-top: 230px;'>No Designs have been Submitted.</h1>";
                                }
                                else{
                                    contest.designs.forEach(design => {
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

                                        // Append the container div to the oneContestDesigns
                                        oneContestDesigns.appendChild(showDesign);

                                        const overlay= document.createElement("div");
                                        overlay.classList.add("overlay");

                                        overlay.innerHTML= `<p>${design.design_Code}</p><br>
                                                                    <p>${design.design_Title}</p><br>
                                                                    <p>${designerName[design.designer_Id]}</p><br>
                                                                    <p><i class="fa fa-heart" aria-hidden="true" style="color:red;">
                                                                    </i>: ${design.rating}</p>`

                                        showDesign.appendChild(overlay);

                                        showDesign.addEventListener("click", ()=>{
                                            console.log("one design Clicked");
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
                                                                          <h3 style="color: darkgreen">${categoryName[design.ctgr_Code]}</h3><br>
                                                                          <h5><u>Designer</u></h5>
                                                                          <h4 style="color: rosybrown">${designerName[design.designer_Id]}</h4><br>
                                                                          <h5><u>Rating</u></h5>
                                                                          <h3><i class="fa fa-heart" aria-hidden="true"
                                                                          style="color:red;"></i>: ${design.rating}</h3><br>
                                                                          <h5 style="margin-bottom: 5px;"><u>Description</u></h5>
                                                                          <p style="color: blue" class="oneDesignDes">${design.description}</p><br>
                                                                          <h5 style="margin-top: 100px;margin-bottom: 5px;"><u>Status</u></h5>
                                                                          ${selectStatus[status[contest.contest_Code+ '-' +design.design_Code]]}<br>
                                                                          </div>
                                                                          <button class="crossOneDesign"><i class="fa fa-times" aria-hidden="true"></i></button>`

                                            const crossOneDesign = document.querySelector(".crossOneDesign");
                                            crossOneDesign.addEventListener("click",()=>{
                                                const onedesign = document.querySelector(".one-design");
                                                onedesign.style.display="none";
                                            })
                                        })
                                    });
                                }
                                // <button className="crossOneContestDesigns"><i className="fa fa-times" aria-hidden="true"></i></button>
                                const crossOneContestDesigns = document.createElement("button");
                                crossOneContestDesigns.innerHTML=`<i class="fa fa-times" aria-hidden="true"></i>`;
                                crossOneContestDesigns.classList.add("crossOneContestDesigns");
                                oneContestDesigns.appendChild(crossOneContestDesigns);

                                crossOneContestDesigns.addEventListener("click",()=>{
                                    oneContestDesigns.style.display="none";
                                    oneContestDesigns.innerHTML=""; //remove all designs to avoid redundancy

                                })
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
                CONTEST.forEach(contest=>{
                    if(new Date(contest.last_Date_of_Sub)>=new Date() && new Date(contest.starting_Date)<=new Date()){
                        console.log(contest);
                        const newRow= document.createElement("tr");
                        newRow.innerHTML= `<td>${contest.contest_Code}</td>
                                                       <td>${contest.contest_Title}</td>
                                                       <td>${adminName[contest.admin_Id]}</td>
                                                       <td>${new Date(contest.starting_Date).toLocaleString()}</td>
                                                       <td>${new Date(contest.last_Date_of_Sub).toLocaleString()}</td>
                                                       <td>${contest.max_sub}</td>
                                                       <td>${res.data.total[contest.contest_Code]}</td>
                                                       <td class="seeDetails${contest.contest_Code}"><u>See</u></td>`;
                        contestTable.appendChild(newRow);

                        //to visit one contest
                        const seeDetails = document.querySelector(`.seeDetails${contest.contest_Code}`);
                        console.log(seeDetails);
                        seeDetails.addEventListener("click",()=>{
                            const oneContest = document.querySelector(".one-contest")
                            oneContest.style.display = "block";
                            oneContest.innerHTML= `<div class="details">
                                                               <h6 style="color: black">${contest.contest_Code}</h6><br>
                                                               <h2 style="color: green"><u>${contest.contest_Title}</u></h2><br>
                                                               <h4 style="color: coral">Hosted by ${adminName[contest.admin_Id]}</h4><br>
                                                               <h4 style="color: mediumpurple">Requested by ${userName[contest.user_ID]}</h4><br>
                                                               <div style="border: 1px solid black;width: 300px;height: 70px;margin-left: 150px;padding-top: 10px;">
                                                               <h3 style="color: deepskyblue">Started on ${new Date(contest.starting_Date).toLocaleString()}</h3><br>
                                                               <h3 style="color: red">End on ${new Date(contest.last_Date_of_Sub).toLocaleString()}</h3><br>
                                                               </div><br>
                                                               <h4 style="color: black"><span><u>Max Submission:</u></span> ${contest.max_sub} <span style="margin-left: 20px;"><u>Total Submission:</u></span> ${res.data.total[contest.contest_Code]} </h4><br>
                                                               <h5><u>Description</u></h5><br>
                                                               <p style="color: darkslategray" class="oneContestDes">${contest.description}</p><br>
                                                               </div>
                                                               
                                                               <div class="contest-status-box">
                                                               <h5><u>Status</u></h5><br>
                                                               <div class="contest-status" style="color: green"><h2>Contest is running</h2>
                                                               </div><br>
                                                               <form class="participateForm">
                                                                    <h3 style="color: #967d00"><u>Participate With your Design</u></h3><br>
                                                                    <select class="selectDesigns" name="selectedDesign",required>
                                                                            <option value='' selected>Select Design</option>
                                                                    </select><br>
                                                                    <button class="takePart">Participate</button>
                                                                    <h5>OR</h5>
                                                                    <button class="uploadFirst">Upload the Design first</button>
                                                               </form>
                                                               <h4 style="color: blue;cursor: pointer;margin-top: 5px;" class="seeSubmittedDesigns"><u>See all submitted Designs</u></h4>
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
                                postData["contest_code"]=contest.contest_Code;
                                console.log(postData);

                                let already=false;
                                contest.designs.forEach(design=>{
                                    if(String(design.design_Code)===postData["selectedDesign"]){
                                        already=true;
                                    }
                                })
                                console.log(already);
                                if(already){
                                    const succespopup = document.querySelector(".successPopup");
                                    succespopup.style.display= "block";
                                    succespopup.innerHTML=
                                        `<h1><i class="fa fa-exclamation-triangle" aria-hidden="true" style="color: #967d00"></i></h1><br>
                                                         <h3>This Design has already been participated</h3><br>
                                                         <button class="ok">Ok</button>`

                                    document.querySelector(".ok").addEventListener("click",()=>{
                                        succespopup.style.display="none";
                                    })
                                }
                                else{
                                    makeRequest({
                                        method: 'post',
                                        url: `/designerParticipateInContest?id=${id}`,
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
                                                succespopup.style.display="none";
                                                window.location.href="Designer_Participate_In_Contest.html";
                                            })
                                        })
                                        .catch((err) => console.error(err));
                                }
                            })

                            document.querySelector(".uploadFirst").addEventListener("click",()=>{
                                event.preventDefault();
                                console.log("upload first clicked");
                                window.location.href="/Raisa/html/Designer_Upload_Design.html";
                            })

                            const seeSubmittedDesigns = document.querySelector(".seeSubmittedDesigns");
                            seeSubmittedDesigns.addEventListener("click",()=>{
                                const oneContestDesigns = document.querySelector(".one-contest-designs");
                                oneContestDesigns.style.display="block";
                                console.log(contest.designs.length);
                                if(contest.designs.length===0){
                                    oneContestDesigns.innerHTML="<h1 style='color: #525151;text-align: center;margin-top: 230px;'>No Designs have been Submitted.</h1>";
                                }
                                else{
                                    contest.designs.forEach(design => {
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

                                        // Append the container div to the oneContestDesigns
                                        oneContestDesigns.appendChild(showDesign);

                                        const overlay= document.createElement("div");
                                        overlay.classList.add("overlay");

                                        overlay.innerHTML= `<p>${design.design_Code}</p><br>
                                                                    <p>${design.design_Title}</p><br>
                                                                    <p>${designerName[design.designer_Id]}</p><br>
                                                                    <p><i class="fa fa-heart" aria-hidden="true" style="color:red;">
                                                                    </i>: ${design.rating}</p>`

                                        showDesign.appendChild(overlay);

                                        showDesign.addEventListener("click", ()=>{
                                            console.log("one design Clicked");
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
                                                                          <h3 style="color: darkgreen">${categoryName[design.ctgr_Code]}</h3><br>
                                                                          <h5><u>Designer</u></h5>
                                                                          <h4 style="color: rosybrown">${designerName[design.designer_Id]}</h4><br>
                                                                          <h5><u>Rating</u></h5>
                                                                          <h3><i class="fa fa-heart" aria-hidden="true"
                                                                          style="color:red;"></i>: ${design.rating}</h3><br>
                                                                          <h5 style="margin-bottom: 5px;"><u>Description</u></h5>
                                                                          <p style="color: blue" class="oneDesignDes">${design.description}</p><br>
                                                                          <h5 style="margin-top: 100px;margin-bottom: 5px;"><u>Status</u></h5>
                                                                          ${selectStatus[status[contest.contest_Code+ '-' +design.design_Code]]}<br>
                                                                          </div>
                                                                          <button class="crossOneDesign"><i class="fa fa-times" aria-hidden="true"></i></button>`

                                            const crossOneDesign = document.querySelector(".crossOneDesign");
                                            crossOneDesign.addEventListener("click",()=>{
                                                const onedesign = document.querySelector(".one-design");
                                                onedesign.style.display="none";
                                            })
                                        })
                                    });
                                }
                                // <button className="crossOneContestDesigns"><i className="fa fa-times" aria-hidden="true"></i></button>
                                const crossOneContestDesigns = document.createElement("button");
                                crossOneContestDesigns.innerHTML=`<i class="fa fa-times" aria-hidden="true"></i>`;
                                crossOneContestDesigns.classList.add("crossOneContestDesigns");
                                oneContestDesigns.appendChild(crossOneContestDesigns);

                                crossOneContestDesigns.addEventListener("click",()=>{
                                    oneContestDesigns.style.display="none";
                                    oneContestDesigns.innerHTML=""; //remove all designs to avoid redundancy

                                })
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
                CONTEST.forEach(contest=>{
                    if(new Date(contest.starting_Date)>new Date()){
                        console.log(contest);
                        const newRow= document.createElement("tr");
                        newRow.innerHTML= `<td>${contest.contest_Code}</td>
                                                       <td>${contest.contest_Title}</td>
                                                       <td>${adminName[contest.admin_Id]}</td>
                                                       <td>${new Date(contest.starting_Date).toLocaleString()}</td>
                                                       <td>${new Date(contest.last_Date_of_Sub).toLocaleString()}</td>
                                                       <td>${contest.max_sub}</td>
                                                       <td>${res.data.total[contest.contest_Code]}</td>
                                                       <td class="seeDetails${contest.contest_Code}"><u>See</u></td>`;
                        contestTable.appendChild(newRow);

                        //to visit one contest
                        const seeDetails = document.querySelector(`.seeDetails${contest.contest_Code}`);
                        console.log(seeDetails);
                        seeDetails.addEventListener("click",()=>{
                            const oneContest = document.querySelector(".one-contest")
                            oneContest.style.display = "block";
                            oneContest.innerHTML= `<div class="details">
                                                               <h6 style="color: black">${contest.contest_Code}</h6><br>
                                                               <h2 style="color: green"><u>${contest.contest_Title}</u></h2><br>
                                                               <h4 style="color: coral">Hosted by ${adminName[contest.admin_Id]}</h4><br>
                                                               <h4 style="color: mediumpurple">Requested by ${userName[contest.user_ID]}</h4><br>
                                                               <div style="border: 1px solid black;width: 300px;height: 70px;margin-left: 150px;padding-top: 10px;">
                                                               <h3 style="color: deepskyblue">Start on ${new Date(contest.starting_Date).toLocaleString()}</h3><br>
                                                               <h3 style="color: red">End on ${new Date(contest.last_Date_of_Sub).toLocaleString()}</h3><br>
                                                               </div><br>
                                                               <h4 style="color: black"><span><u>Max Submission:</u></span> ${contest.max_sub} <span style="margin-left: 20px;"><u>Total Submission:</u></span> ${res.data.total[contest.contest_Code]} </h4><br>
                                                               <h5><u>Description</u></h5><br>
                                                               <p style="color: darkslategray" class="oneContestDes">${contest.description}</p><br>
                                                               </div>
                                                               
                                                               <div class="contest-status-box">
                                                               <h5><u>Status</u></h5><br>
                                                               <div class="contest-status" style="color: deepskyblue"><h2>Contest has not started</h2>
                                                               </div><br>
                                                               </div>

                                                               <button class="crossOneContest"><i class="fa fa-times" aria-hidden="true"></i></button>`

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
                url: `/designerParticipateInContest?id=${id}`, // Include the id in the URL
            })
                .then((res) => {
                    console.log(res);

                    let adminName = {};
                    res.data.admins.forEach(admin=>{
                        adminName[admin.admin_Id]=admin.admin_Name;
                    })

                    let userName = {};
                    res.data.users.forEach(user=>{
                        userName[user.user_id]=user.user_name;
                    })

                    // Sort the array based on the "id" property
                    // arrayOfObjects.sort((a, b) => a.id - b.id);

                    const sortedContestList= res.data.contests;
                    sortedContestList.sort((a,b) => new Date(b.starting_Date) - new Date(a.starting_Date));
                    console.log(sortedContestList);

                    DisplayContest(sortedContestList,res);

                    const pressSearch = document.querySelector(".press-search");
                    const searchBox = document.querySelector(".search-box");

                    const performSearch = () => {
                        const inputValue = searchBox.value.toLowerCase();

                        const filteredContest = sortedContestList.filter(contest => {
                            return `${contest.contest_Code}`.toLowerCase().includes(inputValue) ||
                                `${contest.contest_Title}`.toLowerCase().includes(inputValue) ||
                                `${contest.description}`.toLowerCase().includes(inputValue) ||
                                `${contest.last_Date_of_Sub}`.toLowerCase().includes(inputValue) ||
                                `${contest.starting_Date}`.toLowerCase().includes(inputValue) ||
                                `${contest.admin_Id}`.toLowerCase().includes(inputValue) ||
                                `${contest.user_ID}`.toLowerCase().includes(inputValue) ||
                                `${adminName[contest.admin_Id]}`.toLowerCase().includes(inputValue) ||
                                `${userName[contest.user_ID]}`.toLowerCase().includes(inputValue) ;
                        });

                        DisplayContest(filteredContest,res);
                    };


                    pressSearch.addEventListener("click",performSearch);
                    searchBox.addEventListener("keypress", (event) => {
                        if (event.key === "Enter") {
                            performSearch();
                        }
                    });
                    searchBox.addEventListener("input", () => {
                        if (searchBox.value === "") {
                            DisplayContest(sortedContestList,res) // Show all designs when search box is cleared
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


