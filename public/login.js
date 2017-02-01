$(() => {
    $('#try').click(() => {
        $.post('/try')
    })

    $('#register').click(() => tryLogin('/register'))
    $('#login').click(() => tryLogin('/login'))

    function tryLogin (url) {
        $.post(url, getUser())
            .done(() => location.assign('/sessions'))
            .fail(() => $('.login').addClass('animated wobble'))
    }

    function getUser () {
        return {
            username: $('[name=username]').val(),
            password: $('[name=password]').val()
        }
    }
})
