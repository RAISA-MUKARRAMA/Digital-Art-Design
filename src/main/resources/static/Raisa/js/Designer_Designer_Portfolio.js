document.addEventListener("DOMContentLoaded", function () {
    const makeRequest = async (config) => {
        return await axios(config);
    };

    let id = sessionStorage.getItem("id");
    let name = sessionStorage.getItem("DesignerName");
    if(id==null && name==null){
        window.location.href="/home";
    }
    sessionStorage.setItem("Designer_id", id);
    console.log(id);

    const getData = () => {
        makeRequest({
            method: 'get',
            url: `/designerProfile?id=${id}`
        })
            .then((res) => {
                console.log(res);
                sessionStorage.setItem("DesignerName",res.data.designer.designerName);
                let categories = res.data.ctgrdesiger.map(category => `${category}`).join(','); // Corrected line
                document.querySelector(".user-name").innerHTML = '<i class="fa fa-user icon"></i>'+ " " + res.data.designer.designerName;
                document.querySelector(".information").innerHTML =
                    `<h6>${res.data.designer.designerId}</h6><br>
                    <h4 style="color: #967d00">${res.data.designer.designerName}</h4> <br>
                    <h3><i class="fa fa-heart" aria-hidden="true" style="color:red;"></i> ${res.data.vote}</h3><br>
                    <h5 style="color:darkgreen;">Works for==> ${categories}</h5><br>
                    <h4 style="color: darkblue">Uploaded ${res.data.totalDesigns} designs</h4><br>
                    <h4 style="color: purple">Participated in ${res.data.atncst} contests</h4><br>
                    <h4 style="color: darkred"><i class="fa fa-envelope"></i> ${res.data.designer.emailAddress}</h4><br>
                    <h4 style="color: dodgerblue"><i class="fa fa-phone"></i> ${res.data.designer.contactNo}</h4><br>
                    <h4 style="color: darkolivegreen"><i class="fas fa-home"></i> ${res.data.designer.address}</h4><br>
                    <h4 style="color: #332a02">About Me</h4><br>
                    <p style="color: #332a02;margin-left:2.8rem;max-width: 500px;max-height: 200px;word-wrap: break-word;overflow: auto;">${res.data.designer.bio}</p><br>
                    `;

                // Displaying profile picture
                if (res.data.propic != null) {
                    let base64Image = `data:image/jpeg;base64, ${res.data.propic}`;
                    document.querySelector(".imgcont").src = base64Image;
                } else {
                    document.querySelector(".imgcont").src = "/Raisa/default Profile Picture.jpg";
                }


                //update profile button
                const upProfile= document.querySelector(".upProfPopup")
                document.querySelector(".upprof").addEventListener("click",()=>{
                    console.log("CLICKED");
                    upProfile.style.display="block";
                })
                const cancel= document.querySelector(".cancel");
                cancel.addEventListener("click",()=>{
                    upProfile.style.display="none";
                })

                const matchpassform = document.querySelector(".matchPass");
                matchpassform.addEventListener("submit", (event) => {
                    event.preventDefault(); // Prevent default form submission

                    const input = document.querySelector(".givenPass").value;
                    console.log(input);
                    if (input === res.data.designer.password) {
                        console.log("matched");
                        // Redirect to update profile page
                        window.location.href = "Designer_Update_Profile.html"; // Ensure correct file name and extension
                    }
                    else{
                        const previousElemets= matchpassform.innerHTML;
                        matchpassform.innerHTML= `<h3>Passwords do not match!</h3><br>`;
                        cancel.addEventListener("click",()=>{
                            matchpassform.innerHTML= previousElemets;
                        })
                    }
                });

                document.querySelector(".signout").addEventListener("click",()=>{
                    sessionStorage.removeItem("id");
                    sessionStorage.removeItem("Designer_id");
                    sessionStorage.removeItem("DesignerName");
                    window.location.href="/home";
                })
            })
            .catch((err) => console.error(err));
    };

    getData();

});

