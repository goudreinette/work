resource Session do
  get "/start/:id" do
    Session.stop
    Session.start params[:id]
  end

  get "/stop" do
    Session.stop
  end

  post "/edit/:id" do
    params[:session][:paid?] = false if params[:session][:paid?] == nil
    Session.update params[:id], params[:session]
    redirect "/sessions/#{params[:id]}"
  end
end
