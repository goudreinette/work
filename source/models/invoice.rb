class Invoice < ActiveRecord::Base
  include BelongsToUser
  belongs_to :client
  has_and_belongs_to_many :projects
  before_save :defaults

  def defaults
    self.date ||= Time.now
  end

  def no
    "##{date.strftime('%Y%m%d')}"
  end

  def subtotal
    projects.map(&:cost).inject(0, :+)
  end

  def tax
    subtotal * 0.21
  end

  def total
    subtotal + tax
  end
end
