$(() => {
    $('#try').click(() => {
        $.post('/try')
    })

    $('#register').click(() => tryLogin('/register'))
    $('#login').click(() => tryLogin('/login'))

    function tryLogin (url) {
        $.post(url, $('form').serialize())
            .done(() => location.assign('/sessions'))
            .fail(() => $('.login').addClass('animated wobble'))
    }
})
