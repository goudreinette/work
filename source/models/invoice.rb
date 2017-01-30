class Invoice < ActiveRecord::Base
  belongs_to :client
  has_and_belongs_to_many :jobs

  def no
    "##{date.strftime('%Y%m%d')}"
  end

  def formatted_date
    date.strftime('%a %e %B  %H:%M')
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

  def facts
    {'Client'   => client.name,
     'Date'     => formatted_date,
     'Jobs'     => jobs.map(&:name).join(', '),
     'Subtotal' => subtotal,
     'Tax 21%'  => tax,
     'Total'    => total}
  end
end
