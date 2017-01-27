get "/" do
  redirect "/sessions"
end

# Sessions
get "/sessions" do
  @sessions = Session.all
  erb :sessions
end

# Jobs
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

  get "/:id" do
    job = Job.find params[:id]
    @header = job.name
    @facts = job.facts
    erb :detail
  end
end



# Clients
namespace "/clients" do
  get do
    @clients = Client.all
    erb :clients
  end

  namespace "/new" do
    get do
      @client = Client.new
      erb :client_form
    end

    post do
      client = Client.create params[:client]
      redirect "/clients/#{client.id}"
    end
  end

  namespace "/edit/:id" do
    get do
      @client = Client.find params[:id]
      erb :client_form
    end

    post do
      Client.update params[:id], params[:client]
      redirect "/clients/#{params[:id]}"
    end
  end

  get "/:id" do
    client = Client.find params[:id]
    @header = client.name
    @facts = client.facts
    erb :detail
  end

  get "/delete/:id" do
    Client.destroy params[:id]
    redirect "/clients"
  end
end


# Invoices
get "/invoices" do

end
