<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글목록</title>
    
	<link rel="stylesheet" href="./css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <header>
            <h3>Board System v1.0</h3>
            <p>
                <span>${sessUser.nick}</span>님 반갑습니다.
                <a href="/Jboard2/user/info.do">[정보수정]</a>
                <a href="/Jboard2/user/logout.do">[로그아웃]</a>
            </p>
        </header>