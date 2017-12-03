**WAITER**

Se accede al servidor para recoger el JSON que contiene al información sobre las mesas que conforman el restaurante y los items que ofrece el restaurante.

Se crea la actividad **Tables** en la que se muestran todas las mesas del restaurante. Cuando se pulsa sobre una de ellas se visualizan los **Items** de esta (inicialmente no hay ninguno). Un botón nos permite ver los distintos items que podemos escoger para la mesa que se ha seleccionado. En esta vista, para cada uno de los ítems se muestra una pequeña imagen, el nombre, el precio e indicadores de alérgenos si los tuviese.

Pulsando sobre uno de los Items vamos al detalle donde encontramos una descripción adicional y la posibilidad de incluir anotaciones. Finalmente se puede cancelar la petición del Item o aceptar. En el caso de que se acepte se incluye como Item vinculado a la Mesa.

Desde la actividad de la Mesa podemos, mediante un menú, obtener el total de los Items asociados a la mesa.

El detalle del Item tiene un layout para vertical y otro para horizontal.

Se ha tenido en cuenta el uso de una animación mientras se descarga en background el JSON.