var allDadsURL = "http://localhost:8080/dad/getAll";
var top10Posts = "http://localhost:8080/post/getTop10";
var allPosts = "http://localhost:8080/post/getAll";
getAllDads(allDadsURL);


function E(id) {
    return document.getElementById(id);
}

var isModeratorTrueOrFalse = (sessionStorage.getItem('moderator') === 'true');
var user = {
    id: sessionStorage.getItem('id'),
    username: sessionStorage.getItem('username'),
    moderator: isModeratorTrueOrFalse
};

if (user.id !== null) {
    listPosts('http://localhost:8080/post/' + user.id);
} else {
    listPosts(top10Posts);
}

function listPosts(url) {
    var request = new XMLHttpRequest();
    request.open('GET', url, true);
    var deleteButton = "";
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            var data = JSON.parse(request.responseText);
            var main = E("main");
            main.innerHTML = "";
            for (var post in data) {
                if (user.moderator === true) {
                    deleteButton = `<button id='` + data[post].id + `' class='btn btn-warning' onclick="deletePost(${data[post].id})" >DELETE POST (id ==` + data[post].id + `)</button>`;
                }

                var postItem = document.createElement('div');
                postItem.setAttribute('class', 'mb-3');
                postItem.innerHTML = `<div class="row no-gutters">
                      <div class="col-md-1 bg-light text-center">
                          <a href="javascript:void(0)" onclick="vote(` + data[post].id + `, ` + user.id + `,` + 1 + `)"> <i class="fas fa-chevron-up"></i></a> <br>
                            ` + countVotes(data[post].votes) + `  <br>
                          <a href="javascript:void(0)" onclick="vote(` + data[post].id + `, ` + user.id + `,` + -1 + `)"> <i class="fas fa-chevron-down"></i></a>
                      </div>
                      <div class="col-md-11">
                          <div class="card-body">
                              <p class="card-header bg-white pt-0">r/`
                        + printCategories(data[post].categories)
                        + `• Posted by u/`
                        + data[post].dad.username + ` `
                        + data[post].created + `
                  <h5 class="card-title">  ` + data[post].headline + `  </h5>
                          <p class="card-text">  ` + data[post].content + ` 
                          </p>
                      <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                ` + deleteButton + ` 
                  </div>
                  </div>
                  </div>
                      `;
                main.appendChild(postItem);
            }
        } else {
            alert('Some undefined error while reading from the funny dad server!');
        }
    };

    request.onerror = function () {
        alert('Error connecting to server!');
    };
    request.send();
}

function countVotes(votes) {
    var currentVotes = 0;

    for (var i in votes) {
        currentVotes += votes[i].vote;
    }
    return currentVotes;
}

function printCategories(list) {
    var allCategories = "";

    for (var i in list) {

        if (list[i].name !== undefined) {
            allCategories += "<a href='#' onclick = getPostsbyCategory(" + list[i].id + ") >" + list[i].name + "</a>, ";
        }
    }
    return allCategories.substring(0, allCategories.length - 2);
}

function logout() {
    sessionStorage.clear();
    E('isloggedin').style.display = "none";
    E('logoutbutton').style.display = "none";
    E('loginForm').style.display = "block";
    location.reload();
}

var loggedindiv = E('isloggedin');

if (user.username !== null) {
    E('loginForm').style.display = "none";
    loggedindiv.innerHTML = "Welcome <b>" + user.username + "</b> ";
    var logoutButton = document.createElement('input');
    logoutButton.setAttribute('type', 'submit');
    logoutButton.setAttribute('id', 'logoutbutton');
    logoutButton.setAttribute('value', 'Logout');
    logoutButton.setAttribute('class', 'btn btn-primary');
    logoutButton.setAttribute('onclick', 'logout()');
    loggedindiv.appendChild(logoutButton);

}

function getAllDads(url) {
    var request = new XMLHttpRequest();
    request.open('GET', url, true);
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            var data = JSON.parse(request.responseText);
            var allDads = E("allDads");
            var newList = document.createElement('ul');
            allDads.appendChild(newList);
            allDads.style.listStyle = 'none';
            for (var dad in data) {
                var newItem = document.createElement('li');
                newItem.innerHTML = "<a href='javascript: void(0);' onclick=\"listPosts('http://localhost:8080/post/" + data[dad].id + "')\"> " + data[dad].username + "</a>";
                allDads.appendChild(newItem);
            }
        } else {
            alert('Some undefined error while reading from the funny dad server!');
        }
    };

    request.onerror = function () {
        alert('Error connecting to server!');
    };
    request.send();
}
;

function createNewDadAccount() {
    var url = 'http://localhost:8080/dad/addDad';

    var formData = JSON.stringify($("#createDadForm").map(function () {
        return $(this).find('*').serializeArray()
                .reduce((a, x) => ({...a, [x.name]: x.value}), {});
    }).get());

    var data = formData.substring(1, formData.length - 1);


    fetch(url, {
        method: 'POST',
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        E('isloggedin').innerHTML = "";
        setCurrentUser(response);
        location.reload();
    })
}

function userLogin() {

    var url = 'http://localhost:8080/dad/login';
    var formData = JSON.stringify($("#loginForm").map(function () {
        return $(this).find('*').serializeArray()
                .reduce((a, x) => ({...a, [x.name]: x.value}), {});
    }).get());

    var data = formData.substring(1, formData.length - 1);

    fetch(url, {
        method: 'POST',
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }

    }).then(res => res.json())
            .then(function (response) {
               E('isloggedin').innerHTML = "";
               setCurrentUser(response);
               location.reload();          
            }).catch(error => E('modalLogin').click(), loginError());
}

function loginError() {
    E('isloggedin').innerHTML = "<font color='red'>Wrong username and/or password!</font>";
}

if (user.username !== null) {
    E('login_menu').style.display = 'none';
    E('signup_menu').style.display = 'none';

}

function createPost() {
    var url = 'http://localhost:8080/post/newPost';
    var formData = JSON.stringify($("#createNewPostForm").map(function () {
        return $(this).find('*').serializeArray()
                .reduce((a, x) => ({...a, [x.name]: x.value}), {});
    }).get());

    var data = formData.substring(1, formData.length - 1);

    fetch(url, {
        method: 'POST',
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json())
            .then(response => console.log('Success:', JSON.stringify(response)))
            .catch(error => console.error('Error:', error));
    location.reload();
}


function createFormForPost() {
    if (user.username === null) {
        alert("You need to sign in to be able to post!");
    } else {
        var a = E('createNewPostForm');
        if (a.style.display === 'none') {
            a.style.display = "block";
            E("postUserID").value = user.id;
        } else {
            a.style.display = 'none';
            E("postUserID").value = user.id;
        }
    }
}

function deletePost(id) {
    var url = 'http://localhost:8080/post/deletePost';
    var data = JSON.stringify({"id": id});


    fetch(url, {
        method: 'POST',
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json())
            .then(response => console.log('Success:', JSON.stringify(response)))
            .catch(error => console.error('Error:', error));
    location.reload();
}

function searchPostsbyString() {

    var searchString = $("#form-control").val();

    $.ajax({
        url: '/post/search',
        type: 'POST',
        data: {str: searchString},
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        dataType: "json",
        success: function (data) {
            emptyForm();
            buildForm(data);

        },
        error: function (responseTxt, statusTxt, errorThrown) {
            emptyForm();
            $("#main").html("<p>There is 0 result.</p>");

        }
    });
}

var searchForm = E("form-control");

searchForm.addEventListener("keyup", function (event) {
    if (event.keyCode === 13) {
        searchPostsbyString();

    }
});

function buildForm(data) {
    var main = E("main");
    for (var post in data) {
        var postItem = document.createElement('div');
        postItem.setAttribute('class', 'mb-3');
        postItem.innerHTML = `<div class="row no-gutters">
                        <div class="col-md-1 bg-light text-center">
                            <a href=""> <i class="fas fa-chevron-up"></i></a> <br>
                            ` + countVotes(data[post].votes) + `<br>
                            <a href=""> <i class="fas fa-chevron-down"></i></a>
                        </div>
                        <div class="col-md-11">
                            <div class="card-body">
                                <p class="card-header bg-white pt-0">r/`
                + printCategories(data[post].categories)
                + ` • Posted by u/`
                + data[post].dad.username + ` `
                + data[post].created + `
                    <h5 class="card-title">` + data[post].headline + `</h5>
                            <p class="card-text">` + data[post].content + `
                            </p>
                        <p class="card-text"><small class="text-muted">Last updated 3 mins ago</small></p>
                    </div>
                    </div>
                    </div>
                        `;
        main.appendChild(postItem);
    }
}

function getPostsbyCategory(sel) {
    $.ajax({
        url: '/post/getAll/' + sel,
        type: 'GET',
        success: function (data) {

            emptyForm();
            buildForm(data);

        },
        error: function (responseTxt, statusTxt, errorThrown) {
            emptyForm();
            $("#main").html("<p>There is 0 result.</p>");
        }
    });
}

function emptyForm() {
    let main = E("main");
    main.innerHTML = "";
}

function vote(postId, userId, voteValue) {
    var data = {"postId": postId, "userId": userId, "voteValue": voteValue};
    //Get post from db
    let url = "http://localhost:8080/post/vote";
    fetch(url, {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json())
            .then(response => console.log('Success:', JSON.stringify(response)))
            .catch(error => console.error('Error:', error));
    location.reload();
}

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
}
);

function setCurrentUser(user) {
    sessionStorage.setItem("username", user.username);
    sessionStorage.setItem("moderator", user.moderator);
    sessionStorage.setItem("id", user.id);
}
