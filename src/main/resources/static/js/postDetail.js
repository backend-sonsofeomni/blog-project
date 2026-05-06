import {commentPost, commentDelete} from "/js/axiosApi.js";
// form 기본 제출 방지
document.addEventListener(
    "submit",
    async (e) => {

        if (e.target.id !== "createComment") return;

        e.preventDefault()

        let postId = window.postId

        const requestBody = {
            content : document.getElementById('content').value
        };

        let url = '/comments/' + postId + '/create'

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



document.addEventListener(
    "submit",
    async (e) => {

        if (e.target.id !== "deleteComment") return;

        e.preventDefault()

        let postId = window.postId

        const btn = e.target.querySelector('button')

        const commentId = btn.dataset.id;

        let url = '/comments/' + commentId

        console.log(url)

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