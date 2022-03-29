let index= {
    init: function(){
        $("#btn-save").on("click", ()=>{ // function(){} / () =>{} this를 바인딩
            this.save();
        });

        $("#btn-update").on("click", ()=>{
            this.update();
        });

        $("#btn-update-auth").on("click", ()=>{
            this.update_auth();
        });

        $("#btn-leave").on("click", ()=>{
            this.leave();
        });

        $("#username").on("propertychange change keyup paste input", ()=>{
            this.checkUser();
        });

        $("#re-password").on("propertychange change keyup paste input", ()=>{
            this.checkPassword();
        });
    },

    save: function(){
        //alert('user의 save함수');
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };
        //console.log(data);

        // ajax를 통해 insert 요청
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response){

            if(response.status === 500) {
                alert("회원가입에 실패하였습니다.")

            } else {

                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }

        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    },

    update: function(){
            let data = {
                id: $("#id").val(),
                username: $("#username").val(),
                password: $("#password").val(),
                email: $("#email").val(),
                oauth: $("#oauth").val()
            };

            $.ajax({
                type: "PUT",
                url: "/user",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function(response){
                if(response.status === 500) {
                    alert("회원정보 수정이 실패하였습니다.")

                } else {

                    alert("회원정보 수정이 완료되었습니다.");
                    location.href = "/";
            }
            }).fail(function(error){
                alert(JSON.stringify(error));

            });
        },

    update_auth: function(){
        alert("소셜 계정은 회원 정보 수정이 불가합니다.");
    },


    leave: function(){
        if (confirm("정말 탈퇴하시겠습니까?")) {
            let data = {
                id: $("#id").val(),
                username: $("#username").val(),
                password: $("#password").val(),
                email: $("#email").val(),
                oauth: $("#oauth").val()
            };

            $.ajax({
                type: "DELETE",
                url: "/user",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function(response){
                alert("회원 탈퇴 완료");
                location.href = "/";
            }).fail(function(error){
                alert(JSON.stringify(error));

            });
        }
    },


    checkUser: function(){
        var username = $('#username').val();

        $.ajax({
            type:'POST',
            url:'/auth/user/check',
            data:{username:username}
        }).done(function(response){

            if (response.data == 1) {
                console.log("success : " + response.data);
                $("#username-check").addClass("valid-feedback");
                $("#username-check").html("사용 가능한 아이디입니다.");
                $("#username").addClass("form-control is-valid");
            } else {
                console.log("fail : " + response.data);
                $("#username-check").addClass("invalid-feedback");
                $("#username-check").html("이미 사용중인 아이디입니다.");
                $("#username").addClass("form-control is-invalid");
            }

        }).fail(function(error){
          alert(JSON.stringify(error));

        });
    },

    checkPassword: function(){
            var pwd = $('#password').val();
            var pwd2 = $("#re-password").val();

            if (pwd == pwd2) {
                $("#pwd-check-result").removeClass("alert alert-danger")
                $("#pwd-check-result").addClass("alert alert-success")
                $("#pwd-check-result").html("비밀번호가 일치합니다.")
            } else {
                $("#pwd-check-result").removeClass("alert alert-success")
                $("#pwd-check-result").addClass("alert alert-danger")
                $("#pwd-check-result").html("비밀번호가 일치하지 않습니다.")
            }
        }
}

index.init();

