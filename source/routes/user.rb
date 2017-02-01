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

  def guard!
    redirect '/login' unless authenticated? or @path == '/login'
  end
end

before do
  @path = request.path

  guard!

  if authenticated?
    $USER = user # HACK
    @jobs = user.jobs
    @clients = user.clients
    @invoices = user.invoices
    @sessions = user.sessions
    @active_session = user.active_session
  end
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


post "/try" do
  authenticate username: 'demo', password: 'demo'
  redirect "/sessions"
end

post "/register" do
  User.create params[:user]
  authenticate params[:user]
  redirect "/sessions"
end
