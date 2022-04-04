let index= {
    init: function(){
        $("#btn-save").on("click", ()=>{
            this.save();
        });

        $("#btn-delete").on("click", ()=>{
            this.deleteById();
        });

        $("#btn-update").on("click", ()=>{
            this.update();
        });

        $("#btn-reply-save").on("click", ()=>{
            this.replySave();
        });
    },

    save: function(){
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response){

            if(response.data === -1) {

                alert("게시물 작성에 실패했습니다.")

            } else {
                alert("글쓰기가 완료되었습니다.");
                location.href = "/";
            }

        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    deleteById: function(){
        var id=$("#id").text()

        $.ajax({
            type: "DELETE",
            url: "/api/board/" + id,
            dataType: "json"
        }).done(function(response){
            alert("게시물 삭제가 완료되었습니다.");
            location.href = "/";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    update: function(){
            let id = $("#id").val();

            let data = {
                title: $("#title").val(),
                content: $("#content").val()
            };

            $.ajax({
                type: "PUT",
                url: "/api/board/" + id,
                data: JSON.stringify(data),
                contentType: "application/json; charset=UTF-8",
                dataType: "json"
            }).done(function(response){

                if(response.data === -1) {

                    alert("게시물 수정에 실패했습니다.")

                } else {
                     alert("게시물 수정이 완료되었습니다.");
                    location.href = "/board/"+id;
                }

            }).fail(function(error){
                alert(JSON.stringify(error));
            });
        },

    replySave: function(){
        let data = {
            userId: $("#userId").val(),
            boardId: $("#boardId").val(),
            content: $("#reply-content").val()
        };

        $.ajax({
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(response){
            alert("댓글 작성이 완료되었습니다.");
            location.href = `/board/${data.boardId}`
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    replyDelete: function(boardId, replyId){
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            dataType: "json"
        }).done(function(response){
            alert("댓글 삭제가 완료되었습니다.");
            location.href = `/board/${boardId}`
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();