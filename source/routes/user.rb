helpers do
  def authenticate(credentials)
    session[:username] = credentials[:username]
    session[:password] = credentials[:password]
  end

  def user
    User.find_by(username: session[:username],
                 password: session[:password])
  end

  def authenticated?
    not user.nil?
  end

  def ensure_authenticated
    redirect '/login' unless authenticated?
  end
end

before do
  @path = request.path

  ensure_authenticated unless @path == '/login'

  # TODO: for user
  @jobs = user.jobs
  @clients = user.clients
  @invoices = user.invoices
  @sessions = user.sessions
  @session = user.active_session
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
