$(() => {
    const $timer        = $('.menu .timer')

    if (!$timer) return

    // Timer children
    const $toggle       = $timer.find('.toggle')
    const $play         = $timer.find('.play')
    const $stop         = $timer.find('.stop')
    const $text         = $timer.find('.text')
    const $projectText      = $timer.find('.project')
    const $durationText = $timer.find('.duration')
    const $projectSelect    = $timer.find('.project-select')

    // Event handlers
    $toggle.click(toggle)

    // Show initial state
    update()

    function update (newData) {
        const {projectId, seconds, playing} = newData || $timer.data()

        // Timer
        if (playing) {
            $durationText.timer({seconds})
        } else {
            $durationText.timer('remove')
        }

        // Job label
        const projectName = $projectSelect.find(`[data-id="${projectId}"]`).text()
        $projectText.text(projectName)

        // Visibility: Elements for stopped state
        $projectSelect.toggle(!playing)
        $play.toggle(!playing)

        // Visibility: Elements for playing state
        $text.toggle(playing)
        $stop.toggle(playing)
    }

    function toggle () {
        if ($play.is(':visible')) {
            start()
        } else if ($stop.is(':visible')) {
            stop()
        }
    }

    function start () {
        const projectId = $projectSelect.val()
        $.get(`/sessions/start/${projectId}`)
        update({playing: true, seconds: 0, projectId})
    }

    function stop () {
        $.get(`/sessions/stop`)
        update({playing: false, seconds: 0, projectId: null})
    }
})
