$$
**# Documentación de API v1.0.0**
$$

*********************************************************************

# Model: User

## Endpoints:

**GET:**

description: *retorna una lista con todos los users disponibles*

request: "api/users"

response:
	status: 200
	body:[
		  { 
		   id: 1, 
		   username: "aldo", 
		   password: "password", 
		   roles: [
			{
			  id: 2
			  name: "user"
			}
		   ] 
		  },

		 { 
		   id: 2, 
		   username: "chava", 
		   password: "password", 
		   roles: [
			{
			  id: 2
			  name: "user"
			},
			{
			  id: 3
			  name: "admin"
			}
		   ] 
		 } 
	    ]

---------------------------------------------------------------------

**GET:**

*description: retorna un user por medio de id*

request: "api/users/{$id}"

responses:
	status: 200
	body:{ 
	  id: 1, 
	  username: "aldo", 
	  password: "password", 
	  roles: [
	    {
	      id: 2
		  name: "user"
	    } 
	  ] 
	}

	status: 400
	body: { 		
			message: "user with id {$id} does not exists"
			}

---------------------------------------------------------------------

**POST**

*description: crea un nuevo registro de un user*

request: "api/users"
	   body: { 
		   username: "aldo", 
		   password: "password", 
		},
		notes: no agregues role al usuario, al crearlo, por defecto le agrega un role de usuario cuando se da de alta

response:
	status: 200
	body: { 
		   id: 1, 
		   username: "aldo", 
		   password: "password", 
		   roles: [
			{
			  id: 1
			  name: "user"
			}
		   ] 
		},
	status: 409
	body: {
		message: "user already exists"
	}

---------------------------------------------------------------------

**DELETE:**

*description: elimina un role de un user*

request: "api/users/{$userId}/roles/${roleId}"

responses:
	status: 200

	status: 400
	body: { 		
			message: "user with id {$id} does not exists"
			}

	status: 400
	body: { 		
			message: "role with id {$id} does not exists"
			}

---------------------------------------------------------------------

**DELETE:**

*description: elimina un registro de un user por id*

request: "api/users/{$id}"

responses:
	status: 200

	status: 400
	body: { 		
		   message: "user with id {$id} does not exists"
	       }


/////////////////////////////////////////////////////////////////////


# Model: Role

## Endpoints:

**GET:**

*description: retorna una lista con todos los roles*

request: "api/users/roles"

response:
	status: 200
	body:[
		  { 
		   id: 1, 
		   name: "super admin"
		  },
		  { 
		   id: 2, 
		   name: "user"
		  },
	    ]

---------------------------------------------------------------------

**POST:**

*description: crea un nuevo registro de un role*

request: "api/users/roles"
		body: {  
		  name: "emperador"
	    }

response:
	status: 200
	body: { 
		   id: 4, 
		   name: "emperador"
		  }
	status: 409
	body: { 
		   message: "role already exists"
		  }

---------------------------------------------------------------------

**POST:**

*description: agrega un role a un usuario dado el username y el nombre del role*

request: "api/users/roles"
		body: {  
		  username: "aldo",
		  roleName: "emperador"
	    }

response:
	status: 200

	status: 400
	body: { 
		   message: "user not found"
		  }

	status: 400
	body: { 
		   message: "role not found"
		  }

	status: 409
	body: { 
		   message: "user already has this role"
		  }

---------------------------------------------------------------------

**DELETE:**

*description: elimina un registro de un role por id*

request: "api/users/roles/{$id}"

responses:
	status: 200

	status: 400
	body: { 		
		   message: "role with id {$id} does not exists"
	       }


*********************************************************************

# Model: Bill

## Endpoints:

**GET:**

*description: retorna una lista con todas las bills disponibles*

request: "api/restaurant/bills"

response:
	status: 200
	body:[
            { 
		   id: 1, 
		   date: 14/11/2002, 
		   ammount: 20.00, 
		   isPaid: true, 
		   table: {
		     id: 3
		  },

		 { 
	 	   id: 1, 
		   date: 14/11/2002, 
		   ammount: 20.00, 
		   isPaid: true, 
		   table: {
		     id: 3
		  } 
	      ]

----------------------------------------------------------------------

**GET:**

*description: retorna una bill por medio de id*

request: "api/restaurant/bills/{$id}"

responses:
	status: 200
	body: { 		
		id: 1, 
		date: 14/11/2002, 
		ammount: 20.00, 
		isPaid: true, 
		table: {
			id: 3
			}
	}

	status: 400
	body: { 		
			message: "bill with id {$id} does not exists"
			}

----------------------------------------------------------------------

**POST**

*description: crea un nuevo registro de una bill*

request: "api/restaurant/bills/"
	   body: { 
		ammount: 20.00, 
		isPaid: true, 
		table: {
		  id: 3
	     	}
	   }

response:
	status: 200
	body: { 		
		   id: 1, 
		   date: 14/11/2002, 
		   ammount: 20.00, 
		   isPaid: true, 
		   table: {
		     id: 3
	     	   }
	       }

---------------------------------------------------------------------

**PUT**

*description: acutaliza un registro de una bill por id*

request: "api/restaurant/bills/{$id}"
	   body: { 
		ammount: 20.00, 
		isPaid: true, 
		table: {
		  id: 4
	     	}
	   }
	   notes: * unicos campos a actualizar son los de ejemplo
		     * puedes usar actualización parcial por medio del body

responses:
	status: 200
	body: { 		
		   id: 1, 
		   date: 14/11/2002, 
		   ammount: 20.00, 
		   isPaid: true, 
		   table: {
		     id: 3
	     	   }
	       }

	status: 400
	body: { 		
		   message: "bill with id {$id} does not exists"
	       }

---------------------------------------------------------------------

**DELETE:**

*description: elimina un registro de una bill por id*

request: "api/restaurant/bills/{$id}"

responses:
	status: 200

	status: 400
	body: { 		
		   message: "bill with id {$id} does not exists"
	       }


/////////////////////////////////////////////////////////////////////


# Model: Order

## Endpoints:

**GET:**

*description: retorna una lista con todas las orders disponibles*

request: "api/restaurant/orders"

response:
	status: 200
	body:[
         { 
		   id: 1, 
		   placeDate: 14/11/2002, 
		   ammount: 20.00, 
		   chef: {
			id: 2
		   }, 
		   status: {
		     id: 3
		   },
		   bill: {
			id: 3
		   }
		  }

         { 
		   id: 2, 
		   placeDate: 14/11/2002, 
		   ammount: 60.00, 
		   chef: {
			id: 2
		   }, 
		   status: {
		     id: 3
		   },
		   bill: {
			id: 3
		   }
		 }
	    ]

----------------------------------------------------------------------

**GET:**

*description: retorna una order por medio de id*

request: "api/restaurant/orders/{$id}"

responses:
	status: 200
	body: { 
		   id: 1, 
		   placeDate: 14/11/2002, 
		   ammount: 20.00, 
		   chef: {
			id: 2
		   }, 
		   status: {
		     id: 3
		   },
		   bill: {
			id: 3
		   }
		  }

	status: 400
	body: { 		
		   message: "order with id {$id} does not exists"
	       }

----------------------------------------------------------------------

**POST**

*description: crea un nuevo registro de una order*

request: "api/restaurant/orders/"
	   body: { 
		   ammount: 20.00, 
		   chef: {
			id: 2
		   }, 
		   status: {
		     id: 3
		   },
		   bill: {
			id: 3
		   }
		  }
		notes: asegurarse que los id's que se usan para relación sean correctos

response:
	status: 200
	body: { 
		   id: 1, 
		   placeDate: 14/11/2002, 
		   ammount: 20.00, 
		   chef: {
			id: 2
		   }, 
		   status: {
		     id: 3
		   },
		   bill: {
			id: 3
		   }
		  }

----------------------------------------------------------------------

**PUT**

*description: acutaliza un registro de una bill por id*

request: "api/restaurant/orders/{$id}"
	   body: { 
		   ammount: 20.00, 
		   chef: {
			id: 2
		   }, 
		   status: {
		     id: 3
		   },
		   bill: {
			id: 3
		   }
		  }
	   notes: * unicos campos a actualizar son los de ejemplo
		     * puedes usar actualización parcial por medio del body

responses:
	status: 200
	body: { 		
		   id: 1, 
		   date: 14/11/2002, 
		   ammount: 20.00, 
		   isPaid: true, 
		   table: {
		     id: 3
	     	   }
	       }

	status: 400
	body: { 		
		   message: "order with id {$id} does not exists"
	       }

---------------------------------------------------------------------

**DELETE:**

*description: elimina un registro de una order por id*

request: "api/restaurant/orders/{$id}"

responses:
	status: 200

	status: 400
	body: { 		
		   message: "order with id {$id} does not exists"
	       }


//////////////////////////////////////////////////////////////////////


# Model: Plate

## Endpoints:

**GET:**

*description: retorna una lista con todas los plates disponibles*

request: "api/restaurant/plates"

response:
	status: 200
	body:[
         { 
		   id: 1, 
		   name: "spaghetti", 
		   price: 20.00, 
		   imagePath: "https://dummyimage.png", 
		   order: {
		     id: 3
		   },
		   plateCategory: {
			id: 5
		   }
		  }

         { 
		   id: 2, 
		   name: "pizza", 
		   price: 20.00, 
		   imagePath: "https://dummyimage.png", 
		   order: {
		     id: 3
		   },
		   plateCategory: {
			id: 4
		   }
		 }
	    ]

----------------------------------------------------------------------

**GET:**

*description: retorna un plate por medio de id*

request: "api/restaurant/plates/{$id}"

responses:
	status: 200
	body:{ 		   
		   id: 1, 
		   name: "spaghetti", 
		   price: 20.00, 
		   imagePath: "https://dummyimage.png", 
		   order: {
		     id: 3
		   },
		   plateCategory: {
			id: 5
		   }
		}

	status: 400
	body: { 		
		   message: "plate with id {$id} does not exists"
	       }

----------------------------------------------------------------------

**POST**

*description: crea un nuevo registro de un plate*

request: "api/restaurant/plates/"
	   body: { 
		   name: "lassagna", 
		   price: 20.00, 
		   imagePath: "https://dummyimage.png", 
		   order: {
		     id: 3
		   },
		   plateCategory: {
			id: 5
		   }
		  }
		notes: asegurarse que los id's que se usan para relación sean correctos

response:
	status: 200
	body: { 
		   id: 1, 
		   name: "lassagna", 
		   price: 20.00, 
		   imagePath: "https://dummyimage.png", 
		   order: {
		     id: 3
		   },
		   plateCategory: {
			id: 5
		   }
		  }

----------------------------------------------------------------------

**PUT**

*description: acutaliza un registro de un plate por id*

request: "api/restaurant/plates/{$id}"
	   body: { 
		   name: "spaghetti", 
		   price: 20.00, 
		   imagePath: "https://dummyimage.png", 
		   order: {
		     id: 3
		   },
		   plateCategory: {
			id: 5
		   }
		  }
	   notes: * unicos campos a actualizar son los de ejemplo
		     * puedes usar actualización parcial por medio del body

responses:
	status: 200
	body: { 		
		   id: 1, 
		   name: "spaghetti", 
		   price: 20.00, 
		   imagePath: "https://dummyimage.png", 
		   order: {
		     id: 3
		   },
		   plateCategory: {
			id: 5
		   }
		  }

	status: 400
	body: { 		
		   message: "plate with id {$id} does not exists"
	       }

---------------------------------------------------------------------

**DELETE:**

*description: elimina un registro de un plate por id*

request: "api/restaurant/plates/{$id}"

responses:
	status: 200

	status: 400
	body: { 		
		   message: "plate with id {$id} does not exists"
	       }


//////////////////////////////////////////////////////////////////////


# Model: PlateCategory

## Endpoints:

**GET:**

*description: retorna una lista con todas los plateCategories disponibles*

request: "api/restaurant/plateCategories"

response:
	status: 200
	body:[
         { 
		   id: 1, 
		   name: "pastas" 
		  }

         { 
		   id: 2, 
		   name: "soups", 
		 }
	    ]

----------------------------------------------------------------------

**GET:**

*description: retorna un plateCategory por medio de id*

request: "api/restaurant/plateCategories/{$id}"

responses:
	status: 200
	body:{ 		   
		   id: 1, 
		   name: "pastas" 
		}

	status: 400
	body: { 		
		   message: "plate category with id {$id} does not exists"
	       }

----------------------------------------------------------------------

**POST**

*description: crea un nuevo registro de un plateCategory*

request: "api/restaurant/plateCategories/"
	   body: { 
		   name: "pastas" 
		  }

response:
	status: 200
	body: { 
		   id: 1, 
		   name: "pastas" 
		  }

----------------------------------------------------------------------

**PUT**

*description: acutaliza un registro de un plateCategory por id*

request: "api/restaurant/plateCategories/{$id}"
	   body: { 
		   name: "Salads" 
		  }
	   notes: * unicos campos a actualizar son los de ejemplo
		     * puedes usar actualización parcial por medio del body

responses:
	status: 200
	body: { 		
		   id: 1, 
		   name: "salads" 
		  }

	status: 400
	body: { 		
		   message: "plate category with id {$id} does not exists"
	       }

---------------------------------------------------------------------

**DELETE:**

*description: elimina un registro de un plate category por id*

request: "api/restaurant/plateCategories/{$id}"

responses:
	status: 200

	status: 400
	body: { 		
		   message: "plate category with id {$id} does not exists"
	       }


//////////////////////////////////////////////////////////////////////


# Model: UserTable

## Endpoints:

**GET:**

*description: retorna una lista con todas las tables disponibles*

request: "api/restaurant/tables"

response:
	status: 200
	body:[
         { 
		   id: 1, 
		   number: 1,
		   token: "token"
		   tableSection: {
			id: 3
		   }
		  }

         { 
		   id: 2, 
		   number: 2,
		   token: "token"
		   tableSection: {
			id: 1
		   }
		 }
	    ]

----------------------------------------------------------------------

**GET:**

*description: retorna una table por medio de id*

request: "api/restaurant/tables/{$id}"

responses:
	status: 200
	body:{ 		   
		   id: 1, 
		   number: 1,
		   token: "token"
		   tableSection: {
			id: 3
		   }
		}

	status: 400
	body: { 		
		   message: "table with id {$id} does not exists"
	       }

----------------------------------------------------------------------

**POST**

*description: crea un nuevo registro de una table*

request: "api/restaurant/tables/"
	   body: { 
		   number: 1,
		   token: "token"
		   tableSection: {
			id: 3
		   }
		  }
		notes: asegurarse que los id's que se usan para relación sean correctos

response:
	status: 200
	body: { 
		   id: 1, 
		   name: "pastas" 
		  }

----------------------------------------------------------------------

**PUT**

*description: acutaliza un registro de una table por id*

request: "api/restaurant/tables/{$id}"
	   body: { 
		   numer: 10,
		   token: "newToken",
		   tableSection:{
			id: 1
		   }
		  }
	   notes: * unicos campos a actualizar son los de ejemplo
		     * puedes usar actualización parcial por medio del body

responses:
	status: 200
	body: { 
		   id: 1
		   numer: 10,
		   token: "newToken",
		   tableSection:{
			id: 1
		   }
		  }

	status: 400
	body: { 		
		    message: "table with id {$id} does not exists"
	       }

---------------------------------------------------------------------

**DELETE:**

*description: elimina un registro de una table por id*

request: "api/restaurant/tables/{$id}"

responses:
	status: 200

	status: 400
	body: { 		
		   message: "table with id {$id} does not exists"
	       }


//////////////////////////////////////////////////////////////////////


# Model: TableSection

## Endpoints:

**GET:**

*description: retorna una lista con todas las tableSections disponibles*

request: "api/restaurant/tableSections"

response:
	status: 200
	body:[
         { 
		   id: 1, 
		   name: "Sección whitexicans"
		  }

         { 
		   id: 2, 
		   name: "Sección para prietos"
		 }
	    ]

----------------------------------------------------------------------

**GET:**

*description: retorna una TableSection por medio de id*

request: "api/restaurant/tableSections/{$id}"

responses:
	status: 200
	body:{ 		   
		   id: 1, 
		   name: "Sección whitexicans"
		}

	status: 400
	body: { 		
		   message: "table section with id {$id} does not exists"
	       }

----------------------------------------------------------------------

**POST**

*description: crea un nuevo registro de una TableSection*

request: "api/restaurant/tableSections/"
	   body: { 
		   name: "Sección whitexicans"
		  }

response:
	status: 200
	body: { 
		   id: 1, 
		   name: "Sección whitexicans"
		  }

----------------------------------------------------------------------

**PUT**

*description: acutaliza un registro de una table por id*

request: "api/restaurant/tableSections/{$id}"
	   body: { 
		   name: "Sección clase media"
		  }
	   notes: * unicos campos a actualizar son los de ejemplo
		     * puedes usar actualización parcial por medio del body

responses:
	status: 200
	body: { 
		   id: 1, 
		   name: "Sección clase media"
		  }

	status: 400
	body: { 		
		    message: "table section with id {$id} does not exists"
	       }

---------------------------------------------------------------------

**DELETE:**

*description: elimina un registro de una table por id*

request: "api/restaurant/tableSections/{$id}"

responses:
	status: 200

	status: 400
	body: { 		
		   message: "table with id {$id} does not exists"
	       }


//////////////////////////////////////////////////////////////////////


# Model: Status

## Endpoints:

**GET:**

*description: retorna una lista con todas los status*

request: "api/restaurant/status"

response:
	status: 200
	body:[
         { 
		   id: 1, 
		   name: "processing"
		  }

         { 
		   id: 2, 
		   name: "done"
		 }
	    ]

----------------------------------------------------------------------

**GET:**

*description: retorna un status por medio de id*

request: "api/restaurant/status/{$id}"

responses:
	status: 200
	body:{ 		   
		   id: 1, 
		   name: "processing"
		}

	status: 400
	body: { 		
		   message: "status with id {$id} does not exists"
	       }

----------------------------------------------------------------------

**POST**

*description: crea un nuevo registro de un status*

request: "api/restaurant/status/"
	   body: { 
		   id: 1, 
		   name: "processing"
		  }

response:
	status: 200
	body: { 
		   id: 1, 
		   name: "processing"
		  }

----------------------------------------------------------------------

**PUT**

*description: acutaliza un registro de un status por id*

request: "api/restaurant/status/{$id}"
	   body: { 
		   name: "cooking"
		  }
	   notes: * unicos campos a actualizar son los de ejemplo
		     * puedes usar actualización parcial por medio del body

responses:
	status: 200
	body: { 
		   id: 1, 
		   name: "cooking"
		  }

	status: 400
	body: { 		
		    message: "status section with id {$id} does not exists"
	       }

---------------------------------------------------------------------

**DELETE:**

*description: elimina un registro de un status por id*

request: "api/restaurant/status/{$id}"

responses:
	status: 200

	status: 400
	body: { 		
		   message: "status with id {$id} does not exists"
	       }