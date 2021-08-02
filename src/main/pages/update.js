
let reimbursementSelect;
let reimbursements;
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
let finalGradeTd;
let projectedClaimTd;
let actualClaimTd;
let allMessages;
let messagesDiv;
let allAttachments;
let attachmentsDiv;
let messagesLoading;
let attachmentsLoading;
let addAttachmentButton;
let addAttachmentSuccessH2;
let attachmentUrlInput;
let descriptionInput;
let finalGradeDiv;
let finalGradeInput;
let addFinalGradeButton;
let addFinalGradeSuccessH2;

const initUpdate = () => {

  reimbursementSelect = document.getElementById("reimbursement-select");
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
  finalGradeTd = document.getElementById("final-grade");
  projectedClaimTd = document.getElementById("projected-claim");
  actualClaimTd = document.getElementById("actual-claim");
  messagesDiv = document.getElementById("messages-div");
  attachmentsDiv = document.getElementById("attachments-div");
  messagesLoading = document.getElementById("messages-loading");
  attachmentsLoading = document.getElementById("attachments-loading");
  addAttachmentButton = document.getElementById("add-attachment");
  addAttachmentSuccessH2 = document.getElementById("add-attachment-success");
  attachmentUrlInput = document.getElementById("attachment-url");
  descriptionInput = document.getElementById("attachment-description");
  finalGradeDiv = document.getElementById("final-grade-div");
  finalGradeInput = document.getElementById("final-grade-input");
  addFinalGradeButton = document.getElementById("add-final-grade");
  addFinalGradeSuccessH2 = document.getElementById("add-final-grade-success");

  getReimbursements();
  getAllMessages();
  getAllAttachments();

};

const getReimbursements = () => {

  console.log("Start getReimbursements()");

  const url = "http://localhost:7000/employees/" +
    sessionStorage.getItem("employeeId") + "/reimbursements";

  const xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      if (reimbursements = JSON.parse(this.responseText)) {

        console.log("JSON parsed successfully.");
        console.log(reimbursements);

        displayReimbursements();

      } else {
        console.log("JSON has incorrect syntax!");
      }

    } else if (this.readyState == 4) {
      console.log("Request was not successfully processed!");
    }

  }

  xhr.open("GET", url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send();

};

const displayReimbursements = () => {

  console.log("Start displayReimbursements()");

  for (i = 0; i < reimbursements.length; i++) {

    const reimbursement = reimbursements[i];
    console.log(reimbursement);

    const optionNode = document.createElement("option");
    optionNode.setAttribute("value", String(i));

    const date = new Date(reimbursement.event.startTime);

    const textNode = document.createTextNode(
      String(reimbursement.event.eventName) + ", " +
      String(reimbursement.event.eventType.typeName) + ", on " +
      date.toString()
    );

    console.log(textNode);
    console.log(reimbursementSelect);

    optionNode.appendChild(textNode);
    reimbursementSelect.appendChild(optionNode);

  }

  document.getElementById("reimbursements-loading").remove();
  reimbursementSelect.removeAttribute("disabled");

};

const selectReimbursement = () => {

  console.log("Start selectReimbursement()");

  const i = reimbursementSelect.value;
  const reimbursement = reimbursements[i];

  getMessagesForReimbursement(reimbursement.id);
  getAttachmentsForReimbursement(reimbursement.id);

  // If the reimbursement is ready to add a final grade
  if (reimbursement.approvalStep == 3 && finalGradeDiv.hasAttribute("hidden")) {
    finalGradeDiv.removeAttribute("hidden");
  } else {
    finalGradeDiv.setAttribute("hidden", "");
  }

  let endDate;
  if (tryEndTime = reimbursement.event.endTime) {
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

  let finalGrade;
  if (reimbursement.finalGrade && reimbursement.finalGrade != undefined) {
    finalGrade = reimbursement.finalGrade;
  } else {
    finalGrade = "N/A";
  }

  const projectedClaim = String((Math.round(reimbursement.event.tuition
    * reimbursement.event.eventType.percentCoverage) / 100).toFixed(2)) + " USD";

  let actualClaim;
  if (reimbursement.actualClaim && reimbursement.actualClaim != undefined) {
    actualClaim = String(reimbursement.actualClaim.toFixed(2)) + " USD";
  } else {
    actualClaim = "N/A";
  }

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
  finalGradeTd.innerHTML = finalGrade;
  projectedClaimTd.innerHTML = projectedClaim;
  actualClaimTd.innerHTML = actualClaim;

};

const getAllMessages = () => {

  console.log("Start getMessages()");

  const url = "http://localhost:7000/employees/" +
    sessionStorage.getItem("employeeId") + "/messages";

  const xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      if (allMessages = JSON.parse(this.responseText)) {

        console.log("JSON parsed successfully.");
        console.log(allMessages);

      } else {
        console.log("JSON has incorrect syntax!");
      }

    } else if (this.readyState == 4) {
      console.log("Request was not successfully processed!");
    }

  }

  xhr.open("GET", url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send();

};

const getMessagesForReimbursement = rId => {

  console.log("Start getMessagesForReimbursement()");

  let foundMessages = [];

  for (i = 0; i < allMessages.length; i++) {

    if (allMessages[i].reimbursement.id == rId) {

      foundMessages.push(allMessages[i]);

    }

  }

  displayMessages(foundMessages);
  messagesLoading.remove();

};

const getAttachmentsForReimbursement = rId => {

  console.log("Start getAttachmentsForReimbursement()");

  let foundAttachments = [];

  for (i = 0; i < allAttachments.length; i++) {

    if (allAttachments[i].reimbursement.id == rId) {

      foundAttachments.push(allAttachments[i]);

    }

  }

  displayAttachments(foundAttachments);
  attachmentsLoading.remove();

};

const displayMessages = messages => {

  console.log("Start displayMessages()");

  messagesDiv.innerHTML = "";

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
    messageTypeValue.appendChild(mTypeValueText)

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

    messagesDiv.appendChild(tableNode);

  }

};

const getAllAttachments = () => {

  console.log("Start getAllAttachments()");

  const url = "http://localhost:7000/employees/" +
    sessionStorage.getItem("employeeId") + "/attachments";

  const xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      if (allAttachments = JSON.parse(this.responseText)) {

        console.log("JSON parsed successfully.");
        console.log(allAttachments);

      } else {
        console.log("JSON has incorrect syntax!");
      }

    } else if (this.readyState == 4) {
      console.log("Request was not successfully processed!");
    }

  }

  xhr.open("GET", url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send();
};

const displayAttachments = attachments => {

  console.log("Start displayAttachments()");

  attachmentsDiv.innerHTML = "";

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
    attachmentDescriptionValue.appendChild(attachmentDescriptionValueText)

    attachmentUrlTr.appendChild(attachmentUrlKey);
    attachmentUrlTr.appendChild(attachmentUrlValue);

    attachmentDescriptionTr.appendChild(attachmentDescriptionKey);
    attachmentDescriptionTr.appendChild(attachmentDescriptionValue);

    tableNode.appendChild(attachmentUrlTr);
    tableNode.appendChild(attachmentDescriptionTr);

    attachmentsDiv.appendChild(tableNode);

  }

};

const addAttachment = () => {

  console.log("Start addAttachment()");

  addAttachmentSuccessH2.innerHTML = "Processing...";
  addAttachmentSuccessH2.setAttribute("class", "bg-warning");
  addAttachmentButton.setAttribute("disabled", "");

  let attachment = new Object();

  const reimbursement = reimbursements[reimbursementSelect.value];
  const attachmentUrl = attachmentUrlInput.value;
  const description = descriptionInput.value;

  if (!attachmentUrl || !description) {
    addAttachmentSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
    addAttachmentSuccessH2.setAttribute("class", "bg-danger");
    addAttachmentButton.removeAttribute("disabled");
    return;
  }

  attachment.reimbursement = reimbursement;
  attachment.attachmentUrl = attachmentUrl;
  attachment.description = description;

  const attachmentString = JSON.stringify(attachment);

  console.log("JSON is ready to send");
  console.log(attachmentString);

  const url = "http://localhost:7000/attachments"
  const xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 201) {
      console.log(this.responseText);

      if (JSON.parse(this.responseText)) {
        addAttachmentSuccessH2.innerHTML = "Attachment was added successfully! Redirecting to home...";
        addAttachmentSuccessH2.setAttribute("class", "bg-success");
        setTimeout(redirectToHome(), 5000);
      } else {
        addAttachmentSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
        addAttachmentSuccessH2.setAttribute("class", "bg-danger");
        addAttachmentButton.removeAttribute("disabled");
      }

    } else if (this.readyState == 4) {
      addAttachmentSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
      addAttachmentSuccessH2.setAttribute("class", "bg-danger");
      addAttachmentButton.removeAttribute("disabled");
    }

  }

  xhr.open("POST", url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(attachmentString);

};

const addFinalGrade = () => {

  console.log("Start addFinalGrade()");

  addFinalGradeSuccessH2.innerHTML = "Processing...";
  addFinalGradeSuccessH2.setAttribute("class", "bg-warning");
  addFinalGradeButton.setAttribute("disabled", "");

  const reimbursement = reimbursements[reimbursementSelect.value];

  const finalGrade = finalGradeInput.value;

  if (!finalGrade) {
    addFinalGradeSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
    addFinalGradeSuccessH2.setAttribute("class", "bg-danger");
    addFinalGradeButton.removeAttribute("disabled");
    return;
  }

  reimbursement.finalGrade = finalGrade;
  reimbursement.approvalStep = 4;

  const reimbursementString = JSON.stringify(reimbursement);

  console.log("JSON is ready to send");
  console.log(reimbursementString);

  const url = "http://localhost:7000/reimbursements";
  const xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function () {
    console.log("Begin loop iteration");

    if (this.readyState == 4 && this.status == 200) {
      console.log(this.responseText);

      if (JSON.parse(this.responseText)) {
        addFinalGradeSuccessH2.innerHTML = "Final Grade was added successfully! Redirecting to home...";
        addFinalGradeSuccessH2.setAttribute("class", "bg-success");
        setTimeout(redirectToHome(), 10000);
      } else {
        addFinalGradeSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
        addFinalGradeSuccessH2.setAttribute("class", "bg-danger");
        addFinalGradeButton.removeAttribute("disabled");
      }

    } else if (this.readyState == 4) {
      addFinalGradeSuccessH2.innerHTML = "Something went wrong! Is everything entered correctly?";
      addFinalGradeSuccessH2.setAttribute("class", "bg-danger");
      addFinalGradeButton.removeAttribute("disabled");
    }

  }

  xhr.open("PUT", url, true);
  xhr.setRequestHeader('Content-Type', 'application/json');
  xhr.send(reimbursementString);

}

const redirectToHome = () => {
  document.getElementById("index-link").click();
};