let index= {
    init: function(){
        $("#btn-save").on("click", ()=>{ // function(){} / () =>{} this를 바인딩
            this.save();
        });

        $("#btn-login").on("click", ()=>{ // function(){} / () =>{} this를 바인딩
            this.login();
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
            url: "/blog/api/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response){
            alert("회원가입이 완료되었습니다.");

            location.href = "/blog";
        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    },


    login: function(){
            //alert('user의 save함수');
            let data = {
                username: $("#username").val(),
                password: $("#password").val(),
            };
            //console.log(data);

            // ajax를 통해 insert 요청
            $.ajax({
                // 회원가입 수행 요청
                type: "POST",
                url: "/blog/api/user/login",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function(response){
                alert("로그인이 완료되었습니다.");

                location.href = "/blog";
            }).fail(function(error){
                alert(JSON.stringify(error));

            });
        }
}

index.init();