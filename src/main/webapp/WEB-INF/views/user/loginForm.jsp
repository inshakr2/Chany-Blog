<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">

    <form action="/auth/loginProc" method="post">

      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" name="username" class="form-control" placeholder="Enter username" id="username">
      </div>

      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
      </div>

      <div class="form-group form-check">
        <label class="form-check-label">
          <input name="remember" class="form-check-input" type="checkbox"> Remember me
        </label>
      </div>
    <button id="btn-login" class="btn btn-primary">로그인</button>
    <a href="https://kauth.kakao.com/oauth/authorize?client_id=0b574c03d54a84e92c553163fef5b367&redirect_uri=http://localhost:28088/auth/kakao/callback&response_type=code"><image height="38px" src="/image/kakao_login_button.png"></a>
    </form>

</div>
<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>
