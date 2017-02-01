helpers do
  def authenticate(user)
    session[:user_id] = user.id
  end

  def user
    User.find(session[:user_id])
  end

  def authenticated?
    session[:user_id]
  end

  def login?
    ['/login', '/try', '/register'].include? request.path
  end

  def guard!
    redirect '/login' unless authenticated? or login?
  end

  def try_login(user)
    if user
      authenticate user
      json user
    else
      status 400
    end
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
    try_login User.find_by(username: params[:username], password: params[:password])
  end
end

post "/try" do
  try_login User.create_demo
end

post "/register" do
  try_login User.register params[:username], params[:password]
end
