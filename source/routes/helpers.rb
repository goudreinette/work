helpers do
  def pluralize n, string
    "#{n} #{n == 1 ? string.singularize : string.pluralize}"
  end

  def money n
    "€#{format '%.2f', n || 0}"
  end

  def date date
    date.strftime('%A %e %B')
  end

  def html_date date
    date.strftime('%Y-%m-%d')
  end

  def html_datetime date
    date.strftime('%Y-%m-%d %H:%M')
  end

  def duration n
    seconds = if n.is_a? Numeric or n.nil? then n.to_i else n.duration_in_seconds.to_i end
    [seconds / 3600, seconds / 60 % 60, seconds % 60]
      .map { |t| t.to_s.rjust(2,'0') }.join(':')
  end

  def cost_calculation job
    case job.pricing_type
    when 'fixed'
      "#{money job.cost} / #{job.duration} = #{money (job.pricing_value / job.duration_in_hours)}/hour"
    when 'hourly'
      "#{money job.pricing_value} * #{job.duration} = #{money job.cost}"
    end
  end

  def job_cost job
    "#{job.pricing_type.capitalize}: #{cost_calculation job}"
  end

  def session_span session
    "#{session.start_date.strftime '%a %e %B  %H:%M'}
     -
     #{session.end_date&.strftime('%H:%M') || 'now'}"
  end

  def resource model
    singular = model.name.downcase
    plural   = singular.pluralize
    prefix   = "/#{plural}"

    namespace prefix do
      get do
        erb "#{plural}/all".to_sym
      end

      namespace "/edit/:id" do
        get do
          instance_variable_set "@#{plural}".to_sym, model.find(params[:id])
          erb :"#{plural}/edit".to_sym
        end

        post do
          redirect "/#{plural}/#{params[:id]}"
        end
      end

      namespace "/new" do
        get do
          instance_variable_set "@#{singular}".to_sym, model.new
          erb "#{plural}/edit".to_sym
        end

        post do
          record = model.create params[singular.to_sym]
          redirect "/#{plural}/#{record.id}"
        end
      end

      get "/delete/:id" do
        model.destroy params[:id]
        redirect "/#{plural}"
      end

      get "/:id" do
        instance_variable_set "@#{singular}".to_sym, model.find(params[:id])
        erb "#{plural}/single".to_sym
      end

      # Allow extra routes
      yield
    end
  end
end
