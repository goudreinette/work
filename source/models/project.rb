class Project < ActiveRecord::Base
  include Duration
  include BelongsToUser

  has_many :sessions
  belongs_to :client

  def duration_in_seconds
    sessions.map(&:duration_in_seconds).sum
  end

  def paid_sessions
    sessions.where(paid?: true)
  end

  def paid_duration_in_seconds
    paid_sessions.map(&:duration_in_seconds).sum
  end

  def cost
    case pricing_type
    when 'fixed'
      pricing_value
    when 'hourly'
      pricing_value * (paid_duration_in_seconds/3600)
    end
  end

  def tax
    cost * 0.21
  end

  def cost_with_tax
    cost + tax
  end
end
