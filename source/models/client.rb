class Client < ActiveRecord::Base
  include BelongsToUser
  has_many :jobs
  has_many :invoices

  def sessions
    jobs.flat_map(&:sessions)
  end

  def combined_address
    "#{address}, \n#{postcode}, #{city}"
  end
end
