# Proyecto1Infra2

Este proyeto representa el codigo que desarrollamos para dar solucion al proyecto 1 del espacio academico Infraestructur de Redes.

## Los integrantes del grupo son:
Santiago Mejia- Jacobo Sanchez-Sebastian Quintero Osorio

## Infraestructura	de	Comunicaciones	2021-2
## Trabajo	 #1:	 Aplicación	 Cliente-Servidor	 para	 la	 simulación	de	las	operaciones	de	una	casa	de	apuestas

### 1. Trabajo	a	realizar

El	trabajo	consiste	en	el	desarrollo	de	una	aplicación	que	simule	el	comportamiento	de	algunas	de	
las	operaciones	en	una	casa	de	apuestas	tipo	chance	con	números	de	cuatro	cifras	entre	0000 y	
9999.	Es	una	aplicación	cliente	– servidor	en	la	cual	los	usuarios	utilizan	el	programa	cliente	para	
interactuar	con	la	casa	de	apuestas,	y	la	casa	de	apuestas	usa	el	programa	servidor.	El	programa	
cliente	se	comunica	con	el	programa	servidor	a	través	de	mensajes	enviados	por	la	red.

## Servicios	para	los	usuarios

1. Crear	 cuenta. El	 sistema	 permite	 dos	 clases	 de	 usuarios:	 la	 Casa y	 los	 usuarios	
generales.	La	cuenta	de	la	Casa tiene	el	número	de	cuenta	0 y	saldo	0.	Esta	cuenta	es	
creada	al	iniciar	la	ejecución	del	sistema.	Una	cuenta	de	un	usuario	general,	es	usada	
para	descontar	las	apuestas	realizadas	y	depositar	los	premios	obtenidos.	La	cuenta	se	
abre	con	los	siguientes	datos:	(1)	Número	de	la	cuenta:		un	valor entero	positivo	que	
inicia	en	1 (asignado	por	el	servidor),	(2)	Nombre	del	usuario,	(3)	Saldo.	Una	cuenta	se	
crea	con	saldo	0.	El	programa	cliente	enviará	un	mensaje	con	el	siguiente	contenido:	
CREAR_CUENTA,nombre apellido.	 Por	 restricción,	 no	 se	 permitirán	 nombres	
repetidos.	 El	 servidor	 debe	 dar	 una	 respuesta	 de	 transacción	 exitosa	 o	 error,	
especificando	la	razón	del	error. (8%)

2. Depositar	 dinero	 en	 una	 cuenta. El	 programa	 cliente	 enviará	 un	 mensaje	 con	 el	
siguiente	contenido:	DEPOSITAR,cuenta,valor.	Por	ejemplo:	DEPOSITAR,3,40000.	
El	servidor	deberá	calcular	el	nuevo	saldo	de	la	cuenta.	Se	debe	validar	que	el	valor	a	
depositar	no	sea	negativo.	El	servidor	debe	dar	una	respuesta	de	transacción	exitosa	o	
error,	especificando	la	razón	del	error. (8%)

3. Retirar	dinero	de	una	cuenta. El	programa	cliente	enviará	un	mensaje	con	el	siguiente	
contenido:	 RETIRAR,cuenta,valor.	 Por	 ejemplo:	 RETIRAR,3,20000.	 El	 servidor	
deberá	 calcular	 el	 nuevo	 saldo	 de	 la	 cuenta.	 Es	 necesario	 validar	 que	 el	 saldo	 sea	
suficiente	para	el	retiro	solicitado	y	que	no	sea	un	valor	negativo.	El	servidor	debe	dar	
una	respuesta	de	transacción	exitosa	o	error,	especificando	la	razón	del	error. (8%)

4. Apostar. Una	apuesta	tiene	los	siguientes	tres	datos:	(1)	Número	de	cuenta	del	usuario,	
(2)	tipo	de	apuesta	y	(3)	el	número	al	cual	le	va	a	apostar.	El	tipo	de	apuesta	puede	ser:	
Tipo	A,	las	4	cifras	en	orden	estricto;	Tipo	B,	las	3	últimas	cifras	en	orden	estricto;	y	Tipo	
C:	las	2	últimas	cifras	en	orden	estricto.	El	valor	de	cada	apuesta	es	10000 pesos. Para	
registrar	una	apuesta,	la	 cuenta	 del	 usuario	 debe	existir,	el	 tipo	 de	apuesta	 debe	 ser	
correcto,	el	número	al	cual	se	le	va	a	apostar	debe	coincidir	en	el	número	de	cifras	del	
tipo	de	apuesta	y	el	saldo	de	la	cuenta	debe	ser	suficiente	para	poder	descontar	valor	de	
la	 apuesta.	 El	 programa	 cliente	 enviará	 un	 mensaje	 con	 el	 siguiente	 contenido:	
APOSTAR,cuenta,tipo,numero,	 por	 ejemplo:	 APOSTAR,3,B,523.	 El	 servidor	 debe	
dar	una	respuesta	de	transacción	exitosa	o	error,	especificando	la	razón	del	error.	Si la	
apuesta	 fue	 registrada	 exitosamente,	 el	 saldo	 de	 la	 cuenta	 del	 usuario	 debe	 ser	
actualizado,	lo	mismo	que	el	saldo	de	la	Casa.	Cada	apuesta	debe	quedar	registrada	en	
una	estructura	de	datos. (15%)

5. Cancelar	 cuenta. Al	 cancelar	 una	 cuenta,	 esta	 cuenta	 se	 elimina	 de	la	 estructura	 de	
datos.	Solo	se	puede	cancelar	una	cuenta	cuyo	su	saldo	sea	0 por	lo	que	se	debe	validar	
el	saldo	antes	de	cancelar	la	cuenta.	El	usuario	podría	crear	una	nueva	cuenta	de	ahorros	
si	 lo	 desea.	 El	 programa	 cliente	 enviará	 un	 mensaje	 con	 el	 siguiente	 contenido:	
CANCELAR,cuenta.	Por	ejemplo,	CANCELAR,2.	El	servidor	debe	dar	una	respuesta	de	
transacción	exitosa	o	error,	especificando	la	razón	del	error. (8%)

6. Consultar	saldo. El	programa	cliente	enviará	un	mensaje	con	el	siguiente	contenido:	
CONSULTAR_SALDO,cuenta.	Por	ejemplo:	CONSULTAR_SALDO,4.	El	servidor	debe	dar	
una	respuesta	de	transacción	exitosa	o	error,	especificando	la	razón	del	error. (8%)

## Servicios	para	la	Casa	de Apuestas

7. Cerrar	la	recepción	de	apuestas. La	casa envía	el	mensaje	CERRAR al	servidor.	Si	no	
se	han	registrado	apuestas,	el	servidor	le	pide	confirmación	al	cliente.	El	cliente	deberá	
informar	SI para	cerrar	las	apuestas	a	pesar	de	no	tener	apuestas	registradas,	o	NO si	
desea	 mantener	 la	 posibilidad	 de	 recibir	 apuestas	 de	 los	 usuarios.	 Si	 se	 cierran	 las	
apuestas,	el	servidor	no	permitirá	nuevas	apuestas.	Si	el	sistema	ya	estaba	cerrado,	el	
servidor	debe	informarlo	en	la	respuesta	al	cliente	que	las	apuestas	estaban	cerradas.	
(15%)

8. Reporte consolidado	de	apuestas. La	casa	envía	el	mensaje	REPORTAR al	servidor.	El	
servidor	deberá	informar	las	apuestas	registradas,	indicando	el	nombre	del	usuario,	el
tipo	de	apuesta	y	el	número	en	juego.	Además	la	respuesta	debe	incluir	el	valor	total	
recaudado	por	cada	tipo	de	apuesta. (15%)

9. Realizar	 sorteo.	 El	 programa	 servidor	 genera	 un	 número	 de	 cuatro	 cifras.	 En	 un	
contexto	real,	este	número	debe	ser	generado	de	manera	aleatoria.	En	este	caso,	por	
tratarse	de	un	ejercicio	académico,	el	número	ganador	será	generado	en	forma	manual.	
La	 casa	 envía	 el	 mensaje	 SORTEO,número,	 donde	 número	 es	 el	 número	ganador	 del	
sorteo.	 Como	 consecuencia	 del	 sorteo:	 el	80% del	 valor	 recaudado	 por	 concepto	 de	
apuestas	tipo A,	el	70% del	valor	recaudado	por	concepto	de	apuestas	tipo B y	el	
60% del	 valor	 recaudado	 por	 concepto	 de apuestas	 tipo C,	 será	 depositado	 en	 la	
cuenta	del	ganador	de	la	apuesta.	En	caso	de	que	haya	más	de	un	jugador	con	la	misma	
apuesta,	el	valor	a	pagar	será	dividido	en	partes	iguales	entre	los	ganadores. (15%)


