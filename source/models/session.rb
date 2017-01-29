class Session < ActiveRecord::Base
  include Duration

  default_scope { order start_date: :desc }
  belongs_to :job

  def self.active
    Session.where(end_date: nil).first
  end

  def self.start(job_id)
    Session.create(job_id: job_id, start_date: Time.now)
  end

  def self.stop
    Session.where(end_date: nil).update_all(end_date: Time.new)
  end

  def end_date
    super&.+ 3600
  end

  def duration_in_seconds
    (end_date || (Time.new + 3600)) - start_date
  end


  # FIXME ---------
  def formatted_date
    "#{start_date.strftime '%a %e %B  %H:%M'}
     -
     #{end_date&.strftime('%H:%M') || 'now'}"
  end

  def facts
    {'Job'         => job.name,
     'Description' => description || '-',
     'Duration'    => duration}
  end
end
