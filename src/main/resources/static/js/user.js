let index= {
    init: function(){
        $("#btn-save").on("click", ()=>{ // function(){} / () =>{} this를 바인딩
            this.save();
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
            alert("회원가입이 완료되었습니다.");

            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));

        });
    }
}

index.init();