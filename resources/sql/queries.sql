-- name: insert-job!
INSERT INTO Jobs (name, client_id)
VALUES (:name, :client_id)

-- name: find-jobs
SELECT *
FROM Jobs

-- name: find-job
SELECT *
FROM Jobs
WHERE job_id = :id

-- name: job-cost
SELECT SUM(TIMESTAMPDIFF(MINUTE, start_date, end_date)) / 60 * :hourly_rate
AS cost
FROM Sessions
WHERE job_id = :id

-- name: job-length-in-minutes
SELECT SUM(TIMESTAMPDIFF(MINUTE, start_date, end_date))
AS minutes
FROM Sessions
WHERE job_id = :id

-- name: sessions-for-job
SELECT *,
TIMESTAMPDIFF(MINUTE, start_date , end_date)
AS session_length_in_minutes
FROM Sessions
WHERE job_id = :id

-- name: start-session!
INSERT INTO Sessions (job_id)
VALUES (:id)

-- name: stop-session!
UPDATE Sessions
SET end_date = CURRENT_TIMESTAMP
WHERE end_date IS NULL
