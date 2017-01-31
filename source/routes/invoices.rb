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

  namespace "/new" do
    get do

    end

    post do

    end
  end

  namespace "/edit/:id" do
    get do

    end

    post do

    end
  end

  get "/:id" do
    @invoice = Invoice.find params[:id]
    @header = "Invoice #{@invoice.no}"
    @facts = @invoice.facts

    erb :"invoices/single"
  end
end
