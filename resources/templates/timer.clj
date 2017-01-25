(ns templates.timer
  (use hiccup.form
       templates.helpers))

(defn timer [jobs]
  [:form.timer {:class (active-class (some :running jobs) true)}
   [:i.play.icon]
   [:div.time ""]
   [:select
     (for [{:keys [job_id name]} jobs]
        [:option {:data-id job_id} name])]])
