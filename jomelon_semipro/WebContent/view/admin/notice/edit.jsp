<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <!-- Icon -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
        integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

    <style>
        .notice {
            text-align: center;
        }

        .content {
            width: 100%;
            height: 500px;

        }

        .text-align-center {
            text-align: center;
        }
        

        .btn-text {
            width: 80px;

            background-color: lightgray;

            border: none;

            color: black;;

            padding: 10px 0;

            text-align: center;

            text-decoration: none;

            display: inline-block;

            font-size: 15px;

            margin: 4px;

            cursor: pointer;

        }
        .title{
            width: 100%;
        }
    </style>


</head>

<body>

    <br><br>
    <h2 class="notice">공지사항 수정</h2>
    <br><br>
    <div class="container">

        <form method="post" action="noticeEdit.do?id=${n.id }">
            <div class="margin-top first">
                <table class="table">
                    <tbody>
                        <tr>
                            <th>제목</th>
                            <td colspan="3">
                                <input class="title" type="text" name="title" value="${n.title }"/>
                            </td>
                        </tr>
                        <tr>
                            <th>첨부파일</th>
                            <td colspan="3" ><input type="file" name="file" /> </td>
                        </tr>
                        <tr class="content">
                            <td colspan="4"><textarea class="content" name="content" >${n.content }</textarea></td>
                        </tr>
                        <tr>
                            <td colspan="4" class="text-align-right"><input class="vertical-align" type="checkbox"
                                    id="open" name="open" value="true"><label for="open"
                                    class="margin-left">바로공개</label> </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="text-align-center">
                <input class="btn-text btn-default" type="submit" value="수정" />
                <a class="btn-text btn-cancel" href="noticeList.do">취소</a>
            </div>
        </form>



    </div>

</body>

</html>