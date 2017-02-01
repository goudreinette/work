$(() => {
    $('.try').click(() => {
        $.post('/try')
    })

    $('.register').click(() => {
        $.post('/register', $('form').serialize()).done((response) => {
            // success? redirect
            // fail? animation
        })
    })

    $('.login').click(() => {
        $.post('/login', $('form').serialize()).done((response) => {
            // success? redirect
            // fail? animation
        })
    })
})
