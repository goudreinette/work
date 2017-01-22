(ns macros-test
  (use midje.sweet macros))

(def jobs (resource-routes 'Jobs {}))

(defn req [method uri]
  {:request-method method :uri uri})

(facts "about resource-name"
  (resource-prefix 'Jobs) => "/jobs")

(facts "about resource-routes"
  (jobs (req :get "/jobs")) => (contains {:body "all"})
  (jobs (req :get "/jobs/1")) => (contains {:body "single one"})
  (jobs (req :get "/jobs/new")) => (contains {:body "show form"})
  (jobs (req :post "/jobs/new")) => (contains {:body "create new, show message"})
  (jobs (req :put "/jobs/1")) => (contains {:body "update, show message"})
  (jobs (req :delete "/jobs/1")) => (contains {:body "delete, show message"}))
