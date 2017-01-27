class Job < ActiveRecord::Base
  include Duration

  has_many :sessions
  belongs_to :client

  def duration_in_seconds
    sessions.map(&:duration_in_seconds).sum
  end

  def cost
    case pricing_type
    when 'fixed'
      pricing_value
    when 'hourly'
      pricing_value * (duration_in_seconds/3600)
    end
  end

  def cost_calculation
    case pricing_type
    when 'fixed'
      "€#{format '%.2f', cost}"
    when 'hourly'
      "€#{format '%.2f', pricing_value} * #{duration} = €#{format '%.2f', cost}"
    end
  end

  def cost_text
    "#{pricing_type.to_s.capitalize}: #{cost_calculation}"
  end

  def facts
    {'Client'       => client.name,
     'Sessions'     => sessions.count,
     'Duration'     => duration,
     'Cost'         => cost_text}
  end
end
