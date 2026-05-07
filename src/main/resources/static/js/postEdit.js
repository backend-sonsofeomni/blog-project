import {commentUpdate} from "/js/axiosApi.js";

//
document.addEventListener(
    "submit",
    async (e)=>{

        if(e.target.id !== 'updatePost') return

        e.preventDefault()

        let postId = window.postId

        let visibility = document.getElementById('visibility').value


        const requestBody = {
            title : document.getElementById('title').value,
            content : document.getElementById('content').value,
            visibility : visibility,
            categoryId : document.getElementById('categoryId').value
        };

        let url = `/posts/${postId}`

        try{
            await commentUpdate(url, requestBody)
            if ( visibility === 'PRIVATE' ) {
                window.location.href = `/posts`
            } else{
                window.location.href = `/posts/${postId}`
            }
        } catch(error){
            console.log(error)
        } finally {
            console.log("작업끝")
        }
    }
)