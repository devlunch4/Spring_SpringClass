<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/mvc/multi/param" method="POST"
		enctype="multipart/form-data">
		userid : <input type="text" value="brown" name="userid" /> <br>
		userid : <input type="text" value="sally" name="userid" /> <br>
		<input type="submit" value="전송" />
	</form>
	
	
	
	<h3>List&lt;UsersVo&gt;</h3>
		<form action="/mvc/multi/param" method="POST"
		enctype="multipart/form-data">
		userid : <input type="text" value="brown" name="usersVoList[0].userid" /> <br>
		userid : <input type="text" value="sally" name="usersVoList[1].userid" /> <br>
				usernm : <input type="text" value="브라운" name="usersVoList[0].usernm" /> <br>
		usernm : <input type="text" value="샐리" name="usersVoList[1].usernm" /> <br>
		<input type="submit" value="전송" />
	</form>
</body>
</html>