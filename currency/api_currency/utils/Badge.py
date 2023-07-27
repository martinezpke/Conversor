import requests
from bs4 import BeautifulSoup

class Badge:
    def __init__(self, currency) -> None:
        self.currency = currency
    
    def dataCurrency(self):
        url = f'https://www.google.com/finance/quote/{self.currency}'
        
        try:
            # this take the HTML of the url
            page = requests.request("GET", url, 
                                    headers={
                                        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36 Edg/91.0.864.59',
                                        'Accept-Language': 'es'
                                        })

            # Check if the request was successful
            page.raise_for_status()
            
            # Create a BeautifulSoup object to parse the HTML
            soup = BeautifulSoup(page.content, 'html.parser')
            
            valueBase = soup.find("div", class_="YMlKec fxKbKc")
            ValueDisclaimer = soup.find("div", class_="ygUjEc")
            name_currency = soup.find("div", class_="zzDege")
            
            result = self.processValue(valueBase.text)
            
            return result, ValueDisclaimer.text, name_currency.text
            
        
        except requests.exceptions.RequestException as e:
            print(f'Error fetching the page: {e}')
        
    def processValue(self, value):
        valueCurrency = value.replace(".", "")
        valueCurrency = valueCurrency.replace(",", ".")
        result = float(valueCurrency)
        return result
