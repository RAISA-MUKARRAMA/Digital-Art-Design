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
    }
    sessionStorage.setItem("Designer_id", id);
    console.log(id);

    document.querySelector(".user-name").innerHTML = '<i class="fa fa-user icon"></i>'+ " " + name;
    console.log(name);

    const getCategory = () =>{
        makeRequest({
            method: 'get',
            url: `/getCategory?id=${id}`,
        })
            .then((res)=>{
                console.log(res);
                const selectElement = document.querySelector(".category");
                res.data.forEach(category=>{
                    const optionElement = document.createElement("option");
                    optionElement.text = category.ctgr_Title;
                    optionElement.value = category.ctgr_code;
                    selectElement.add(optionElement);
                })
                const getData = () => {
                    const form = document.querySelector('.upld-form');

                    // Serialize the form data
                    const formData = new FormData(form);

                    // Check file size
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

                        makeRequest({
                            method: 'post',
                            url: `/designerUploadDesign?id=${id}`,
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
                                    window.location.href="Designer_My_Designs.html";
                                })
                            })
                            .catch((err) => console.error(err));
                    }

                };

                document.querySelector(".sub-but").addEventListener("click",()=>{
                    event.preventDefault();
                    getData();
                })
            })
            .catch((err) => console.error(err));
    }

    getCategory();
});

