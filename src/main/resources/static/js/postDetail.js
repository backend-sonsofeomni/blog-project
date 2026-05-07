import {commentPost, commentDelete, commentPostWithParams, postDelete} from "/js/axiosApi.js";

// 댓글 생성 API
document.addEventListener(
    "submit",
    async (e) => {

        if (e.target.id !== "createComment") return;

        e.preventDefault()

        let postId = window.postId

        const requestBody = {
            content : document.getElementById('content').value
        };

        let url = `/comments/${postId}/create`;

        try{
            const result = await commentPost(url,requestBody);
            document.body.innerHTML = result.data;
        } catch(error){
            console.log(error);
        } finally {
            console.log("작업 끝");
        }
    }
);

// 댓글 삭제 API ( DELETE )
document.addEventListener(
    "submit",
    async (e) => {

        if (e.target.id !== "deleteComment") return;

        e.preventDefault()

        let postId = window.postId

        const btn = e.target.querySelector('button')

        const commentId = btn.dataset.id;

        let url = `/comments/${commentId}`


        try{
            await commentDelete(url);
            window.location.href = `/posts/${postId}`
        } catch(error){
            console.log(error);
        } finally {
            console.log("작업 끝");
        }
    }
);

// 대댓글 API ( POST )
document.addEventListener(
    "submit",
    async (e) => {

        if (e.target.id !== "replyComment") return;

        e.preventDefault()

        let postId = window.postId

        const requestBody = {
            content :  e.target.replyContent.value
        };

        const btn = e.target.querySelector('button')

        const commentId = btn.dataset.id;

        let url = `/comments/${commentId}/reply`

        try{
            await commentPostWithParams(url,requestBody,postId);
            window.location.href = `/posts/${postId}`
        } catch(error){
            console.log(error);
        } finally {
            console.log("작업 끝");
        }
    }
);

// 게시물 삭제 API ( DELETE )
document.addEventListener(
    "submit",
    async (e) => {

        if (e.target.id !== "deletePost") return;

        e.preventDefault()

        let postId = window.postId

        const btn = e.target.querySelector('button')

        let url = `/posts/${postId}`


        try{
            await postDelete(url);
            window.location.href = `/posts`
        } catch(error){
            console.log(error);
        } finally {
            console.log("작업 끝");
        }
    }
);