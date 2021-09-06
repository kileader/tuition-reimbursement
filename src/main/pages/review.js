let reimbursementSelect;
let reimbursements;
let employeeNameTd;
let depHeadNameTd;
let benCoNameTd;
let eventNameTd;
let startTimeTd;
let endTimeTd;
let locationTd;
let tuitionTd;
let typeNameTd;
let percentCoverageTd;
let formatNameTd;
let formatDescriptionTd;
let gradeCutoffTd;
let justificationTd;
let submissionTimeTd;
let hoursMissedTd;
let projectedClaimTd;
let messagesDiv;
let messagesTableDiv;
let attachments;
let attachmentsDiv;
let attachmentsTableDiv;
let messagesLoading;
let attachmentsLoading;
let newMessageDiv;
let addMessageSuccessH2;
let attachmentUrlInput;
let descriptionInput;
let messageTypeSelect;
let messageBodyInput;
let step4OnlyDiv;
let awardReimbursementButton;
let addMessageButton;
let awardReimbursementSuccessH2;
let actualClaimInput;

const initReview = () => {
  reimbursementSelect = document.getElementById("reimbursement-select");
  employeeNameTd = document.getElementById("employee-name-td");
  depHeadNameTd = document.getElementById("dep-head-name");
  benCoNameTd = document.getElementById("ben-co-name");
  eventNameTd = document.getElementById("event-name");
  startTimeTd = document.getElementById("start-time");
  endTimeTd = document.getElementById("end-time");
  locationTd = document.getElementById("location");
  tuitionTd = document.getElementById("tuition");
  typeNameTd = document.getElementById("type-name");
  percentCoverageTd = document.getElementById("percent-coverage");
  formatNameTd = document.getElementById("format-name");
  formatDescriptionTd = document.getElementById("format-description");
  gradeCutoffTd = document.getElementById("passing-grade-cutoff");
  justificationTd = document.getElementById("justification");
  submissionTimeTd = document.getElementById("submission-time");
  hoursMissedTd = document.getElementById("hours-missed");
  projectedClaimTd = document.getElementById("projected-claim");
  messagesDiv = document.getElementById("messages-div");
  attachmentsDiv = document.getElementById("attachments-div");
  messagesTableDiv = document.getElementById("messages-table");
  attachmentsTableDiv = document.getElementById("attachments-table");
  messagesLoading = document.getElementById("messages-loading");
  attachmentsLoading = document.getElementById("attachments-loading");
  newMessageDiv = document.getElementById("new-message-div");
  addMessageSuccessH2 = document.getElementById("add-message-success");
  attachmentUrlInput = document.getElementById("attachment-url");
  descriptionInput = document.getElementById("attachment-description");
  messageTypeSelect = document.getElementById("message-type-select");
  messageBodyInput = document.getElementById("message-body");
  addMessageButton = document.getElementById("add-message");
  step4OnlyDiv = document.getElementById("step-4-only");
  awardReimbursementButton = document.getElementById("award-reimbursement");
  awardReimbursementSuccessH2 = document.getElementById("award-reimbursement-success");
  actualClaimInput = document.getElementById("actual-claim");

  getReimbursements();
};

const getReimbursements = () => {
  console.log("Start getReimbursements()");

  let url = "http://localhost:7000/reviewers/" + sessionStorage.getItem("employeeId") + "/reimbursements";

  let xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      if ((reimbursements = JSON.parse(this.responseText))) {
        console.log("JSON parsed successfully.");
        console.log(reimbursements);

        displayReimbursements();
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

const displayReimbursements = () => {
  console.log("Start displayReimbursements()");

  for (i = 0; i < reimbursements.length; i++) {
    let reimbursement = reimbursements[i];
    console.log(reimbursement);

    let optionNode = document.createElement("option");
    optionNode.setAttribute("value", String(i));

    let reimburseeName = reimbursement.reimbursee.firstName + " " + reimbursement.reimbursee.lastName;

    let date = new Date(reimbursement.event.startTime);

    let currentDate = new Date();

    let textNode;
    if (currentDate + 60480000 * 2 > date) {
      textNode = document.createTextNode(
        "URGENT! " + reimburseeName + " at " + reimbursement.event.eventName + " on " + date.toString()
      );
      optionNode.prepend(textNode);
    } else {
      textNode = document.createTextNode(
        reimburseeName + " at " + reimbursement.event.eventName + " on " + date.toString()
      );
      optionNode.appendChild(textNode);
    }

    console.log(textNode);
    console.log(reimbursementSelect);

    reimbursementSelect.appendChild(optionNode);
  }

  document.getElementById("reimbursements-loading").remove();
  reimbursementSelect.removeAttribute("disabled");
  document.getElementById("reimbursement-request").removeAttribute("hidden");
};

const selectReimbursement = () => {
  console.log("Start selectReimbursement()");

  let i = reimbursementSelect.value;
  let reimbursement = reimbursements[i];

  getAttachmentsForReimbursement(reimbursement.id);
  getMessagesForReimbursement(reimbursement.id);

  let endDate;
  if ((tryEndTime = reimbursement.event.endTime)) {
    endDate = new Date(tryEndTime);
    endDateString = endDate.toString();
  } else {
    endDate = "N/A";
  }

  let hoursMissed;
  if (reimbursement.hoursMissed && reimbursement.hoursMissed != undefined) {
    hoursMissed = reimbursement.hoursMissed;
  } else {
    hoursMissed = "N/A";
  }

  let projectedClaim =
    String((Math.round(reimbursement.event.tuition * reimbursement.event.eventType.percentCoverage) / 100).toFixed(2)) +
    " USD";

  employeeNameTd.innerHTML = reimbursement.reimbursee.firstName + " " + reimbursement.reimbursee.lastName;
  eventNameTd.innerHTML = reimbursement.event.eventName;
  startTimeTd.innerHTML = new Date(reimbursement.event.startTime).toString();
  endTimeTd.innerHTML = endDate;
  locationTd.innerHTML = reimbursement.event.location;
  tuitionTd.innerHTML = String(reimbursement.event.tuition.toFixed(2)) + " USD";
  typeNameTd.innerHTML = reimbursement.event.eventType.typeName;
  percentCoverageTd.innerHTML = reimbursement.event.eventType.percentCoverage;
  formatNameTd.innerHTML = reimbursement.event.gradingFormat.formatName;
  formatDescriptionTd.innerHTML = reimbursement.event.gradingFormat.description;
  gradeCutoffTd.innerHTML = reimbursement.event.gradingFormat.passingGradeCutoff;
  justificationTd.innerHTML = reimbursement.description;
  submissionTimeTd.innerHTML = new Date(reimbursement.submissionTime).toString();
  hoursMissedTd.innerHTML = hoursMissed;
  projectedClaimTd.innerHTML = projectedClaim;

  if (reimbursement.approvalStep == 4) {
    if (step4OnlyDiv.hasAttribute("hidden")) {
      step4OnlyDiv.removeAttribute("hidden");
    }
    newMessageDiv.setAttribute("hidden", "");
  } else {
    if (newMessageDiv.hasAttribute("hidden")) {
      newMessageDiv.removeAttribute("hidden");
    }
    step4OnlyDiv.setAttribute("hidden", "");
  }
};

const getMessagesForReimbursement = (rId) => {
  console.log("Start getMessagesForReimbursement()");

  let url = "http://localhost:7000/reimbursements/" + rId + "/messages";

  let xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      if ((messages = JSON.parse(this.responseText))) {
        console.log("JSON parsed successfully.");
        console.log(messages);
        displayMessages(messages);
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

const getAttachmentsForReimbursement = (rId) => {
  console.log("Start getAttachmentsForReimbursement()");

  let url = "http://localhost:7000/reimbursements/" + rId + "/attachments";

  let xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      if ((attachments = JSON.parse(this.responseText))) {
        console.log("JSON parsed successfully.");
        console.log(attachments);
        displayAttachments(attachments);
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

const displayMessages = (messages) => {
  console.log("Start displayMessages()");

  messagesTableDiv.innerHTML = "";

  for (i = 0; i < messages.length; i++) {
    let message = messages[i];
    console.log(message);

    let tableNode = document.createElement("table");
    tableNode.setAttribute("class", "table table-dark table-hover");

    let approverTypeTr = document.createElement("tr");
    let messageTypeTr = document.createElement("tr");
    let timeSentTr = document.createElement("tr");
    let messageTr = document.createElement("tr");

    let approverTypeKey = document.createElement("td");
    let approverTypeValue = document.createElement("td");

    let messageTypeKey = document.createElement("td");
    let messageTypeValue = document.createElement("td");

    let timeSentKey = document.createElement("td");
    let timeSentValue = document.createElement("td");

    let messageKey = document.createElement("td");
    let messageValue = document.createElement("td");

    let aTypeKeyText = document.createTextNode("Approver Type");
    let aTypeValueText = document.createTextNode(message.approverType);

    let mTypeKeyText = document.createTextNode("Message Type");
    let mTypeValueText = document.createTextNode(message.messageType);

    let timeSentKeyText = document.createTextNode("Time Sent");
    let timeSent = new Date(message.timeSent);
    let timeSentValueText = document.createTextNode(timeSent);

    let messageKeyText = document.createTextNode("Message");
    let messageValueText = document.createTextNode(message.message);

    approverTypeKey.appendChild(aTypeKeyText);
    approverTypeValue.appendChild(aTypeValueText);

    messageTypeKey.appendChild(mTypeKeyText);
    messageTypeValue.appendChild(mTypeValueText);

    timeSentKey.appendChild(timeSentKeyText);
    timeSentValue.appendChild(timeSentValueText);

    messageKey.appendChild(messageKeyText);
    messageValue.appendChild(messageValueText);

    approverTypeTr.appendChild(approverTypeKey);
    approverTypeTr.appendChild(approverTypeValue);

    messageTypeTr.appendChild(messageTypeKey);
    messageTypeTr.appendChild(messageTypeValue);

    timeSentTr.appendChild(timeSentKey);
    timeSentTr.appendChild(timeSentValue);

    messageTr.appendChild(messageKey);
    messageTr.appendChild(messageValue);

    tableNode.appendChild(approverTypeTr);
    tableNode.appendChild(messageTypeTr);
    tableNode.appendChild(timeSentTr);
    tableNode.appendChild(messageTr);

    messagesTableDiv.appendChild(tableNode);
  }

  if (messagesDiv.hasAttribute("hidden")) {
    messagesDiv.removeAttribute("hidden");
  }

  messagesLoading.remove();
};

const displayAttachments = (attachments) => {
  console.log("Start displayAttachments()");

  attachmentsTableDiv.innerHTML = "";

  for (i = 0; i < attachments.length; i++) {
    let attachment = attachments[i];
    console.log(attachment);

    let tableNode = document.createElement("table");
    tableNode.setAttribute("class", "table table-dark table-hover");

    let attachmentUrlTr = document.createElement("tr");
    let attachmentDescriptionTr = document.createElement("tr");

    let attachmentUrlKey = document.createElement("td");
    let attachmentUrlValue = document.createElement("td");

    let attachmentDescriptionKey = document.createElement("td");
    let attachmentDescriptionValue = document.createElement("td");

    let attachmentUrlKeyText = document.createTextNode("Attachment URL");
    let attachmentUrlValueText = document.createTextNode(attachment.attachmentUrl);

    let attachmentDescriptionKeyText = document.createTextNode("Attachment Description");
    let attachmentDescriptionValueText = document.createTextNode(attachment.description);

    attachmentUrlKey.appendChild(attachmentUrlKeyText);
    attachmentUrlValue.appendChild(attachmentUrlValueText);

    attachmentDescriptionKey.appendChild(attachmentDescriptionKeyText);
    attachmentDescriptionValue.appendChild(attachmentDescriptionValueText);

    attachmentUrlTr.appendChild(attachmentUrlKey);
    attachmentUrlTr.appendChild(attachmentUrlValue);

    attachmentDescriptionTr.appendChild(attachmentDescriptionKey);
    attachmentDescriptionTr.appendChild(attachmentDescriptionValue);

    tableNode.appendChild(attachmentUrlTr);
    tableNode.appendChild(attachmentDescriptionTr);

    attachmentsTableDiv.appendChild(tableNode);
  }

  if (attachmentsDiv.hasAttribute("hidden")) {
    attachmentsDiv.removeAttribute("hidden");
  }

  attachmentsLoading.remove();
};

const addMessage = () => {
  console.log("Run addMessage()");

  addMessageSuccessH2.innerHTML = "Processing...";
  addMessageSuccessH2.setAttribute("class", "bg-warning");
  addMessageButton.setAttribute("disabled", "disabled");

  let message = new Object();

  let reimbursement = reimbursements[reimbursementSelect.value];
  let messageType = messageTypeSelect.value;
  let messageBody = messageBodyInput.value;

  if (!reimbursement || !messageType || !messageBody) {
    addMessageSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
    addMessageSuccessH2.setAttribute("class", "bg-danger");
    addMessageButton.removeAttribute("disabled");
    return;
  }

  message.reimbursement = reimbursement;

  if (reimbursement.approvalStep == 2) {
    message.approverType = "Benefits Coordinator";
  } else if (reimbursement.approvalStep == 1) {
    message.approverType = "Department Head";
  } else if (reimbursement.approvalStep == 0) {
    message.approverType = "Supervisor";
  }

  message.messageType = messageType;
  message.message = messageBody;

  let messageString = JSON.stringify(message);

  console.log("JSON is ready to send");
  console.log(messageString);

  let url = "http://localhost:7000/messages";
  let xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 201) {
      console.log(this.responseText);

      if (JSON.parse(this.responseText)) {
        addMessageSuccessH2.innerHTML = "Message was added successfully! Redirecting to home...";
        addMessageSuccessH2.setAttribute("class", "bg-success");
        setTimeout(redirectToHome(), 5000);
      } else {
        addMessageSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
        addMessageSuccessH2.setAttribute("class", "bg-danger");
        addMessageButton.removeAttribute("disabled");
      }
    } else if (this.readyState == 4) {
      addMessageSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
      addMessageSuccessH2.setAttribute("class", "bg-danger");
      addMessageButton.removeAttribute("disabled");
    }
  };

  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.send(messageString);
};

const awardReimbursement = () => {
  console.log("Start awardReimbursement()");

  awardReimbursementSuccessH2.innerHTML = "Processing...";
  awardReimbursementSuccessH2.setAttribute("class", "bg-warning");
  awardReimbursementButton.setAttribute("disabled", "disabled");

  let actualClaim = actualClaimInput.value;

  if (!actualClaim) {
    awardReimbursementSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
    awardReimbursementSuccessH2.setAttribute("class", "bg-danger");
    aawardReimbursementButton.removeAttribute("disabled");
    return;
  }

  const reimbursement = reimbursements[reimbursementSelect.value];
  reimbursement.approvalStep = 5;
  reimbursement.actualClaim = actualClaim;
  let reimbursementString = JSON.stringify(reimbursement);

  console.log("JSON is ready to send");
  console.log(reimbursementString);

  let url = "http://localhost:7000/reimbursements";
  let xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      if (JSON.parse(this.responseText)) {
        awardReimbursementSuccessH2.innerHTML = "Award was added successfully! Redirecting to home...";
        awardReimbursementSuccessH2.setAttribute("class", "bg-success");
        setTimeout(redirectToHome(), 5000);
      } else {
        awardReimbursementSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
        awardReimbursementSuccessH2.setAttribute("class", "bg-danger");
        awardReimbursementButton.removeAttribute("disabled");
      }
    } else if (this.readyState == 4) {
      awardReimbursementSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
      awardReimbursementSuccessH2.setAttribute("class", "bg-danger");
      awardReimbursementButton.removeAttribute("disabled");
    }
  };

  xhr.open("PUT", url, true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.send(reimbursementString);
};

const redirectToHome = () => {
  document.getElementById("index-link").click();
};
