from locust import HttpUser, task

class User(HttpUser):
    @task
    def about(self):
        self.client.get("/loop?")