$(() => {
    $('#try').click(()      => tryLogin('/try'))
    $('#register').click(() => tryLogin('/register'))
    $('#login').click(()    => tryLogin('/login'))

    function tryLogin (url) {
        $.post(url, $('form').serialize())
            .done(() => location.assign('/sessions'))
            .fail(() => {
                $('.login').addClass('animated wobble')
                setTimeout(() => $('.login').removeClass('animated wobble'), 1000)
            })
    }
})
