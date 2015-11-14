Rails.application.routes.draw do
  resources :categories
  resources :transactions

  root 'transactions#index'
end
