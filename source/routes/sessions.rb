namespace "/sessions" do
  get do
    @sessions = Session.order('start_date DESC')
    erb :sessions
  end

  get "/start/:id" do
    Session.stop
    Session.start params[:id]
  end

  get "/stop" do
    Session.stop
  end

  get "/:id" do
    session = Session.find params[:id]
    @header = session.formatted_date
    @facts = session.facts
    erb :detail
  end
end
