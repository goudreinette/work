$(() => {
    const $timer                    = $('.menu .timer')
    const {playing, job, startdate} = $timer.data()


    $timer.find('.play').toggleClass('hidden', playing)
    $timer.find('.play').toggleClass('hidden', !playing)

    // $timer.find('.duration').timer({
    //
    // })
})
