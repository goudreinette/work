(ns templates.timer
  (use hiccup.form
       templates.helpers))

(defn timer [jobs]
  [:form.timer {:class (active-class (some :running jobs) true)}
   [:i.play.icon]
   [:div.time.hidden ""]
   [:select
     (for [{:keys [job_id name]} jobs]
        [:option {:value job_id} name])]])
