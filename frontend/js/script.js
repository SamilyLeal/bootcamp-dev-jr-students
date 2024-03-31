var students = [];
var courses = [];

loadCourses();
loadStudents();

function loadCourses() {
    $.ajax({
        url: "http://localhost:8080/courses",
        type: "GET",
        async: false,
        success: (response) => {
            courses = response;
            for (var c of courses) {
                document.getElementById("selectCourse").innerHTML += `<option value=${c.id}>${c.name}</option>`;
            }
        }
    });
}

function loadStudents() {
    $.getJSON("http://localhost:8080/students", (response) => {
        students = response;

        for (let s of students) {
            addNewRow(s);
        }
    });
}

function save() {
    var student = {
        id: students.length + 1,
        name: document.getElementById("inputName").value,
        email: document.getElementById("inputEmail").value,
        phone: document.getElementById("inputPhone").value,
        idCourse: document.getElementById("selectCourse").value,
        period: document.getElementById("inputPeriod").value
    };

    $.ajax({
        url: "http://localhost:8080/students",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(student),
        success: (savedStudent) => {
            addNewRow(savedStudent);
            students.push(savedStudent);
            document.getElementById("formStudents").reset();
        }
    });
}

//Add new Row
function addNewRow(student) {
    var table = document.getElementById("studentsTable");
    var newRow = table.insertRow();

    //Insert id
    var idNode = document.createTextNode(student.id);
    newRow.insertCell().appendChild(idNode);

    //Insert name
    var nameNode = document.createTextNode(student.name);
    newRow.insertCell().appendChild(nameNode);

    //Insert email
    var emailNode = document.createTextNode(student.email);
    newRow.insertCell().appendChild(emailNode);

    //Insert phone
    var phoneNode = document.createTextNode(student.phone);
    newRow.insertCell().appendChild(phoneNode);

    //Insert course
    var courseNode = document.createTextNode(courses[student.idCourse - 1].name);
    newRow.insertCell().appendChild(courseNode);

    //Insert period
    var periodNode = document.createTextNode(student.period);
    newRow.insertCell().appendChild(periodNode);
}