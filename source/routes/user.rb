helpers do
  def authenticate(user)
    session[:username] = User.username
    session[:password] = User.password
  end

  def user
    User.find_by(username: session[:username],
                 password: session[:password])
  end

  def authenticated?
    User.exists? session[:username], session[:password]
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
    authenticate User.find_by(params[:user])
    redirect "/sessions"
  end
end


post "/try" do
  authenticate User.create_demo
  redirect "/sessions"
end

post "/register" do
  User.create params[:user]
  authenticate params[:user]
  redirect "/sessions"
end
