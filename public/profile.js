$(() => {
    $('#profile #import').change((e) => {
        if (confirm('Importing will overwrite existing user data. Continue?')) {
            data = new FormData()
            data.append('file', e.target.files[0])
            var request = new XMLHttpRequest()
            request.open("POST", "/import")
            request.send(data)
        }
    })
})
