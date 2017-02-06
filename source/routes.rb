require_relative "routes/helpers.rb"
require_relative "routes/sessions.rb"
require_relative "routes/invoices.rb"
require_relative "routes/profile.rb"
require_relative "routes/user.rb"

get "/" do
  redirect "/sessions"
end

get "/statistics" do
  erb :statistics
end


# Resources
resource Job
resource Client
