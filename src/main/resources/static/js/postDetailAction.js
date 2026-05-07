

document.addEventListener("click", (e) => {

    console.log(".")

    const btn = e.target.closest(".activeReply");

    if (!btn) return;

    console.log("?");

    e.preventDefault();

    const commentItem = btn.closest(".comment-item");

    const replyForm = commentItem.querySelector(".createReply");

    replyForm.classList.toggle("hidden");
});