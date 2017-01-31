require_relative "routes/helpers.rb"
require_relative "routes/user.rb"
require_relative "routes/clients.rb"
require_relative "routes/jobs.rb"
require_relative "routes/sessions.rb"
require_relative "routes/invoices.rb"
require_relative "routes/statistics.rb"
require_relative "routes/profile.rb"

get "/" do
  redirect "/sessions"
end
