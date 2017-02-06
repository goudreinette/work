resource Invoice do
  before do
    if params[:job_ids]
      params[:invoice][:jobs] = Job.find(params[:job_ids])
    end
  end

  get "/download/:id" do
    invoice = Invoice.find params[:id]
    # HACK
    generated_pdf_path = DefaultTemplate.render_to_pdf(invoice)
    filename = File.basename(generated_pdf_path)
    send_file generated_pdf_path, filename: filename
  end
end
