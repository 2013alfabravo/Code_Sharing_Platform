<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
<style>
textarea {
            width: 500px;
            padding: 5px;
            margin-left: 5px;
          }

button {
            margin: 10px;
            padding 2px;
        }

</style>
<script>
function send() {
            let object = {
            "code": document.getElementById("code_snippet").value,
            "time": document.getElementById("time_restriction").value,
            "views": document.getElementById("views_restriction").value
            };

            let json = JSON.stringify(object);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/code/new', false);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);

            if (xhr.status == 200) {
            alert("Success!");
            }
            }

</script>
<title>Create</title>
</head>
<body>
<textarea id="code_snippet">// Enter your code here</textarea>
<br>
<p>Time restriction:</p><input id="time_restriction" type="number" value="0">
<p>Views restriction:</p><input id="views_restriction" type="number" value="0"><br>
<button id="send_snippet" type="submit" onclick="send()">Submit</button>
</body>
</html>