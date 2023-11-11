# PubSubDoc

## 起動方法

1. docker で kafka などを立ち上げます
2. .run の run configuration のうち、kafka-single を使うと kafka を利用する設定でサービス群が立ち上がります。
3. localhost:8080, localhost:8180, localhost:8280, localhost:8380 にアクセスするとそれぞれのサービスの Swagger を開けます。

## Run on docker

```shell
docker compose up
```

### Set Environment Variables.

```
SPRING_PROFILES_ACTIVE=kafka-single
```

