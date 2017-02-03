namespace "/profile" do
  get do
    erb :profile
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
