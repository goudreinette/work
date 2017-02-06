module Resource
  def resource model, &block
    singular = model.name.downcase
    plural   = singular.pluralize
    prefix   = "/#{plural}"

    namespace prefix do
      # Allow overriding
      class_eval(&block) if block_given?

      get do
        instance_variable_set "@prefix".to_sym, plural
        erb "#{plural}/all".to_sym
      end

      namespace "/edit/:id" do
        get do
          instance_variable_set "@#{singular}".to_sym, model.find(params[:id])
          erb "#{plural}/edit".to_sym
        end

        post do
          model.update params[:id], params[singular.to_sym]
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
    end
  end
end


register Resource
