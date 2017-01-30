namespace "/invoices" do
  get do
    @prefix = 'invoices'
    @cards = Invoice.all

    @header = lambda(&:no)
    @meta = lambda do |invoice|
      "#{invoice.formatted_date},
       #{invoice.jobs.count}
       #{invoice.jobs.count > 1 ? 'jobs' : 'job'}"
    end

    erb :"partials/cards"
  end
end
