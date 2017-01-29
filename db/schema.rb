ActiveRecord::Schema.define do
  create_table :clients do |t|
    t.string :name
    t.string :address
    t.string :city
    t.string :postcode, limit: 6
  end

  create_table :jobs do |t|
    t.string     :name
    t.column     :pricing_type, "ENUM('fixed', 'hourly')", default: 'fixed'
    t.decimal    :pricing_value, precision: 2, scale: 10
    t.belongs_to :client
  end

  create_table :sessions do |t|
    t.string     :name
    t.datetime   :start_date
    t.datetime   :end_date
    t.text       :description
    t.belongs_to :job
  end
end
