namespace "/sessions" do
  get do
    erb :"sessions/all"
  end

  get "/start/:id" do
    Session.stop
    Session.start params[:id]
  end

  get "/stop" do
    Session.stop
  end

  namespace "/edit/:id" do
    get do
      @session = Session.find params[:id]
      erb :"sessions/edit"
    end

    post do
      params[:session][:description] = nil if params[:session][:description] == ''
      params[:session][:paid?] = false if params[:session][:paid?] == nil
      Session.update params[:id], params[:session]
      redirect "/sessions/#{params[:id]}"
    end
  end

  get "/:id" do
    @session = Session.find params[:id]
    erb :"sessions/single"
  end
end
