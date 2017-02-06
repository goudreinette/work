require_relative "../models"

# namespace "/jobs" do
#   get do
#     @prefix = 'jobs'
#     erb :"jobs/all"
#   end
#
#   namespace "/new" do
#     get do
#       @job = Job.new
#       erb :"jobs/edit"
#     end
#
#     post do
#       job = Job.create params[:job]
#       redirect "/jobs/#{job.id}"
#     end
#   end
#
#   namespace "/edit/:id" do
#     get do
#       @job = Job.find params[:id]
#       erb :"jobs/edit"
#     end
#
#     post do
#       job = Job.update params[:id], params[:job]
#       redirect "/jobs/#{job.id}"
#     end
#   end
#
#   get "/:id" do
#     @job = Job.find params[:id]
#     erb :"jobs/single"
#   end
#
#   get "/delete/:id" do
#     Job.destroy params[:id]
#     redirect "/jobs"
#   end
# end
resource Job
