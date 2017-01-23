(ns macros-test
  (use midje.sweet macros))

; (defresource Jobs)
;
; (defn req [method uri]
;   {:request-method method :uri uri})
;
; (facts "about resource-name"
;   (resource-prefix 'Jobs) => "/jobs")
;
; (facts "about resource-routes"
;   (Jobs (req :get "/jobs"))      => (contains {:body "all"})
;   (Jobs (req :get "/jobs/1"))    => (contains {:body "single one"})
;   (Jobs (req :get "/jobs/new"))  => (contains {:body "show form"})
;   (Jobs (req :post "/jobs/new")) => (contains {:body "create new, show message"})
;   (Jobs (req :put "/jobs/1"))    => (contains {:body "update, show message"})
;   (Jobs (req :delete "/jobs/1")) => (contains {:body "delete, show message"}))
