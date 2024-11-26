document.getElementById("loadMore").addEventListener("click", function () {
    const currentPage = parseInt(this.getAttribute("data-current-page"));
    const totalPages = parseInt(this.getAttribute("data-total-pages"));

    if (currentPage < totalPages - 1) {
        fetch(`/?page=${currentPage + 1}`)
            .then(response => response.text())
            .then(data => {
                const tempDiv = document.createElement('div');
                tempDiv.innerHTML = data;
                const newProducts = tempDiv.querySelectorAll('.product-item');
                document.querySelector('.product-list').append(...newProducts);

                this.setAttribute("data-current-page", currentPage + 1);

                // Ẩn nút nếu đã tải hết
                if (currentPage + 1 >= totalPages - 1) {
                    this.style.display = "none";
                }
            });
    }
});
