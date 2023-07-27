from django.http import JsonResponse
from django.views import View
from .utils.Badge import Badge

# Create your views here.
class currency(View):
    def get(self, request, currencys):
        badgeCurrency = Badge(currencys)
        
        value, disclaimer, name_currency = badgeCurrency.dataCurrency()
        
        return JsonResponse({'message': 'success', 'currency':currencys, "disclaimer":disclaimer, "name":name_currency , 'base':1, 'To':value})