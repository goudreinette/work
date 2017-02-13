ActiveRecord::Schema.define do
  create_table :clients do |t|
    t.string :name
    t.string :address
    t.string :city
    t.string :postcode, limit: 6
    t.belongs_to :user
  end

  create_table :projects do |t|
    t.string     :name
    t.column     :pricing_type, "ENUM('fixed', 'hourly')", default: 'fixed'
    t.float      :pricing_value
    t.belongs_to :client
    t.belongs_to :user
  end

  create_table :sessions do |t|
    t.string     :name
    t.datetime   :start_date
    t.datetime   :end_date
    t.text       :description
    t.boolean    :paid?
    t.belongs_to :project
    t.belongs_to :user
  end

  create_table :invoices do |t|
    t.datetime   :date
    t.belongs_to :client
    t.belongs_to :user
  end

  create_join_table :invoices, :projects do |t|
    t.index [:project_id, :invoice_id]
    t.index [:invoice_id, :project_id]
  end

  create_table :users do |t|
    t.string  :username
    t.string  :password
    t.string  :wakatime_api_key
    t.float   :default_hourly_rate
    t.boolean :pomodoro?
  end
end
