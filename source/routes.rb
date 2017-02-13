get "/" do
  redirect "/sessions"
end

get "/statistics" do
  erb :statistics
end


# Resources
resource Project
resource Client
