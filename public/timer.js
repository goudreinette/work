$(() => {
    const $timer             = $('.menu .timer')
    const {jobId, seconds}       = $timer.data()


    $timer.find('.play').click(start)
    $timer.find('.stop').click(stop)


    if (jobId) {
        const jobName = $timer.find(`.job-select [data-id="${jobId}"]`).text()
        $timer.find('.job').text(jobName)
        $timer.find('.stop').removeClass('hidden')
        $timer.find('.duration').timer({seconds})
    } else {
        $timer.find('.play, .job-select').removeClass('hidden')
    }

    function start () {
        // duplication
        const jobId = $timer.find(`.job-select`).val()
        const jobName = $timer.find(`.job-select [data-id="${jobId}"]`).text()
        $timer.find('.job').text(jobName)
        // ---
        $.get(`/sessions/start/${jobId}`)
        $timer.find('.duration').timer()
        $timer.find('.play, .stop, .text, .job-select').toggleClass('hidden')
    }

    function stop () {
        $.get(`/sessions/stop`)
        $timer.find('.duration').timer('remove')
        $timer.find('.play, .stop, .text, .job-select').toggleClass('hidden')
    }
})
