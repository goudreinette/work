get "/sessions" do
  @sessions = Session.all
  erb :sessions
end
