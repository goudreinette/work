class Client < ActiveRecord::Base
  has_many :jobs
  has_many :invoices

  def sessions
    jobs.flat_map(&:sessions)
  end

  def facts
    {'Address'  => address,
     'City'     => city,
     'Postcode' => postcode,
     'Jobs'     => jobs.count,
     'Sessions' => sessions.count}
  end
end
