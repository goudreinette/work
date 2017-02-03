namespace "/invoices" do
  helpers do
    def jobs_from_multiselect
      params[:invoice][:jobs] = Job.find(params[:job_ids])
    end
  end

  get do
    @prefix = 'invoices'
    @header = lambda(&:no)
    @cards = @invoices
    @meta = lambda do |invoice|
      "#{date invoice.date}, #{pluralize invoice.jobs.count, 'jobs'}"
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

  get "/download/:id" do
    invoice = Invoice.find params[:id]
    # HACK
    generated_pdf_path = DefaultTemplate.render_to_pdf(invoice)
    filename = File.basename(generated_pdf_path)
    send_file generated_pdf_path, filename: filename
  end

  get "/:id" do
    @invoice = Invoice.find params[:id]
    erb :"invoices/single"
  end
end
