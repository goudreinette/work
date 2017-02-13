class Session < ActiveRecord::Base
  include Duration
  include BelongsToUser

  belongs_to :project
  default_scope { order start_date: :desc }
  before_save :empty_means_nil

  def self.active(user_id)
    Session.where(end_date: nil, user_id: user_id).first
  end

  def self.today(user_id)
    Session.where(
      user_id: user_id,
      end_date: Time.now.beginning_of_day..Time.now.end_of_day)
  end

  def self.start(project_id)
    Session.stop
    Session.create(project_id: project_id, start_date: Time.new, paid?: true)
  end

  def self.stop
    Session.where(end_date: nil).update_all(end_date: Time.new)
  end

  def empty_means_nil
    self.description = nil if self.description == ''
  end

  def duration_in_seconds
    (end_date || Time.new) - start_date
  end
end
