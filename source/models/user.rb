class User < ActiveRecord::Base
  has_many :clients
  has_many :jobs
  has_many :sessions
  has_many :invoices

  def self.register(data)
    User.create(username: data[:username],
                password: data[:password])
  end

  def self.create_demo
    n = pluck(:id).max + 1
    User.register({username: "demo-#{n}", password: ""})
  end

  def active_session
    Session.active id
  end

  def total_time
    sessions.map(&:duration_in_seconds).reduce(0, :+)
  end

  def time_today
    Session.today(id).map(&:duration_in_seconds).reduce(:+)
  end

  def total_earned
    jobs.map(&:cost).reduce(0, :+)
  end

  def average_hourly_rate
    if total_earned > 0
      total_earned / (total_time / 3600)
    else
      0
    end
  end

  def import

  end

  def export
    {sessions: sessions.map(&:attributes),
     jobs: jobs.map(&:attributes),
     clients: '',
     invoices: ''}.to_yaml
  end
end
