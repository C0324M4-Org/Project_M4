$('#deleteModal').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    let categoryId = button.data('id');
    let categoryName = button.data('name');
    let form = $(this).find('form');
    let deleteUrl = '/admin/category/delete/' + categoryId;
    form.attr('action', deleteUrl);
    let modalBody = $(this).find('.modal-body');
    modalBody.text('Bạn có chắc chắn muốn xóa loại sản phẩm ' + categoryName + ' ?');
});