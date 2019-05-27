var allDadsURL = "http://localhost:8080/dad/getAll";
var allPostsURL = "http://localhost:8080/post/getAll";
var top10Posts = "http://localhost:8080/post/getTop10";
var categoryUrl = "http://localhost:8080/post/category/getCategoryByName";
var postByIdUrl = ""

getAllDads(allDadsURL);
listPosts(top10Posts);
//            listAllPosts(allPostsURL);

function listPosts(url) {
    var request = new XMLHttpRequest();
    request.open('GET', url, true);
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            var data = JSON.parse(request.responseText);
            var main = document.getElementById("main");
            for (var post in data) {

                var postItem = document.createElement('div');
                postItem.setAttribute('id', data[post].id); //Lagt till PostId för att kunna hämta id vid voting
                postItem.setAttribute('class', 'mb-3');
                //Lagt till onclick="VoteUp()" onclick="VoteDown()"
                postItem.innerHTML = `<div class="row no-gutters">
                        <div class="col-md-1 bg-light text-center">
                            <a href="" onclick="voteUp(this)"> <i class="fas fa-chevron-up"></i></a> <br>
                            ` + countVotes(data[post].votes) + `<br>
                            <a href="" onclick="voteDown(this)"> <i class="fas fa-chevron-down"></i></a>
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
            allCategories += "<a href='" + categoryUrl + "/" + list[i].name + "'>" + list[i].name + "</a>, ";
            console.log(list[i].name);
        }
    }
    return allCategories.substring(0, allCategories.length - 2);
}

var isLoggedIn = false;
const urlParams = new URLSearchParams(window.location.search);
const name = urlParams.get('username');
const moderator = urlParams.get('moderator');

if (name.length > 0 && moderator.length > 0) {
    setCurrentUser(name, moderator);
    isLoggedIn = true;
}

function setCurrentUser(username, moderator) {
    sessionStorage.setItem('username', JSON.stringify(username));
    sessionStorage.setItem('moderator', JSON.stringify(moderator));
}

function logout() {
    sessionStorage.clear();
    isLoggedIn = false;
    document.getElementById('isloggedin').style.display = "none";
    document.getElementById('logoutbutton').style.display = "none";
    document.getElementById('loginForm').style.display = "block";
    window.location.search = "";
}

var loggedindiv = document.getElementById('isloggedin');

if (sessionStorage.getItem('username').length > 0) {
    document.getElementById('loginForm').style.display = "none";
    loggedindiv.innerHTML = "Welcome <b>" + sessionStorage.getItem('username') + "</b> ";
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
            var allDads = document.getElementById("allDads");
            var newList = document.createElement('ul');
            allDads.appendChild(newList);
            allDads.style.listStyle = 'none';
            for (var dad in data) {
                var newItem = document.createElement('li');
                newItem.innerHTML = "<a href='http://localhost:8080/post/" + data[dad].id + " '> " + data[dad].username + "</a>";
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
    var formData = JSON.stringify($("#createDadForm").serializeArray());
    fetch(url, {
        method: 'POST',
        body: formData,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json())
            .then(response => console.log('Success:', JSON.stringify(response)))
            .catch(error => console.error('Error:', error));
}

function createNewPost() {
    var url = 'http://localhost:8080/post/newPost';
    var formData = JSON.stringify($("#createNewPostForm").serializeArray());
    fetch(url, {
        method: 'POST',
        body: formData,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(res => res.json())
            .then(response => console.log('Success:', JSON.stringify(response)))
            .catch(error => console.error('Error:', error));
}

function createFormForPost() {
    var a = document.getElementById('createNewPostForm');
    if (a.style.display === 'none') {
        a.style.display = "block";
    } else {
        a.style.display = 'none';
    }
}

function voteUp(elem) {
    var postId = elem.parentNode.parentNode.parentNode.id;
    //Get post from db
    let url = "http://localhost:8080/post/postid/" + postId;
    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
    }).then(res => res.json())
            .then(response => console.log('Success:', JSON.stringify(response)))
            .catch(error => console.error('Error:', error));

}

$(function () {
    $('[data-toggle="tooltip"]').tooltip();
});