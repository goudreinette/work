class Session < ActiveRecord::Base
  include Duration
  include BelongsToUser

  default_scope { order start_date: :desc }
  belongs_to :job

  def self.active(user_id)
    Session.where(end_date: nil, user_id: user_id).first
  end

  def self.start(job_id)
    Session.create(job_id: job_id, start_date: Time.new)
  end

  def self.stop
    Session.where(end_date: nil).update_all(end_date: Time.new)
  end

  def duration_in_seconds
    (end_date || Time.new) - start_date
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
