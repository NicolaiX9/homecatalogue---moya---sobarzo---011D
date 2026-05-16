Aplicaciones requeridas para su creación:

Github Desktop, Xamp, Postman y Visual Studio Code.

**MICROSERVICIOS**:

1. Manejo de usuario  
2. Manejo productos  
3. Manejo de stock  
4. Registro de almacenes  
5. Manejo de carrito

**Bases de datos**:

1. db\_usuarios  
2. db\_productos  
3. db\_stock  
4. db\_almacenes  
5. db\_carritos

- **Tipo usuario**

* **Puerto:**9091  
* **URL:** /api/v1/usuarios/tipos


Datos JSON: 

{  
    "rol":"Administrador"  
    }

{  
    "rol":"Cliente"  
    }

- **Usuario**

* **Puerto:** 9091  
* **URL:** /api/v1/usuarios


Datos JSON: 

   {  
    "run":"12.415.547-8",  
    "email":"agusGonzles@gmail.com",  
    "nombre":"Agustin Gonzales",  
    "password":"Agu123",  
    "tipoUsuario": {"id": 1}  
    }

{  
     
    "run":"9.418.547-2",  
    "email":"Rod.Pena@gmail.com",  
    "nombre":"Rodrigo Peña",  
    "password":"Gogeta125",  
    "tipoUsuario": {"id": 1}  
   

}

{  
    "run":"15.438.647-K",  
    "email":"Javcan@gmail.com",  
    "nombre":"Javiera Canales",  
    "password": "12octbros\!\!",    
    "tipoUsuario": {"id": 2}  
}

- **ProductoCategoria**

* **Puerto:** 9094  
* **URL**: /api/v1/productos/categoria

Datos JSON: 

{  
    "categoria":"Hogar"  
    }  
{  
    "categoria":"Herramientas"  
    }  
{  
    "categoria":"Jardín"  
    }

- **Producto**

* **Puerto:** 9094  
* **URL:** /api/v1/productos

Datos JSON: 

{  
    "nombre":"Estante de 5 Niveles Negro ",  
    "descripcion":"180x90x40 \- Color Negro",  
    "precio":21000,  
    "productoCategoria": {"id":2}  
    }

{  
    "nombre":"Estante de 5 Niveles Negro ",  
    "descripcion":"180x90x40 \- Color Negro",  
    "precio":21000,  
    "productoCategoria": {"id":2}  
    }

{  
    "nombre":"Estante de 5 Niveles Verde ",  
    "descripcion":"180x90x40 \- Color Verde",  
    "precio":21000,  
    "productoCategoria": {"id":1}  
    }

- **Carrito**

* **Puerto:** 9095  
* **URL:** /api/v1/carritos

Datos JSON: 

{  
    "total":100000,  
    "idUsuario": 1  
    }  
{  
    "total":150000,  
    "idUsuario": 1  
    }  
{  
    "total":200000,  
    "idUsuario": 2  
    }

- **Carrito Detalle**

* **Puerto:** 9095  
* **URL:** /api/v1/carritos/carritosdetalle


Datos JSON: 

{  
    "idProducto":1,  
    "cantidad":4,  
    "carrito": {"id":1}  
    }

{  
    "idProducto":2,  
    "cantidad":2,  
    "carrito": {"id":2}  
    }

{  
    "idProducto":3,  
    "cantidad":3,  
    "carrito": {"id":3}  
    }

- **Almacén**

* **Puerto:** 9092  
* **URL:** /api/v1/almacenes


Datos JSON: 

{  
   "calleDireccion":"San alfonso",  
   "numeroDireccion":"1050"  
    }

{  
   "calleDireccion":"Huerfanos",  
   "numeroDireccion":"904"  
    }

{  
   "calleDireccion":"Hugo bravo",  
   "numeroDireccion":"84"  
    }

- **Stock**

* **Puerto:** 9093  
* **URL:** /api/v1/stock

Datos JSON: 

{  
    "cantidad":150,  
    "idProducto":1,  
    "idAlmacen":1  
    }

{  
    "cantidad":200,  
    "idProducto":2,  
    "idAlmacen":2  
    }

{  
    "cantidad":200,  
    "idProducto":3,  
    "idAlmacen":3  
    }

