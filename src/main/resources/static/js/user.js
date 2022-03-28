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
        }
}

index.init();