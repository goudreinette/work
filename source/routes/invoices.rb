namespace "/invoices" do
  helpers do
    def jobs_from_multiselect
      params[:invoice][:jobs] = Job.find(params[:job_ids])
    end
  end

  get do
    @prefix = 'invoices'
    @header = lambda(&:no)
    @cards = @sessions
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
      jobs_from_multiselect
      invoice = Invoice.create params[:invoice]
      redirect "/invoices/#{invoice.id}"
    end
  end

  namespace "/edit/:id" do
    get do
      @invoice = Invoice.find params[:id]
      erb :"invoices/edit"
    end

    post do
      jobs_from_multiselect
      Invoice.update params[:id], params[:invoice]
      redirect "/invoices/#{params[:id]}"
    end
  end

  get "/delete/:id" do
    Invoice.destroy params[:id]
    redirect "/invoices"
  end


  get "/:id" do
    @invoice = Invoice.find params[:id]
    @header = "Invoice #{@invoice.no}"
    @facts = @invoice.facts

    erb :"invoices/single"
  end
end
