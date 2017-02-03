$(() => {
    $('.ui.dropdown').dropdown()
    $('input[type=date], input[type=datetime-local]').datepicker({
        language: 'en',
        dateFormat: 'yyyy-mm-dd'
    })
})
