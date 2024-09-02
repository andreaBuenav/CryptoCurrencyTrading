El proyecto está hecho basado en el patrón MVC, adicionalmente añadiendole los servicios en las clases necesarias, en este proyecto no hice
Controllers por cada operacion sino por cada clase, ya que si lo hacía por métodos después habrían demasiadas clases, decidí mejor agrupar los métodos 
comunes en un solo controlador. Por ejemplo el controlador WalletController, tiene deposito, venta de crypto por trades, compra de crypto por trades y compra 
de criptos por medio de la cryptoStore que maneja los precios del mercado. 
El userAccountController maneja el login y el registro.

mini disclaimer: Puse que los depósitos sean realizados solo con enteros, sin embargo al realizar ventas si se disminuyen decimales en el balance.
También los usuarios pueden vender criptos por partes, sin embargo estas cantidades no pueden ser menores o iguales a 0, es decir 0.5, 0.2 etc. No
son oermitidos parala venta, pero 1.5, 4.6 etc, si son permitidos.
