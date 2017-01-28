require_relative "routes/login.rb"
require_relative "routes/clients.rb"
require_relative "routes/jobs.rb"
require_relative "routes/sessions.rb"
require_relative "routes/invoices.rb"

get "/" do
  redirect "/login"
end
