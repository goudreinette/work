class Session < ActiveRecord::Base
  belongs_to :job
end

class Job < ActiveRecord::Base
  has_many :sessions
  belongs_to :client
end

class Client <  ActiveRecord::Base
  has_many :jobs
end
