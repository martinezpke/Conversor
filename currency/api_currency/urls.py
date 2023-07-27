from django.urls import path
from .views import currency


urlpatterns = [
    path("api/convert/<str:currencys>", currency.as_view(), name="convert_currency"),
]
