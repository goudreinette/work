-- name: session-length-in-minutes
SELECT TIMESTAMPDIFF(MINUTE, start_date , end_date)
FROM Sessions
AS length
