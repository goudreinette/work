namespace "/sessions" do
  get do
    @sessions = Session.order('start_date DESC')
    erb :sessions
  end

  get "/start/:id" do
    Session.start params[:id]
  end

  get "/stop" do
    Session.stop
  end

  get "/:id" do
    'single'
  end
end
