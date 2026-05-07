

document.addEventListener("click", (e) => {

    const btn = e.target.closest(".activeReply");

    if (!btn) return;

    e.preventDefault();

    const commentItem = btn.closest(".comment-item");

    const replyForm = commentItem.querySelector(".createReply");

    replyForm.classList.toggle("hidden");
});