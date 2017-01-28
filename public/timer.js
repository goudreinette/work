$(() => {
    const $timer             = $('.menu .timer')
    const {jobId, seconds}       = $timer.data()


    $timer.find('.play').click(start)
    $timer.find('.stop').click(stop)

    if (jobId) {
        $timer.find('.stop').removeClass('hidden')
    } else {
        $timer.find('.start').removeClass('hidden')
    }

    if (jobId) {
        const jobName = $timer.find(`.job-select [data-id="${jobId}"]`).text()
        debugger
        $timer.find('.job').text(jobName)
    }

    function start () {
        $.get(`/sessions/start/${jobId}`)
        $timer.find('.duration').timer({seconds})
    }

    function stop () {
        $.get()
    }
})
