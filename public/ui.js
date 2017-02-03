$(() => {
    $('.ui.dropdown').dropdown()
    $('input[type=date], .air').datepicker({
        language: 'en',
        dateFormat: 'yyyy-mm-dd',
        timeFormat: 'hh:ii'
    })
})
