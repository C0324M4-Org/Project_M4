$(function () {
    $("#slider-range").slider({
        range: true,
        min: 0,
        max: 500000,
        values: [0, 500000],
        slide: (e, ui) => {
            $("#min-price").text(`Từ ${ui.values[0].toLocaleString('vi-VN')}đ : `);
            $("#max-price").text(`${ui.values[1].toLocaleString('vi-VN')}đ`);
        }
    });
});