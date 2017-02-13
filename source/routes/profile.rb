namespace "/profile" do
  get do
    erb :'profile/show'
  end

  namespace "/edit" do
    get do
      erb :'profile/edit'
    end

    post do
      @user.wakatime_api_key = params[:user][:wakatime_api_key]
      @user.save
      redirect "/profile"
    end
  end
end

post "/import" do
  data = params[:file][:tempfile].read
  user.import data
  data
end

get "/export" do
  attachment "export.yaml"
  user.export
end
