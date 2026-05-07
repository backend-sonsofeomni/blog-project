import {categoryUpdate, categoryDelete} from "/js/axiosApi.js";

// 댓글 수정
document.addEventListener(
    "submit",
    async (e)=>{

        if(e.target.id !== 'updateCategory') return;

        e.preventDefault()

        const btn = e.target.querySelector('button')

        const categoryId = btn.dataset.id;

        const requestBody = {
            title : document.getElementById('title').value
        };

        let url = `/categories/${categoryId}`

        try{
            await categoryUpdate(url, requestBody)
            window.location.href = `/categories`
        } catch(error){
            console.log(error)
        } finally {
            console.log("작업끝")
        }
    }
)

// 댓글 삭제
document.addEventListener(
    "submit",
    async (e)=>{

        if(e.target.id !== 'deleteCategory') return;

        e.preventDefault()

        const btn = e.target.querySelector('button')

        const categoryId = btn.dataset.id;

        let url = `/categories/${categoryId}`

        console.log(url)

        try{
            await categoryDelete(url)
            window.location.href = `/categories`
        } catch(error){
            console.log(error)
        } finally {
            console.log("작업끝")
        }
    }
)