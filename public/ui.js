$(() => {
    $('.ui.dropdown').dropdown()
    $('input[type=date]').datepicker({
        language: 'en',
        dateFormat: 'yyyy-mm-dd'
    })
})
