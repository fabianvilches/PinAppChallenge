1) Crear un Dockerfile para empaquetar la aplicación (ver en archivo)
2) Compilar y construir la imagen Docker:
	mvn clean package
	docker build -t PinApp-Challenge .

3) Probar la imagen localmente
	docker run -p 8080:8080 PinApp-Challenge

