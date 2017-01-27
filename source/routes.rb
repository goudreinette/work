require_relative "routes/clients.rb"
require_relative "routes/jobs.rb"
require_relative "routes/sessions.rb"
require_relative "routes/invoices.rb"

before do
  @path = request.path
end

get "/" do
  redirect "/sessions"
end
