helpers do
  def authenticate(credentials)
    session[:username] = credentials[:username]
    session[:password] = credentials[:password]
  end

  def authenticated?
    User.exists?(username: session[:username],
                 password: session[:password])
  end

  def ensure_authenticated
    redirect '/login' unless authenticated?
  end
end

before do
  @path = request.path

  ensure_authenticated unless @path == '/login'

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
    authenticate params[:user]
    redirect "/sessions"
  end
end
