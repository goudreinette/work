before do
  @path = request.path

  # TODO: for user
  @jobs = Job.all
  @clients = Client.all
  @invoices = Invoice.all
  @sessions = Session.all
  @session = Session.active
end

namespace "/login" do
  get do
    erb :login, layout: false
  end

  post do
    # Authenticate here
    redirect "/sessions"
  end
end
