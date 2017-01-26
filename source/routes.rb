get "/" do
  @sessions = Session.all
  erb :sessions
end

get "/jobs" do
  @jobs = Job.all
  erb :jobs
end
