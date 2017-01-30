class Invoice < ActiveRecord::Base
  belongs_to :client
  has_and_belongs_to_many :jobs

  def no
    "##{date.strftime('%Y%m%d')}"
  end

  def formatted_date
    date.strftime('%a %e %B  %H:%M')
  end

  def facts
    {'Client'   => client,
     'Date'     => formatted_date,
     'Jobs'     => jobs.map(&:name),
     'Subtotal' => subtotal,
     'Tax 21%'  => tax,
     'Total'    => total}
  end
end
