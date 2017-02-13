resource Invoice do
  before do
    if params[:project_ids]
      params[:invoice][:projects] = Job.find(params[:project_ids])
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
