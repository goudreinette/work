namespace "/jobs" do
  get do
    @prefix = 'jobs'
    @jobs = Job.all
    erb :"jobs/all"
  end

  namespace "/new" do
    get do
      @job = Job.new
      @clients = Client.all
      erb :"jobs/edit"
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
      erb :"jobs/edit"
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
    erb :"partials/detail"
  end

  get "/delete/:id" do
    Job.destroy params[:id]
    redirect "/jobs"
  end
end
