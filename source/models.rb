class Session < ActiveRecord::Base
  belongs_to :job

  def duration
    end_date - start_date # Time.now.strftime "%H:%M:%S"
  end
end

class Job < ActiveRecord::Base
  has_many :sessions
  belongs_to :client

  def duration
    sessions.map(&:duration).sum
  end

  def price
    case pricing_type
    when 'fixed'
      pricing_value
    when 'hourly'
      pricing_value * duration.in_hours
    end
  end
end

class Client <  ActiveRecord::Base
  has_many :jobs

  def sessions
    jobs.flat_map(&:sessions)
  end

  def facts
    {'Address'  => address,
     'City'     => city,
     'Postcode' => postcode,
     'Jobs'     => jobs.count,
     'Sessions' => session_count}
  end
end
