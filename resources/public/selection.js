$(() => {

    function selectItem (e) {
        const index = $(e.target).closest('.list .segment').index()

        $(`main .single`).addClass('hidden')
        $(`main .single:eq(${index})`).removeClass('hidden')
        $('#edit').removeClass('hidden')
    }


    function toggleTimer () {
        const playing = $('.timer .icon').hasClass('play')
        const id = $('.timer select').val()

        $('.timer select').toggleClass('hidden', playing)
        $('.timer .time').toggleClass('hidden', !playing)
        $('.timer .icon').toggleClass('stop', playing)
        $('.timer .icon').toggleClass('play', !playing)

        if (playing) {
            $.get(`/sessions/start/${id}`)
        } else {
            $.get(`/sessions/stop`)
        }
    }

    function updateTimer () {
        const playing = $('.timer .icon').hasClass('stop')
        const seconds = $('.timer .time').html()

        if (playing) {
            $('.timer .time').html(Number(seconds) + 1)
        }
    }

    $('.list .segment').click(selectItem)
    $('.timer .icon').click(toggleTimer)
    setInterval(updateTimer, 1000)
})
