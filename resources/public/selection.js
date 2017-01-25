$(() => {
    $('.list .segment').click((e) => {
        const index = $(e.target).closest('.list .segment').index()

        $(`main .single`).addClass('hidden')
        $(`main .single:eq(${index})`).removeClass('hidden')
        $('#edit').removeClass('hidden')
    })

    $('.timer .icon').click((e) => {
        var playing = $('.timer .icon').hasClass('play')

        $('.timer select').toggleClass('hidden', playing)
        $('.timer .time').toggleClass('hidden', !playing)
        $('.timer .icon').toggleClass('stop', playing)
        $('.timer .icon').toggleClass('play', !playing)
    })

    function updateTimer () {
        var playing = $('.timer .icon').hasClass('play')
        var seconds = $('.timer .time').html()

        if (playing) {
            $('.timer .time').html(seconds++)
        }
    }
})
