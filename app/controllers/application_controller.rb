class ApplicationController < ActionController::Base
  protect_from_forgery with: :null_session
  before_action :allow_all_origins

  def allow_all_origins
  	response.headers['Access-Control-Allow-Origin'] = "*"
    response.headers['Access-Control-Allow-Methods'] = "*"
  end
end
