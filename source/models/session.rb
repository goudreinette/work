class Session < ActiveRecord::Base
  include Duration

  belongs_to :job

  def self.start(job_id)
    Session.create(job_id: job_id)
  end

  def self.stop
    Session.where(end_date: nil).update_all(end_date: Time.new)
  end

  # FIXME
  def end_date
    super + 3600
  end

  def duration_in_seconds
    (end_date || Time.new) - start_date
  end
end
