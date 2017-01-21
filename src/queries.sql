-- name: find-jobs
SELECT *
FROM Jobs

-- name: find-job
SELECT *
FROM Jobs
WHERE job_id = :job_id

-- name: sessions-for-job
SELECT *
FROM Sessions
NATURAL JOIN Jobs
WHERE job_id = :job_id

-- name: session-lengths-in-minutes-for-job
SELECT TIMESTAMPDIFF(MINUTE, start_date , end_date)
AS minutes
FROM Sessions
NATURAL JOIN Jobs
WHERE job_id = :job_id
