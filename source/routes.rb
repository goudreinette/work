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
get "/clients" do
  @clients = Client.all
  erb :clients
end

get "/clients/new" do
  @client = Client.new
  erb :form
end

post "/clients/new" do
  Client.create params[:client]
  redirect "/clients"
end

get "/clients/edit/:id" do
  @client = Client.find params[:id]
  erb :form
end

post "/clients/edit/:id" do
  Client.update params[:client]
  redirect "/clients"
end

get "/clients/:id" do
  client = Client.find params[:id]
  @header = client.name
  @facts = client.facts
  erb :detail
end


# Invoices
get "/invoices" do

end
