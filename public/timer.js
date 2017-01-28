$(() => {
    const $timer             = $('.menu .timer')
    const {jobId, seconds}       = $timer.data()


    $timer.find('.play').click(start)
    $timer.find('.stop').click(stop)

    if (jobId) {
        $timer.find('.stop').removeClass('hidden')
    } else {
        $timer.find('.play').removeClass('hidden')
    }

    if (jobId) {
        const jobName = $timer.find(`.job-select [data-id="${jobId}"]`).text()
        $timer.find('.job').text(jobName)
    }

    function start () {
        $.get(`/sessions/start/${jobId}`)
        $timer.find('.duration').timer()
        $timer.find('.play, .stop, .duration').toggleClass('hidden')
    }

    function stop () {
        $.get(`/sessions/stop`)
        $timer.find('.duration').timer('remove')
        $timer.find('.play, .stop, .duration').toggleClass('hidden')
    }
})
