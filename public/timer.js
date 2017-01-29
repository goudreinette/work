$(() => {
    const $timer           = $('.menu .timer')

    if (!$timer) return

    // Timer children
    const $play         = $timer.find('.play')
    const $stop         = $timer.find('.stop')
    const $text         = $timer.find('.text')
    const $jobText      = $timer.find('.job')
    const $durationText = $timer.find('.duration')
    const $jobSelect    = $timer.find(`.job-select`)

    // Other elements
    const $feedDuration = $('.feed .event:first-child .duration span')

    // Event handlers
    $play.click(start)
    $stop.click(stop)
    $jobSelect.change(switchJob)

    // Show initial state
    update()


    function update () {
        const {jobId, seconds, playing} = $timer.data()

        // Timer
        if (playing) {
            $durationText.timer({seconds})
            $feedDuration.timer({seconds})
        } else {
            $durationText.timer('remove')
            $feedDuration.timer('remove')
        }

        // Job label
        const jobId   = $jobSelect.val()
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
        $timer.data('job-id', jobId)
    }

    function start () {
        $.get(`/sessions/start/${jobId}`)
        $timer.data('playing', true)
    }

    function stop () {
        $.get(`/sessions/stop`)
        $timer.data('playing', false)
        $timer.data('seconds', 0)
    }
})
