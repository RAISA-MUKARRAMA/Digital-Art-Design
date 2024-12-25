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


    const getData = () => {
        // Select the form element
        const form = document.querySelector('.upld-form');

        // Serialize the form data
        const formData = new FormData(form);

        let fileSizeExceeded = false;
        for (let [key, value] of formData.entries()) {
            if (value instanceof File && value.size > 1000000) { // 1000000 bytes = 1000KB
                fileSizeExceeded = true;
                break;
            }
        }
        if(fileSizeExceeded){
            const succespopup = document.querySelector(".successPopup");
            succespopup.style.display= "block";
            succespopup.innerHTML=
                `<h1><i class="fa fa-warning" aria-hidden="true" style="color: red"></i></h1><br>
                     <h3>Maximum upload size exceeded</h3><br>
                     <button class="ok">Ok</button>`
            document.querySelector(".ok").addEventListener("click",()=>{
                succespopup.style.display = "none";
            })
        }
        else{
            // Convert the form data into an object
            const postData = {};
            formData.forEach((value, key) => {
                postData[key] = value;
            });
            console.log(postData);
            if(postData.newpas && postData.newpas.length < 8){
                const errorpopup = document.querySelector(".successPopup");
                errorpopup.style.display= "block";
                errorpopup.innerHTML=
                    `<h1><i class="fa fa-exclamation-triangle" aria-hidden="true" style="color: #967d00"></i></h1><br>
                     <h3>New password is less than 8 characters</h3><br>
                     <button class="ok">Ok</button>`
                document.querySelector(".ok").addEventListener("click",()=>{
                    errorpopup.style.display= "none";
                })
            }
            else{
                // Get the designer id from sessionStorage
                let id = sessionStorage.getItem("id");

                makeRequest({
                    method: 'post',
                    url: `/designerUpdateProfile?id=${id}`, // Include the id in the URL
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
                            window.location.href="Designer_Designer_Portfolio.html";
                        })
                    })
                    .catch((err) => console.error(err));
            }
        }
    };



    document.querySelector(".sub-but").addEventListener("click",()=>{
        event.preventDefault();
        getData();
    })

});

