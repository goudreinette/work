namespace "/jobs" do
  get do
    @jobs = Job.all
    erb :jobs
  end

  namespace "/new" do
    get do
      @job = Job.new
      @clients = Client.all
      erb :job_form
    end

    post do
      job = Job.create params[:job]
      redirect "/jobs/#{job.id}"
    end
  end

  namespace "/edit/:id" do
    get do
      @job = Job.find params[:id]
      @clients = Client.all
      erb :job_form
    end

    post do
      job = Job.update params[:id], params[:job]
      redirect "/jobs/#{job.id}"
    end
  end

  get "/:id" do
    job = Job.find params[:id]
    @header = job.name
    @facts = job.facts
    erb :detail
  end

  get "/delete/:id" do
    Job.destroy params[:id]
    redirect "/jobs"
  end
end
