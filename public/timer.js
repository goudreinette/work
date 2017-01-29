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
    $jobSelect.change(switchJob)

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

    function switchJob () {
        const jobId = $jobSelect.val()
        $timer.attr('data-job-id', jobId)
    }

    function start () {
        const jobId = $jobSelect.val()
        $.get(`/sessions/start/${jobId}`)
        $timer.attr('data-playing', true)
        update({playing: true, seconds: 0, jobId})
    }

    function stop () {
        $.get(`/sessions/stop`)
        $timer.attr('data-playing', false)
        $timer.attr('data-seconds', 0)
        update({playing: false, seconds: 0, jobId: null})
    }
})
