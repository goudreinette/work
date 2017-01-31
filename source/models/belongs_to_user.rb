module BelongsToUser
  def self.included(base)
    base.class_eval do
      belongs_to  :user
      before_save :associate_with_user
    end
  end

  def associate_with_user
    if defined? session
      user_id = User.find_by(username: session[:username],
                             password: session[:password])
    end
  end
end
