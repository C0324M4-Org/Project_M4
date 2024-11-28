$('#deleteModal').on('show.bs.modal', function (event) {
    let button = $(event.relatedTarget);
    let productId = button.data('id');
    let productName = button.data('name');
    let form = $(this).find('form');
    let deleteUrl = '/admin/delete/' + productId;
    form.attr('action', deleteUrl);
    let modalBody = $(this).find('.modal-body');
    modalBody.text('Bạn có chắc chắn muốn xóa sản phẩm ' + productName + ' ?');
});

