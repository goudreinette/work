$(() => {
    $('.list .segment').click((e) => {
        const index = $(e.target).closest('.list .segment').index()

        $(`main .single`).addClass('hidden')
        $(`main .single:eq(${index})`).removeClass('hidden')
        $('#edit').removeClass('hidden')
    })

})
