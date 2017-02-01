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

  def sessions_today
    Session.today(id)
  end

  def total_time
    sessions.map(&:duration_in_seconds).reduce(:+)
  end

  def time_today
    sessions_today.map(&:duration_in_seconds).reduce(:+)
  end

  def total_earned
    jobs.map(&:cost).reduce(:+)
  end

  def average_hourly_rate
    total_hours / total_earned
  end
end
