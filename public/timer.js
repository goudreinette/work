$(() => {
    const $timer        = $('.menu .timer')

    if (!$timer) return

    // Timer children
    const $play         = $timer.find('.play')
    const $stop         = $timer.find('.stop')
    const $text         = $timer.find('.text')
    const $jobText      = $timer.find('.job')
    const $durationText = $timer.find('.duration')
    const $jobSelect    = $timer.find('.job-select')

    // Event handlers
    $play.click(start)
    $stop.click(stop)

    // Show initial state
    update()

    function update (newData) {
        const {jobId, seconds, playing} = newData || $timer.data()

        // Timer
        if (playing) {
            $durationText.timer({seconds})
        } else {
            $durationText.timer('remove')
        }

        // Job label
        const jobName = $jobSelect.find(`[data-id="${jobId}"]`).text()
        $jobText.text(jobName)

        // Visibility: Elements for stopped state
        $jobSelect.toggle(!playing)
        $play.toggle(!playing)

        // Visibility: Elements for playing state
        $text.toggle(playing)
        $stop.toggle(playing)
    }

    function start () {
        const jobId = $jobSelect.val()
        $.get(`/sessions/start/${jobId}`)
        update({playing: true, seconds: 0, jobId})
    }

    function stop () {
        $.get(`/sessions/stop`)
        update({playing: false, seconds: 0, jobId: null})
    }
})
