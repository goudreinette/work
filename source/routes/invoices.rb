namespace "/invoices" do
  helpers do
    def find_jobs_from_multiselect

    end
  end

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
      @invoice = Invoice.new
      erb :"invoices/edit"
    end

    post do
      invoice = Invoice.create params[:invoice]
      redirect "/invoices/#{invoice[:id]}"
    end
  end

  namespace "/edit/:id" do
    get do
      @invoice = Invoice.find params[:id]
      erb :"invoices/edit"
    end

    post do

      Invoice.update params[:id], params[:invoice]
      redirect "/invoices/#{params[:id]}"
    end
  end

  get "/:id" do
    @invoice = Invoice.find params[:id]
    @header = "Invoice #{@invoice.no}"
    @facts = @invoice.facts

    erb :"invoices/single"
  end
end
