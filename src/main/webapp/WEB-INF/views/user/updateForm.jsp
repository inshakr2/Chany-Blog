<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form>
      <input type="hidden" id="id" value="${principal.user.id}"/>
      <input type="hidden" id="oauth" value="${principal.user.oauth}"/>
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" value="${principal.user.username}" class="form-control" id="username" placeholder="Enter username" name="username" readonly>
      </div>
      <c:choose>
            <c:when test="${principal.user.oauth=='NORMAL'}">
              <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" >
              </div>

              <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" value="${principal.user.email}" class="form-control" id="email" placeholder="Enter Email Address" name="email" >
              </div>

              <button type="button" id="btn-update" class="btn btn-primary">수정하기</button>
            </c:when>

            <c:otherwise>
              <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" placeholder="Not Allowed" name="password" readonly>
              </div>
              <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" value="${principal.user.email}" class="form-control" id="email" placeholder="Not Allowed" name="email" readonly>
              </div>

              <button type="button" id="btn-update-auth" class="btn btn-danger">수정하기</button>
            </c:otherwise>
      </c:choose>


    </form>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
