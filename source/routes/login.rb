before do
  @path = request.path
  @jobs = Job.all
end

namespace "/login" do
  get do
    erb :login, layout: false
  end

  post do
    # Authenticate here
    redirect "/sessions"
  end
end
