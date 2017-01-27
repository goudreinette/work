class Session < ActiveRecord::Base
  belongs_to :job

  def self.start(job_id)
    Session.create(job_id: job_id)
  end

  def duration_in_seconds
    (end_date || Time.new) + 3600 - start_date # FIXME
  end
end
