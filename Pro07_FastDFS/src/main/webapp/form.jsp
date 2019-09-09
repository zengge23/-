<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
</head>

<body>

	<form action="${pageContext.request.contextPath }/upload" method="post" enctype="multipart/form-data">
		你看我的新头像牛逼吗？
		<input type="file" name="picture"/>
		<br/>
		<input type="submit" value="上传"/>
	</form>

</body>
</html>