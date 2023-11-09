rootProject.name = "pubsubdoc"

include(
    "appkit:application-basic",
    "axon:application",
    "doc-service:doc-api",
    "doc-service:doc-shared",
    "doc-service:doc-web",

    "pubsubdoc-service:back",
    "pubsubdoc-service:back-api",

    "payment-service:payment-api",
    "payment-service:payment-shared",
    "payment-service:payment-web",

    "user-service:user-api",
    "user-service:user-sdk",
    "user-service:user-shared",
    "user-service:user-web",
)