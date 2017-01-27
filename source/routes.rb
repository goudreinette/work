get "/" do
  redirect "/sessions"
end

# Sessions
get "/sessions" do
  @sessions = Session.all
  erb :sessions
end

# Jobs
get "/jobs" do
  @jobs = Job.all
  erb :jobs
end


# Clients
namespace "/clients" do
  get "/" do
    @clients = Client.all
    erb :clients
  end

  get "/:id" do
    client = Client.find params[:id]
    @header = client.name
    @facts = client.facts
    erb :detail
  end

  namespace "/new" do
    get do
      @client = Client.new
      erb :client_form
    end

    post do
      Client.create params[:client]
      redirect "/clients/#{params[:id]}"
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
end


# Invoices
get "/invoices" do

end
