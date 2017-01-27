namespace "/sessions" do
  get do
    @sessions = Session.order('start_date DESC')
    erb :sessions
  end

  get "/start/:id" do
    'start'
  end

  get "/stop" do
    'stop'
  end

  get "/:id" do
    'single'
  end
end
