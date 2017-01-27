get "/sessions" do
  @sessions = Session.order('start_date DESC')
  erb :sessions
end
