class Invoice < ActiveRecord::Base
  include BelongsToUser
  belongs_to :client
  has_and_belongs_to_many :jobs

  def no
    "##{date.strftime('%Y%m%d')}"
  end

  def recipient
    "#{client.name},\n#{client.combined_address}"
  end

  def subtotal
    jobs.map(&:cost).inject(:+)
  end

  def tax
    subtotal * 0.21
  end

  def total
    subtotal + tax
  end
end
