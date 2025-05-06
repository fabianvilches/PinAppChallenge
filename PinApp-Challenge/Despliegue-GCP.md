METODO RAPIDO Y SENCILLO DE DESPLIEGUE EN GCP:
==============================================

1) gcloud run deploy PinAppChallenge \
    --image gcr.io/PinApp-Challenge/PinAppChallenge \
    --platform managed \
    --region us-central1 \
    --allow-unauthenticated

Una vez desplegado el microservicio, se prueba y GCP genera una URL pública:

https://PinApp-Challenge-xyz.run.app
