<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
    <div class="container">
        <button class="btn btn-success m-1" onclick="history.back()">Back</button>
        <c:if test="${board.user.id == principal.user.id}" >
            <button id="btn-delete" class="btn btn-danger float-right m-1">삭제</button>
            <a href="/board/${board.id}/updateForm" class="btn btn-warning float-right m-1">수정</a>
        </c:if>
        <br />
        <div>
            글 번호 : <span id="id"><i>${board.id}</i></span>
        </div>
        <div class="jumbotron">
            <h1> ${board.title} </h1>
            <div>
                <p class="font-italic text-right ">${board.user.username}</p>
                <p class="font-weight-lighter text-right">${board.createDate}</p>
            </div>
        <hr />
            <p>${board.content}</p>
        </div>

        <div class="card">
            <form>
             <input type="hidden" id="userId" value="${principal.user.id}" />
             <input type="hidden" id="boardId" value="${board.id}"/>
             <div class="card-body">
                 <textarea id="reply-content" class="form-control" rows="1"></textarea>
             </div>
             <div class="card-footer">
                 <button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
             </div>
            </form>
        </div>
        <br>
        <div class="card">
            <div class="card-header">댓글 리스트</div>
            <ul id="reply--box" class="list-group">

                <c:forEach var="reply" items="${board.replies}">
                    <li id="reply--1" class="list-group-item d-flex justify-content-between">
                           <div>${reply.content}</div>
                           <div class="d-flex">
                                <div class="font-italic">작성자 : ${reply.user.username} &nbsp;</div>
                                <button class="badge">삭제</button>
                           </div>
                    </li>
                </c:forEach>

            </ul>
        </div>
    </div>

<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
