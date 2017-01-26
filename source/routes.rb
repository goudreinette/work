get "/" do
  redirect "/sessions"
end

get "/sessions" do
  @sessions = Session.all
  erb :sessions
end

get "/jobs" do
  @jobs = Job.all
  erb :jobs
end
