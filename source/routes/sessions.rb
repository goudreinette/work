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
      Session.update params[:id], params[:session]
      redirect "/sessions/#{params[:id]}"
    end
  end

  get "/:id" do
    session = Session.find params[:id]
    @header = "#{session.job.name} #{session.formatted_date}"
    @facts = {'Job'         => session.job.name,
              'Description' => session.description || '-',
              'Paid session?' => session.paid? ? 'Yes' : 'No',
              'Duration'    => session.duration}
    erb :"partials/detail"
  end
end
