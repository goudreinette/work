namespace "/profile" do
  get do
    erb :profile
  end
end

get "/import" do
  # todo
end

get "/export" do
  attachment "export.yaml"
  user.export
end
