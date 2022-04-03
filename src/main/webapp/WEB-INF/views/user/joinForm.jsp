<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form>

      <div class="form-group row">
        <label for="username">Username:</label>
        <input type="text" class="form-control" id="username-join" placeholder="Enter username" name="username" required>
        <div id="username-check"></div>
      </div>


      <div class="form-group row">
        <label for="password">Password:</label>
        <input type="password" class="form-control" id="password" placeholder="Enter password" name="password" required>
      </div>

      <div class="form-group row">
        <label for="re-password">Confirm Password:</label>
        <input type="re-password" class="form-control" id="re-password" placeholder="Confirm password" name="re-password" required>
      </div>
      <div class="alert alert-danger" id="pwd-check-result">비밀번호가 일치하지 않습니다.</div>

      <div class="form-group row">
        <label for="email">Email:</label>
        <input type="email" class="form-control" id="email" placeholder="Enter Email Address" name="email" required>
      </div>
      

    </form>
    <button id="btn-save" class="btn btn-primary">가입하기</button>
</div>

<script src="/js/user.js"></script>


<%@ include file="../layout/footer.jsp"%>
