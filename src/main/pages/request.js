// Author: Kevin Leader

// Declare page element variables
let chooseEvBtn;
let createEvBtn;

let chooseEvDiv;
let createEvDiv;

let chooseGfBtn;
let createGfBtn;

let chooseGfDiv;
let createGfDiv;

let futureEventsArr;
let futureEventSelect;

let eventTypeSelect;

let gradingFormatsArr;
let gradingFormatSelect;

let chooseEventSelected;
let chooseGradingFormatSelected;

// Define variables and load lists
const initRequest = () => {
  chooseEvBtn = document.getElementById("choose-event");
  createEvBtn = document.getElementById("create-event");

  chooseEvDiv = document.getElementById("choose-from-events");
  createEvDiv = document.getElementById("create-new-event");

  chooseGfBtn = document.getElementById("choose-format");
  createGfBtn = document.getElementById("create-format");

  chooseGfDiv = document.getElementById("choose-from-formats");
  createGfDiv = document.getElementById("create-new-format");

  futureEventSelect = document.getElementById("event-select");
  eventTypeSelect = document.getElementById("event-type");
  gradingFormatSelect = document.getElementById("format-select");

  getEventTypes();
  getGradingFormats();
  getFutureEvents();
};

const getEventTypes = () => {
  console.log("Start getEventTypes()");

  const url = "http://localhost:7000/event_types";
  const xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      if ((eventTypesArr = JSON.parse(this.responseText))) {
        console.log("JSON parsed successfully.");
        console.log(eventTypesArr);

        displayEventTypes();
      } else {
        console.log("JSON has incorrect syntax!");
      }
    } else if (this.readyState == 4) {
      console.log("Request was not successfully processed!");
    }
  };

  xhr.open("GET", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.send();
};

const displayEventTypes = () => {
  console.log("Start displayEventTypes");

  for (let i = 0; i < eventTypesArr.length; i++) {
    const eventType = eventTypesArr[i];
    console.log(eventType);

    const optionNode = document.createElement("option");
    optionNode.setAttribute("value", String(eventType.id));

    const textNode = document.createTextNode(
      String(eventType.typeName) + ", " + String(eventType.percentCoverage) + "% of tuition covered"
    );

    optionNode.appendChild(textNode);
    eventTypeSelect.appendChild(optionNode);
  }

  document.getElementById("event-types-loading").remove();
  eventTypeSelect.removeAttribute("disabled");
};

const getGradingFormats = () => {
  console.log("Start getGradingFormats()");

  const url = "http://localhost:7000/grading_formats";
  const xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      if ((gradingFormatsArr = JSON.parse(this.responseText))) {
        console.log("JSON parsed successfully.");
        console.log(gradingFormatsArr);

        displayGradingFormats();
      } else {
        console.log("JSON has incorrect syntax!");
      }
    } else if (this.readyState == 4) {
      console.log("Request was not successfully processed!");
    }
  };

  xhr.open("GET", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.send();
};

const displayGradingFormats = () => {
  console.log("Start displayGradingFormats()");

  for (let i = 0; i < gradingFormatsArr.length; i++) {
    const gradingFormat = gradingFormatsArr[i];
    console.log(gradingFormat);

    const optionNode = document.createElement("option");
    optionNode.setAttribute("value", String(gradingFormat.id));

    const textNode = document.createTextNode(
      String(gradingFormat.formatName) +
        ", " +
        String(gradingFormat.description) +
        ", " +
        "passing grade cutoff: " +
        String(gradingFormat.passingGradeCutoff)
    );

    optionNode.appendChild(textNode);
    gradingFormatSelect.appendChild(optionNode);
  }

  document.getElementById("grading-formats-loading").remove();
  gradingFormatSelect.removeAttribute("disabled");
};

const getFutureEvents = () => {
  console.log("Start getFutureEvents()");

  const url = "http://localhost:7000/future_events";
  const xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      if ((futureEventsArr = JSON.parse(this.responseText))) {
        console.log("JSON parsed successfully.");
        console.log(futureEventsArr);

        displayFutureEvents();
      } else {
        console.log("JSON has incorrect syntax!");
      }
    } else if (this.readyState == 4) {
      console.log("Request was not successfully processed!");
    }
  };

  xhr.open("GET", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.send();
};

const displayFutureEvents = () => {
  console.log("Start displayFutureEvents()");

  for (let i = 0; i < futureEventsArr.length; i++) {
    const futureEvent = futureEventsArr[i];
    console.log(futureEvent);

    const optionNode = document.createElement("option");
    optionNode.setAttribute("value", String(futureEvent.id));

    const date = new Date(futureEvent.startTime);

    const textNode = document.createTextNode(String(futureEvent.eventName) + " on " + date.toString());

    optionNode.appendChild(textNode);
    futureEventSelect.appendChild(optionNode);
  }

  document.getElementById("future-events-loading").remove();
  futureEventSelect.removeAttribute("disabled");
};

const showChooseEvent = () => {
  if (chooseEvDiv.hasAttribute("hidden")) {
    chooseEvDiv.removeAttribute("hidden");
  }
  chooseEvBtn.setAttribute("class", "btn btn-primary");

  createEvDiv.setAttribute("hidden", "");
  createEvBtn.setAttribute("class", "btn btn-secondary");

  chooseEventSelected = true;
};

const showCreateEvent = () => {
  if (createEvDiv.hasAttribute("hidden")) {
    createEvDiv.removeAttribute("hidden");
  }
  createEvBtn.setAttribute("class", "btn btn-primary");

  chooseEvDiv.setAttribute("hidden", "");
  chooseEvBtn.setAttribute("class", "btn btn-secondary");

  chooseEventSelected = false;
};

const showChooseFormat = () => {
  if (chooseGfDiv.hasAttribute("hidden")) {
    chooseGfDiv.removeAttribute("hidden");
  }
  chooseGfBtn.setAttribute("class", "btn btn-primary");

  createGfDiv.setAttribute("hidden", "");
  createGfBtn.setAttribute("class", "btn btn-secondary");

  chooseGradingFormatSelected = true;
};

const showCreateFormat = () => {
  if (createGfDiv.hasAttribute("hidden")) {
    createGfDiv.removeAttribute("hidden");
  }
  createGfBtn.setAttribute("class", "btn btn-primary");

  chooseGfDiv.setAttribute("hidden", "");
  chooseGfBtn.setAttribute("class", "btn btn-secondary");

  chooseGradingFormatSelected = false;
};

const verifyThenSubmit = () => {
  console.log("Start verifyThenSubmit()");

  const employeeId = sessionStorage.getItem("employeeId");
  const eventId = futureEventSelect.value;
  const eventName = document.getElementById("event-name").value;
  const startDate = document.getElementById("start-date").value;
  const startTime = document.getElementById("start-time").value;
  const location = document.getElementById("location").value;
  const tuition = document.getElementById("tuition").value;
  const typeId = eventTypeSelect.value;
  const formatId = gradingFormatSelect.value;
  const formatName = document.getElementById("format-name").value;
  const formatDescription = document.getElementById("format-description").value;
  const passingGradeCutoff = document.getElementById("passing-grade-cutoff").value;
  const endDate = document.getElementById("end-date").value;
  const endTime = document.getElementById("end-time").value;
  const description = document.getElementById("description").value;
  const hoursMissed = document.getElementById("hours-missed").value;

  let requestForm = new Object();

  requestForm.employeeId = employeeId;

  if (chooseEventSelected) {
    requestForm.eventId = eventId;
  } else {
    requestForm.eventName = eventName;
    requestForm.startDate = startDate;
    requestForm.startTime = startTime;
    requestForm.location = location;
    requestForm.tuition = tuition;
    requestForm.typeId = typeId;

    if (chooseGradingFormatSelected) {
      requestForm.formatId = formatId;
    } else {
      requestForm.formatName = formatName;
      requestForm.formatDescription = formatDescription;
      requestForm.passingGradeCutoff = passingGradeCutoff;
    }

    requestForm.endDate = endDate;
    requestForm.endTime = endTime;
  }

  requestForm.description = description;
  requestForm.hoursMissed = hoursMissed;

  const requestFormString = JSON.stringify(requestForm);

  console.log("JSON is ready to send");
  console.log(requestFormString);

  sendRequestFormString(requestFormString);
};

const sendRequestFormString = (requestFormString) => {
  console.log("Start sendRequestFormString()");

  const successH2 = document.getElementById("success");
  successH2.innerHTML = "Processing...";
  successH2.setAttribute("class", "bg-warning");

  const submitButton = document.getElementById("submit");
  submitButton.setAttribute("disabled", "");

  const url = "http://localhost:7000/reimbursements";
  const xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 201) {
      console.log(this.responseText);

      if (JSON.parse(this.responseText)) {
        successH2.innerHTML = "Reimbursement Request was added successfully! Redirecting to home...";
        successH2.setAttribute("class", "bg-success");
        setTimeout(redirectToHome(), 5000);
      } else {
        successH2.innerHTML = "Something went wrong! Is everything entered correctly?";
        successH2.setAttribute("class", "bg-danger");
        submitButton.removeAttribute("disabled");
      }
    } else if (this.readyState == 4) {
      successH2.innerHTML = "Something went wrong! Is everything entered correctly?";
      successH2.setAttribute("class", "bg-danger");
      submitButton.removeAttribute("disabled");
    }
  };

  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.send(requestFormString);
};

const redirectToHome = () => {
  document.getElementById("index-link").click();
};
