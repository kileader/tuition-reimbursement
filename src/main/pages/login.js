
const initLogin = () => {
  console.log("Begin initLogin()");

  let loginContainer = document.getElementById("login-container");
  let pageContainer = document.getElementById("page-container");
  let employeeNameSpan = document.getElementById("employee-name")

  let firstName = sessionStorage.getItem("firstName");
  let lastName = sessionStorage.getItem("lastName");

  if (firstName) {
    console.log("Name exists")
    if (pageContainer.hasAttribute("hidden")) {
      pageContainer.removeAttribute("hidden");
    }
    loginContainer.setAttribute("hidden", "");
    employeeNameSpan.innerHTML = firstName + " " + lastName;
  } else {
    console.log("Name doesn't exist")
    if (loginContainer.hasAttribute("hidden")) {
      loginContainer.removeAttribute("hidden");
    }
    pageContainer.setAttribute("hidden", "");
  }

}

const attemptLogin = () => {

  let emailInput = document.getElementById("email");
  let passwordInput = document.getElementById("password");

  let loginForm = new Object();

  loginForm.email = emailInput.value;
  loginForm.password = passwordInput.value;

  console.log(loginForm);

  let loginFormString = JSON.stringify(loginForm);

  console.log("JSON is ready to send");

  sendLoginFormString(loginFormString);

}

const sendLoginFormString = loginFormString => {

  console.log("Start sendLoginFormString()");

  let loginSuccessH2 = document.getElementById("login-success");
  loginSuccessH2.innerHTML = "Processing...";
  loginSuccessH2.setAttribute("class", "bg-warning");

  let loginButton = document.getElementById("login-button");
  loginButton.setAttribute("disabled", "");

  const url = "http://localhost:7000/login"
  let xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      let employee = JSON.parse(this.responseText);
      console.log(employee)

      if (employee) {
        sessionStorage.setItem("employeeId", employee.id);
        sessionStorage.setItem("firstName", employee.firstName);
        sessionStorage.setItem("lastName", employee.lastName);
        sessionStorage.setItem("email", employee.email);

        loginSuccessH2.innerHTML = "Log in attempt was successful!";
        loginSuccessH2.setAttribute("class", "bg-success");
        loginButton.removeAttribute("disabled");
        initLogin();
      } else {
        console.log("ready state is: " + this.readyState + ", status is: "
          + this.status + ", employee is " + JSON.stringify(employee));
        loginSuccessH2.innerHTML = "Something went wrong!";
        loginSuccessH2.setAttribute("class", "bg-danger");
        loginButton.removeAttribute("disabled");
      }

    } else if (this.status == 500) {
      console.log("Server side error")
      loginSuccessH2.innerHTML = "Server side error!";
      loginSuccessH2.setAttribute("class", "bg-danger");
      loginButton.removeAttribute("disabled");
    } else if (this.status == 404) {
      console.log("Invalid log in");
      loginSuccessH2.innerHTML = "Email or password is invalid!";
      loginSuccessH2.setAttribute("class", "bg-danger");
      loginButton.removeAttribute("disabled");
    } else if (this.readyState == 4) {
      console.log("Unknown error. Status is: " + this.status);
      loginSuccessH2.innerHTML = "Unknown error!";
      loginSuccessH2.setAttribute("class", "bg-danger");
      loginButton.removeAttribute("disabled");
    }

    console.log("End loop iteration");
    console.log(this.readyState);
  }

  xhr.open("POST", url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(loginFormString);

}

const logout = () => {
  sessionStorage.clear();
  document.getElementById("index-link").click();
}