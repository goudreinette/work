get "/sessions" do
  @sessions = Session.order(:end_date)
  erb :sessions
end
