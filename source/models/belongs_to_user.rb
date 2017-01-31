module BelongsToUser
  def self.included(base)
    base.class_eval do
      belongs_to :user
      before_save :associate_with_user
    end
  end

  def associate_with_user
    p $user
    user = $user # HACK
  end
end
