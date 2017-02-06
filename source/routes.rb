get "/" do
  redirect "/sessions"
end

get "/statistics" do
  erb :statistics
end


# Resources
resource Job
resource Client
