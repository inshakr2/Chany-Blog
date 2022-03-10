<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
    <div class="container">
        <button class="btn btn-success m-1" onclick="history.back()">Back</button>
        <button id="btn-delete" class="btn btn-danger float-right m-1">삭제</button>
        <button id="btn-update" class="btn btn-warning float-right m-1">수정</button>
        <br /><br />

        <div class="jumbotron">
            <h1> ${board.title} </h1>
            <div>
                <p class="font-italic text-right ">${board.user.username}</p>
                <p class="font-weight-lighter text-right">${board.createDate}</p>
            </div>
        <hr />
            <p>${board.content}</p>
        </div>

    </div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
