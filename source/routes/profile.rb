namespace "/profile" do
  get do
    erb :profile
  end

  post do

    redirect "/profile"
  end
end
