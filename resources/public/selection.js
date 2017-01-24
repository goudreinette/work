$(() => {
    $('.list .segment').click((e) => {
        const index = $(e.target).index()
        console.log(index)
        $(`main .single`).addClass('hidden')
        $(`main .single:eq(${index})`).removeClass('hidden')
    })

})
