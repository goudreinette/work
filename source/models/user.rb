class User < ActiveRecord::Base
  has_many :clients
  has_many :jobs
  has_many :sessions
  has_many :invoices

  def self.exists?(username, password)
    super username: username, password: password
  end

  def self.register(username, password)
    User.create(username: username,
                password: password) unless exists?(username, password)
  end

  def self.create_demo
    n = pluck(:id).max + 1
    User.register("demo-#{n}", "")
  end

  def active_session
    Session.active id
  end
end
