# employee-service-kotlin

A sample service to try with spring boot kotlin

- kotlin
- http2
- java 11
- validation
- jasypt
- message source

## Try http2

- Test a GET http2 call through curl
    ```shell
  curl -v --http2 -H "Authorization: Basic YWxsZW46cGFzc3dvcmQ=" http://localhost:8080/departments/1
    ```

### Postgres

- helm install postgres-release bitnami/postgresql -n postgres
- To connect to your database from outside the cluster execute the following commands:
    - export POSTGRES_PASSWORD=$(kubectl get secret --namespace postgres postgres-release-postgresql -o jsonpath="{.data.postgresql-password}" | base64 --decode)
    - echo $POSTGRES_PASSWORD
    - kubectl run postgresql-client --rm --tty -i --restart='Never' --namespace postgres  --image bitnami/postgresql:latest --env="PGPASSWORD=$POSTGRES_PASSWORD" --command -- psql --host postgres-release-postgresql -U postgres -d postgres -p 5432