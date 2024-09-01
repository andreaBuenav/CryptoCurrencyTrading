El proyecto está hecho basado en el patrón MVC, adicionalmente añadiendole los servicios en las clases necesarias, en este proyecto no hice
Controllers por cada operacion sino por cada clase, ya que si lo hacía por métodos después habrían demasiadas clases, decidí mejor agrupar los métodos 
comunes en un solo controlador. Por ejemplo el controlador WalletController, tiene deposito, venta de crypto por trades, compra de crypto por trades y compra 
de criptos por medio de la cryptoStore que maneja los precios del mercado. 
El userAccountController maneja el login y el registro.

mini disclaimer: Puse que los depósitos sean realizados solo con enteros, sin embargo al realizar ventas si se disminuyen sus decimales
