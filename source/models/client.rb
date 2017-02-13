class Client < ActiveRecord::Base
  include BelongsToUser
  has_many :projects
  has_many :invoices

  def sessions
    projects.flat_map(&:sessions)
  end

  def combined_address
    "#{address}, \n#{postcode}, #{city}"
  end
end
