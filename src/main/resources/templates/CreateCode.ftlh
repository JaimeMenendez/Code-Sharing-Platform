<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="/css/styles.css">
    <title>Create</title>
</head>
<body>
<form class="code-container" onsubmit="return false;">
    <label for="code_snippet">Code: </label>
    <br>
    <textarea id="code_snippet" placeholder="Write your code here"
              class="code resize-ta min-text-box-height"></textarea>
    <br>

    <div class="container">
        <div>
            <label for="time_restriction">Time restriction: </label><input id="time_restriction" type="text" value="0"/>
        </div>
        <div>
            <label for="views_restriction">Views restriction: </label><input id="views_restriction" type="text" value="0"/>
        </div>
    </div>

    <button class="blue-button" id="send_snippet" type="submit" onclick="send()">Submit</button>
</form>


<script>
    function send() {
        let object = {
            "code": document.getElementById("code_snippet").value,
            "time": Number.parseInt(document.getElementById("time_restriction").value),
            "views": Number.parseInt(document.getElementById("views_restriction").value)
        };

        let json = JSON.stringify(object);

        let xhr = new XMLHttpRequest();
        xhr.open("POST", '/api/code/new', false)
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);

        if (xhr.status == 200) {
            let json = JSON.parse(xhr.response);
            alert("Success!, share your snippet in " + window.location.hostname + "/" + json.id);
            console.log(xhr.response);
        }
    }

    textarea = document.querySelector("#code_snippet");
    textarea.addEventListener('input', autoResize, false);

    function autoResize() {
        this.style.height = 'auto';
        this.style.height = this.scrollHeight + 'px';
    }

</script>

</body>
</html>