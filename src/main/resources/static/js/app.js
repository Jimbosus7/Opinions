const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});



function meth(){
  var model = {
  
  "name": document.getElementById("name").value,
  "email": document.getElementById("email").value,
  "password": document.getElementById("password").value,
  };
$.ajax({
  type: "POST",
  url: "/SinUp",

  ContentType: "application/json ;charset=UTF-8",

  crossOrigin: true,
  dataType: "json",
  headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
  },
 data: this.JSON.stringify(model),

  success: function(data) {
        console.log(data);
        if(data[0]=="true")
        window.location.reload();

        document.getElementById("textwritting").innerHTML=data[0];
  },
  error: function(jqXHR, testStatus, errorThrown) {
      console.log("error");
  }
});
}
