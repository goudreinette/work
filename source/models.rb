class Session < ActiveRecord::Base
  belongs_to :job

  def duration
    TimeDifference.between(start_date, end_date).in_minutes
  end
end

class Job < ActiveRecord::Base
  has_many :sessions
  belongs_to :client
end

class Client <  ActiveRecord::Base
  has_many :jobs

  def session_count
    jobs.flat_map { |j| j.sessions }
        .count
  end

  def facts
    {'Address'  => client.address,
     'City'     => client.city,
     'Postcode' => client.postcode,
     'Jobs'     => client.jobs.count,
     'Sessions' => client.session_count}
  end
end
