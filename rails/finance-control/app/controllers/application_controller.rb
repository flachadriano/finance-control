class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  protect_from_forgery with: :null_session
  before_action :allow_all_origins

  def allow_all_origins
  	response.headers['Access-Control-Allow-Origin'] = "*"
  end

end
