get "/" do
  redirect "/sessions"
end

get "/sessions" do
  @sessions = Session.all
  erb :sessions
end

get "/jobs" do
  @jobs = Job.all
  erb :jobs
end

get "/clients" do
  @clients = Client.all
  erb :clients
end

get "/clients/:id" do
  client = Client.find params[:id]
  @header = client.name
  @facts = {'Address' => client.address,
            'City' => client.city,
            'Postcode' => client.postcode}
  erb :detail
end

get "/invoices" do

end
